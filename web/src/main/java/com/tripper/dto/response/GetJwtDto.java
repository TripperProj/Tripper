package com.tripper.dto.response;

import io.swagger.annotations.ApiModel;
import lombok.AllArgsConstructor;
import lombok.Getter;

import java.io.Serializable;

@ApiModel(value = "반환할 토큰 정보를 갖고 있는 클래스")
@Getter
@AllArgsConstructor
public class GetJwtDto implements Serializable {

    private static final long serialVersionUID = -8091879091924046844L;
    private final String jwttoken;

    public String getToken() {
        return this.jwttoken;
    }
}
