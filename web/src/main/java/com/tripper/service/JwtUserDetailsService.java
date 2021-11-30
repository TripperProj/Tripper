package com.tripper.service;

import com.tripper.domain.user.User;
import com.tripper.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.sql.Array;
import java.util.ArrayList;
import java.util.Collection;
import java.util.NoSuchElementException;

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

//        Collection<SimpleGrantedAuthority> roles = new ArrayList<SimpleGrantedAuthority>();
//        roles.add(new SimpleGrantedAuthority("ROLE_USER"));
//        User user = userRepository.findById(memId)
//                .orElseThrow(() -> new NoSuchElementException());
//        return new User(user.getMemId(), user.getPassword(), roles);
//
////        return new User(user.getMemId(), user.getPassword(), new ArrayList<>());
////        String password = user.getPassword();
////
//        if ("user_id".equals(memId)) {
//            return new User("user_id", "$2a$10$jCvWm3NXDRFs/EfuI4h4.u0ZxNocv.ZkgEy6qbjVXrfQ5.KzLfhAe", new ArrayList<>());
//        } else {
//            throw new UsernameNotFoundException("User not found with memId: " + memId);
//        }

    }
}