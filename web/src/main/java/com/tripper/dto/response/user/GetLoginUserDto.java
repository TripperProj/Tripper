package com.tripper.dto.response.user;

import com.tripper.domain.user.Role;
import io.swagger.annotations.ApiModel;
import lombok.AllArgsConstructor;
import lombok.Getter;

@ApiModel(value = "로그인 Response DTO")
@Getter
@AllArgsConstructor
public class GetLoginUserDto {

    private static final long serialVersionUID = -8091879091924046844L;
    private final String jwttoken;
    private Long user_id;
    private String memId;
    private Role auth;

    public String getToken() {
        return this.jwttoken;
    }
}
