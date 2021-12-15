package com.tripper.dto.request.board;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter @Setter
@AllArgsConstructor
public class CreateCommentDto {
    private String content;
}