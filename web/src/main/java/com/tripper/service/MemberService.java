package com.tripper.service;

import com.tripper.domain.user.UserInfo;
import com.tripper.repository.MemberRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * @author HanJiyoung
 * 회원 관련 서비스 클래스
 */
@Service
@Transactional(readOnly = true)
@RequiredArgsConstructor
public class MemberService {

    private final MemberRepository memberRepository;

    /**
     * 회원 전체 조회하는 함수
     * @return 조회한 회원 목록
     */
    public List<UserInfo> findAllUsers() {
        return memberRepository.findAll();
    }
}
