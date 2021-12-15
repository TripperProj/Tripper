package com.tripper.dto.response.hotel;

import com.tripper.domain.Photo;
import com.tripper.domain.hotel.Room;
import com.tripper.domain.hotel.RoomType;
import io.swagger.annotations.ApiModel;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@ApiModel(value = "객실 Respose DTO")
@Getter @Setter
@AllArgsConstructor
public class GetRoomDto {

    private String name;
    private List<Photo> photos;
    private int standardCapcacity;
    private int maxCapcacity;
    private List<Room> rooms;
    private int price;

    public GetRoomDto(RoomType roomType) {
        this.name = roomType.getName();
        this.photos = roomType.getPhotos();
        this.standardCapcacity = roomType.getStandardCapacity();
        this.maxCapcacity = roomType.getMaxCapacity();
        this.rooms = roomType.getRooms();
        this.price = roomType.getRooms().get(0).getPrice();
    }
}
