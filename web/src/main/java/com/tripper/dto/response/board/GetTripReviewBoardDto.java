package com.tripper.dto.response.board;

import com.tripper.domain.board.Comment;
import com.tripper.domain.board.TripReviewBoard;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Getter @Setter
@AllArgsConstructor
public class GetTripReviewBoardDto {

    private Long id;
    private String title;
    private String content;
    private int hits;
    private int likes;
    private LocalDateTime dateTime;
    private String memId;

    private List<GetCommentDto> comments = new ArrayList<>();

    public GetTripReviewBoardDto(TripReviewBoard board) {
        this.id = board.getId();
        this.title = board.getTitle();
        this.content = board.getContent();
        this.hits = board.getHits();
        this.likes = board.getLikes();
        this.dateTime = board.getCreatedTime();
        this.memId = board.getUser().getMemId();

        for (Comment comment : board.getComments()) {
            this.comments.add(new GetCommentDto(comment));
        }
    }
}
