package com.tripper.controller;

import com.tripper.dto.request.hotel.*;
import com.tripper.dto.response.hotel.GetCrawlingHotelDto;
import com.tripper.dto.response.hotel.GetHotelDto;
import com.tripper.dto.response.hotel.GetHotelListDto;
import com.tripper.dto.response.hotel.GetRoomDto;
import com.tripper.service.HotelService;
import com.tripper.service.PhotoService;
import io.swagger.annotations.*;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.security.Principal;
import java.util.List;

@Api(tags = "호텔 API")
@RestController
@RequiredArgsConstructor
@RequestMapping("/hotel")
@Slf4j
public class HotelController {

    private final HotelService hotelService;
    private final PhotoService photoService;

    /*---------------------------------    생성    -------------------------------*/
    @ApiOperation(
            value = "호텔 등록"
            , notes = "호텔 등록 폼에서 입력한 정보로 호텔 등록을 실행한다.")
    @ResponseStatus(HttpStatus.CREATED)
    @PostMapping("/manage/createHotel")
    public Long createHotel(Principal principal,
                            @ApiParam(value = "호텔 등록 폼에 입력한 정보를 담고있는 객체") CreateHotelDto createHotelDto) throws Exception {
        return hotelService.createHotel(createHotelDto, principal.getName());
    }

    @ApiOperation(
            value = "객실 등록"
            , notes = "객실 등록 폼에서 입력한 정보로 글 등록을 실행한다.")
    @ResponseStatus(HttpStatus.OK)
    @PostMapping("/manage/{hotel_id}/createRoom")
    public ResponseEntity createRoom(@ApiParam(value = "객실을 등록할 호텔의 고유 id") @PathVariable("hotel_id") Long hotel_id,
                                     @ApiParam(value = "객실 등록 폼에 입력한 정보를 담고있는 객체") CreateRoomDto createRoomDto) throws Exception {

        hotelService.createRoom(hotel_id, createRoomDto);
        return ResponseEntity.ok().build();

    }

    /*---------------------------------    조회    -------------------------------*/
    @ApiOperation(
            value = "호텔 목록 조회"
            , notes = "db에 저장된 호텔 목록을 전체 조회한다.")
    @GetMapping("/getHotelList")
    public ResponseEntity<GetHotelListDto> getHotelList() {

        GetHotelListDto hotels = hotelService.findAllHotels();
        return ResponseEntity.ok().body(hotels);

    }

    @ApiOperation(
            value = "특정 호텔 1개 조회"
            , notes = "hotel_id 일치하는 호텔을 조회한다.")
    @GetMapping("/{hotel_id}/getHotel")
    public ResponseEntity<GetHotelDto> getHotel(@ApiParam(value = "조회할 호텔의 고유 id") @PathVariable("hotel_id") Long hotel_id) {

        GetHotelDto getHotelDto = hotelService.findByHotelId(hotel_id);
        return ResponseEntity.ok().body(getHotelDto);

    }

    @ApiOperation(
            value = "특정 객실 조회"
            , notes = "roomtype_id가 일치하는 객실을 조회한다.")
    @GetMapping("/{roomtype_id}/getRooms")
    public ResponseEntity<GetRoomDto> getRoom(@ApiParam(value = "조회할 객실 분류의 고유 id") @PathVariable("roomtype_id") Long roomtype_id) {

        GetRoomDto getRoomDto = hotelService.findByRoomTypeId(roomtype_id);
        return ResponseEntity.ok().body(getRoomDto);

    }

    /*---------------------------------    수정    -------------------------------*/
    @ApiOperation(
            value = "호텔 정보 수정"
            , notes = "호텔 정보 수정을 실행한다.")
    @PostMapping("/manage/{hotel_id}/updateHotel")
    public ResponseEntity updateHotel(@ApiParam(value = "수정할 호텔의 고유 id") @PathVariable("hotel_id") Long hotel_id,
                                      @ApiParam(value = "호텔 수정 폼에 입력한 정보를 갖고있는 객체") UpdateHotelDto updateHotelDto) throws Exception {

        hotelService.updateHotel(hotel_id, updateHotelDto);
        return ResponseEntity.ok().build();

    }

    @ApiOperation(
            value = "객실 정보 수정"
            , notes = "객실 정보 수정을 실행한다.")
    @PostMapping("/manage/{roomtype_id}/updateRoom")
    public ResponseEntity updateHotel(@ApiParam(value = "수정할 객실의 고유 id") @PathVariable("roomtype_id") Long roomtype_id,
                                      @ApiParam(value = "객실 수정 폼에 입력한 정보를 갖고있는 객체") UpdateRoomDto updateRoomDto) throws Exception {

        hotelService.updateRoom(roomtype_id, updateRoomDto);
        return ResponseEntity.ok().build();

    }

    /*---------------------------------    삭제    -------------------------------*/
    @ApiOperation(
            value = "호텔 삭제"
            , notes = "호텔 삭제를 실행한다.")
    @GetMapping("/manage/{hotel_id}/deleteHotel")
    public ResponseEntity deletePost(@ApiParam(value = "삭제할 호텔의 고유 id") @PathVariable("hotel_id") Long hotel_id) {

        hotelService.deleteHotelById(hotel_id);
        return ResponseEntity.ok().build();

    }

    @ApiOperation(
            value = "객실 삭제"
            , notes = "객실 삭제를 실행한다.")
    @PostMapping("/manage/{roomtype_id}/deleteRoom")
    public ResponseEntity deleteRoomType(@ApiParam(value = "삭제할 객실의 고유 id") @PathVariable("roomtype_id") Long roomtype_id) {

        hotelService.deleteRoomTypeById(roomtype_id);
        return ResponseEntity.ok().build();

    }

    @ApiOperation(
            value = "사진 삭제"
            , notes = "기존에 등록돼있던 사진 삭제를 실행한다.")
    @PostMapping("/manage/{photo_id}/deletePhoto")
    public ResponseEntity deletePhoto(@ApiParam(value = "삭제할 사진의 고유 id") @PathVariable("photo_id") Long photo_id) {

        photoService.deletePhotoById(photo_id);
        return ResponseEntity.ok().build();

    }

    @ApiOperation(
            value = "방 호수 삭제"
            , notes = "특정 객실에 배정돼있던 방 호수 삭제를 실행한다.")
    @PostMapping("/manage/{room_id}/deleteRoomNum")
    public ResponseEntity deleteRoom(@ApiParam(value = "삭제할 객실의 고유 id") @PathVariable("room_id") Long room_id) {

        hotelService.deleteRoomById(room_id);
        return ResponseEntity.ok().build();

    }

    @ApiOperation(
            value = "호텔 크롤링"
            , notes = "호텔 크롤링 후 가져온 데이터를 뷰페이지로 넘겨준다.")
    @PostMapping("/crawlingHotels")
    public String crawlHotel(@ModelAttribute CrawlingHotelDto crawlingHotelDto, Model model){
        List<GetCrawlingHotelDto> hotels = hotelService.crawlingHotels(crawlingHotelDto);
        model.addAttribute("hotelList", hotels);
        return "hotel/hotel_search_list";
    }

}