package com.tripper.service;

import com.tripper.domain.hotel.Reservation;
import com.tripper.domain.hotel.ReservationStatus;
import com.tripper.domain.hotel.Room;
import com.tripper.domain.user.User;
import com.tripper.dto.request.hotel.CreateReservationDto;
import com.tripper.dto.response.hotel.GetReservationDto;
import com.tripper.dto.response.hotel.GetReservationListDto;
import com.tripper.repository.ReservationRepository;
import com.tripper.repository.RoomRepository;
import com.tripper.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

/**
 * @author HanJiyoung
 * 예약 관련 서비스 클래스
 */
@Service
@Transactional(readOnly = true)
@Slf4j
@RequiredArgsConstructor
public class ReservationService {

    private final ReservationRepository reservationRepository;
    private final UserRepository userRepository;
    private final RoomRepository roomRepository;

    /**
     * 객실 예약
     */
    @Transactional
    public void createReservation(String name, Long room_id, CreateReservationDto createReservationDto) {

        /* 엔티티 조회 */
        User user = userRepository.findByMemId(name);
        Room room = roomRepository.findById(room_id)
                .orElseThrow(() -> new IllegalArgumentException("존재하지 않는 객실 입니다."));

        Reservation reservation = Reservation.builder()
                .checkin(createReservationDto.getCheckin())
                .checkout(createReservationDto.getCheckout())
                .adultNum(createReservationDto.getAdultNum())
                .childNum(createReservationDto.getChildNum())
                .status(ReservationStatus.RESERVED)
                .user(user)
                .room(room)
                .build();

        reservationRepository.save(reservation);

    }

    /**
     * 전체 예약 조회
     */
    public GetReservationListDto findAllReservations() {

        List<Reservation> reservations = reservationRepository.findAll();
        List<GetReservationDto> getReservationDtos = new ArrayList<>();

        for (Reservation reservation : reservations) {
            GetReservationDto getReservationDto = setReservationDto(reservation);
            getReservationDtos.add(getReservationDto);
        }
        int total = getReservationDtos.size();

        return new GetReservationListDto(getReservationDtos, total);

    }

    /**
     * 현재 로그인한 사용자의 예약 조회
     */
    public GetReservationListDto findReservationsByMemId(String memId) {

        List<Reservation> reservations = reservationRepository.findByUser_MemId(memId);
        List<GetReservationDto> getReservationDtos = new ArrayList<>();

        for (Reservation reservation : reservations) {
            GetReservationDto getReservationDto = setReservationDto(reservation);
            getReservationDtos.add(getReservationDto);
        }
        int total = getReservationDtos.size();
        return new GetReservationListDto(getReservationDtos, total);

    }

    /**
     * 특정 호텔에 대한 예약 조회
     */
    public GetReservationListDto findReservationsByHotelId(Long hotel_id) {

        List<Reservation> reservations = reservationRepository.findByRoom_RoomType_Hotel_Id(hotel_id);
        List<GetReservationDto> getReservationDtos = new ArrayList<>();

        for (Reservation reservation : reservations) {
            GetReservationDto getReservationDto = setReservationDto(reservation);
            getReservationDtos.add(getReservationDto);
        }
        int total = getReservationDtos.size();

        return new GetReservationListDto(getReservationDtos, total);
    }

    /**
     * 특정 객실에 대한 예약 조회
     */
    public GetReservationListDto findReservationsByRoomId(Long room_id) {

        List<Reservation> reservations = reservationRepository.findByRoom_Id(room_id);
        List<GetReservationDto> getReservationDtos = new ArrayList<>();

        for (Reservation reservation : reservations) {
            GetReservationDto getReservationDto = setReservationDto(reservation);
            getReservationDtos.add(getReservationDto);
        }
        int total = getReservationDtos.size();

        return new GetReservationListDto(getReservationDtos, total);
    }

    /**
     * 예약 취소
     */
    @Transactional
    public void cancelReservation(Long reservation_id) {

        Reservation reservation = reservationRepository.findById(reservation_id)
                .orElseThrow(() -> new IllegalArgumentException("존재하지 않는 주문 입니다."));
        reservation.cancel();

    }

    /**
     *  예약 response dto 세팅
     */
    public GetReservationDto setReservationDto(Reservation reservation) {

        Room room = reservation.getRoom();
        String hotel = null;
        String roomType = null;
        int roomNum = 0;

        if(room != null) {
            hotel = room.getRoomType().getHotel().getName();
            roomType = room.getRoomType().getName();
            roomNum = room.getRoomNum();
        }
        String memId = reservation.getUser().getMemId();
        String checkin = reservation.getCheckin();
        String checkout = reservation.getCheckout();
        int adultNum = reservation.getAdultNum();
        int childNum = reservation.getChildNum();
        ReservationStatus status = reservation.getStatus();

        GetReservationDto getReservationDto = new GetReservationDto(hotel, roomType, roomNum, memId, checkin, checkout, adultNum, childNum, status);

        return getReservationDto;
    }
}
