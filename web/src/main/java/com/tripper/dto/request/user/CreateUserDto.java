package com.tripper.dto.request.user;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@ApiModel(value = "회원가입 폼 Request DTO")
@Getter @Setter
@AllArgsConstructor
public class CreateUserDto {

    @ApiModelProperty(value = "로그인 시 사용할 id")
    private String memId;

    @ApiModelProperty(value = "비밀번호")
    private String password;

    @ApiModelProperty(value = "사용자 이름")
    private String name;

    @ApiModelProperty(value = "사용자 전화번호")
    private String phone;

    @ApiModelProperty(value = "사용자 이메일")
    private String email;

    @ApiModelProperty(value = "사용자 닉네임")
    private String nickname;

}
