package com.example.geektrust.service;

import com.example.geektrust.constants.CommandTypes;
import com.example.geektrust.entity.Driver;
import com.example.geektrust.entity.Ride;
import com.example.geektrust.entity.Rider;
import com.example.geektrust.utility.CommandParams;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.List;
import java.util.Map;

public class RideServiceImpl implements RideShareService{
    private static final Logger LOGGER= LoggerFactory.getLogger(RideServiceImpl.class);
    private List<Driver> driverList;
    private Map<String,Ride> rideMap;

    private Map<String,Rider> riderMap;
    @Override
    public void processCommand(List<CommandParams> params) {
            String outPutString="";
            for(CommandParams input:params){
                LOGGER.info("Command->{}",input);
                if(input.getCommand().equals(CommandTypes.ADD_RIDER.getType())){
                    addDriver(input.getTokens());
                }
                else if (input.getCommand().equals(CommandTypes.ADD_DRIVER.getType())) {
                    addDriver(input.getTokens());
                }
                else if (input.getCommand().equals(CommandTypes.MATCH.getType())) {
                    matchRider(input.getTokens());
                }
                else if (input.getCommand().equals(CommandTypes.START_RIDE.getType())) {
                    startRide(input.getTokens());
                }
                else if (input.getCommand().equals(CommandTypes.STOP_RIDE.getType())) {
                    stopRide(input.getTokens());
                }
                else if (input.getCommand().equals(CommandTypes.BILL.getType())) {
                    printBill(input.getTokens());
                }
            }
    }

    @Override
    public void addRider(List<String> token) {

    }

    @Override
    public void addDriver(List<String> token) {

    }

    @Override
    public Ride matchRider(List<String> token) {
        return null;
    }

    @Override
    public Ride startRide(List<String> token) {
        return null;
    }

    @Override
    public Ride stopRide(List<String> token) {
        return null;
    }

    @Override
    public void printBill(List<String> token) {

    }
}
