package com.tripper.dto.request;

import io.swagger.annotations.ApiModel;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.io.Serializable;

@ApiModel(value = "로그인 폼에 입력한 정보를 갖고있는 클래스")
@Getter @Setter
@NoArgsConstructor
@AllArgsConstructor
public class CreateJwtDto implements Serializable {

    private static final long serialVersionUID = 5926468583005150707L;
    private String memId;
    private String password;

}
