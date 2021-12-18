package com.tripper.domain.hotel;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.tripper.domain.BaseTimeEntity;
import com.tripper.domain.Photo;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.extern.slf4j.Slf4j;

import javax.persistence.*;

import java.util.ArrayList;
import java.util.List;

import static javax.persistence.CascadeType.REMOVE;
import static javax.persistence.FetchType.LAZY;
import static javax.persistence.GenerationType.IDENTITY;

@NoArgsConstructor
@Entity
@Getter
public class RoomType extends BaseTimeEntity {

    @Id
    @GeneratedValue(strategy = IDENTITY)
    @Column(name = "roomtype_id")
    private Long id;

    private String name;
    private int standardCapacity;
    private int maxCapacity;

    @ManyToOne(fetch = LAZY)
    @JoinColumn(name = "hotel_id")
    private Hotel hotel;

    @JsonIgnore
    @OneToMany(mappedBy = "hotel", orphanRemoval = true)
    private List<Photo> photos = new ArrayList<>();

    @JsonIgnore
    @OneToMany(mappedBy = "roomType", orphanRemoval = true)
    private List<Room> rooms = new ArrayList<>();

    @Builder
    public RoomType(String name, int standardCapacity, int maxCapacity, Hotel hotel) {
        this.name = name;
        this.standardCapacity = standardCapacity;
        this.maxCapacity = maxCapacity;
        this.hotel = hotel;
        hotel.getRoomTypes().add(this);
    }

    public void addPhoto(Photo photo) {
        this.photos.add(photo);

        if(photo.getRoomType() != this) {
            photo.setHotel(this.getHotel());
            photo.setRoomType(this);
        }
    }

    /* 객실 정보 수정 */
    public void updateRoomTypeInfo(String name, int standardCapacity, int maxCapacity) {
        this.name = name;
        this.standardCapacity = standardCapacity;
        this.maxCapacity = maxCapacity;
    }
}
