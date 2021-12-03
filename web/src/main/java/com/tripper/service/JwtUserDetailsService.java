package com.tripper.service;

import com.tripper.domain.user.User;
import com.tripper.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

/**
 * db에서 UserDetail을 얻어와서 AuthenticationManager에게 제공하는 역할 수행
 *
 */
@Service
@RequiredArgsConstructor
public class JwtUserDetailsService implements UserDetailsService {

    private final UserRepository userRepository;

    @Override
    public User loadUserByUsername(String memId) throws UsernameNotFoundException {
        User user = userRepository.findByMemId(memId);
        return user;
    }
}