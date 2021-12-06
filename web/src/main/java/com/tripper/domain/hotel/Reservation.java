package com.tripper.domain.hotel;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;

import java.time.LocalDateTime;

import static javax.persistence.FetchType.LAZY;
import static javax.persistence.GenerationType.IDENTITY;

@NoArgsConstructor
@Entity
@Getter @Setter
public class Reservation {

    @Id @GeneratedValue(strategy = IDENTITY)
    @Column(name = "reservation_id")
    private Long id; // PK

    private LocalDateTime startDate;
    private LocalDateTime endDate;

    @Enumerated(EnumType.STRING)
    private ReservationStatus status;

    @ManyToOne(fetch = LAZY)
    @JoinColumn(name = "room_id")
    private Room room;

    public Reservation(LocalDateTime startDate, LocalDateTime endDate, ReservationStatus status, Room room) {
        this.startDate = startDate;
        this.endDate = endDate;
        this.status = status;
        this.room = room;
        room.getReservations().add(this);
    }

}
