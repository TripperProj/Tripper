package com.tripper.repository;

import com.tripper.domain.hotel.Reservation;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface ReservationRepository extends JpaRepository<Reservation, Long> {

    List<Reservation> findByUser_MemId(String memId);
    List<Reservation> findByRoom_RoomType_Hotel_Id(Long hotelId);
    List<Reservation> findByRoom_Id(Long roomId);

}