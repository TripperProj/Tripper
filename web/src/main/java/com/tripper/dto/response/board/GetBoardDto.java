package com.tripper.dto.response.board;

import com.tripper.domain.board.Board;
import com.tripper.domain.board.BoardStatus;
import io.swagger.annotations.ApiModel;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

@ApiModel(value = "여행메이트 게시판 글 Respose DTO")
@Getter @Setter
@AllArgsConstructor
public class GetBoardDto {

    private Long id;
    private String title;
    private String destination;
    private String startDate;
    private String endDate;
    private int recruitment;
    private String content;
    private BoardStatus status;
    private int hits;
    private int likes;
    private LocalDateTime dateTime;
    private String memId;

    public GetBoardDto(Board board) {
        this.id = board.getId();
        this.title = board.getTitle();
        this.destination = board.getDestination();
        this.startDate = board.getStartDate();
        this.endDate = board.getEndDate();
        this.recruitment = board.getRecruitment();
        this.content = board.getContent();
        this.status = board.getStatus();
        this.hits = board.getHits();
        this.likes = board.getLikes();
        this.dateTime = board.getDateTime();
        this.memId = board.getUser().getMemId();
    }

}
