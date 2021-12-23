package com.tripper.dto.request.budget;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.NotBlank;

@Getter @Setter
@AllArgsConstructor
public class UpdateCategoryNameDto {
    @NotBlank(message = "예산은 공백이 아닌 문자만 가능합니다.")
    private String name;
}
