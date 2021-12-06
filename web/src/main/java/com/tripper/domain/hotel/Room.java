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
public class Room {

    @Id
    @GeneratedValue(strategy = IDENTITY)
    @Column(name = "room_id")
    private Long id; // PK

    private int roomNum;
    private int price;

    @ManyToOne(fetch = LAZY)
    @JoinColumn(name = "roomtype_id")
    private RoomType roomType;

    @JsonIgnore
    @OneToMany(mappedBy = "room")
    private List<Reservation> reservations = new ArrayList<>();

    public Room(int roomNum, int price, RoomType roomType) {
        this.roomNum = roomNum;
        this.price = price;
        this.roomType = roomType;
        roomType.getRooms().add(this);
    }
}
