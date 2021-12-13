package com.tripper.domain.board;

import com.tripper.domain.BaseTimeEntity;
import com.tripper.domain.user.User;
import com.tripper.dto.request.board.CreateBoardDto;
import com.tripper.dto.request.board.UpdateBoardDto;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.extern.slf4j.Slf4j;

import javax.persistence.*;

import java.time.LocalDateTime;

import static javax.persistence.FetchType.LAZY;
import static javax.persistence.GenerationType.IDENTITY;

@Entity
@Getter @Setter
@Slf4j
@NoArgsConstructor
public class Board extends BaseTimeEntity {

    @Id @GeneratedValue(strategy = IDENTITY)
    @Column(name = "board_id")
    private Long id; // PK

    private String title;
    private String destination;
    private String startDate;
    private String endDate;
    private int recruitment;
    private String content;

    @Enumerated(EnumType.STRING)
    private BoardStatus status;

    private int hits;
    private int likes;

    @ManyToOne(fetch = LAZY)
    @JoinColumn(name = "user_id")
    private User user;

    /*글 등록 */
    public Board(CreateBoardDto dto, User user) {
        this.title = dto.getTitle();
        this.destination = dto.getDestination();
        this.startDate = dto.getStartDate();
        this.endDate = dto.getEndDate();
        this.recruitment = dto.getRecruitment();
        this.content = dto.getContent();
        this.status = BoardStatus.OPEN;
        this.hits = 0;
        this.likes = 0;
        this.user = user;
        user.getBoards().add(this);
    }

    /* 글 수정 */
    public void updateBoard(UpdateBoardDto dto) {
        this.title = dto.getTitle();
        this.destination = dto.getDestination();
        this.recruitment = dto.getRecruitment();
        this.startDate = dto.getStartDate();
        this.endDate = dto.getEndDate();
        this.content = dto.getContent();
    }

    /* 조회수 증가 */
    public void addHits(int hit) {
        this.hits += hit;
    }

    /* 좋아요수 증가 */
    public void addLikes(int likes) {
        this.likes += likes;
    }

    /* 좋아요수 감소 */
    public void subtractLikes(int likes) {
        this.likes -= likes;
    }

}
