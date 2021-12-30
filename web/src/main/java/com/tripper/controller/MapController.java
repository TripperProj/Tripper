package com.tripper.controller;

import com.tripper.dto.response.map.GetPlaceListDto;
import com.tripper.service.MapService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiParam;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

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
    public ResponseEntity<GetPlaceListDto> keywordSearch(@ApiParam(value = "장소 검색 키워드") @RequestParam String keyword) {

        GetPlaceListDto keywordSearchList = mapService.keywordSearch(keyword);
        return ResponseEntity.ok().body(keywordSearchList);

    }

    /**
     * 관광명소 추천받기
     */
    @GetMapping("/place/recommendation")
    public ResponseEntity<GetPlaceListDto> categorySearch(@ApiParam(value = "현재 경유지 목록") @RequestBody String stopovers) {

        GetPlaceListDto categorySearchList = mapService.categorySearch(stopovers);
        return ResponseEntity.ok().body(categorySearchList);

    }

//    /**
//     * 최단 경로 구하기
//     */
//    @GetMapping("/place/shortest-route")
//    public ResponseEntity<String> shortestRoute(@ApiParam(value = "현재 경유지 목록") @RequestBody String stopovers) {
//
//        String routeRslt = mapService.getDistance();
//        return ResponseEntity.ok().body(routeRslt);
//
//    }

}
