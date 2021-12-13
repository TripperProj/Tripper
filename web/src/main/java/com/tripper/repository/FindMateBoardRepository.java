package com.tripper.repository;

import com.tripper.domain.board.FindMateBoard;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * 게시판 관련 레포지토리
 */
public interface FindMateBoardRepository extends JpaRepository<FindMateBoard, Long> {
}
