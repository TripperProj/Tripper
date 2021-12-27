package com.tripper.dto.response.map;

import com.google.gson.annotations.SerializedName;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Builder;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

@ApiModel(value = "경유지 DTO")
@Getter
@Builder
public class GetStopoverDto {

    @ApiModelProperty(value = "주소")
    @SerializedName("address_name")
    private String addressName;

    @ApiModelProperty(value = "id")
    @SerializedName("id")
    private String id;

    @ApiModelProperty(value = "전화번호")
    @SerializedName("phone")
    private String phone;

    @ApiModelProperty(value = "장소명")
    @SerializedName("place_name")
    private String placeName;

    @ApiModelProperty(value = "카카오맵에서 제공하는 장소 정보 url")
    @SerializedName("place_url")
    private String placeUrl;

    @ApiModelProperty(value = "도로명")
    @SerializedName("road_address_name")
    private String roadAddressName;

    @ApiModelProperty(value = "x좌표")
    @SerializedName("x")
    private String x;

    @ApiModelProperty(value = "y좌표")
    @SerializedName("y")
    private String y;

}
