package com.tripper.domain.hotel;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

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
}
