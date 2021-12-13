package com.tripper.dto.response.board;

import com.tripper.domain.board.TripReviewBoard;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter @Setter
public class GetTripReviewHeaderDto {
    private Long id;
    private String title;
    private LocalDateTime createTime;
    private String memId;

    public GetTripReviewHeaderDto(TripReviewBoard board) {
        this.id = board.getId();
        this.title = board.getTitle();
        this.createTime = board.getCreatedTime();
        this.memId = board.getUser().getMemId();
    }
}
