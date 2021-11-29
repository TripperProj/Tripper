package com.tripper.domain.board;

import com.tripper.domain.user.User;
import com.tripper.dto.request.CreateBoardDto;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;

import java.time.LocalDateTime;

import static javax.persistence.FetchType.LAZY;
import static javax.persistence.GenerationType.IDENTITY;

@Entity
@Getter @Setter
public class Board {

    @Id @GeneratedValue(strategy = IDENTITY)
    @Column(name = "board_id")
    private Long id; // PK

    private String title;
    private String destination;
    private int recruitment;
    private String content;

    @Enumerated(EnumType.STRING)
    private BoardStatus status;

    private int hits;
    private int likes;
    private LocalDateTime dateTime;

    @ManyToOne(fetch = LAZY)
    @JoinColumn(name = "user_id")
    private User user;

    /* 연관관계 메서드 */
    /**
     * 사용자 정보 세팅
     * @param user
     */
    public void setUser(User user) {
        this.user = user;
        user.getBoards().add(this);
    }

    /* 생성 메서드 */
    /**
     * 글 등록하는 함수
    */
    public static Board createBoard(CreateBoardDto form, User user) {
        Board board = new Board();
        board.setUser(user);
        board.setTitle(form.getTitle());
        board.setDestination(form.getDestination());
        board.setRecruitment(form.getRecruitment());
        board.setContent(form.getContent());
        board.setStatus(BoardStatus.OPEN);
        board.setHits(0);
        board.setLikes(0);
        board.setDateTime(LocalDateTime.now());
        return board;
    }

}
