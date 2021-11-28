package com.tripper.repository;

import com.tripper.domain.user.UserInfo;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.NoResultException;

/**
 * @author HanJiyoung
 * 회원 관련 레포지토리 클래스
 */
@Repository
@RequiredArgsConstructor
@Slf4j
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

    public String checkEmailAuthCode(String authCode, String memId) {

        Object rslt = em.createQuery("select u.emailAuthCode from UserInfo u where u.memId = :memId", UserInfo.class)
                .setParameter("memId", memId)
                .getSingleResult();
        String dbAuthCode = rslt.toString();
        log.info("db에 저장돼있던 이메일 인증 코드: ", dbAuthCode);

        /* 일치 여부에 따라 return */
        if (authCode.equals(dbAuthCode)) {
            return "ok";
        }
        return "fail";
    }

}
