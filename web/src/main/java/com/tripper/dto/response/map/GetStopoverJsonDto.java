package com.tripper.dto.response.map;

import com.google.gson.annotations.SerializedName;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;

import java.util.List;

@ApiModel(value = "현재 경유지 목록 DTO")
@Getter
public class GetStopoverJsonDto {

    @ApiModelProperty(value = "경유지 목록")
    @SerializedName("stopovers")
    private List<GetStopoverDto> getStopoverDtos;

}
