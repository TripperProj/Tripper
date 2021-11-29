package com.tripper.domain.budget;

import com.tripper.domain.trip.Trip;
import lombok.Getter;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Getter
public class Budget {

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "budget_id")
    private Long id;

    private int totalAmount;

    @OneToMany(mappedBy = "budget", cascade = CascadeType.ALL)
    private List<Category> categories = new ArrayList<>();

    @OneToOne
    @JoinColumn(name = "trip_id")
    private Trip trip;

    protected Budget () {
    }

    // Budget 생성자
    // category list 의 0번째 index 에 항상 기타 항목 추가
    public Budget (int totalAmount, Trip trip) {
        this.totalAmount = totalAmount;
        this.trip = trip;

        //연관관계 설정
        trip.setBudget(this);

        //기타·남은 예산 : categories.get(0)
        Category category = new Category("기타", totalAmount);
        category.setBudget(this);
        this.categories.add(category);
    }

    // 총 예산 수정
    public void updateTotalAmount (int newTotalAmount) {
        int amount = this.categories.get(0).getAmount();
        int additionalAmount = newTotalAmount - this.totalAmount;

        if (amount + additionalAmount < 0) {
            throw new IllegalArgumentException("예산을 줄일 수 없습니다.");
        }

        this.categories.get(0).updateAmount(amount + additionalAmount);
    }

    // 총 지출 금액
    public int getTotalExpenditure(){
        int totalExpenditure = 0;
        for (Category category : this.categories) {
            totalExpenditure += category.getExpenditure();
        }

        return totalExpenditure;
    }

    // 남은 금액
    public int getRemainTotalAmount() {
        return this.totalAmount - getTotalExpenditure();
    }


    // 카테고리 추가
    public void addCategory (Category category) {
        int amount = this.categories.get(0).getAmount();
        if (amount < category.getAmount()){
            throw new IllegalArgumentException("해당 카테고리 예산이 남은 예산을 초과합니다.");
        }
        this.categories.get(0).updateAmount(amount - category.getAmount());

        // 연관관계 설정
        category.setBudget(this);
        this.categories.add(category);
    }


    // 소비 항목 추가
    public void addItemToCategory(Long categoryId, Item item){
        Category targetCategory = findCategoryWithId(categoryId);

        targetCategory.addItem(item);
    }

    // 카테고리 예산 수정
    public void updateCategoryAmount (int newAmount, Long categoryId) {
        // 남은 예산
        int amount = this.categories.get(0).getAmount();

        Category targetCategory = findCategoryWithId(categoryId);

        int additionalAmount = newAmount - targetCategory.getAmount();
        if (amount < additionalAmount){
            throw new IllegalArgumentException("해당 카테고리 예산이 남은 예산을 초과합니다.");
        }

        // 남은 예산 및 해당 카테고리의 예산 수정
        this.categories.get(0).updateAmount(amount - additionalAmount);
        targetCategory.updateAmount(newAmount);
    }

    // 카테고리 이름 수정
    public void updateCategoryName (String name, Long categoryId) {
        Category targetCategory = findCategoryWithId(categoryId);
        targetCategory.updateName(name);
    }

    // 카테고리 삭제
    public void deleteCategory(Long categoryId){
        Category targetCategory = findCategoryWithId(categoryId);

        // 남은 예산에 지울 카테고리 예산을 더해줌
        this.categories.get(0).updateAmount(
                this.categories.get(0).getAmount() + targetCategory.getAmount());
        categories.remove(targetCategory);

        this.categories.remove(targetCategory);
    }

    // 항목 내용, 지출 수정
    public void updateItem(Long categoryId, Long itemId, String content, int expenditure) {
        Category targetCategory = findCategoryWithId(categoryId);

        targetCategory.findItemWithId(itemId).updateItem(content, expenditure);
    }

    // 항목 삭제
    public void deleteItem(Long categoryId, Long itemId){
        Category targetCategory = findCategoryWithId(categoryId);

        targetCategory.deleteItem(itemId);
    }

    // categoryId 로 category 탐색 + 예외 처리
    private Category findCategoryWithId(Long categoryId) {
        Category targetCategory = null;
        for (Category category : this.categories) {
            if (category.getId().equals(categoryId)) {
                targetCategory = category;
                break;
            }
        }

        if (targetCategory == null) {
            throw new IllegalArgumentException("존재하지 않는 카테고리 입니다.");
        }
        return targetCategory;
    }
}