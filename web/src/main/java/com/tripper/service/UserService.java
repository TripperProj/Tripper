package com.tripper.service;

import com.tripper.domain.user.UserInfo;
import com.tripper.domain.user.UserRepository;
import com.tripper.dto.UserInfoDto;
import lombok.RequiredArgsConstructor;
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
public class UserService implements UserDetailsService {

    private final UserRepository userRepository;

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
                .auth(infoDto.getAuth())
                .password(infoDto.getPassword()).build()).getId();
    }

    /**
     * 아이디 존재 여부 판단하는 함수
     */
    private void validateDuplicateMemId(UserInfo userInfo) {
        if(userRepository.findByMemId(userInfo.getMemId()) != null) {
            throw new IllegalStateException("이미 존재하는 아이디입니다.");
        }
    }
}
