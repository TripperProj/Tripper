package com.tripper.dto;

import lombok.Getter;
import lombok.Setter;

/**
 * @author HanJiyoung
 * 회원 가입 폼에 입력한 정보 들어있는 클래스
 */
@Getter @Setter
public class UserInfoDto {

    private String memId;
    private String password;
    private String name;
    private String phone;
    private String email;
    private String nickname;
    private String auth;

}
