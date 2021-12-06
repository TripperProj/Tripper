package com.tripper.controller;

import com.tripper.dto.request.hotel.CrawlingHotelDto;
import com.tripper.dto.request.hotel.CreateHotelDto;
import com.tripper.dto.response.hotel.GetCrawlingHotelDto;
import com.tripper.dto.response.hotel.GetHotelListDto;
import com.tripper.service.HotelService;
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

    @ApiOperation(
            value = "호텔 크롤링"
            , notes = "호텔 크롤링 후 가져온 데이터를 뷰페이지로 넘겨준다.")
    @PostMapping("/crawling")
    public String crawlHotel(@ModelAttribute CrawlingHotelDto crawlingHotelDto, Model model){
        List<GetCrawlingHotelDto> hotels = hotelService.crawlingHotels(crawlingHotelDto);
        model.addAttribute("hotelList", hotels);
        return "hotel/hotel_search_list";
    }

    @ApiOperation(
            value = "호텔 등록"
            , notes = "호텔 등록 폼에서 입력한 정보로 글 등록을 실행한다.")
    @ResponseStatus(HttpStatus.CREATED)
    @PostMapping("/create")
    public Long create(Principal principal,
                       @ApiParam(value = "호텔 등록 폼에 입력한 정보를 담고있는 객체") CreateHotelDto createHotelDto) throws Exception {
        return hotelService.createHotel(createHotelDto, createHotelDto.getPhotos(), principal.getName());
    }

    @ApiOperation(
            value = "호텔 목록 조회"
            , notes = "db에 저장된 호텔 목록을 전체 조회한다.")
    @GetMapping("/list")
    public ResponseEntity<GetHotelListDto> getHotelList() {

        GetHotelListDto hotels = hotelService.findAllHotels();
        return ResponseEntity.ok().body(hotels);

    }
}