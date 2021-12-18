package com.tripper.dto.response;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Getter;

@ApiModel(value = "사진 Respose DTO")
@Getter
@AllArgsConstructor
public class GetPhotoDto {

    @ApiModelProperty(value = "사진 고유 번호")
    private Long id;

    @ApiModelProperty(value = "원본파일명")
    private String originName;

    @ApiModelProperty(value = "파일 경로")
    private String filePath;

}
