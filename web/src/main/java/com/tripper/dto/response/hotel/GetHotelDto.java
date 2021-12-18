package com.tripper.dto.response.hotel;

import com.tripper.dto.response.GetPhotoListDto;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Getter;

@ApiModel(value = "호텔 Respose DTO")
@Getter
@AllArgsConstructor
public class GetHotelDto {

    @ApiModelProperty(value = "호텔 고유 아이디")
    private Long id;

    @ApiModelProperty(value = "호텔 이름")
    private String name;

    @ApiModelProperty(value = "주소")
    private String address;

    @ApiModelProperty(value = "호텔 사진 목록")
    private GetPhotoListDto photos;

    @ApiModelProperty(value = "객실 목록")
    private GetRoomListDto rooms;

}
