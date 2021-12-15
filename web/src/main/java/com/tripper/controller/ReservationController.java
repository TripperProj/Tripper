package com.tripper.controller;

import com.tripper.domain.hotel.Reservation;
import com.tripper.dto.request.hotel.CreateReservationDto;
import com.tripper.dto.response.hotel.GetHotelListDto;
import com.tripper.dto.response.hotel.GetReservationListDto;
import com.tripper.service.HotelService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.security.Principal;
import java.util.List;

@Api(tags = "호텔 예약 API")
@RestController
@RequiredArgsConstructor
@RequestMapping("/reservation")
@Slf4j
public class ReservationController {

    private final HotelService hotelService;

    @ApiOperation(
            value = "호텔 예약"
            , notes = "호텔 예약 폼에서 입력한 정보로 호텔 예약을 실행한다.")
    @ResponseStatus(HttpStatus.CREATED)
    @PostMapping("/{room_id}/create")
    public Long createReservation(Principal principal,
                                  @ApiParam(value = "예약할 객실의 고유 id") @PathVariable("room_id") Long room_id,
                                  @ApiParam(value = "호텔 예약 폼에 입력한 정보를 담고있는 객체") CreateReservationDto createReservationDto) throws Exception {
        return hotelService.createReservation(principal.getName(), room_id, createReservationDto);
    }

    @ApiOperation(
            value = "전체 예약 목록 조회"
            , notes = "모든 예약을 조회한다.")
    @GetMapping("/list")
    public ResponseEntity<GetReservationListDto> findAllReservations() {

        GetReservationListDto getReservationListDto = hotelService.findAllReservations();
        return ResponseEntity.ok().body(getReservationListDto);

    }

    @ApiOperation(
            value = "현재 로그인한 사용자의 예약 조회"
            , notes = "현재 로그인한 사용자의 예약을 조회한다.")
    @GetMapping("/list/bymemId")
    public ResponseEntity<GetReservationListDto> findReservationsByMemId(Principal principal) {

        GetReservationListDto getReservationListDto = hotelService.findReservationsByMemId(principal.getName());
        return ResponseEntity.ok().body(getReservationListDto);

    }

    @ApiOperation(
            value = "특정 호텔에 대한 예약 조회"
            , notes = "특정 호텔의 모든 예약을 조회한다.")
    @GetMapping("/list/byhotelId/{hotel_id}")
    public ResponseEntity<GetReservationListDto> findReservationsByHotelId(@ApiParam(value = "조회할 호텔의 고유 id") @PathVariable("hotel_id") Long hotel_id) {

        GetReservationListDto getReservationListDto = hotelService.findReservationsByHotelId(hotel_id);
        return ResponseEntity.ok().body(getReservationListDto);

    }

    @ApiOperation(
            value = "특정 객실에 대한 예약 조회"
            , notes = "특정 객실의 예약을 조회한다.")
    @GetMapping("/list/byroomId/{room_id}")
    public ResponseEntity<GetReservationListDto> findReservationsByRoomId(@ApiParam(value = "조회할 객실의 고유 id") @PathVariable("room_id") Long room_id) {

        GetReservationListDto getReservationListDto = hotelService.findReservationsByRoomId(room_id);
        return ResponseEntity.ok().body(getReservationListDto);

    }


    @ApiOperation(
            value = "예약 취소"
            , notes = "예약 취소를 실행한다.")
    @PostMapping("/{reservation_id}/cancelReservation")
    public ResponseEntity cancelReservation(@ApiParam(value = "취소할 예약의 고유 id") @PathVariable("reservation_id") Long reservation_id) {

        hotelService.cancelReservation(reservation_id);
        return ResponseEntity.ok().build();

    }

}
