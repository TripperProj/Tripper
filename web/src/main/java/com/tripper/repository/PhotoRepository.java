package com.tripper.repository;

import com.tripper.domain.Photo;
import com.tripper.domain.hotel.Hotel;
import com.tripper.domain.user.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface PhotoRepository extends JpaRepository<Photo, Long>  {

//    @Modifying
//    @Query("DELETE FROM Photo WHERE hotel.id = :hotel_id")
//    void deleteByHotelId(@Param("hotel_id") Long hotel_id);
//
//    @Modifying
//    @Query("DELETE FROM Photo WHERE roomType.id = :roomType_id")
//    void deleteByRoomTypeId(@Param("roomType_id") Long roomType_id);

}
