package com.tripper.dto.response;

import io.swagger.annotations.ApiModel;
import lombok.AllArgsConstructor;
import lombok.Getter;

import java.util.List;

@ApiModel(value = "사진 리스트 Respose DTO")
@Getter
@AllArgsConstructor
public class GetPhotoListDto {

    List<GetPhotoDto> photos;
    int photoTotal;

}
