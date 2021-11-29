package com.tripper.config;

import com.tripper.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

/**
 * @author HanJiyoung
 * Security 관련 설정 클래스
 */
@RequiredArgsConstructor
@EnableWebSecurity
@Configuration
public class SecurityConfig extends WebSecurityConfigurerAdapter {

    private final UserService userService;

    /**
     * 인증을 무시할 경로 설정하는 함수
     */
    @Override
    public void configure(WebSecurity web) {
        web.ignoring().antMatchers("/css/**", "/js/**", "/img/**", "h2-console/**");
    }

    /**
     * http 관련 인증 설정하는 함수
     */
    @Override
    public void configure(HttpSecurity http) throws Exception {
        http
                .csrf().disable().headers().frameOptions().disable() // h2-console 화면 사용하기 위함
                .and()
                .authorizeRequests()
                .antMatchers("/auth", "/signup", "/user", "/login").permitAll() // 누구나 접근 가능
                .antMatchers("/").hasRole("USER") // USER, ADMIN 만 접근 가능
                .antMatchers("/admin").hasRole("ADMIN") // ADMIN 만 접근 가능
                .anyRequest().authenticated() // 나머지는 권한이 있기만 하면 접근 가능
                .and()
                .formLogin() // 로그인에 대한 설정
                .loginPage("/auth") // 로그인 페이지 링크
//                .loginProcessingUrl("/loginProcess")
//                .usernameParameter("username")
//                .passwordParameter("password")
                .defaultSuccessUrl("/") // 로그인 성공시 연결되는 주소
                .and()
                .logout() // 로그아웃 관련 설정
                .logoutSuccessUrl("/login") // 로그아웃 성공시 연결되는 주소
                .invalidateHttpSession(true) // 로그아웃시 저장해 둔 세션 날리기
        ;
    }

    /**
     * 로그인 시 필요한 정보 가져오는 함수
     * @param auth
     * @throws Exception
     */
    @Override
    public void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.userDetailsService(userService)
                .passwordEncoder(new BCryptPasswordEncoder());
    }
}
