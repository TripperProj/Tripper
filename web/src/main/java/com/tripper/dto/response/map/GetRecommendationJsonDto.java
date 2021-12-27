package com.tripper.dto.response.map;

import com.google.gson.annotations.SerializedName;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;

import java.util.List;

@ApiModel(value = "추천 경유지 목록 DTO")
@Getter
public class GetRecommendationJsonDto {

    @ApiModelProperty(value = "경유지 목록")
    @SerializedName("documents")
    private List<GetStopoverDto> getStopoverDtos;

    @ApiModelProperty(value = "메타 정보")
    @SerializedName("meta")
    private GetJsonMetaDto getJsonMetaDto;
}
