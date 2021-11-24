package com.tripper.repository;

import com.tripper.domain.board.BoardInfo;
import com.tripper.domain.user.UserInfo;
import lombok.RequiredArgsConstructor;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import java.util.List;

/**
 * @author HanJiyoung
 * 회원 관련 컨트롤러 클래스
 */
@Repository
@RequiredArgsConstructor
public class MemberRepository {

    private final EntityManager em;

    public List<UserInfo> findAll() {
        return em.createQuery("select u from UserInfo u", UserInfo.class)
                .getResultList();
    }

    public UserInfo findUserByMemId(String memId) {
        return em.createQuery("select u from UserInfo u where u.memId = :memId", UserInfo.class)
                .setParameter("memId", memId)
                .getSingleResult();
    }

}
