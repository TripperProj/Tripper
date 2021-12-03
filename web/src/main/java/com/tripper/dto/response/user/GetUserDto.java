package com.tripper.dto.response.user;

import com.tripper.domain.user.Role;
import com.tripper.domain.user.User;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter @Setter
@AllArgsConstructor
public class GetUserDto {

    private Long id;
    private String memId;
    private String password;
    private String name;
    private String phone;
    private String email;
    private String nickname;
    private Role auth;
    private String emailAuthCode;

    public GetUserDto(User user) {
        this.id = user.getId();
        this.memId = user.getMemId();
        this.password = user.getPassword();
        this.name = user.getName();
        this.phone = user.getPhone();
        this.email = user.getEmail();
        this.nickname = user.getNickname();
        this.auth = user.getAuth();
        this.emailAuthCode = user.getEmailAuthCode();
    }
}
