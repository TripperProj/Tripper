package com.tripper.domain.budget;

import lombok.Getter;

import javax.persistence.*;
import java.time.LocalDateTime;

@Entity
@Getter
public class Item {

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "item_id")
    private Long id;

    private String content;

    private int expenditure;

    private LocalDateTime timeStamp;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "category_id")
    private Category category;

    protected Item() {
    }

    public Item(String content, int expenditure){
        this.content = content;
        this.expenditure = expenditure;
    }

    public void updateItem(String content, int expenditure){
        this.content = content;
        this.expenditure = expenditure;
    }

    public void setCategory(Category category){
        this.category = category;
    }
}