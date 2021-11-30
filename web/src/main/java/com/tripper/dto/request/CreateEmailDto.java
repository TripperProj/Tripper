package com.tripper.dto.request;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter @Setter
@NoArgsConstructor
public class CreateEmailDto {

    private String address;
    private String title;
    private String message;

}