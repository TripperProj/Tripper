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
public class Hotel {

    @Id @GeneratedValue(strategy = IDENTITY)
    @Column(name = "hotel_id")
    private Long id; // PK

    private String name;
    private String img;

    @Embedded
    private Address address;

    @ManyToOne(fetch = LAZY)
    @JoinColumn(name = "hotelmanager_id")
    private HotelManager hotelManager;


    /* 연관관계 메서드 */
    /**
     * 호텔 매니저 정보 세팅
     * @param hotelManager
     */
    public void setHotelManager(HotelManager hotelManager) {
        this.hotelManager = hotelManager;
//        hotelManager.getHotelManagers().add(this);
    }


}
