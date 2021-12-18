package com.tripper.controller;

import com.tripper.dto.response.hotel.GetHotelDto;
import com.tripper.dto.response.hotel.GetHotelListDto;
import com.tripper.dto.response.hotel.GetRoomDto;
import com.tripper.service.HotelService;
import io.swagger.annotations.*;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@Api(tags = "호텔 API")
@RestController
@RequiredArgsConstructor
@Slf4j
public class HotelController {

    private final HotelService hotelService;

    @ApiOperation(
            value = "호텔 목록 조회"
            , notes = "db에 저장된 호텔 목록을 전체 조회한다.")
    @GetMapping("/hotels")
    public ResponseEntity<GetHotelListDto> getHotelList() {

        GetHotelListDto hotels = hotelService.findAllHotels();
        return ResponseEntity.ok().body(hotels);

    }

    @ApiOperation(
            value = "특정 호텔 1개 조회"
            , notes = "hotel_id 일치하는 호텔을 조회한다.")
    @GetMapping("/hotel/{hotelId}")
    public ResponseEntity<GetHotelDto> getHotel(@ApiParam(value = "조회할 호텔의 고유 id") @PathVariable("hotelId") Long hotelId) {

        GetHotelDto getHotelDto = hotelService.findByHotelId(hotelId);
        return ResponseEntity.ok().body(getHotelDto);

    }

    @ApiOperation(
            value = "특정 객실 조회"
            , notes = "roomtype_id가 일치하는 객실을 조회한다.")
    @GetMapping("/roomtype/{roomtypeId}")
    public ResponseEntity<GetRoomDto> getRoom(@ApiParam(value = "조회할 객실 분류의 고유 id") @PathVariable("roomtypeId") Long roomtypeId) {

        GetRoomDto getRoomDto = hotelService.findByRoomTypeId(roomtypeId);
        return ResponseEntity.ok().body(getRoomDto);

    }

}