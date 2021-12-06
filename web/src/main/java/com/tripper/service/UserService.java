package com.tripper.service;

import com.tripper.domain.user.User;
import com.tripper.dto.request.user.CreateUserDto;
import com.tripper.dto.request.user.UpdateUserDto;
import com.tripper.dto.response.user.GetUserDto;
import com.tripper.dto.response.user.GetUserListDto;
import com.tripper.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
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
    public String checkMemIdExists(String memId) {

        User user = userRepository.findByMemId(memId);
        String rslt = "";

        if(user != null) {
            rslt = "fail";
        }
        else if(user == null) {
            rslt = "ok";
        }
        return rslt;
    }

    /**
     * 회원 전체 조회하는 함수
     */
    public GetUserListDto findAllUsers() {

        List<User> users = userRepository.findAll();
        List<GetUserDto> getUserDtoList = new ArrayList<>();

        for (User user : users) {
            GetUserDto getUserDto = new GetUserDto(user);
            getUserDtoList.add(getUserDto);
        }

        return new GetUserListDto(getUserDtoList);

    }

    /**
     * 회원 1명 조회하는 함수
     */
    public User findUserByMemId(String memId) {
        return userRepository.findByMemId(memId);
    }

    /**
     * 회원 정보 수정 함수
     */
    @Transactional
    public void updateUser(Long user_id, UpdateUserDto updateUserDto) {

        BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();
        updateUserDto.setPassword(encoder.encode(updateUserDto.getPassword()));

        /* 엔티티 조회 */
        User user = userRepository.findById(user_id)
                .orElseThrow(() -> new IllegalArgumentException("존재하지 않는 회원 입니다."));

        /* 수정 내역으로 업데이트 */
        user.updateUser(updateUserDto);
    }

    /**
     * 회원 탈퇴 함수
     */
    @Transactional
    public void deleteUserById(Long user_id) {
        userRepository.deleteById(user_id);
    }

}
