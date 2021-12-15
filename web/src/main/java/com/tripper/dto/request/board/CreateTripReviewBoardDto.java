package com.tripper.dto.request.board;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;

@ApiModel(value = "게시글 등록 폼 Request DTO")
@Getter @Setter
@RequiredArgsConstructor
public class CreateTripReviewBoardDto {

    @ApiModelProperty(value = "글 제목")
    private String title;

    @ApiModelProperty(value = "글 내용")
    private String content;
    
}
