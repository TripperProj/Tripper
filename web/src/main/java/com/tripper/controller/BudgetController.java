package com.tripper.controller;

import com.tripper.dto.request.*;
import com.tripper.dto.response.GetBudgetDto;
import com.tripper.dto.response.GetBudgetItemsDto;
import com.tripper.service.BudgetService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@Api(tags = "예산관리 API")
@RestController
@RequestMapping("trips/{tripId}/budget")
@RequiredArgsConstructor
public class BudgetController {

    private final BudgetService budgetService;

    @ApiOperation(value = "예산 조회", notes = "예산을 조회합니다.", tags = "예산 API")
    @GetMapping
    public ResponseEntity<GetBudgetDto> getBudgetInfo (@PathVariable("tripId") Long tripId){

        GetBudgetDto budgetInfo = budgetService.getBudgetInfo(tripId);

        return ResponseEntity.ok().body(budgetInfo);
    }
    
    @ApiOperation(value = "예산 항목 조회", notes = "예산 항목을 카테고리 별로 조회합니다.", tags = "예산 API")
    @GetMapping("/{budgetId}/categories/{categoryId}")
    public ResponseEntity<GetBudgetItemsDto> getBudgetItemsInCategory (@PathVariable("budgetId") Long budgetId,
                                                                       @PathVariable("categoryId") Long categoryId) {

        GetBudgetItemsDto budgetItemsDtos = budgetService.getBudgetItemsInCategory(budgetId, categoryId);

        return ResponseEntity.ok().body(budgetItemsDtos);
    }

    @ApiOperation(value = "예산 생성", notes = "예산을 생성합니다.", tags = "예산 API")
    @PostMapping
    public ResponseEntity createBudget (@Valid @RequestBody CreateOrUpdateBudgetDto dto,
                                        @PathVariable("tripId") Long tripId) {

        budgetService.createBudget(dto, tripId);

        return ResponseEntity.ok().build();
    }

    @ApiOperation(value = "예산 수정", notes = "예산을 수정합니다.", tags = "예산 API")
    @PutMapping("/{budgetId}")
    public ResponseEntity updateBudget (@Valid @RequestBody CreateOrUpdateBudgetDto dto,
                                        @PathVariable("budgetId") Long budgetId) {

        budgetService.updateBudget(dto, budgetId);

        return ResponseEntity.ok().build();
    }

    @ApiOperation(value = "카테고리 생성", notes = "카테고리를 생성합니다.", tags = "예산 API")
    @PostMapping("/{budgetId}/categories")
    public ResponseEntity addCategory (@Valid @RequestBody AddCategoryDto dto,
                                       @PathVariable("budgetId") Long budgetId) {
        budgetService.addCategory(dto, budgetId);

        return ResponseEntity.ok().build();
    }

    @ApiOperation(value = "카테고리 예산 수정", notes = "카테고리의 예산을 수정합니다.", tags = "예산 API")
    @PutMapping("/{budgetId}/categories/{categoryId}/amount")
    public ResponseEntity updateCategoryAmount (@Valid @RequestBody UpdateCategoryAmountDto dto,
                                       @PathVariable("budgetId") Long budgetId,
                                       @PathVariable("categoryId") Long categoryId) {

        budgetService.updateCategoryAmount(dto, budgetId, categoryId);

        return ResponseEntity.ok().build();
    }

    @ApiOperation(value = "카테고리 이름 수정", notes = "카테고리의 이름을 수정합니다.", tags = "예산 API")
    @PutMapping("/{budgetId}/categories/{categoryId}/name")
    public ResponseEntity updateCategoryName (@Valid @RequestBody UpdateCategoryNameDto dto,
                                       @PathVariable("budgetId") Long budgetId,
                                       @PathVariable("categoryId") Long categoryId) {

        budgetService.updateCategoryName(dto, budgetId, categoryId);

        return ResponseEntity.ok().build();
    }

    @ApiOperation(value = "카테고리 삭제", notes = "카테고리의 삭제합니다.", tags = "예산 API")
    @DeleteMapping("/{budgetId}/categories/{categoryId}")
    public ResponseEntity deleteCategory (@PathVariable("budgetId") Long budgetId,
                                          @PathVariable("categoryId") Long categoryId) {

        budgetService.deleteCategory(budgetId, categoryId);

        return ResponseEntity.ok().build();
    }

    @ApiOperation(value = "예산 항목 생성", notes = "예산 항목을 생성합니다.", tags = "예산 API")
    @PostMapping("/{budgetId}/categories/{categoryId}/items")
    public ResponseEntity addItem (@Valid @RequestBody AddItemDto dto,
                                   @PathVariable("budgetId") Long budgetId,
                                   @PathVariable("categoryId") Long categoryId) {

        budgetService.addItem(dto, budgetId, categoryId);

        return ResponseEntity.ok().build();
    }

    @ApiOperation(value = "예산 항목 수정", notes = "예산 항목을 수정합니다.", tags = "예산 API")
    @PutMapping("/{budgetId}/categories/{categoryId}/items/{itemId}")
    public ResponseEntity updateItem (@Valid @RequestBody UpdateItemDto dto,
                                   @PathVariable("budgetId") Long budgetId,
                                   @PathVariable("categoryId") Long categoryId,
                                   @PathVariable("itemId") Long itemId) {

        budgetService.updateItem(dto, budgetId, categoryId, itemId);

        return ResponseEntity.ok().build();
    }

    @ApiOperation(value = "예산 항목 삭제", notes = "예산 항목을 삭제합니다.", tags = "예산 API")
    @DeleteMapping("/{budgetId}/categories/{categoryId}/items/{itemId}")
    public ResponseEntity deleteItem (@PathVariable("budgetId") Long budgetId,
                                      @PathVariable("categoryId") Long categoryId,
                                      @PathVariable("itemId") Long itemId) {

        budgetService.deleteItem(budgetId, categoryId, itemId);

        return ResponseEntity.ok().build();
    }
}
