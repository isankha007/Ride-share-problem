package com.example.geektrust.entity;

public abstract class User {
    private final String id;
    private final Integer xCoordinate;

    private final Integer yCoordinate;

    public User(String id, Integer xCoordinate, Integer yCoordinate) {
        this.id = id;
        this.xCoordinate = xCoordinate;
        this.yCoordinate = yCoordinate;
    }



    public String getId() {
        return id;
    }



    public Integer getxCoordinate() {
        return xCoordinate;
    }



    public Integer getyCoordinate() {
        return yCoordinate;
    }



    @Override
    public String toString() {
        return "User{" +
                "id='" + id + '\'' +
                ", xCoordinate=" + xCoordinate +
                ", yCoordinate=" + yCoordinate +
                '}';
    }
}
