package com.example.geektrust.service;

import com.example.geektrust.constants.CommandTypes;
import com.example.geektrust.constants.RideStatus;
import com.example.geektrust.entity.Driver;
import com.example.geektrust.entity.Ride;
import com.example.geektrust.entity.Rider;
import com.example.geektrust.exception.IdNotFoundException;
import com.example.geektrust.utility.CalcuationUtility;
import com.example.geektrust.utility.CommandParams;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.*;

public class RideServiceImpl implements RideShareService{
    //private static final Logger LOGGER= LoggerFactory.getLogger(RideServiceImpl.class);
    private Map<String,Driver> driverMap=new HashMap<>();
    private Map<String,Ride> rideMap=new HashMap<>();

    private Map<String,Rider> riderMap=new HashMap<>();

    private SortedMap<Double,String> matchedDriverMap=new TreeMap<>();


    public Map<String, Driver> getDriverMap() {
        return driverMap;
    }

    public Map<String, Ride> getRideMap() {
        return rideMap;
    }

    public Map<String, Rider> getRiderMap() {
        return riderMap;
    }

    public RideServiceImpl() {
    }

    @Override
    public void processCommand(List<CommandParams> params) {
            String outPutString="";
            for(CommandParams input:params){
                //LOGGER.info("Command->{}",input);
              //  System.out.println("+++++++"+input);
                if(input.getCommand().equals(CommandTypes.ADD_RIDER.getType())){
                    addRider(input.getTokens());
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
    public Map<String,Rider> addRider(List<String> token) {

        if(!riderMap.containsKey(token.get(0))){
            String id=token.get(0);
            int x= Integer.parseInt(token.get(1));
            int y= Integer.parseInt(token.get(2));
            Rider rider=new Rider(id,x,y);
            riderMap.put(id,rider);
             }
        return riderMap;
    }

    @Override
    public Map<String,Driver> addDriver(List<String> token) {
        //if(!driverList.contains(token.get(0))){
            String id=token.get(0);
            int x= Integer.parseInt(token.get(1));
            int y= Integer.parseInt(token.get(2));
            Driver driver=new Driver(id,x,y);
            driver.setAvailable(true);
            driverMap.put(id,driver);
            return driverMap;
       // }
    }

    @Override
    public String matchRider(List<String> token) {
       // System.out.println(token);
        StringBuilder outputSb=new StringBuilder();
        if(riderMap.containsKey(token.get(0))){
            Rider tempRider=riderMap.get(token.get(0));
            if(!tempRider.isRiding()){
                matchedDriverMap.clear();
                driverMap.forEach((s, driver) -> driverMatched(tempRider,driver));

                if(matchedDriverMap.size()>0){
                    System.out.print("DRIVERS_MATCHED  ");
                    matchedDriverMap.forEach((s, driver) -> {
                        outputSb.append(driver+" ");
                       // System.out.print(driver+" ");
                    });
                }else{
                    outputSb.append("NO_DRIVERS_AVAILABLE");
                   // System.out.println("NO_DRIVERS_AVAILABLE");
                }
                System.out.println(outputSb.toString().trim());

            }else{
                throw new IdNotFoundException("Rider Id Not found");
            }

        }
        return outputSb.toString().trim();
    }

    @Override
    public Optional<Ride> startRide(List<String> token) {
        String rideId=token.get(0);
        int nthDriver= Integer.parseInt(token.get(1));
        String riderId=token.get(2);
        StringBuilder sbOutput=new StringBuilder();
        if(matchedDriverMap.size()<nthDriver){
            System.out.println("Driver is not available");
            return null;
        } else if (rideMap.containsKey(rideId)) {
            System.out.println("INVALID_RIDE");
        }else{
            Ride ride=new Ride();
            Rider rider = riderMap.get(riderId);
            if(rider.isRiding())return  Optional.of(new Ride());
            rider.setRiding(true);
            String nthDeriverId = matchedDriverMap.values().stream().skip(nthDriver - 1).findFirst().get();
            System.out.println(nthDeriverId+" "+driverMap);
            Driver driver = driverMap.get(nthDeriverId);
            driver.setAvailable(false);
            driverMap.put(String.valueOf(nthDeriverId),driver);
            ride.setId(rideId);
            ride.setRideStatus(RideStatus.RIDE_STARTED);
            rideMap.put(rideId,ride);
            System.out.println("RIDE_STARTED "+ride.getId());
        }
        return Optional.of(new Ride());
    }

    @Override
    public Optional<Ride> stopRide(List<String> token) {
        String rideId=token.get(0);
        int x= Integer.parseInt(token.get(1));
        int y= Integer.parseInt(token.get(2));
        long timeTaken= Long.parseLong(token.get(3));
        if(!rideMap.containsKey(rideId)){
            System.out.println("INVALID_RIDE");
            return Optional.of(new Ride());
        }
        return Optional.of(new Ride());
    }

    @Override
    public void printBill(List<String> token) {

    }

    public boolean driverMatched(Rider rider,Driver driver){
        if(!driver.isAvailable())return false;
        double distance = CalcuationUtility.getDistance(rider.getxCoordinate(), rider.getyCoordinate()
                , driver.getxCoordinate(), driver.getyCoordinate());

        if(distance<=allowedDistance){
            //System.out.println(rider+" === "+driver+" === "+distance);
            matchedDriverMap.put(distance,driver.getId());
            return true;
        }
        return false;
    }
}
