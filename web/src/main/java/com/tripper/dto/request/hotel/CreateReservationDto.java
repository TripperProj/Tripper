package com.tripper.dto.request.hotel;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;

@ApiModel(value = "호텔 예약 폼 Request DTO")
@Getter @Setter
@RequiredArgsConstructor
public class CreateReservationDto {

    @ApiModelProperty(value = "체크인 날짜")
    private String checkin;

    @ApiModelProperty(value = "체크아웃 날짜")
    private String checkout;

    @ApiModelProperty(value = "성인 인원수")
    private int adultNum;

    @ApiModelProperty(value = "아동 인원수")
    private int childNum;

}
