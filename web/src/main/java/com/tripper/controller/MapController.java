package com.tripper.controller;

import com.google.gson.Gson;
import com.tripper.dto.response.map.GetRecommendationListDto;
import com.tripper.dto.response.map.GetStopoverDto;
import com.tripper.dto.response.map.GetStopoverJsonDto;
import com.tripper.service.MapService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiParam;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;

@Api(tags = "지도 API")
@RestController
@RequiredArgsConstructor
@Slf4j
public class MapController {

    private final MapService mapService;

    /**
     * 키워드로 장소 검색
     */
    @GetMapping("/place/keyword")
    public String keywordSearch(@ApiParam(value = "장소 검색 키워드") @RequestParam String keyword) {

        String keywordRslt = mapService.keywordSearch(keyword);
        return keywordRslt;

    }

    /**
     * 관광명소 추천받기
     */
    @GetMapping("/place/recommendation")
    public GetRecommendationListDto recommend(@ApiParam(value = "현재 경유지 목록") @RequestBody String stopovers) {

        GetStopoverJsonDto getStopoverJsonDto = new Gson().fromJson(stopovers, GetStopoverJsonDto.class);
        List<GetStopoverDto> getStopoverDtos = new ArrayList<>();

        for(GetStopoverDto myGetStopoverDto : getStopoverJsonDto.getGetStopoverDtos()){
            String x = myGetStopoverDto.getX();
            String y = myGetStopoverDto.getY();
            String recommendations = mapService.categorySearch(x, y);

            getStopoverDtos = mapService.getRecommendationStopoverList(getStopoverDtos, recommendations);
        }
        int total = getStopoverDtos.size();

        return new GetRecommendationListDto(getStopoverDtos, total);

    }

    /**
     * 최단 경로 보기
     */
    @GetMapping("/place/shortest-route")
    public String shortestRoute() {
        String routeRslt = mapService.shortestRoute();
        return routeRslt;
    }

}
