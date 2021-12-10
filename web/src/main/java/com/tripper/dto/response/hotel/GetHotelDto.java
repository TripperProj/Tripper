package com.tripper.dto.response.hotel;

import com.tripper.domain.Photo;
import com.tripper.domain.hotel.Hotel;
import io.swagger.annotations.ApiModel;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;

@ApiModel(value = "호텔 Respose DTO")
@Getter @Setter
@AllArgsConstructor
public class GetHotelDto {

    private Long id;
    private String name;
    private String address;
    private List<Photo> photos;

    public GetHotelDto(Hotel hotel) {
        this.id = hotel.getId();
        this.name = hotel.getName();
        this.address = hotel.getAddress();
        this.photos = hotel.getPhotos();
    }

}
