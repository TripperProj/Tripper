package com.tripper.dto.request;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@ApiModel(value = "사진 등록 Request DTO")
@Getter @Setter
@Builder
public class CreatePhotoDto {

    @ApiModelProperty(value = "파일 원본 이름")
    private String originName;

    @ApiModelProperty(value = "파일 경로")
    private String filePath;

    @ApiModelProperty(value = "파일 크기")
    private Long fileSize;

}
