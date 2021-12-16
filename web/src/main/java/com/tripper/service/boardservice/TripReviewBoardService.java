package com.tripper.service.boardservice;

import com.tripper.domain.board.Comment;
import com.tripper.domain.board.TripReviewBoard;
import com.tripper.domain.user.User;
import com.tripper.dto.request.board.CreateCommentDto;
import com.tripper.dto.request.board.CreateTripReviewBoardDto;
import com.tripper.dto.request.board.UpdateTripReviewBoardDto;
import com.tripper.dto.response.board.GetTripReviewBoardDto;
import com.tripper.dto.response.board.GetTripReviewHeaderDto;
import com.tripper.dto.response.board.GetTripReviewHeaderListDto;
import com.tripper.repository.TripReviewBoardRepository;
import com.tripper.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class TripReviewBoardService {

    private final TripReviewBoardRepository boardRepository;
    private final UserRepository userRepository;

    /**
     * 글 등록
     */
    @Transactional(readOnly = true)
    public Long registerPost(CreateTripReviewBoardDto dto, String memId) {

        /* 엔티티 조회 */
        User user = userRepository.findByMemId(memId);

        /* 글 생성*/
        TripReviewBoard board = new TripReviewBoard(dto.getTitle(), dto.getContent(), user);

        /* 글 저장 */
        boardRepository.save(board);

        return board.getId();
    }

    /**
     * 게시판 목록 조회하는 함수
     */
    @Transactional(readOnly = true)
    public GetTripReviewHeaderListDto findAllBoards() {

        List<TripReviewBoard> boards = boardRepository.findAll();
        List<GetTripReviewHeaderDto> getBoardDtoList = new ArrayList<>();

        for (TripReviewBoard board : boards) {
            GetTripReviewHeaderDto getBoardDto = new GetTripReviewHeaderDto(board);
            getBoardDtoList.add(getBoardDto);
        }

        return new GetTripReviewHeaderListDto(getBoardDtoList);
    }

    /**
     * board_id로 게시글 조회하는 함수
     */
    @Transactional
    public GetTripReviewBoardDto findByBoardId(Long boardId) {

        /* 엔티티 조회 */
        TripReviewBoard board = boardRepository.findById(boardId)
                .orElseThrow(() -> new IllegalArgumentException("존재하지 않는 게시글 입니다."));

        /* 조회수 증가 */
        board.addHits();

        return new GetTripReviewBoardDto(board);
    }

    /**
     * 좋아요수 증가 함수
     */
    public void addLikes(Long boardId) {

        /* 엔티티 조회 */
        TripReviewBoard board = boardRepository.findById(boardId)
                .orElseThrow(() -> new IllegalArgumentException("존재하지 않는 게시글 입니다."));

        /* 좋아요수 증가 */
        board.addLikes();
    }

    /**
     * 좋아요수 감소 함수
     */
    public void subtractLikes(Long boardId) {

        /* 엔티티 조회 */
        TripReviewBoard board = boardRepository.findById(boardId)
                .orElseThrow(() -> new IllegalArgumentException("존재하지 않는 게시글 입니다."));

        /* 좋아요수 감소 */
        board.subtractLikes();
    }

    /**
     * 게시글 수정 함수
     */
    public void updatePost(Long boardId, UpdateTripReviewBoardDto dto) {

        /* 엔티티 조회 */
        TripReviewBoard board = boardRepository.findById(boardId)
                .orElseThrow(() -> new IllegalArgumentException("존재하지 않는 게시글 입니다."));

        /* 수정 내역으로 업데이트 */
        board.updateTitleAndContent(dto.getTitle(), dto.getContent());
    }

    /**
     * 게시글 삭제 함수
     */
    public void deletePostById(Long boardId) {
        boardRepository.deleteById(boardId);
    }

    /**
     * 댓글 추가 함수
     */
    public void addComment(CreateCommentDto dto, Long boardId, String memId) {
        /* 엔티티 조회 */
        User user = userRepository.findByMemId(memId);

        TripReviewBoard board = boardRepository.findById(boardId)
                .orElseThrow(() -> new IllegalArgumentException("존재하지 않는 게시글 입니다."));

        Comment comment = new Comment(dto.getContent(), board, user);
        board.addComment(comment);
    }
}
