package com.tripper.dto.response.budget;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter @Setter
@AllArgsConstructor
public class GetBudgetItemDto {
    private Long id;
    private String content;
    private int expenditure;
}
