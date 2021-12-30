package com.tripper.service;

import com.google.gson.JsonArray;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import com.tripper.dto.response.map.*;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Mono;

import java.util.ArrayList;
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

    private static final String TMAP_API_HOST = "apis.openapi.sk.com";
    private static final String NAME_SEARCH_URL = "/tmap/pois";
    private static final String CATEGORY_SEARCH_URL = "/tmap/pois/search/around";
    private static final String ROUTE_SEARCH_URL = "/tmap/routes";

    @Value("${map.tmap.appkey}")
    private String appkey;

    /**
     * 키워드로 장소 검색 후, 필요한 데이터만 파싱해서 GetPlaceListDto 생성한 후 반환
     */
    public GetPlaceListDto keywordSearch(String keyword) {

        String keywordSearchRslt = getKeywordSearchRslt(keyword);

        JsonParser jsonParser = new JsonParser();
        JsonObject jsonObject = (JsonObject) jsonParser.parse(keywordSearchRslt);
        JsonObject searchPoiInfo = (JsonObject) jsonObject.get("searchPoiInfo");
        JsonObject pois = (JsonObject) searchPoiInfo.get("pois");
        JsonArray poi = (JsonArray) pois.get("poi");

        /* GetPlaceDto 리스트 생성 */
        List<GetPlaceDto> getPlaceDtos = new ArrayList<>();
        addKeywordRsltPlaces(getPlaceDtos, poi);
        int total = getPlaceDtos.size();

        return new GetPlaceListDto(getPlaceDtos, total);
    }

    /**
     * Tmap api 사용해서 키워드로 장소 검색하기
     */
    public String getKeywordSearchRslt(String keyword) {

        Mono<String> mono = WebClient.builder()
                .baseUrl(TMAP_API_HOST)
                .build().get()
                .uri(uriBuilder -> uriBuilder.path(NAME_SEARCH_URL)
                        .queryParam("searchKeyword", keyword)
                        .queryParam("appKey", appkey)
                        .build()
                )
                .exchangeToMono(clientResponse -> clientResponse.bodyToMono(String.class));

        return mono.block();

    }

    /**
     * 파싱한 데이터의 escape 문자 삭제
     */
    public String removeEscapeString(String str) {

        str = str.replace("\\\\", "");
        str = str.replace("\\", "");
        str = str.replace("\"", "");

        return str;

    }

    /**
     * 매개변수로 받은 각 경유지들의 반경 1km에 있는 관광명소 검색 후, 필요한 데이터만 파싱해서 GetPlaceListDto 생성한 후 반환
     */
    public GetPlaceListDto categorySearch(String stopovers) {

        /* 매개변수로 받은 경유지 파싱 */
        JsonParser jsonParser = new JsonParser();
        JsonObject jsonObject = (JsonObject) jsonParser.parse(stopovers);
        JsonArray stopover = (JsonArray) jsonObject.get("stopovers");

        List<GetPlaceDto> getPlaceDtos = new ArrayList<>();
        for(int i = 0; i < stopover.size(); i++) {
            JsonObject place = (JsonObject) stopover.get(i);

            Float x = Float.parseFloat(removeEscapeString(place.get("x").toString()));
            Float y = Float.parseFloat(removeEscapeString(place.get("y").toString()));

            String categorySearchRslt = getCategorySearchRslt(x, y);

            /* 카테고리 검색 결과 파싱 */
            jsonObject = (JsonObject) jsonParser.parse(categorySearchRslt);
            JsonObject searchPoiInfo = (JsonObject) jsonObject.get("searchPoiInfo");
            JsonObject pois = (JsonObject) searchPoiInfo.get("pois");
            JsonArray poi = (JsonArray) pois.get("poi");

            /* GetPlaceDto 리스트 생성 */
            addCategoryRsltPlaces(getPlaceDtos, poi);
        }
        int total = getPlaceDtos.size();

        return new GetPlaceListDto(getPlaceDtos, total);

    }

    /**
     * Tmap api 사용해서 카테고리로 반경 1km 관광명소 검색하기
     */
    public String getCategorySearchRslt(Float x, Float y) {

        Mono<String> mono = WebClient.builder()
                .baseUrl(TMAP_API_HOST)
                .build().get()
                .uri(uriBuilder -> uriBuilder.path(CATEGORY_SEARCH_URL)
                        .queryParam("centerLon", x)
                        .queryParam("centerLat", y)
                        .queryParam("categories", "관광명소")
                        .queryParam("radius", 1)
                        .queryParam("appKey", appkey)
                        .build()
                )
                .exchangeToMono(clientResponse -> clientResponse.bodyToMono(String.class));

        return mono.block();

    }

    /**
     * GetPlaceDto 리스트에 키워드 검색 결과 장소 추가하기
     */
    public void addKeywordRsltPlaces(List<GetPlaceDto> placeDtos, JsonArray poi) {

        for(int i = 0; i < poi.size(); i++) {
            JsonObject place = (JsonObject) poi.get(i);
            JsonObject newAddressList = (JsonObject) place.get("newAddressList");
            JsonArray newAddress = (JsonArray) newAddressList.get("newAddress");
            JsonObject newAddr = (JsonObject) newAddress.get(0);

            GetPlaceDto getPlaceDto = GetPlaceDto.builder()
                    .id(removeEscapeString(place.get("id").toString()))
                    .name(removeEscapeString(place.get("name").toString()))
                    .tel(removeEscapeString(place.get("telNo").toString()))
                    .roadAddress(removeEscapeString(newAddr.get("fullAddressRoad").toString()))
                    .x(removeEscapeString(newAddr.get("frontLon").toString()))
                    .y(removeEscapeString(newAddr.get("frontLat").toString()))
                    .build();
            placeDtos.add(getPlaceDto);
        }

    }

    /**
     * GetPlaceDto 리스트에 카테고리 검색 결과 장소 추가하기
     */
    public void addCategoryRsltPlaces(List<GetPlaceDto> placeDtos, JsonArray poi) {

        boolean alreadyAdded = false;

        for(int i = 0; i < poi.size(); i++) {
            JsonObject place = (JsonObject) poi.get(i);

            /* 이미 추가되어있는 장소면 그 장소의 하위 장소들은 추가하지 X (ex.주차장..)*/
            for (GetPlaceDto pl : placeDtos) {
                if (pl.getName().contains(removeEscapeString(place.get("name").toString()))) {
                    alreadyAdded = true;
                    break;
                }
            }

            if(!alreadyAdded) {
                GetPlaceDto getPlaceDto = GetPlaceDto.builder()
                        .id(removeEscapeString(place.get("id").toString()))
                        .name(removeEscapeString(place.get("name").toString()))
                        .tel(removeEscapeString(place.get("telNo").toString()))
                        .roadAddress(removeEscapeString(place.get("upperAddrName").toString())
                                + " " + removeEscapeString(place.get("middleAddrName").toString())
                                + " " + removeEscapeString(place.get("roadName").toString())
                                + " " + removeEscapeString(place.get("buildingNo1").toString())
                                + " " + removeEscapeString(place.get("buildingNo2").toString())
                        )
                        .x(removeEscapeString(place.get("frontLon").toString()))
                        .y(removeEscapeString(place.get("frontLat").toString()))
                        .build();
                placeDtos.add(getPlaceDto);
            }
            alreadyAdded = false;
        }

    }

//    /**
//     * 최단 경로 생성 후 반환
//     */
//    public String shortestRoute(String stopovers) {
//        GetStopoverJsonDto getStopoverJsonDto = new Gson().fromJson(stopovers, GetStopoverJsonDto.class);
//        List<GetStopoverDto> getStopoverDtos = new ArrayList<>();
//
//        for(GetStopoverDto getStopoverDto : getStopoverJsonDto.getGetStopoverDtos()){
//            log.info(getStopoverDto.getPlaceName());
//        }
//        return null;
//
//    }
//
//    /**
//     * Tmap api 사용해서 두 지점간 거리 구하기
//     */
//    public String getDistance() {
//
//        Mono<String> mono = WebClient.builder()
//                .baseUrl(TMAP_API_HOST)
//                .build().get()
//                .uri(uriBuilder -> uriBuilder.path(ROUTE_SEARCH_URL)
//                        .queryParam("startX", "126.492769004244")
//                        .queryParam("startY", "33.5070789578184")
//                        .queryParam("endX", "126.54222094512")
//                        .queryParam("endY", "33.3766655632143")
//                        .queryParam("resCoordType", "WGS84GEO")
//                        .queryParam("searchOption", 10)
//                        .queryParam("totalValue", 2)
//                        .build()
//                )
//                .header("appKey", appkey)
//                .exchangeToMono(clientResponse -> {
//                    return clientResponse.bodyToMono(String.class);
//                });
//
//        return mono.block();
//
//    }

}
