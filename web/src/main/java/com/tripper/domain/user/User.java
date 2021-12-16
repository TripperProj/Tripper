package com.tripper.domain.user;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.tripper.domain.BaseTimeEntity;
import com.tripper.domain.board.Board;
import com.tripper.domain.hotel.Hotel;
import com.tripper.domain.hotel.Reservation;
import lombok.*;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import javax.persistence.*;
import java.util.*;

import static javax.persistence.GenerationType.IDENTITY;
import static lombok.AccessLevel.PROTECTED;

/**
 * @author HanJiyoung
 * 회원 정보 엔티티 클래스
 */
@Entity
@Getter
@NoArgsConstructor(access = PROTECTED)
@Slf4j
public class User extends BaseTimeEntity implements UserDetails {

    @Id @GeneratedValue(strategy = IDENTITY)
    @Column(name = "user_id")
    private Long id;

    @Column(unique = true, nullable = false)
    private String memId;

    private String password;
    private String name;
    private String phone;
    private String email;
    private String nickname;

    @Enumerated(EnumType.STRING)
    private Role auth;

    private String emailAuthCode;

    @JsonIgnore
    @OneToMany(mappedBy = "user")
    private List<Board> boards = new ArrayList<>();

    @JsonIgnore
    @OneToMany(mappedBy = "user")
    private List<Hotel> hotels = new ArrayList<>();

    @JsonIgnore
    @OneToMany(mappedBy = "user")
    private List<Reservation> reservations = new ArrayList<>();

    @Builder
    public User(String memId, String password, String name, String phone, String email, String nickname, Role auth) {
        this.memId = memId;
        this.password = password;
        this.name = name;
        this.phone = phone;
        this.email = email;
        this.nickname = nickname;
        this.auth = auth;
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {

        Set<GrantedAuthority> roles = new HashSet<>();
        for(String role : auth.toString().split("_")) {
            roles.add(new SimpleGrantedAuthority(role));
        }
        return roles;

    }

    @Override
    public String getUsername() {
        return memId;
    }

    @Override
    public String getPassword() {
        return this.password;
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return true;
    }

    /* 회원정보 수정 */
    public void updateUserInfo(String password, String phone) {
        this.password = password;
        this.phone = phone;
    }

    /* 권한 수정*/
    public void updateAuth(Role auth) {
        this.auth = auth;
    }

    /* 이메일 인증 코드 저장 */
    public void setEmailAuthCode(String emailAuthCode) {
        this.emailAuthCode = emailAuthCode;
    }

}
