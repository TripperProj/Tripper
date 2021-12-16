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
    public void createUser(CreateUserDto createUserDto) {

        BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();
        createUserDto.setPassword(encoder.encode(createUserDto.getPassword()));

        Role auth = Role.ROLE_NOTCERTIFIED;

        if(createUserDto.getMemId().equals("admin")) {
            auth = Role.ROLE_ADMIN;
        }

        User user = User.builder()
                .memId(createUserDto.getMemId())
                .password(createUserDto.getPassword())
                .name(createUserDto.getName())
                .phone(createUserDto.getPhone())
                .email(createUserDto.getEmail())
                .nickname(createUserDto.getNickname())
                .auth(auth)
                .build();

        userRepository.save(user);

    }

    /**
     * 아이디 중복체크
     */
    public Boolean checkMemIdExists(String memId) {

        User user = userRepository.findByMemId(memId);
        Boolean isExists;

        if(user != null) {
            isExists = true;
        }
        else {
            isExists = false;
        }

        return isExists;
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
        int total = getUserDtoList.size();

        return new GetUserListDto(getUserDtoList, total);

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
    public void updateUserInfo(Long userId, UpdateUserDto updateUserDto) {

        BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();
        updateUserDto.setPassword(encoder.encode(updateUserDto.getPassword()));

        User user = userRepository.findById(userId)
                .orElseThrow(() -> new IllegalArgumentException("존재하지 않는 회원 입니다."));

        String updatePassword = updateUserDto.getPassword();
        String updatePhone = updateUserDto.getPhone();

        user.updateUserInfo(updatePassword, updatePhone);
    }

    /**
     * 회원 탈퇴
     */
    @Transactional
    public void deleteUserById(Long userId) {
        userRepository.deleteById(userId);
    }

    /**
     * 회원 권한 변경
     */
    @Transactional
    public void updateAuth(Long userId, Role auth) {

        User user = userRepository.findById(userId)
                .orElseThrow(() -> new IllegalArgumentException("존재하지 않는 회원 입니다."));

        user.updateAuth(auth);
    }
}
