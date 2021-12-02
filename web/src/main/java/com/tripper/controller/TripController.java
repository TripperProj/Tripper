package com.tripper.controller;

import com.tripper.dto.request.CreateTripDto;
import com.tripper.dto.request.UpdateTripDto;
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
@Api(tags = "여행 API")
@RestController
@RequestMapping("/trips")
@RequiredArgsConstructor
public class TripController {

    private final TripService tripService;

    @ApiOperation(value = "여행 생성", notes = "여행을 생성합니다.", tags = "여행 API")
    @PostMapping
    public ResponseEntity createTrip(@Valid @RequestBody CreateTripDto dto, Principal principal) {

        String memId = principal.getName();
        tripService.createTrip(dto, memId);

        return ResponseEntity.ok().build();
    }

    @ApiOperation(value = "여행 수정", notes = "여행을 수정합니다.", tags = "여행 API")
    @PutMapping("/{tripId}")
    public ResponseEntity updateTrip(@Valid @RequestBody UpdateTripDto dto, @PathVariable("tripId") Long tripId) {
        tripService.updateTrip(dto, tripId);

        return ResponseEntity.ok().build();
    }

    @ApiOperation(value = "여행 조회", notes = "여행을 조회합니다.", tags = "여행 API")
    @GetMapping
    public ResponseEntity<GetTripListDto> getTrips(Principal principal) {

        String memId = principal.getName();
        GetTripListDto trips = tripService.getTrips(memId);

        return ResponseEntity.ok().body(trips);
    }

    @ApiOperation(value = "여행 삭제", notes = "여행을 삭제합니다.", tags = "여행 API")
    @DeleteMapping("/{tripId}")
    public ResponseEntity deleteTrip(@PathVariable("tripId") Long tripId) {

        tripService.deleteTrip(tripId);

        return ResponseEntity.ok().build();
    }
}
