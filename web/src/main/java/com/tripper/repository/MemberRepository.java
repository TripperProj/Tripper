package com.tripper.repository;

import com.tripper.domain.board.BoardInfo;
import com.tripper.domain.user.UserInfo;
import lombok.RequiredArgsConstructor;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import java.util.List;

/**
 * @author HanJiyoung
 * 회원 관련 레포지토리 클래스
 */
@Repository
@RequiredArgsConstructor
public class MemberRepository {

    private final EntityManager em;

    public UserInfo findUserByMemId(String memId) {

        UserInfo userInfo = new UserInfo();

        try {
            userInfo = em.createQuery("select u from UserInfo u where u.memId = :memId", UserInfo.class)
                    .setParameter("memId", memId)
                    .getSingleResult();
        } catch (NoResultException e) {
            return null;
        }
        return userInfo;

    }

}
