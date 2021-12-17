package com.tripper.dto.response.budget;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter @Setter
@AllArgsConstructor
public class GetCategoryDto {
    private Long id;
    private String name;
    private int amount;
    private int remainAmount;
}
