package com.tripper.dto.request;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;

@ApiModel(value = "게시글 폼에 입력한 정보를 갖고있는 클래스")
@Getter @Setter
@RequiredArgsConstructor
public class CreateBoardDto {

    @ApiModelProperty(value = "글 제목")
    private String title;

    @ApiModelProperty(value = "여행지")
    private String destination;

    @ApiModelProperty(value = "모집 인원수")
    private int recruitment;

    @ApiModelProperty(value = "글 내용")
    private String content;
    
}
