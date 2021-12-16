package com.tripper.domain.hotel;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.tripper.domain.BaseTimeEntity;
import com.tripper.domain.Photo;
import com.tripper.dto.request.hotel.UpdateRoomDto;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import static javax.persistence.CascadeType.REMOVE;
import static javax.persistence.FetchType.LAZY;
import static javax.persistence.GenerationType.IDENTITY;

@NoArgsConstructor
@Entity
@Getter @Setter
public class RoomType extends BaseTimeEntity {

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
    @OneToMany(mappedBy = "hotel", cascade = REMOVE)
    private List<Photo> photos = new ArrayList<>();

    @JsonIgnore
    @OneToMany(mappedBy = "roomType", cascade = REMOVE)
    private List<Room> rooms = new ArrayList<>();

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
            photo.setRoomType(this);
        }
    }

    public void updateRoomType(UpdateRoomDto updateRoomDto) {
        this.name = updateRoomDto.getName();
        this.standardCapacity = updateRoomDto.getStandardCapacity();
        this.maxCapacity = updateRoomDto.getMaxCapacity();
    }
}
