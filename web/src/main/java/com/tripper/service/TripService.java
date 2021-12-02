package com.tripper.service;

import com.tripper.domain.trip.Schedule;
import com.tripper.domain.trip.Trip;
import com.tripper.domain.user.UserInfo;
import com.tripper.dto.request.CreateScheduleDto;
import com.tripper.dto.request.CreateTripDto;
import com.tripper.dto.request.UpdateScheduleDto;
import com.tripper.dto.request.UpdateTripDto;
import com.tripper.dto.response.GetScheduleDto;
import com.tripper.dto.response.GetScheduleListDto;
import com.tripper.dto.response.GetTripDto;
import com.tripper.dto.response.GetTripListDto;
import com.tripper.repository.MemberRepository;
import com.tripper.repository.TripRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
@Transactional
public class TripService {

    private final TripRepository tripRepository;
    private final MemberRepository memberRepository;

    public void createTrip(CreateTripDto dto, String memId){
        UserInfo userInfo = memberRepository.findUserByMemId(memId);
        Trip tripInfo = new Trip(dto.getDestination(),
                dto.getStartDate(), dto.getEndDate(), userInfo);

        tripRepository.save(tripInfo);
    }

    public void updateTrip(UpdateTripDto dto, Long tripId){
        Trip trip = tripRepository.findById(tripId)
                .orElseThrow(() -> new IllegalArgumentException("존재하지 않는 여행입니다."));

        trip.updateTrip(dto.getDestination(), dto.getStartDate(), dto.getEndDate());
    }

    @Transactional(readOnly = true)
    public GetTripListDto getTrips(String memId) {
        List<Trip> byUserInfo_memId = tripRepository.findByUserInfo_MemId(memId);

        List<GetTripDto> tripInfoDtoList = new ArrayList<>();
        for (Trip tripInfo : byUserInfo_memId) {
            GetTripDto tripInfoDto = new GetTripDto(tripInfo.getId(), tripInfo.getDestination(), tripInfo.getStartDate(), tripInfo.getEndDate());
            tripInfoDtoList.add(tripInfoDto);
        }

        return new GetTripListDto(tripInfoDtoList);
    }

    public void deleteTrip(Long tripId){
        tripRepository.deleteById(tripId);
    }

    public void createScheduleToTrip(CreateScheduleDto dto, Long tripId) {
        Trip trip = tripRepository.findById(tripId)
                .orElseThrow(() -> new IllegalAccessError("존재하지 않는 여행입니다."));

        Schedule schedule = new Schedule(dto.getName(), dto.getContent(), dto.getStartTime(), dto.getEndTime(), trip);
        trip.addSchedule(schedule);
    }

    public void updateSchedule(UpdateScheduleDto dto, Long tripId, Long scheduleId) {
        Trip trip = tripRepository.findById(tripId)
                .orElseThrow(() -> new IllegalAccessError("존재하지 않는 여행입니다."));

        trip.updateSchedule(scheduleId, dto.getName(), dto.getContent(), dto.getStartTime(), dto.getEndTime());
    }

    public GetScheduleListDto getSchedules(Long tripId) {
        Trip trip = tripRepository.findById(tripId)
                .orElseThrow(() -> new IllegalAccessError("존재하지 않는 여행입니다."));

        List<GetScheduleDto> getScheduleDtoList = new ArrayList<>();
        for (Schedule schedule : trip.getSchedules()) {
            GetScheduleDto getScheduleDto = new GetScheduleDto(schedule.getId(), schedule.getName(), schedule.getContent(), schedule.getStartTime(), schedule.getEndTime());
            getScheduleDtoList.add(getScheduleDto);
        }

        return new GetScheduleListDto(getScheduleDtoList);
    }

    public void deleteSchedule(Long tripId, Long scheduleId) {
        Trip trip = tripRepository.findById(tripId)
                .orElseThrow(() -> new IllegalAccessError("존재하지 않는 여행입니다."));

        trip.deleteSchedule(scheduleId);
    }
}
