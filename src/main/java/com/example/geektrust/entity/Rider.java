package com.example.geektrust.entity;

public class Rider extends User{

    private boolean isRiding;

    public boolean isRiding() {
        return isRiding;
    }

    public void setRiding(boolean riding) {
        isRiding = riding;
    }

    public Rider(String id, Integer xCoordinate, Integer yCoordinate) {
        super(id, xCoordinate, yCoordinate);
    }
}
