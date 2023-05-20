package com.example.geektrust.service;

import com.example.geektrust.entity.Ride;
import com.example.geektrust.utility.CommandParams;

import java.util.List;

public interface RideShareService {
    void processCommand(List<CommandParams> params);

    void addRider(List<String> token);

    void addDriver(List<String> token);

    Ride matchRider(List<String> token);

    Ride startRide(List<String> token);

    Ride stopRide(List<String> token);

    void printBill(List<String> token);


}
