package com.tripper.domain.board;

import com.tripper.domain.user.UserInfo;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;

import java.time.LocalDateTime;
import java.util.List;

import static javax.persistence.FetchType.LAZY;
import static javax.persistence.GenerationType.IDENTITY;

@Entity
@Getter @Setter
public class BoardInfo {

    @Id @GeneratedValue(strategy = IDENTITY)
    @Column(name = "board_id")
    private Long id; // PK

    private String title;
    private String destination;
    private String recruitment;
    private String content;

    @Enumerated(EnumType.STRING)
    private BoardStatus status;

    private int hits;
    private int likes;
    private LocalDateTime dateTime;

    @ManyToOne(fetch = LAZY)
    @JoinColumn(name = "user_id")
    private UserInfo userInfo;

    /* 연관관계 메서드 */
    /**
     * 사용자 정보 세팅
     * @param userInfo
     */
    public void setUserInfo(UserInfo userInfo) {
        this.userInfo = userInfo;
        userInfo.getBoards().add(this);
    }

    /* 생성 메서드 */
    /**
     * 글 등록하는 함수
    */
    public static BoardInfo createBoard(BoardForm form, UserInfo userInfo) {
        BoardInfo boardInfo = new BoardInfo();
        boardInfo.setUserInfo(userInfo);
        boardInfo.setTitle(form.getTitle());
        boardInfo.setDestination(form.getDestination());
        boardInfo.setRecruitment(form.getRecruitment());
        boardInfo.setContent(form.getContent());
        boardInfo.setStatus(BoardStatus.OPEN);
        boardInfo.setHits(0);
        boardInfo.setLikes(0);
        boardInfo.setDateTime(LocalDateTime.now());
        return boardInfo;
    }

}
