package com.example.geektrust.entity;

import com.example.geektrust.constants.RideStatus;

public class Driver extends User{
    private boolean isAvailable;

    private double distanceFromRider;

    public double getDistanceFromRider() {
        return distanceFromRider;
    }

    public void setDistanceFromRider(double distanceFromRider) {
        this.distanceFromRider = distanceFromRider;
    }

    public Driver(String id, Integer xCoordinate, Integer yCoordinate) {
        super(id, xCoordinate, yCoordinate);
    }

    public boolean isAvailable() {
        return isAvailable;
    }

    public void setAvailable(boolean available) {
        isAvailable = available;
    }


}
