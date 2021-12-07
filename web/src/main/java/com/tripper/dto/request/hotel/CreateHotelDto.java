package com.tripper.dto.request.hotel;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

@ApiModel(value = "호텔 등록 폼 Request DTO")
@Getter @Setter
@RequiredArgsConstructor
public class CreateHotelDto {

    @ApiModelProperty(value = "호텔 이름")
    private String name;

    @ApiModelProperty(value = "호텔 이미지")
    private List<MultipartFile> photos;

    @ApiModelProperty(value = "호텔 주소")
    private String address;

}
