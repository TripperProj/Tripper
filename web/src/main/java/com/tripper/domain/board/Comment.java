package com.tripper.domain.board;

import lombok.Getter;

import javax.persistence.*;

@Entity @Getter
public class Comment {

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "comment_id")
    private Long id;

    private String content;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "board_id")
    private TripReviewBoard board;

    protected Comment() {
    }

    public Comment(String content, TripReviewBoard board) {
        this.content = content;
        this.board = board;
    }
}

