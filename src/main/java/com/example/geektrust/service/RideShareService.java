package com.example.geektrust.service;

import com.example.geektrust.entity.Driver;
import com.example.geektrust.entity.Ride;
import com.example.geektrust.entity.Rider;
import com.example.geektrust.utility.CommandParams;

import java.util.List;
import java.util.Map;
import java.util.Optional;



public interface RideShareService {
    int allowedDistance=5;
    void processCommand(List<CommandParams> params);

    Map<String, Rider> addRider(List<String> token);

    Map<String, Driver> addDriver(List<String> token);

    String matchRider(List<String> token);

    Optional<Ride> startRide(List<String> token);

    Optional<Ride> stopRide(List<String> token);

    String printBill(List<String> token);


}
