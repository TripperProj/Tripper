package com.tripper.repository;

import com.tripper.domain.board.BoardInfo;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;

@Repository
@RequiredArgsConstructor
public class BoardRepository {

    private final EntityManager em;

    public void save(BoardInfo boardInfo) {
        em.persist(boardInfo);
    }

}
