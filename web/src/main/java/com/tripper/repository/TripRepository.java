package com.tripper.repository;

import com.tripper.domain.trip.Trip;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * 여행 관련 레포지토리
 */
@Repository
public interface TripRepository extends JpaRepository<Trip, Long> {
    List<Trip> findByUserInfo_MemId(String memId);
}
