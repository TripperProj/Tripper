package com.tripper.dto.response.hotel;

import com.tripper.dto.response.board.GetBoardDto;
import io.swagger.annotations.ApiModel;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;

@ApiModel(value = "호텔 리스트 Respose DTO")
@Getter @Setter
@AllArgsConstructor
public class GetHotelListDto {
    List<GetHotelDto> hotels = new ArrayList<>();
}
