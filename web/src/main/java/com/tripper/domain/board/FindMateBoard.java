package com.tripper.domain.board;

import com.tripper.domain.user.User;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.extern.slf4j.Slf4j;

import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;

@Entity
@Slf4j
@Getter
@NoArgsConstructor
public class FindMateBoard extends Board{

    private String destination;
    private String startDate;
    private String endDate;
    private int recruitment;

    @Enumerated(EnumType.STRING)
    private RecruitmentStatus recruitmentStatus;

    /*글 등록 */
    public FindMateBoard(String title, String content, User user,
                         String destination, String startDate, String endDate, int recruitment) {
        super(title, content, user);
        this.destination = destination;
        this.startDate = startDate;
        this.endDate = endDate;
        this.recruitment = recruitment;
        this.recruitmentStatus = RecruitmentStatus.OPEN;
    }

    /* 글 수정 */
    public void updateBoard(String title, String content, String destination, int recruitment, String startDate, String endDate) {
        super.updateTitleAndContent(title, content);
        this.destination = destination;
        this.recruitment = recruitment;
        this.startDate = startDate;
        this.endDate = endDate;
    }
}

