package com.tripper.repository;

import com.tripper.domain.board.Board;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

/**
 * 여행 메이트 찾기 게시판 관련 레포지토리
 */
public interface BoardRepository extends JpaRepository<Board, Long> {

    @Query("SELECT b FROM Board b WHERE b.id = :id")
    Board findByBoardId(@Param("id") Long id);

}
