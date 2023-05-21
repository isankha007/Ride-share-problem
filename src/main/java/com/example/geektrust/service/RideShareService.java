package com.example.geektrust.service;

import com.example.geektrust.entity.Driver;
import com.example.geektrust.entity.Ride;
import com.example.geektrust.entity.Rider;
import com.example.geektrust.utility.CommandParams;

import java.util.List;
import java.util.Map;
import java.util.Optional;

        /*
         A base fare of ₹50 is charged for every ride.
         An additional ₹6.5 is charged for every kilometer traveled.
         An additional ₹2 is charged for every minute spent in the ride.
         A service tax of 20% is added to the final amount.
         */

public interface RideShareService {
    int allowedDistance=5;
    void processCommand(List<CommandParams> params);

    Map<String, Rider> addRider(List<String> token);

    Map<String, Driver> addDriver(List<String> token);

    String matchRider(List<String> token);

    Optional<Ride> startRide(List<String> token);

    Optional<Ride> stopRide(List<String> token);

    void printBill(List<String> token);


}
