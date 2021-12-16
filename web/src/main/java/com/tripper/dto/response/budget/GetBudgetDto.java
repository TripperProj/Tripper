package com.tripper.dto.response.budget;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter @Setter
@AllArgsConstructor
public class GetBudgetDto {
    private Long id;
    private int totalAmount;
    private int totalExpenditure;
    private int remainTotalAmount;

    private List<GetCategoryDto> categories;
}