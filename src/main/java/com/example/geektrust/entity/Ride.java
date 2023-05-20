package com.example.geektrust.entity;

import com.example.geektrust.constants.RideStatus;

public class Ride {
    private String id;

    private RideStatus rideStatus;

    private Rider rider;

    private Driver driver;


    private Integer timeTaken;

    private double amount;

    public Ride() {
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public RideStatus getRideStatus() {
        return rideStatus;
    }

    public void setRideStatus(RideStatus rideStatus) {
        this.rideStatus = rideStatus;
    }

    public Rider getRider() {
        return rider;
    }

    public void setRider(Rider rider) {
        this.rider = rider;
    }

    public Driver getDriver() {
        return driver;
    }

    public void setDriver(Driver driver) {
        this.driver = driver;
    }
}
