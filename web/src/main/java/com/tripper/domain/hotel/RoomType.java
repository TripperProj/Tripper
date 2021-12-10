package com.tripper.domain.hotel;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;

import java.util.ArrayList;
import java.util.List;

import static javax.persistence.FetchType.LAZY;
import static javax.persistence.GenerationType.IDENTITY;

@NoArgsConstructor
@Entity
@Getter @Setter
public class RoomType {

    @Id
    @GeneratedValue(strategy = IDENTITY)
    @Column(name = "roomtype_id")
    private Long id; // PK

    private String name;
    private int standardCapacity;
    private int maxCapacity;

    @ManyToOne(fetch = LAZY)
    @JoinColumn(name = "hotel_id")
    private Hotel hotel;

    @JsonIgnore
    @OneToMany(mappedBy = "roomType")
    private List<Room> rooms = new ArrayList<>();

    public RoomType(String name, int standardCapacity, int maxCapacity, Hotel hotel) {
        this.name = name;
        this.standardCapacity = standardCapacity;
        this.maxCapacity = maxCapacity;
        this.hotel = hotel;
        hotel.getRoomTypes().add(this);
    }

}
