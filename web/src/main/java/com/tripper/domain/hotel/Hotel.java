package com.tripper.domain.hotel;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.tripper.domain.BaseTimeEntity;
import com.tripper.domain.Photo;
import com.tripper.domain.user.User;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;

import java.util.ArrayList;
import java.util.List;

import static javax.persistence.GenerationType.IDENTITY;

@NoArgsConstructor
@Entity
@Getter
public class Hotel extends BaseTimeEntity {

    @Id @GeneratedValue(strategy = IDENTITY)
    @Column(name = "hotel_id")
    private Long id;

    private String name;
    private String address;

    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;

    @JsonIgnore
    @OneToMany(mappedBy = "hotel", orphanRemoval = true)
    private List<RoomType> roomTypes = new ArrayList<>();

    @JsonIgnore
    @OneToMany(mappedBy = "hotel", orphanRemoval = true)
    private List<Photo> photos = new ArrayList<>();

    @Builder
    public Hotel(String name, String address, User user) {
        this.name = name;
        this.address = address;
        this.user = user;
        user.getHotels().add(this);
    }

    public void addPhoto(Photo photo) {
        this.photos.add(photo);

        if(photo.getHotel() != this) {
            photo.setHotel(this);
        }
    }

    /* 호텔 정보 수정 */
    public void updateHotelInfo(String name, String address) {
        this.name = name;
        this.address = address;
    }
}
