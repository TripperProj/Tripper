package com.tripper.dto.request.hotel;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

@ApiModel(value = "객실 수정 폼 Request DTO")
@Getter @Setter
@RequiredArgsConstructor
public class UpdateRoomDto {

    @ApiModelProperty(value = "객실 이름")
    private String name;

    @ApiModelProperty(value = "객실 이미지")
    private List<MultipartFile> photos;

    @ApiModelProperty(value = "기준 인원")
    private int standardCapacity;

    @ApiModelProperty(value = "최대 인원")
    private int maxCapacity;

    @ApiModelProperty(value = "호수")
    private List<Integer> roomNum;

    @ApiModelProperty(value = "1박 비용")
    private int price;

}
