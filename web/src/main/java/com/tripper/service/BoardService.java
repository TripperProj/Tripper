package com.tripper.service;

import com.tripper.dto.request.CreateBoardDto;
import com.tripper.domain.board.Board;
import com.tripper.domain.user.User;
import com.tripper.repository.BoardRepository;
import com.tripper.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

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
    public List<Board> findAllBoards() {
        return boardRepository.findAll();
    }

    /**
     * board_id로 게시글 조회하는 함수
     * @return
     */
    @Transactional
    public Board findByBoardId(Long boardId) {

        /* 조회수 증가 */
        addHits(boardId);

        /* 엔티티 조회 */
        Board board = boardRepository.findByBoardId(boardId);
        return board;

    }

    /**
     * 조회수 증가 함수
     */
    @Transactional
    public void addHits(Long boardId) {

        /* 엔티티 조회 */
        Board board = boardRepository.findByBoardId(boardId);

        /* 조회수 증가 */
        int curHits = board.getHits();
        board.setHits(curHits + 1);
        boardRepository.save(board);

    }

    /**
     * 좋아요수 증가 함수
     */
    @Transactional
    public void addLikes(Long boardId) {

        /* 엔티티 조회 */
        Board board = boardRepository.findByBoardId(boardId);

        /* 좋아요수 증가 */
        int curLikes = board.getLikes();
        board.setLikes(curLikes + 1);
        boardRepository.save(board);

    }

    /**
     * 좋아요수 감소 함수
     */
    @Transactional
    public void subtractLikes(Long boardId) {

        /* 엔티티 조회 */
        Board board = boardRepository.findByBoardId(boardId);

        /* 좋아요수 감소 */
        int curLikes = board.getLikes();
        board.setLikes(curLikes - 1);
        boardRepository.save(board);

    }

    @Transactional
    public void updatePost(Long boardId, String title, String destination, int recruitment, String content) {

        /* 엔티티 조회 */
        Board board = boardRepository.findByBoardId(boardId);

        /* 수정 내역으로 업데이트 */
        board.setTitle(title);
        board.setDestination(destination);
        board.setRecruitment(recruitment);
        board.setContent(content);
        boardRepository.save(board);

    }

    @Transactional
    public void deletePostById(Long boardId) {
        boardRepository.deleteById(boardId);
    }
}
