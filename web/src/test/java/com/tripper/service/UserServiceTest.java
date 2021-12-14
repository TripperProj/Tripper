package com.tripper.service;

import com.tripper.domain.user.Role;
import com.tripper.domain.user.User;
import com.tripper.dto.request.user.CreateUserDto;
import com.tripper.repository.UserRepository;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.transaction.annotation.Transactional;

import static org.junit.Assert.*;

@RunWith(SpringRunner.class)
@SpringBootTest
@Transactional
public class UserServiceTest {

    @Autowired private UserRepository userRepository;

    @Test
    public void 회원가입() {
        // given
        CreateUserDto createUserDto = new CreateUserDto("user22", "0000", "홍길동", "010-6565-5655", "gildong@gmail.com", "길동2");
        BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();
        createUserDto.setPassword(encoder.encode(createUserDto.getPassword()));
        User user = new User(createUserDto, Role.ROLE_NOTCERTIFIED);

        // when
        Long createdId = userRepository.save(user).getId();

        // then
        Long findId = userRepository.findById(createdId).get().getId();
        assertEquals(createdId, findId);
    }
}