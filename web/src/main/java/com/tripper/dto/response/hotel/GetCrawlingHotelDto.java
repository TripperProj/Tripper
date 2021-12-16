package com.tripper.dto.response.hotel;

import io.swagger.annotations.ApiModel;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@ApiModel(value = "크롤링한 호텔 Respose DTO")
@Getter @Setter
@ToString
public class GetCrawlingHotelDto {
    private String name;
    private String imgsrc;
    private String lowestprice;

    public GetCrawlingHotelDto() {
    }
}
