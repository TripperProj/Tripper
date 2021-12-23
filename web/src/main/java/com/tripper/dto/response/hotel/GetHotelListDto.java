package com.tripper.dto.response.hotel;

import io.swagger.annotations.ApiModel;
import lombok.AllArgsConstructor;
import lombok.Getter;

import java.util.List;

@ApiModel(value = "호텔 리스트 Respose DTO")
@Getter
@AllArgsConstructor
public class GetHotelListDto {

    List<GetHotelDto> hotels;
    int hotelTotal;

}
