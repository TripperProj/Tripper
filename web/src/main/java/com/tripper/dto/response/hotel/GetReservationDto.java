package com.tripper.dto.response.hotel;

import com.tripper.domain.hotel.ReservationStatus;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Getter;

@ApiModel(value = "객실 예약 Respose DTO")
@Getter
@AllArgsConstructor
public class GetReservationDto {

    @ApiModelProperty(value = "호텔 이름")
    private String hotel;

    @ApiModelProperty(value = "객실 이름")
    private String roomType;

    @ApiModelProperty(value = "호수")
    private int room;

    @ApiModelProperty(value = "예약자 아이디")
    private String memId;

    @ApiModelProperty(value = "체크인 날짜")
    private String checkin;

    @ApiModelProperty(value = "체크아웃 날짜")
    private String checkout;

    @ApiModelProperty(value = "성인 인원수")
    private int adultNum;

    @ApiModelProperty(value = "아동 인원수")
    private int childNum;

    @ApiModelProperty(value = "예약 상태")
    private ReservationStatus status;

}
