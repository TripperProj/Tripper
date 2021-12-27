package com.tripper.dto.response.map;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Getter;

import java.util.List;

@ApiModel(value = "추천 경유지 리스트 Response DTO")
@Getter
@AllArgsConstructor
public class GetRecommendationListDto {

    @ApiModelProperty(value = "추천 경유지 리스트")
    List<GetStopoverDto> recommendedGetStopoverDtos;

    @ApiModelProperty(value = "추천 경유지 총 개수")
    int total;

}
