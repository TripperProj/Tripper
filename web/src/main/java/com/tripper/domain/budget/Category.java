package com.tripper.domain.budget;

import lombok.Getter;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Getter
public class Category {

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "category_id")
    private Long id;

    private String name;

    private int amount;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "budget_id")
    private Budget budget;

    @OneToMany(mappedBy = "category", cascade = CascadeType.ALL)
    private List<Item> items = new ArrayList<>();

    protected Category() {
    }

    public Category(String name, int amount) {
        this.name = name;
        this.amount = amount;
    }

    public void setBudget (Budget budget) {
        this.budget = budget;
    }

    public void updateName (String name) {
        this.name = name;
    }

    public void updateAmount(int newAmount){
        this.amount = newAmount;
    }

    public int getExpenditure(){
        int sum = 0;
        for (Item item : this.items) {
            sum += item.getExpenditure();
        }
        return sum;
    }

    public int getRemainAmount(){
        return amount - getExpenditure();
    }

    public void addItem(Item item){
        item.setCategory(this);
        items.add(item);
    }

    public Item findItemWithId(Long itemId){
        Item targetItem = null;
        for (Item item : this.items) {
            if (item.getId().equals(itemId)) {
                targetItem = item;
                break;
            }
        }

        if (targetItem == null) {
            throw new IllegalArgumentException("존재하지 않는 항목입니다.");
        }

        return targetItem;
    }

    public void deleteItem(Long itemId){
        if (!items.removeIf(item -> item.getId().equals(itemId))) {
            throw new IllegalArgumentException("존재하지 않는 항목입니다.");
        }
    }
}
