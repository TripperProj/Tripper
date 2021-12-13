package com.tripper.repository;

import com.tripper.domain.board.Board;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * 여행 메이트 찾기 게시판 관련 레포지토리
 */
public interface BoardRepository extends JpaRepository<Board, Long> {

}
