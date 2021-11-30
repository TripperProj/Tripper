package com.tripper.service;

import com.tripper.domain.budget.Budget;
import com.tripper.domain.budget.Category;
import com.tripper.domain.budget.Item;
import com.tripper.domain.trip.Trip;
import com.tripper.dto.request.*;
import com.tripper.dto.response.GetBudgetDto;
import com.tripper.dto.response.GetBudgetItemDto;
import com.tripper.dto.response.GetBudgetItemsDto;
import com.tripper.dto.response.GetCategoryDto;
import com.tripper.repository.BudgetRepository;
import com.tripper.repository.TripRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class BudgetService {

    private final TripRepository tripRepository;
    private final BudgetRepository budgetRepository;

    @Transactional(readOnly = false)
    public GetBudgetDto getBudgetInfo(Long tripId) {
        Trip trip = tripRepository.findById(tripId)
                .orElseThrow(() -> new IllegalArgumentException("존재하지 않는 여행입니다."));

        Budget budget = trip.getBudget();

        List<Category> categories = budget.getCategories();

        List<GetCategoryDto> categoryDtoList = new ArrayList<>();
        for (Category category : categories) {
            GetCategoryDto getCategoryDto = new GetCategoryDto(category.getId(),
                    category.getName(), category.getAmount(), category.getRemainAmount());
            categoryDtoList.add(getCategoryDto);
        }

        return new GetBudgetDto(budget.getId(), budget.getTotalAmount(),
                budget.getTotalExpenditure(), budget.getRemainTotalAmount(), categoryDtoList);
    }

    @Transactional(readOnly = false)
    public GetBudgetItemsDto getBudgetItemsInCategory(Long budgetId, Long categoryId) {
        Budget budget = findBudgetById(budgetId);
        Category category = budget.findCategoryWithId(categoryId);

        List<GetBudgetItemDto> budgetItemDtos = new ArrayList<>();
        for (Item item : category.getItems()) {
            GetBudgetItemDto getBudgetItemDto = new GetBudgetItemDto(item.getId(), item.getContent(), item.getExpenditure());
            budgetItemDtos.add(getBudgetItemDto);
        }

        return new GetBudgetItemsDto(budgetItemDtos);
    }

    public void createBudget (CreateOrUpdateBudgetDto dto, Long tripId) {
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
