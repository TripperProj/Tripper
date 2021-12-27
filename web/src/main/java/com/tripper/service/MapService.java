package com.tripper.service;

import com.google.gson.Gson;
import com.tripper.dto.response.map.GetRecommendationJsonDto;
import com.tripper.dto.response.map.GetStopoverDto;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Mono;

import java.util.List;

/**
 * @author HanJiyoung
 * 지도 관련 서비스 클래스
 */
@Service
@Transactional(readOnly = true)
@ConfigurationProperties("map")
@Slf4j
public class MapService {

    private static final String KAKAOMAP_API_HOST = "dapi.kakao.com";
    private static final String KEYWORD_SEARCH_URL = "/v2/local/search/keyword.json";
    private static final String CATEGORY_SEARCH_URL = "/v2/local/search/category.json";

    private static final String TMAP_API_HOST = "apis.openapi.sk.com";
    private static final String ROUTE_SEARCH_URL = "/tmap/routes";

    @Value("${map.kakao.restkey}")
    private String restkey;

    @Value("${map.tmap.appkey}")
    private String appkey;

    /**
     * 키워드로 장소 검색
     */
    public String keywordSearch(String keyword) {

        Mono<String> mono = WebClient.builder()
                .baseUrl(KAKAOMAP_API_HOST)
                .build().get()
                .uri(uriBuilder -> uriBuilder.path(KEYWORD_SEARCH_URL)
                        .queryParam("query", keyword)
                        .build()
                )
                .header("Authorization", "KakaoAK " + restkey)
                .exchangeToMono(clientResponse -> {
                    return clientResponse.bodyToMono(String.class);
                });

        return mono.block();
        
    }

    /**
     * 매개변수로 받은 좌표의 반경 1km 내에 있는 관광명소를 반환
     */
    public String categorySearch(String x, String y) {

        Mono<String> mono = WebClient.builder()
                .baseUrl(KAKAOMAP_API_HOST)
                .build().get()
                .uri(uriBuilder -> uriBuilder.path(CATEGORY_SEARCH_URL)
                        .queryParam("category_group_code", "AT4")
                        .queryParam("x", x)
                        .queryParam("y", y)
                        .queryParam("radius", 1000)
                        .build()
                )
                .header("Authorization", "KakaoAK " + restkey)
                .exchangeToMono(clientResponse -> {
                    return clientResponse.bodyToMono(String.class);
                });

        return mono.block();

    }

    /**
     * 추천 관광명소들을 리스트에 넣어줌
     */
    public List<GetStopoverDto> getRecommendationStopoverList(List<GetStopoverDto> getStopoverDtos, String recommendations) {

        GetRecommendationJsonDto getRecommendationJsonDto = new Gson().fromJson(recommendations, GetRecommendationJsonDto.class);

        for(GetStopoverDto recommendedGetStopoverDto : getRecommendationJsonDto.getGetStopoverDtos()) {
            GetStopoverDto getStopoverDto = GetStopoverDto.builder()
                    .addressName(recommendedGetStopoverDto.getAddressName())
                    .id(recommendedGetStopoverDto.getId())
                    .phone(recommendedGetStopoverDto.getPhone())
                    .placeName(recommendedGetStopoverDto.getPlaceName())
                    .placeUrl(recommendedGetStopoverDto.getPlaceUrl())
                    .roadAddressName(recommendedGetStopoverDto.getRoadAddressName())
                    .x(recommendedGetStopoverDto.getX())
                    .y(recommendedGetStopoverDto.getY())
                    .build();
            getStopoverDtos.add(getStopoverDto);
        }

        return getStopoverDtos;

    }

    /**
     * 최단 경로 반환
     */
    public String shortestRoute() {
        return null;

    }

    /**
     * 두 지점 간 거리 받아오기
     */
    public String getDistance() {

        Mono<String> mono = WebClient.builder()
                .baseUrl(TMAP_API_HOST)
                .build().get()
                .uri(uriBuilder -> uriBuilder.path(ROUTE_SEARCH_URL)
                        .queryParam("startX", "126.492769004244")
                        .queryParam("startY", "33.5070789578184")
                        .queryParam("endX", "126.42601744245376")
                        .queryParam("endY", "33.23799399299591")
                        .queryParam("reqCoordType", "WGS84GEO")
                        .queryParam("resCoordType", "WGS84GEO")
                        .queryParam("endY", "33.23799399299591")
                        .queryParam("endY", "33.23799399299591")
                        .queryParam("searchOption", 10)
                        .queryParam("totalValue", 2)
                        .build()
                )
                .header("appKey", appkey)
                .exchangeToMono(clientResponse -> {
                    return clientResponse.bodyToMono(String.class);
                });

        return mono.block();

    }

}
