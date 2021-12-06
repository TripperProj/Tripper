package com.tripper.service;

import com.tripper.dto.request.board.CreateBoardDto;
import com.tripper.domain.board.Board;
import com.tripper.domain.user.User;
import com.tripper.dto.request.board.UpdateBoardDto;
import com.tripper.dto.response.board.GetBoardDto;
import com.tripper.dto.response.board.GetBoardListDto;
import com.tripper.repository.BoardRepository;
import com.tripper.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import java.util.ArrayList;
import java.util.List;

@Service
@Transactional(readOnly = true)
@RequiredArgsConstructor
@Slf4j
public class BoardService {

    private final BoardRepository boardRepository;
    private final UserRepository userRepository;

    /**
     * 글 등록
     */
    public Long registerPost(CreateBoardDto createBoardDto, String memId) {

        /* 엔티티 조회 */
        User user = userRepository.findByMemId(memId);

        /* 글 생성*/
        Board board = Board.createBoard(createBoardDto, user);

        /* 글 저장 */
        boardRepository.save(board);

        return board.getId();

    }

    /**
     * 게시판 글 전체 조회하는 함수
     * @return 조회한 게시판 글 목록
     */
    public GetBoardListDto findAllBoards() {

        List<Board> boards = boardRepository.findAll();
        List<GetBoardDto> getBoardDtoList = new ArrayList<>();

        for (Board board : boards) {
            GetBoardDto getBoardDto = new GetBoardDto(board);
            getBoardDtoList.add(getBoardDto);
        }

        return new GetBoardListDto(getBoardDtoList);

    }

    /**
     * board_id로 게시글 조회하는 함수
     * @return
     */
    @Transactional
    public GetBoardDto findByBoardId(Long board_id) {

        /* 엔티티 조회 */
        Board board = boardRepository.findById(board_id)
                .orElseThrow(() -> new IllegalArgumentException("존재하지 않는 게시글 입니다."));

        /* 조회수 증가 */
        board.addHits(1);

        GetBoardDto getBoardDto = new GetBoardDto(board);

        return getBoardDto;

    }

    /**
     * 좋아요수 증가 함수
     */
    @Transactional
    public void addLikes(Long board_id) {

        /* 엔티티 조회 */
        Board board = boardRepository.findById(board_id)
                .orElseThrow(() -> new IllegalArgumentException("존재하지 않는 게시글 입니다."));

        /* 좋아요수 감소 */
        board.addLikes(1);

    }

    /**
     * 좋아요수 감소 함수
     */
    @Transactional
    public void subtractLikes(Long board_id) {

        /* 엔티티 조회 */
        Board board = boardRepository.findById(board_id)
                .orElseThrow(() -> new IllegalArgumentException("존재하지 않는 게시글 입니다."));

        /* 좋아요수 증가 */
        board.subtractLikes(1);

    }

    /**
     * 게시글 수정 함수
     */
    @Transactional
    public void updatePost(Long board_id, UpdateBoardDto updateBoardDto) {

        /* 엔티티 조회 */
        Board board = boardRepository.findById(board_id)
                .orElseThrow(() -> new IllegalArgumentException("존재하지 않는 게시글 입니다."));

        /* 수정 내역으로 업데이트 */
        board.updateBoard(updateBoardDto);

    }

    /**
     * 게시글 삭제 함수
     */
    @Transactional
    public void deletePostById(Long board_id) {
        boardRepository.deleteById(board_id);
    }

}
