package com.tripper.controller;

import com.tripper.dto.request.board.CreateBoardDto;
import com.tripper.dto.request.hotel.CrawlingHotelDto;
import com.tripper.dto.request.hotel.CreateHotelDto;
import com.tripper.dto.response.hotel.GetCrawlingHotelDto;
import com.tripper.service.HotelService;
import io.swagger.annotations.*;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

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

//    @ApiOperation(
//            value = "호텔 등록"
//            , notes = "호텔 등록 폼에서 입력한 정보로 글 등록을 실행한다.")
//    @ApiResponses(value = { @ApiResponse(code = 200, message = "ok: 호텔 등록 성공.") })
//    @PostMapping("/create")
//    public String create(Authentication authentication,
//                         @ApiParam(value = "호텔 등록 폼에 입력한 정보를 담고있는 객체") @RequestBody CreateHotelDto dto) {
//
//
//
//    }
}