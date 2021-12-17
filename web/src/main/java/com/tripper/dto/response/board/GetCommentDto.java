package com.tripper.dto.response.board;

import com.tripper.domain.board.Comment;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter @Setter
public class GetCommentDto {
    private Long id;
    private String content;
    private LocalDateTime createdTime;
    private String memId;

    public GetCommentDto(Comment comment) {
        this.id = comment.getId();
        this.content = comment.getContent();
        this.createdTime = comment.getCreatedTime();
        this.memId = comment.getUser().getMemId();
    }
}
