package com.tripper.controller;

import com.tripper.dto.request.CreateScheduleDto;
import com.tripper.dto.request.UpdateScheduleDto;
import com.tripper.dto.response.GetScheduleListDto;
import com.tripper.service.ScheduleService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@RequestMapping("/trips/{tripId}/schedules")
@RequiredArgsConstructor
public class ScheduleController {

    private final ScheduleService scheduleService;

    @PostMapping
    public ResponseEntity createSchedule(@Valid @RequestBody CreateScheduleDto dto,
            @PathVariable("tripId") Long tripId) {

        scheduleService.createSchedule(dto, tripId);

        return ResponseEntity.ok().build();
    }

    @PutMapping("/{scheduleId}")
    public ResponseEntity updateSchedule(@Valid @RequestBody UpdateScheduleDto dto, @PathVariable("scheduleId") Long scheduleId) {

        scheduleService.updateSchedule(dto, scheduleId);

        return ResponseEntity.ok().build();
    }


    @GetMapping
    public ResponseEntity<GetScheduleListDto> getSchedules(@PathVariable("tripId") Long tripId) {

        GetScheduleListDto schedules = scheduleService.getSchedules(tripId);

        return ResponseEntity.ok().body(schedules);
    }

    @DeleteMapping("/{scheduleId}")
    public ResponseEntity deleteSchedule(@PathVariable("scheduleId") Long scheduleId) {

        scheduleService.deleteSchedule(scheduleId);

        return ResponseEntity.ok().build();
    }
}