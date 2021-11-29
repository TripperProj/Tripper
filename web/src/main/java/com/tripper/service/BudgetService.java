package com.tripper.service;

import com.tripper.domain.budget.Budget;
import com.tripper.domain.budget.Category;
import com.tripper.domain.budget.Item;
import com.tripper.domain.trip.Trip;
import com.tripper.dto.request.*;
import com.tripper.repository.BudgetRepository;
import com.tripper.repository.TripRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class BudgetService {

    private final TripRepository tripRepository;
    private final BudgetRepository budgetRepository;

    public void createBudget (CreateOrUpdateBudgetDto dto, Long tripId){
        Trip trip = tripRepository.findById(tripId)
                .orElseThrow(() -> new IllegalArgumentException("존재하지 않는 여행입니다."));
        Budget budget = new Budget(dto.getTotalAmount(), trip);

        budgetRepository.save(budget);
    }

    public void updateBudget (CreateOrUpdateBudgetDto dto, Long budgetId) {
        Budget budget = findBudgetById(budgetId);

        budget.updateTotalAmount(dto.getTotalAmount());
    }

    public void addCategory (AddCategoryDto dto, Long budgetId) {
        Budget budget = findBudgetById(budgetId);

        Category category = new Category(dto.getName(), dto.getAmount());
        budget.addCategory(category);
    }

    public void updateCategoryAmount (UpdateCategoryAmountDto dto, Long budgetId, Long categoryId) {
        Budget budget = findBudgetById(budgetId);

        budget.updateCategoryAmount(dto.getAmount(), categoryId);
    }

    public void updateCategoryName (UpdateCategoryNameDto dto, Long budgetId, Long categoryId) {
        Budget budget = findBudgetById(budgetId);

        budget.updateCategoryName(dto.getName(), categoryId);
    }

    public void deleteCategory (Long budgetId, Long categoryId) {
        Budget budget = findBudgetById(budgetId);

        budget.deleteCategory(categoryId);
    }

    public void addItem (AddItemDto dto, Long budgetId, Long categoryId) {
        Budget budget = findBudgetById(budgetId);

        Item item = new Item(dto.getContent(), dto.getExpenditure());
        budget.addItemToCategory(categoryId, item);
    }

    public void updateItem (UpdateItemDto dto, Long budgetId, Long categoryId, Long itemId) {
        Budget budget = findBudgetById(budgetId);

        budget.updateItem(categoryId, itemId, dto.getContent(), dto.getExpenditure());
    }

    public void deleteItem (Long budgetId, Long categoryId, Long itemId) {
        Budget budget = findBudgetById(budgetId);

        budget.deleteItem(categoryId, itemId);
    }

    private Budget findBudgetById(Long budgetId) {
        return budgetRepository.findById(budgetId)
                .orElseThrow(() -> new IllegalArgumentException("존재하지 않는 예산입니다."));
    }
}
