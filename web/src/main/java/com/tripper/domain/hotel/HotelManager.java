package com.tripper.domain.hotel;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.tripper.domain.user.User;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;

import java.util.ArrayList;
import java.util.List;

import static javax.persistence.FetchType.LAZY;
import static javax.persistence.GenerationType.IDENTITY;

@NoArgsConstructor
@Entity
@Getter @Setter
public class HotelManager {

    @Id @GeneratedValue(strategy = IDENTITY)
    @Column(name = "hotelmanager_id")
    private Long id; // PK

    private String companyName;

    @ManyToOne(fetch = LAZY)
    @JoinColumn(name = "user_id")
    private User user;

    @JsonIgnore
    @OneToMany(mappedBy = "hotelManager")
    private List<Hotel> hotels = new ArrayList<>();

    @JsonIgnore
    @OneToMany(mappedBy = "user")
    private List<HotelManager> hotelManagers = new ArrayList<>();

    /* 연관관계 메서드 */
    /**
     * 사용자 정보 세팅
     * @param user
     */
    public void setUser(User user) {
        this.user = user;
        user.getHotelManagers().add(this);
    }

}
