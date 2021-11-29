package com.tripper.controller;

import com.tripper.domain.hotel.HotelForm;
import com.tripper.domain.hotel.HotelInfo;
import com.tripper.service.HotelService;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * @author HanJiyoung
 * '호텔 찾기' 기능 컨트롤러 클래스
 */
@Controller
@Slf4j
public class HotelController {
    private final HotelService hotelService;

    @Autowired
    public HotelController(HotelService hotelService) {
        this.hotelService = hotelService;
    }

    @ApiOperation(
            value = "호텔검색 폼으로 이동"
            , notes = "호텔검색 폼으로 이동한다.")
    @RequestMapping("/hotel")
    public String goHotelForm() {
        return "hotel/hotel_form";
    }

    @ApiOperation(
            value = "호텔 크롤링"
            , notes = "호텔 크롤링 후 가져온 데이터를 뷰페이지로 넘겨준다.")
    @PostMapping("/hotel/crawling")
    public String crawlHotel(@ModelAttribute HotelForm hotelForm, Model model){
        List<HotelInfo> hotels = hotelService.crawlingHotels(hotelForm);
        model.addAttribute("hotelList", hotels);
        return "hotel/hotel_search_list";
    }
}