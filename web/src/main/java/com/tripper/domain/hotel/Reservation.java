package com.tripper.domain.hotel;

import com.tripper.domain.BaseTimeEntity;
import com.tripper.domain.user.User;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;

import static javax.persistence.FetchType.LAZY;
import static javax.persistence.GenerationType.IDENTITY;

@Entity
@Getter
@NoArgsConstructor
public class Reservation extends BaseTimeEntity {

    @Id @GeneratedValue(strategy = IDENTITY)
    @Column(name = "reservation_id")
    private Long id;

    private String checkin;
    private String checkout;
    private int adultNum;
    private int childNum;

    @Enumerated(EnumType.STRING)
    private ReservationStatus status;

    @ManyToOne(fetch = LAZY)
    @JoinColumn(name = "room_id")
    private Room room;

    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;

    @Builder
    public Reservation(String checkin, String checkout, int adultNum, int childNum, ReservationStatus status, Room room, User user) {
        this.checkin = checkin;
        this.checkout = checkout;
        this.adultNum = adultNum;
        this.childNum = childNum;
        this.status = status;
        this.room = room;
        room.getReservations().add(this);
        this.user = user;
        user.getReservations().add(this);
    }

    public void cancel() {
        this.status = ReservationStatus.CANCELLED;
    }

}
