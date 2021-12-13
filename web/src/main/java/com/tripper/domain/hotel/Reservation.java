package com.tripper.domain.hotel;

import com.tripper.domain.user.User;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;

import static javax.persistence.FetchType.LAZY;
import static javax.persistence.GenerationType.IDENTITY;

@NoArgsConstructor
@Entity
@Getter @Setter
public class Reservation {

    @Id @GeneratedValue(strategy = IDENTITY)
    @Column(name = "reservation_id")
    private Long id; // PK

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

    public Reservation(String checkin, String checkout, ReservationStatus status, Room room, User user) {
        this.checkin = checkin;
        this.checkout = checkout;
        this.status = status;
        this.room = room;
        room.getReservations().add(this);
        this.user = user;
        user.getResrvations().add(this);
    }

    public void cancel() {
        this.setStatus(ReservationStatus.CANCELLED);
    }

}
