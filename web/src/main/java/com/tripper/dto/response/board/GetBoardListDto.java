package com.tripper.dto.response.board;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;

@Getter @Setter
@AllArgsConstructor
public class GetBoardListDto {
    List<GetBoardDto> boards = new ArrayList<>();
}
