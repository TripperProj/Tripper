package com.tripper.dto.response.user;

import io.swagger.annotations.ApiModel;
import lombok.AllArgsConstructor;
import lombok.Getter;

import java.io.Serializable;

@ApiModel(value = "토큰 Response DTO")
@Getter
@AllArgsConstructor
public class GetJwtDto implements Serializable {

    private static final long serialVersionUID = -8091879091924046844L;
    private final String jwttoken;
    private String memId;

    public String getToken() {
        return this.jwttoken;
    }
}
