package com.tripper.dto.response.map;

import com.google.gson.annotations.SerializedName;
import io.swagger.annotations.ApiModelProperty;

public class GetJsonMetaDto {

    @ApiModelProperty(value = "end 여부")
    @SerializedName("is_end")
    private Boolean isEnd;

    @ApiModelProperty(value = "한 페이지 개수")
    @SerializedName("pageable_count")
    private int count;

    @ApiModelProperty(value = "같은 이름 존재 여부")
    @SerializedName("same_name")
    private String sameName;

    @ApiModelProperty(value = "총 개수")
    @SerializedName("total_count")
    private int totalCount;
}
