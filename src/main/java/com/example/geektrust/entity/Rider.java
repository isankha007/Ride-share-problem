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

    public void setMatchedDriver(List<Driver> matchedDriver) {
        this.matchedDriver = matchedDriver;
    }
    /*private LinkedHashMap<String,Integer> matchedDriverMap=new LinkedHashMap<>();

    public LinkedHashMap<String, Integer> getMatchedDriverMap() {
        return matchedDriverMap;
    }

    public void setMatchedDriverMap(LinkedHashMap<String, Integer> matchedDriverMap) {
        this.matchedDriverMap = matchedDriverMap;
    }*/

    public void setRiding(boolean riding) {
        isRiding = riding;
    }

    public Rider(String id, Integer xCoordinate, Integer yCoordinate) {
        super(id, xCoordinate, yCoordinate);
    }



}
