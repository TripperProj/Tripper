package com.tripper.domain.hotel;

import lombok.Getter;

import javax.persistence.Embeddable;

@Embeddable
@Getter
public class Address {

    private String city;
    private String street;
    private String zipcode;
    private double xCoordinate;
    private double yCoordinate;

    protected Address() {
    }

    public Address(String city, String street, String zipcode, double xCoordinate, double yCoordinate) {
        this.city = city;
        this.street = street;
        this.zipcode = zipcode;
        this.xCoordinate = xCoordinate;
        this.yCoordinate = yCoordinate;
    }

}


