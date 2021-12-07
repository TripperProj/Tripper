package com.tripper.dto.response.board;

import io.swagger.annotations.ApiModel;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;

@ApiModel(value = "여행메이트 게시판 글 리스트 Respose DTO")
@Getter @Setter
@AllArgsConstructor
public class GetBoardListDto {
    List<GetBoardDto> boards = new ArrayList<>();
}
