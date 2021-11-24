package com.tripper.service;

import com.tripper.domain.board.BoardForm;
import com.tripper.domain.board.BoardInfo;
import com.tripper.domain.user.UserInfo;
import com.tripper.domain.user.UserRepository;
import com.tripper.repository.BoardRepository;
import com.tripper.repository.MemberRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
@Transactional(readOnly = true)
@RequiredArgsConstructor
@Slf4j
public class BoardService {

    private final BoardRepository boardRepository;
    private final MemberRepository memberRepository;

    /**
     * 글 등록
     */
    public Long registerPost(BoardForm form, String memId) {

        /* 엔티티 조회 */
        UserInfo user = memberRepository.findUserByMemId(memId);

        /* 글 생성*/
        BoardInfo boardInfo = BoardInfo.createBoard(form, user);

        /* 글 저장 */
        boardRepository.save(boardInfo);

        return boardInfo.getId();

    }

    /**
     * 게시판 글 전체 조회하는 함수
     * @return 조회한 게시판 글 목록
     */
    public List<BoardInfo> findAllBoards() {

        return boardRepository.findAll();

    }

    /**
     * board_id로 게시글 조회하는 함수
     * @return
     */
    @Transactional
    public BoardInfo findByBoardId(Long board_id) {

        /* 조회수 증가 */
        addHits(board_id);

        /* 엔티티 조회 */
        BoardInfo boardInfo = boardRepository.findOne(board_id);

        return boardInfo;

    }

    /**
     * 조회수 증가 함수
     */
    @Transactional
    public void addHits(Long board_id) {

        /* 엔티티 조회 */
        BoardInfo boardInfo = boardRepository.findOne(board_id);

        /* 조회수 증가 */
        int curHits = boardInfo.getHits();
        boardInfo.setHits(curHits + 1);
        boardRepository.save(boardInfo);

    }

    /**
     * 좋아요수 증가 함수
     */
    @Transactional
    public void addLikes(Long board_id) {

        /* 엔티티 조회 */
        BoardInfo boardInfo = boardRepository.findOne(board_id);

        /* 좋아요수 증가 */
        int curLikes = boardInfo.getLikes();
        boardInfo.setLikes(curLikes + 1);
        boardRepository.save(boardInfo);

    }

    /**
     * 좋아요수 감소 함수
     */
    @Transactional
    public void subtractLikes(Long board_id) {

        /* 엔티티 조회 */
        BoardInfo boardInfo = boardRepository.findOne(board_id);

        /* 좋아요수 감소 */
        int curLikes = boardInfo.getLikes();
        boardInfo.setLikes(curLikes - 1);
        boardRepository.save(boardInfo);

    }

    @Transactional
    public void updatePost(Long board_id, String title, String destination, int recruitment, String content) {

        /* 엔티티 조회 */
        BoardInfo boardInfo = boardRepository.findOne(board_id);

        /* 수정 내역으로 업데이트 */
        boardInfo.setTitle(title);
        boardInfo.setDestination(destination);
        boardInfo.setRecruitment(recruitment);
        boardInfo.setContent(content);
        boardRepository.save(boardInfo);

    }

    @Transactional
    public void deletePostById(Long board_id) {
        boardRepository.deleteById(board_id);
    }
}
