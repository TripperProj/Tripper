package com.tripper.domain.trip;

import com.tripper.domain.budget.Budget;
import com.tripper.domain.schedule.Schedule;
import com.tripper.domain.user.User;
import lombok.Getter;

import javax.persistence.*;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Entity
@Getter
public class Trip {

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "trip_id")
    private Long id;

    private String destination;
    private LocalDate startDate;
    private LocalDate endDate;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id")
    private User user;

    @OneToMany(mappedBy = "trip", cascade = CascadeType.ALL)
    private List<Schedule> schedules = new ArrayList<>();

    @OneToOne(mappedBy = "trip")
    private Budget budget;

    protected Trip() {}

    public Trip(String destination, LocalDate startDate, LocalDate endDate, User user) {
        this.destination = destination;
        this.startDate = startDate;
        this.endDate = endDate;
        this.user = user;
    }

    public void updateTrip(String destination, LocalDate startDate, LocalDate endDate) {
        this.destination = destination;
        this.startDate = startDate;
        this.endDate = endDate;
    }

    public void setBudget(Budget budget){
        this.budget = budget;
    }
}
