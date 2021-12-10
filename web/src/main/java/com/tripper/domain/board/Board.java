package com.tripper.domain.board;

import com.tripper.domain.user.User;
import lombok.Getter;

import javax.persistence.*;
import java.time.LocalDateTime;

@Entity
@Getter
@Inheritance(strategy = InheritanceType.SINGLE_TABLE)
@DiscriminatorColumn(name = "dtype")
public class Board {

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "board_id")
    private Long id;

    private String title;
    private String content;
    private int hits;
    private int likes;
    private LocalDateTime createdTime;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id")
    private User user;

    protected Board() {
    }

    public Board(String title, String content, User user) {
        this.title = title;
        this.content = content;
        this.user = user;
        this.likes = 0;
        this.hits = 0;
        this.createdTime = LocalDateTime.now();
    }

    public void updateTitleAndContent(String title, String content) {
        this.title = title;
        this.content = content;
    }

    public void addHits() {
        this.hits++;
    }


    public void addLikes(){
        this.likes++;
    }

    public void subtractLikes(){
        this.likes--;
    }
}

