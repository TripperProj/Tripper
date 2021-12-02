package com.tripper.controller;

import com.tripper.dto.request.CreateScheduleDto;
import com.tripper.dto.request.CreateTripDto;
import com.tripper.dto.request.UpdateScheduleDto;
import com.tripper.dto.request.UpdateTripDto;
import com.tripper.dto.response.GetScheduleListDto;
import com.tripper.dto.response.GetTripListDto;
import com.tripper.service.TripService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.security.Principal;

/**
 * 여행 CRUD 관련 Controller
 */
@Api(tags = "여행관리 API")
@RestController
@RequestMapping("/trips")
@RequiredArgsConstructor
public class TripController {

    private final TripService tripService;

    @ApiOperation(value = "여행 생성", notes = "여행을 생성합니다.", tags = "여행관리 API")
    @PostMapping
    public ResponseEntity createTrip(@Valid @RequestBody CreateTripDto dto, Principal principal) {

        String memId = principal.getName();
        tripService.createTrip(dto, memId);

        return ResponseEntity.ok().build();
    }

    @ApiOperation(value = "여행 수정", notes = "여행을 수정합니다.", tags = "여행관리 API")
    @PutMapping("/{tripId}")
    public ResponseEntity updateTrip(@Valid @RequestBody UpdateTripDto dto, @PathVariable("tripId") Long tripId) {
        tripService.updateTrip(dto, tripId);

        return ResponseEntity.ok().build();
    }

    @ApiOperation(value = "여행 조회", notes = "여행을 조회합니다.", tags = "여행관리 API")
    @GetMapping
    public ResponseEntity<GetTripListDto> getTrips(Principal principal) {

        String memId = principal.getName();
        GetTripListDto trips = tripService.getTrips(memId);

        return ResponseEntity.ok().body(trips);
    }

    @ApiOperation(value = "여행 삭제", notes = "여행을 삭제합니다.", tags = "여행관리 API")
    @DeleteMapping("/{tripId}")
    public ResponseEntity deleteTrip(@PathVariable("tripId") Long tripId) {

        tripService.deleteTrip(tripId);

        return ResponseEntity.ok().build();
    }

    @ApiOperation(value = "일정 생성", notes = "일정을 생성합니다.", tags = "여행관리 API")
    @PostMapping("/{tripId}/schedules")
    public ResponseEntity createSchedule(@Valid @RequestBody CreateScheduleDto dto,
                                         @PathVariable("tripId") Long tripId) {

        tripService.createScheduleToTrip(dto, tripId);

        return ResponseEntity.ok().build();
    }

    @ApiOperation(value = "일정 수정", notes = "일정을 수정합니다.", tags = "여행관리 API")
    @PutMapping("/{tripId}/schedules/{scheduleId}")
    public ResponseEntity updateSchedule(@Valid @RequestBody UpdateScheduleDto dto,
                                         @PathVariable("tripId") Long tripId,
                                         @PathVariable("scheduleId") Long scheduleId) {

        tripService.updateSchedule(dto, tripId, scheduleId);

        return ResponseEntity.ok().build();
    }

    @ApiOperation(value = "일정 조회", notes = "일정을 조회합니다.", tags = "여행관리 API")
    @GetMapping("/{tripId}/schedules")
    public ResponseEntity<GetScheduleListDto> getSchedules(@PathVariable("tripId") Long tripId) {

        GetScheduleListDto schedules = tripService.getSchedules(tripId);

        return ResponseEntity.ok().body(schedules);
    }

    @ApiOperation(value = "일정 삭제", notes = "일정을 삭제합니다.", tags = "여행관리 API")
    @DeleteMapping("/{tripId}/schedules/{scheduleId}")
    public ResponseEntity deleteSchedule(@PathVariable("tripId") Long tripId,
                                         @PathVariable("scheduleId") Long scheduleId) {

        tripService.deleteSchedule(tripId, scheduleId);

        return ResponseEntity.ok().build();
    }
}
