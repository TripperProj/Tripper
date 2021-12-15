package com.tripper.dto.request.board;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;

@ApiModel(value = "게시글 등록 폼 Request DTO")
@Getter @Setter
@RequiredArgsConstructor
public class CreateFindMateBoardDto {

    @ApiModelProperty(value = "글 제목")
    private String title;

    @ApiModelProperty(value = "여행지")
    private String destination;

    @ApiModelProperty(value = "모집 인원수")
    private int recruitment;

    @ApiModelProperty(value = "여행 시작일")
    private String startDate;

    @ApiModelProperty(value = "여행 종료일")
    private String endDate;

    @ApiModelProperty(value = "글 내용")
    private String content;
    
}
