package com.tripper.dto.response.board;

import com.tripper.domain.board.FindMateBoard;
import com.tripper.domain.board.RecruitmentStatus;
import io.swagger.annotations.ApiModel;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

@ApiModel(value = "여행메이트 게시판 글 Response DTO")
@Getter @Setter
@AllArgsConstructor
public class GetFindMateBoardDto {

    private Long id;
    private String title;
    private String destination;
    private String startDate;
    private String endDate;
    private int recruitment;
    private String content;
    private RecruitmentStatus status;
    private int hits;
    private int likes;
    private LocalDateTime createTime;
    private String memId;

    public GetFindMateBoardDto(FindMateBoard board) {
        this.id = board.getId();
        this.title = board.getTitle();
        this.destination = board.getDestination();
        this.startDate = board.getStartDate();
        this.endDate = board.getEndDate();
        this.recruitment = board.getRecruitment();
        this.content = board.getContent();
        this.status = board.getRecruitmentStatus();
        this.hits = board.getHits();
        this.likes = board.getLikes();
        this.createTime = board.getCreatedTime();
        this.memId = board.getUser().getMemId();
    }

}
