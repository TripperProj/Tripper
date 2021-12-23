package com.tripper.dto.request.user;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.io.Serializable;

@ApiModel(value = "로그인 Request DTO")
@Getter @Setter
@NoArgsConstructor
public class CreateJwtDto implements Serializable {

    private static final long serialVersionUID = 5926468583005150707L;
    
    @ApiModelProperty(value = "사용자 로그인 아이디")
    private String memId;

    @ApiModelProperty(value = "사용자 비밀번호")
    private String password;

}
