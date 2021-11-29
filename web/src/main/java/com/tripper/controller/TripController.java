package com.tripper.controller;

import com.tripper.dto.request.CreateTripDto;
import com.tripper.dto.request.UpdateTripDto;
import com.tripper.dto.response.GetTripListDto;
import com.tripper.service.TripService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.security.Principal;

/**
 * 여행 CRUD 관련 Controller
 */
@RestController
@RequestMapping("/trips")
@RequiredArgsConstructor
public class TripController {

    private final TripService tripService;

    @PostMapping
    public ResponseEntity createTrip(@Valid @RequestBody CreateTripDto dto, Principal principal) {

        String memId = principal.getName();
        tripService.createTrip(dto, memId);

        return ResponseEntity.ok().build();
    }

    @PutMapping("/{tripId}")
    public ResponseEntity updateTrip(@Valid @RequestBody UpdateTripDto dto, @PathVariable("tripId") Long tripId) {
        tripService.updateTrip(dto, tripId);

        return ResponseEntity.ok().build();
    }

    @GetMapping
    public ResponseEntity<GetTripListDto> getTrips(Principal principal) {

        String memId = principal.getName();
        GetTripListDto trips = tripService.getTrips(memId);

        return ResponseEntity.ok().body(trips);
    }

    @DeleteMapping("/{tripId}")
    public ResponseEntity deleteTrip(@PathVariable("tripId") Long tripId) {

        tripService.deleteTrip(tripId);

        return ResponseEntity.ok().build();
    }
}
