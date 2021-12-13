package com.tripper.dto.response;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter @Setter
@AllArgsConstructor
public class GetBudgetItemsDto {
    private List<GetBudgetItemDto> items;
}
