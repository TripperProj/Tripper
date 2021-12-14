package com.tripper.service;

import com.tripper.domain.user.Role;
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
@Service
@Transactional(readOnly = true)
@RequiredArgsConstructor
@Slf4j
public class UserService implements UserDetailsService {

    private final UserRepository userRepository;

    @Override
    public User loadUserByUsername(String memId) throws UsernameNotFoundException {
        return userRepository.findById(memId)
                .orElseThrow(() -> new UsernameNotFoundException(memId));
    }

    /**
     * 회원가입
     */
    @Transactional
    public Long createUser(CreateUserDto createUserDto) {

        BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();
        createUserDto.setPassword(encoder.encode(createUserDto.getPassword()));
        User user = new User(createUserDto, Role.ROLE_NOTCERTIFIED);

        return userRepository.save(user).getId();

    }

    /**
     * 아이디 중복체크
     */
    public User checkMemIdExists(String memId) {

        User user = userRepository.findByMemId(memId);
        return user;
//        if(user != null) {
//            throw new IllegalStateException("이미 존재하는 아이디입니다.");
//        }
//        String rslt = "";
//
//        if(user != null) {
//            rslt = "fail";
//        }
//        else if(user == null) {
//            rslt = "ok";
//        }
//        return rslt;
    }

    /**
     * 회원 전체 조회
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
     * 회원 1명 조회
     */
    public User findUserByMemId(String memId) {
        return userRepository.findByMemId(memId);
    }

    /**
     * 회원 정보 수정
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
     * 회원 탈퇴
     */
    @Transactional
    public void deleteUserById(Long user_id) {
        userRepository.deleteById(user_id);
    }

}
