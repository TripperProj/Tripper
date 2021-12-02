package com.tripper.controller;

import com.tripper.dto.request.CreateScheduleDto;
import com.tripper.dto.request.UpdateScheduleDto;
import com.tripper.dto.response.GetScheduleListDto;
import com.tripper.service.ScheduleService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@Api(tags = "일정 API")
@RestController
@RequestMapping("/trips/{tripId}/schedules")
@RequiredArgsConstructor
public class ScheduleController {

    private final ScheduleService scheduleService;

    @ApiOperation(value = "일정 생성", notes = "일정을 생성합니다.", tags = "일정 API")
    @PostMapping
    public ResponseEntity createSchedule(@Valid @RequestBody CreateScheduleDto dto,
            @PathVariable("tripId") Long tripId) {

        scheduleService.createSchedule(dto, tripId);

        return ResponseEntity.ok().build();
    }

    @ApiOperation(value = "일정 수정", notes = "일정을 수정합니다.", tags = "일정 API")
    @PutMapping("/{scheduleId}")
    public ResponseEntity updateSchedule(@Valid @RequestBody UpdateScheduleDto dto, @PathVariable("scheduleId") Long scheduleId) {

        scheduleService.updateSchedule(dto, scheduleId);

        return ResponseEntity.ok().build();
    }

    @ApiOperation(value = "일정 조회", notes = "일정을 조회합니다.", tags = "일정 API")
    @GetMapping
    public ResponseEntity<GetScheduleListDto> getSchedules(@PathVariable("tripId") Long tripId) {

        GetScheduleListDto schedules = scheduleService.getSchedules(tripId);

        return ResponseEntity.ok().body(schedules);
    }

    @ApiOperation(value = "일정 삭제", notes = "일정을 삭제합니다.", tags = "일정 API")
    @DeleteMapping("/{scheduleId}")
    public ResponseEntity deleteSchedule(@PathVariable("scheduleId") Long scheduleId) {

        scheduleService.deleteSchedule(scheduleId);

        return ResponseEntity.ok().build();
    }
}