package com.tripper.dto.request.user;

import io.swagger.annotations.ApiModel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@ApiModel(value = "이메일 전송 Request DTO")
@Getter @Setter
@NoArgsConstructor
public class CreateEmailDto {

    private String address;
    private String title;
    private String message;

}