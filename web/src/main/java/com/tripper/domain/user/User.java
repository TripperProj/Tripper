package com.tripper.domain.user;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.tripper.domain.BaseTimeEntity;
import com.tripper.domain.board.Board;
import com.tripper.domain.hotel.Hotel;
import com.tripper.domain.hotel.Reservation;
import com.tripper.dto.request.user.CreateUserDto;
import com.tripper.dto.request.user.UpdateUserDto;
import lombok.*;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import javax.persistence.*;
import java.util.*;

import static javax.persistence.GenerationType.IDENTITY;

/**
 * @author HanJiyoung
 * 회원 정보 엔티티 클래스
 */
@Entity
@Getter @Setter
@NoArgsConstructor
public class User extends BaseTimeEntity implements UserDetails {

    @Id @GeneratedValue(strategy = IDENTITY)
    @Column(name = "user_id")
    private Long id; // PK

    @Column(unique = true, nullable = false)
    private String memId;

//    @Column(nullable = false)
    private String password;

//    @Column(nullable = false)
    private String name;

//    @Column(nullable = false)
    private String phone;

//    @Column(nullable = false)
    private String email;

//    @Column(nullable = false)
    private String nickname;

//    @Column(nullable = false)
    @Enumerated(EnumType.STRING)
    private Role auth;

//    @Column
    private String emailAuthCode;

    @JsonIgnore
    @OneToMany(mappedBy = "user")
    private List<Board> boards = new ArrayList<>();

    @JsonIgnore
    @OneToMany(mappedBy = "user")
    private List<Hotel> hotels = new ArrayList<>();

    @JsonIgnore
    @OneToMany(mappedBy = "user")
    private List<Reservation> resrvations = new ArrayList<>();

    @Builder
    public User(CreateUserDto createUserDto, Role auth) {
        this.memId = createUserDto.getMemId();
        this.password = createUserDto.getPassword();
        this.name = createUserDto.getName();
        this.phone = createUserDto.getPhone();
        this.email = createUserDto.getEmail();
        this.nickname = createUserDto.getEmail();
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
    public void updateUser(UpdateUserDto updateUserDto) {
        this.password = updateUserDto.getPassword();
        this.phone = updateUserDto.getPhone();
        this.email = updateUserDto.getEmail();
        this.nickname = updateUserDto.getNickname();
        this.auth = updateUserDto.getAuth();
    }
}
