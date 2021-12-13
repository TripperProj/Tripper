package com.tripper.service.boardservice;

import com.tripper.domain.board.FindMateBoard;
import com.tripper.domain.user.User;
import com.tripper.dto.request.board.CreateFindMateBoardDto;
import com.tripper.dto.request.board.UpdateFindMateBoardDto;
import com.tripper.dto.response.board.GetFindMateBoardDto;
import com.tripper.dto.response.board.GetFindMateBoardListDto;
import com.tripper.repository.FindMateBoardRepository;
import com.tripper.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
@Transactional
public class FindMateBoardService {

    private final FindMateBoardRepository boardRepository;
    private final UserRepository userRepository;

    /**
     * 글 등록
     */
    @Transactional(readOnly = true)
    public Long registerPost(CreateFindMateBoardDto dto, String memId) {

        /* 엔티티 조회 */
        User user = userRepository.findByMemId(memId);

        /* 글 생성*/
        FindMateBoard board = new FindMateBoard(dto.getTitle(), dto.getContent(), user,
                dto.getDestination(), dto.getStartDate(), dto.getEndDate(), dto.getRecruitment());

        /* 글 저장 */
        boardRepository.save(board);

        return board.getId();
    }

    /**
     * 게시판 글 전체 조회하는 함수
     */
    @Transactional(readOnly = true)
    public GetFindMateBoardListDto findAllBoards() {

        List<FindMateBoard> boards = boardRepository.findAll();
        List<GetFindMateBoardDto> getBoardDtoList = new ArrayList<>();

        for (FindMateBoard board : boards) {
            GetFindMateBoardDto getBoardDto = new GetFindMateBoardDto(board);
            getBoardDtoList.add(getBoardDto);
        }

        return new GetFindMateBoardListDto(getBoardDtoList);
    }

    /**
     * board_id로 게시글 조회하는 함수
     */
    @Transactional
    public GetFindMateBoardDto findByBoardId(Long boardId) {

        /* 엔티티 조회 */
        FindMateBoard board = boardRepository.findById(boardId)
                .orElseThrow(() -> new IllegalArgumentException("존재하지 않는 게시글 입니다."));

        /* 조회수 증가 */
        board.addHits();

        return new GetFindMateBoardDto(board);
    }

    /**
     * 좋아요수 증가 함수
     */
    public void addLikes(Long boardId) {

        /* 엔티티 조회 */
        FindMateBoard board = boardRepository.findById(boardId)
                .orElseThrow(() -> new IllegalArgumentException("존재하지 않는 게시글 입니다."));

        /* 좋아요수 증가 */
        board.addLikes();
    }

    /**
     * 좋아요수 감소 함수
     */
    public void subtractLikes(Long boardId) {

        /* 엔티티 조회 */
        FindMateBoard board = boardRepository.findById(boardId)
                .orElseThrow(() -> new IllegalArgumentException("존재하지 않는 게시글 입니다."));

        /* 좋아요수 감소 */
        board.subtractLikes();
    }

    /**
     * 게시글 수정 함수
     */
    public void updatePost(Long boardId, UpdateFindMateBoardDto dto) {

        /* 엔티티 조회 */
        FindMateBoard board = boardRepository.findById(boardId)
                .orElseThrow(() -> new IllegalArgumentException("존재하지 않는 게시글 입니다."));

        /* 수정 내역으로 업데이트 */
        board.updateBoard(dto.getTitle(), dto.getContent(),
                dto.getDestination(), dto.getRecruitment(), dto.getStartDate(), dto.getEndDate());
    }

    /**
     * 게시글 삭제 함수
     */
    public void deletePostById(Long boardId) {
        boardRepository.deleteById(boardId);
    }

    /**
     * 게시글 모집 마감
     */
    public void closeRecruitment(Long boardId) {
        /* 엔티티 조회 */
        FindMateBoard board = boardRepository.findById(boardId)
                .orElseThrow(() -> new IllegalArgumentException("존재하지 않는 게시글 입니다."));

        board.closeRecruitment();
    }
}
