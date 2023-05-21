package com.example.geektrust.entity;

import java.util.SortedMap;
import java.util.TreeMap;

public class Rider extends User{

    private boolean isRiding;

    public boolean isRiding() {
        return isRiding;
    }

    private SortedMap<Double,String> matchedDriverMap=new TreeMap<>();

    public SortedMap<Double, String> getMatchedDriverMap() {
        return matchedDriverMap;
    }

    public void setMatchedDriverMap(SortedMap<Double, String> matchedDriverMap) {
        this.matchedDriverMap = matchedDriverMap;
    }

    public void setRiding(boolean riding) {
        isRiding = riding;
    }

    public Rider(String id, Integer xCoordinate, Integer yCoordinate) {
        super(id, xCoordinate, yCoordinate);
    }



}
