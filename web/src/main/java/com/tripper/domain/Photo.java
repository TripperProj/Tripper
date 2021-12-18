package com.tripper.domain;

import com.tripper.domain.hotel.Hotel;
import com.tripper.domain.hotel.RoomType;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;

import static javax.persistence.GenerationType.IDENTITY;

@Getter
@NoArgsConstructor(access = AccessLevel.PUBLIC)
@Entity
public class Photo extends BaseTimeEntity {

    @Id @GeneratedValue(strategy = IDENTITY)
    @Column(name = "photo_id")
    private Long id;

    @Column(nullable = false)
    private String originName;

    @Column(nullable = false)
    private String filePath;

    private Long fileSize;

    @ManyToOne
    @JoinColumn(name = "hotel_id")
    private Hotel hotel;

    @ManyToOne
    @JoinColumn(name = "roomtype_id")
    private RoomType roomType;

    @Builder
    public Photo(String originName, String filePath, Long fileSize){
        this.originName = originName;
        this.filePath = filePath;
        this.fileSize = fileSize;
    }

    public void setHotel(Hotel hotel) {
        this.hotel = hotel;
        hotel.getPhotos().add(this);
    }

    public void setRoomType(RoomType roomType) {
        this.roomType = roomType;
        roomType.getPhotos().add(this);
    }
}