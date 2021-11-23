package com.tripper.domain.user;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface UserRepository extends JpaRepository<UserInfo, Long> {
    Optional<UserInfo> findByMemId(String memId); // 이메일 통해 회원 조회하기 위함
}
