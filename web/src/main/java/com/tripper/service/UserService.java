package com.tripper.service;

import com.tripper.domain.user.UserInfo;
import com.tripper.domain.user.UserRepository;
import com.tripper.dto.UserInfoDto;
import com.tripper.repository.MemberRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author HanJiyoung
 * 회원 관련 서비스 클래스
 */
@RequiredArgsConstructor
@Service
@Slf4j
public class UserService implements UserDetailsService {

    private final UserRepository userRepository;
    private final MemberRepository memberRepository;

    @Override
    public UserInfo loadUserByUsername(String memId) throws UsernameNotFoundException {
        return userRepository.findByMemId(memId)
                .orElseThrow(() -> new UsernameNotFoundException(memId));
    }

    /**
     * 회원 db에 insert하는 함수
     */
    public Long save(UserInfoDto infoDto) {

        BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();
        infoDto.setPassword(encoder.encode(infoDto.getPassword()));

        return userRepository.save(UserInfo.builder()
                .memId(infoDto.getMemId())
                .name(infoDto.getName())
                .phone(infoDto.getPhone())
                .email(infoDto.getEmail())
                .nickname(infoDto.getNickname())
                .password(infoDto.getPassword())
                .auth(infoDto.getAuth()).build()).getId();
    }

    /**
     * 아이디 중복체크 하는 함수
     */
    public UserInfo checkMemIdExists(String memId) {

        UserInfo userInfo = new UserInfo();
        userInfo = memberRepository.findUserByMemId(memId);

        if(userInfo != null) {
            return userInfo;
        }
        else {
            return null;
        }

    }

    /**
     * 회원 전체 조회하는 함수
     */
    public List<UserInfo> findAllUsers() {
        return userRepository.findAll();
    }

    /**
     * 회원 1명 조회하는 함수
     */
    public UserInfo findUserByMemId(String memId) {
        return memberRepository.findUserByMemId(memId);
    }

}
