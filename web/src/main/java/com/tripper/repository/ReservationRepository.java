package com.tripper.repository;

import com.tripper.domain.hotel.Reservation;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface ReservationRepository extends JpaRepository<Reservation, Long> {

    @Query("SELECT r FROM Reservation r WHERE r.user.memId = :memId")
    List<Reservation> findReservationsByMemId(@Param("memId") String memId);

    @Query("SELECT r FROM Reservation r WHERE r.room.roomType.hotel.id = :hotelId")
    List<Reservation> findReservationsByHotelId(@Param("hotelId") Long hotelId);

    @Query("SELECT r FROM Reservation r WHERE r.room.id= :roomId")
    List<Reservation> findReservationsByRoomId(@Param("roomId") Long roomId);
}