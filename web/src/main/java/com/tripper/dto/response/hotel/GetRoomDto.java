package com.tripper.dto.response.hotel;

import com.tripper.dto.response.GetPhotoListDto;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Getter;

import java.util.List;

@ApiModel(value = "객실 Respose DTO")
@Getter
@AllArgsConstructor
public class GetRoomDto {

    @ApiModelProperty(value = "객실 분류 고유 번호")
    private Long id;

    @ApiModelProperty(value = "객실 분류명")
    private String name;

    @ApiModelProperty(value = "기준 인원")
    private int standardCapacity;

    @ApiModelProperty(value = "최대 인원")
    private int maxCapacity;

    @ApiModelProperty(value = "하위 객실들")
    private List<Integer> rooms;

    @ApiModelProperty(value = "1박 가격")
    private int price;

    @ApiModelProperty(value = "객실 사진")
    private GetPhotoListDto photos;

}
