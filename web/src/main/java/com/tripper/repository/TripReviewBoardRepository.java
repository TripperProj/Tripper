package com.tripper.repository;

import com.tripper.domain.board.TripReviewBoard;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * 게시판 관련 레포지토리
 */
public interface TripReviewBoardRepository extends JpaRepository<TripReviewBoard, Long> {
}
