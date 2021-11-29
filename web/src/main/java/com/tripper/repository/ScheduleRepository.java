package com.tripper.repository;

import com.tripper.domain.schedule.Schedule;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

/**
 * 일정 관련 레포지토리
 */
public interface ScheduleRepository extends JpaRepository<Schedule, Long> {
    List<Schedule> findByTrip_Id(Long tripId);
}
