package com.tripper.dto.request.hotel;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;

@ApiModel(value = "객실 등록 폼 Request DTO")
@Getter @Setter
public class CreateRoomDto {

    @ApiModelProperty(value = "호수")
    private int roomNum;

    @ApiModelProperty(value = "1박 비용")
    private int price;

}
