package com.tripper.dto.request;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Positive;

@Getter @Setter
@AllArgsConstructor
public class AddCategoryDto {
    @NotBlank(message = "예산은 공백이 아닌 문자만 가능합니다.")
    private String name;
    @Positive(message = "예산은 양수만 가능합니다.")
    private int amount;
}
