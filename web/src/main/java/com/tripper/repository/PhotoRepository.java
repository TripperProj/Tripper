package com.tripper.repository;

import com.tripper.domain.Photo;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface PhotoRepository extends JpaRepository<Photo, Long> {

    List<Photo> findByHotel_Id(Long hotelId);
    List<Photo> findByRoomType_Id(Long roomtypeId);
    void deleteByRoomType_Id(Long roomtypeId);

}
