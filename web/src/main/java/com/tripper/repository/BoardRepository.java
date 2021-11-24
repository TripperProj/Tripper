package com.tripper.repository;

import com.tripper.domain.board.BoardInfo;
import com.tripper.domain.user.UserInfo;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import java.util.List;

@Repository
@RequiredArgsConstructor
@Slf4j
public class BoardRepository {

    private final EntityManager em;

    public void save(BoardInfo boardInfo) {
        em.persist(boardInfo);
    }

    public List<BoardInfo> findAll() {
        return em.createQuery("select b from BoardInfo b", BoardInfo.class)
                .getResultList();
    }

    public BoardInfo findOne(Long board_id) {
        return em.find(BoardInfo.class, board_id);
    }

    public void deleteById(Long board_id) {
        em.remove(findOne(board_id));
    }

}
