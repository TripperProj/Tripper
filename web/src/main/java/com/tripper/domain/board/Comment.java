package com.tripper.domain.board;

import com.tripper.domain.user.User;
import lombok.Getter;
import org.hibernate.annotations.Fetch;

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

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id")
    private User user;

    protected Comment() {
    }

    public Comment(String content, TripReviewBoard board, User user) {
        this.content = content;
        this.board = board;
        this.user = user;
    }
}

