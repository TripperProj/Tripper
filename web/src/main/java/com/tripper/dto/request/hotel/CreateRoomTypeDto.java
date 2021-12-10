package com.tripper.dto.request.hotel;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

@ApiModel(value = "객실 종류 등록 폼 Request DTO")
@Getter @Setter
public class CreateRoomTypeDto {

    @ApiModelProperty(value = "객실 이름")
    private String name;

    @ApiModelProperty(value = "객실 이미지")
    private List<MultipartFile> photos;

    @ApiModelProperty(value = "기준 인원")
    private int standardCapcacity;

    @ApiModelProperty(value = "최대 인원")
    private int maxCapcacity;

}
