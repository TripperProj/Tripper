package com.tripper.domain.board;

import com.tripper.domain.user.User;

import javax.persistence.Entity;
import javax.persistence.OneToMany;
import java.util.ArrayList;
import java.util.List;

@Entity
public class TripReviewBoard extends Board {

    @OneToMany(mappedBy = "board")
    private List<Comment> comments = new ArrayList<>();

    protected TripReviewBoard() {
    }

    public TripReviewBoard(String title, String content, User user) {
        super(title, content, user);
    }

    public void addComment(Comment comment) {
        this.comments.add(comment);
    }

    public void removeComment(Long commentId){
        if(!comments.removeIf(comment -> comment.getId().equals(commentId))) {
            throw new IllegalArgumentException("존재하지 않는 댓글입니다");
        }
    }
}