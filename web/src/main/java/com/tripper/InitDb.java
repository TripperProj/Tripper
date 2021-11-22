package com.tripper;

import com.tripper.domain.user.UserInfo;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.PostConstruct;
import javax.persistence.EntityManager;

/**
 * @author HanJiyoung
 * 스프링 부트 실행시 조회용 샘플 데이터를 넣어주는 클래스
 */
@Component
@RequiredArgsConstructor
@Slf4j
public class InitDb {

    private final InitService initService;

    @PostConstruct
    public void init() {
        initService.dbInit();
    }

    @Component
    @Transactional
    @RequiredArgsConstructor
    static class InitService {

        private final EntityManager em;

        public void dbInit() {
            log.info("init1" + this.getClass());
            BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();
            UserInfo userInfo = createUser("user1", encoder.encode("0000"), "김철수", "010-1111-2222", "kim@naver.com", "여행러", "ROLE_USER");
            em.persist(userInfo);
        }

        private UserInfo createUser(String memId, String password, String name, String phone, String email, String nickname, String auth) {
            UserInfo userInfo = new UserInfo(memId, password, name, phone, email, nickname, auth);
            return userInfo;
        }
    }
}
