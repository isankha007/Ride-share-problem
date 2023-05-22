package com.example.geektrust.entity;

import java.util.*;

public class Rider extends User{

    private boolean isRiding;

    public boolean isRiding() {
        return isRiding;
    }

    private List<Driver> matchedDriver=new ArrayList<>();

    public List<Driver> getMatchedDriver() {
        return matchedDriver;
    }

    public void updateMatchedDriver(List<Driver> matchedDriver) {
        this.matchedDriver = matchedDriver;
    }


    public void updateIsRiding(boolean riding) {
        isRiding = riding;
    }

    public Rider(String id, Integer xCoordinate, Integer yCoordinate) {
        super(id, xCoordinate, yCoordinate);
    }





}
