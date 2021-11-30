package com.tripper.service;

import com.tripper.domain.user.User;
import com.tripper.dto.request.CreateUserDto;
import com.tripper.repository.UserRepository;
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

    @Override
    public User loadUserByUsername(String memId) throws UsernameNotFoundException {
        return userRepository.findById(memId)
                .orElseThrow(() -> new UsernameNotFoundException(memId));
    }

    /**
     * 회원 db에 insert하는 함수
     */
    public Long save(CreateUserDto createUserDto) {

        BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();
        createUserDto.setPassword(encoder.encode(createUserDto.getPassword()));

        return userRepository.save(User.builder()
                .memId(createUserDto.getMemId())
                .name(createUserDto.getName())
                .phone(createUserDto.getPhone())
                .email(createUserDto.getEmail())
                .nickname(createUserDto.getNickname())
                .password(createUserDto.getPassword())
                .auth(createUserDto.getAuth()).build()).getId();
    }

    /**
     * 아이디 중복체크 하는 함수
     */
    public User checkMemIdExists(String memId) {

        User user = userRepository.findByMemId(memId);

        if(user != null) {
            return user;
        }
        else {
            return null;
        }

    }

    /**
     * 회원 전체 조회하는 함수
     */
    public List<User> findAllUsers() {
        return userRepository.findAll();
    }

    /**
     * 회원 1명 조회하는 함수
     */
    public User findUserByMemId(String memId) {
        return userRepository.findByMemId(memId);
    }

}
