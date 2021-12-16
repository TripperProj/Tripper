package com.tripper.dto.response.hotel;

import com.tripper.domain.hotel.Hotel;
import com.tripper.domain.hotel.Reservation;
import com.tripper.domain.hotel.ReservationStatus;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@ApiModel(value = "객실 예약 Respose DTO")
@Getter @Setter
@AllArgsConstructor
public class GetReservationDto {

    @ApiModelProperty(value = "체크인 날짜")
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

    public GetReservationDto(Reservation reservation) {
        this.memId = reservation.getUser().getMemId();
        this.checkin = reservation.getCheckin();
        this.checkout = reservation.getCheckout();
        this.adultNum = reservation.getAdultNum();
        this.childNum = reservation.getChildNum();
        this.status = reservation.getStatus();
    }

}
