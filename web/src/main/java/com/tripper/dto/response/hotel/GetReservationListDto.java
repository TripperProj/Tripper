package com.tripper.dto.response.hotel;

import io.swagger.annotations.ApiModel;
import lombok.AllArgsConstructor;
import lombok.Getter;

import java.util.List;

@ApiModel(value = "객실 예약 리스트 Respose DTO")
@Getter
@AllArgsConstructor
public class GetReservationListDto {

    List<GetReservationDto> reservationDtos;
    int reservationTotal;
}
