package com.tripper.dto.response.hotel;

import io.swagger.annotations.ApiModel;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

/**
 * @author HanJiyoung
 * 크롤링한 호텔 데이터를 갖고있는 클래스
 */
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
