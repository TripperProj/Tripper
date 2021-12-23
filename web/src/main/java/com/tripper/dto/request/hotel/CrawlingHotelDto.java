package com.tripper.dto.request.hotel;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;

@ApiModel(value = "호텔 검색 폼 Request DTO")
@Getter @Setter
public class CrawlingHotelDto {

    @ApiModelProperty(value = "여행지")
    private String location;

    @ApiModelProperty(value = "체크인 날짜")
    private String checkin;

    @ApiModelProperty(value = "체크아웃 날짜")
    private String checkout;

    @ApiModelProperty(value = "성인 인원수")
    private String adult;

    @ApiModelProperty(value = "아동 인원수")
    private String child;

    public CrawlingHotelDto(String location, String checkin, String checkout, String adult, String child) {
        this.location = location;
        this.checkin = checkin;
        this.checkout = checkout;
        this.adult = adult;
        this.child = child;
    }

    public String makeRsltUrl() {
        return "&checkin=" + checkin + "&checkout=" + checkout + "&rooms=" + adult + ":" + child;
    }
}
