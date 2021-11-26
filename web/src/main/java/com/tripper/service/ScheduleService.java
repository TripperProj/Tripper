package com.tripper.service;

import com.tripper.domain.schedule.Schedule;
import com.tripper.domain.trip.Trip;
import com.tripper.dto.request.CreateScheduleDto;
import com.tripper.dto.request.UpdateScheduleDto;
import com.tripper.dto.response.GetScheduleDto;
import com.tripper.dto.response.GetScheduleListDto;
import com.tripper.repository.ScheduleRepository;
import com.tripper.repository.TripRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
@Transactional
public class ScheduleService {

    private final ScheduleRepository scheduleRepository;
    private final TripRepository tripRepository;

    public void createSchedule(CreateScheduleDto dto, Long tripId){
        Trip trip = tripRepository.findById(tripId)
                .orElseThrow(() -> new IllegalAccessError("존재하지 않는 여행 입니다."));

        Schedule schedule = new Schedule(dto.getName(), dto.getContent(), dto.getStartTime(), dto.getEndTime(), trip);
        scheduleRepository.save(schedule);
    }

    public void updateSchedule(UpdateScheduleDto dto, Long scheduleId){
        Schedule schedule = scheduleRepository.findById(scheduleId)
                .orElseThrow(() -> new IllegalArgumentException("존재하지 않는 일정 입니다."));

        schedule.updateSchedule(dto.getName(), dto.getContent(), dto.getStartTime(), dto.getEndTime());
    }

    public GetScheduleListDto getSchedules(Long tripId){
        List<Schedule> schedules = scheduleRepository.findByTrip_Id(tripId);

        List<GetScheduleDto> getScheduleDtoList = new ArrayList<>();
        for (Schedule schedule : schedules) {
            GetScheduleDto getScheduleDto = new GetScheduleDto(schedule.getId(), schedule.getName(), schedule.getContent(), schedule.getStartTime(), schedule.getEndTime());
            getScheduleDtoList.add(getScheduleDto);
        }

        return new GetScheduleListDto(getScheduleDtoList);
    }

    public void deleteSchedule(Long scheduleId){
        scheduleRepository.deleteById(scheduleId);
    }
}
