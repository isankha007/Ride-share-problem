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
import java.util.stream.Collectors;

public class RideServiceImpl implements RideShareService{
    //private static final Logger LOGGER= LoggerFactory.getLogger(RideServiceImpl.class);
    private Map<String,Driver> driverMap=new HashMap<>();
    private Map<String,Ride> rideMap=new HashMap<>();

    private Map<String,Rider> riderMap=new HashMap<>();

    //private SortedMap<Double,String> matchedDriverMap=new TreeMap<>();


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
                tempRider.getMatchedDriver().clear();
                driverMap.forEach((s, driver) -> driverMatched(tempRider,driver));
                //System.out.println(driverMap+"----"+tempRider.getMatchedDriverMap());
                if(tempRider.getMatchedDriver().size()>0){
                    System.out.print("DRIVERS_MATCHED ");
                   /* LinkedHashMap<String, Integer> matchMap = tempRider.getMatchedDriverMap().entrySet().stream()
                            .sorted((o1, o2) -> (o1.getValue().compareTo(o2.getValue())))
                            .collect(Collectors.toMap(
                                    Map.Entry::getKey,
                                    Map.Entry::getValue,
                                    (e1, e2) -> e1, LinkedHashMap::new
                            ));*/
                    Comparator<Driver> driverComparator=Comparator.comparing(Driver::getDistanceFromRider)
                            .thenComparing(Driver::getId);

                    tempRider.getMatchedDriver().sort(driverComparator);

                    tempRider.getMatchedDriver().forEach(driver ->
                            outputSb.append(driver.getId()+" ")
                       // System.out.print(driver+" ");
                    );
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
        Rider rider = riderMap.get(riderId);
        if(rider.getMatchedDriver().size()<nthDriver){
            System.out.println("INVALID_RIDE");
            return Optional.of(new Ride());
        } else if (rideMap.containsKey(rideId)) {
            System.out.println("INVALID_RIDE");
        }else{
            Ride ride=new Ride();
            if(rider.isRiding())return  Optional.of(new Ride());
            rider.setRiding(true);
            String nthDeriverId = rider.getMatchedDriver().get(nthDriver-1).getId(); //rider.getMatchedDriverMap().entrySet().stream().skip(nthDriver-1).toString();
            //System.out.println(nthDeriverId+" "+rider.getMatchedDriver()+" N value "+nthDriver);
            Driver driver = driverMap.get(nthDeriverId);
            driver.setAvailable(false);
            driverMap.put(String.valueOf(nthDeriverId),driver);
            ride.setId(rideId);
            ride.setRider(rider);
            ride.setDriver(driver);
            ride.setRideStatus(RideStatus.RIDE_NOT_COMPLETED);
            rideMap.put(rideId,ride);
            System.out.println("RIDE_STARTED "+ride.getId());
            return Optional.of(ride);
        }
        return Optional.of(new Ride());
    }

    @Override
    public Optional<Ride> stopRide(List<String> token) {
        String rideId=token.get(0);
        int x= Integer.parseInt(token.get(1));
        int y= Integer.parseInt(token.get(2));
        Integer timeTaken= Integer.valueOf((token.get(3)));
        StringBuilder sbOutput=new StringBuilder();
        if(!rideMap.containsKey(rideId)){
            System.out.println("INVALID_RIDE");
            return Optional.of(new Ride());
        }else {
            Ride ride = rideMap.get(rideId);
            ride.setRideStatus(RideStatus.RIDE_STOPPED);
            ride.setxCoordinate(x);
            ride.setyCoordinate(y);
            ride.setTimeTaken(timeTaken);


            Rider rider = riderMap.get(ride.getRider().getId());
            rider.setRiding(false);
            riderMap.put(rider.getId(),rider);

            Driver driver = driverMap.get(ride.getDriver().getId());
            driver.setAvailable(true);
            driverMap.put(driver.getId(),driver);
            double distanceCovered=CalcuationUtility.getDistance(rider.getxCoordinate(),rider.getyCoordinate(),x,y);
            ride.setAmount(CalcuationUtility.calculateAmount(distanceCovered,timeTaken));

            System.out.println("RIDE_STOPPED "+ride.getId());
        }
        return Optional.of(new Ride());
    }

    @Override
    public String printBill(List<String> token) {
        String rideId=token.get(0);
        StringBuilder sbOutput=new StringBuilder();
        if(!rideMap.containsKey(rideId)){
            System.out.println("INVALID_RIDE");
        } else if (rideMap.get(rideId).getRideStatus().equals(RideStatus.RIDE_NOT_COMPLETED)) {
            System.out.println("RIDE_NOT_COMPLETED");
        }
        else{
            Ride ride = rideMap.get(rideId);
           // System.out.println("BILL "+ride.getId()+" "+String.format("%.2f",ride.getAmount()));
            sbOutput.append("BILL "+ride.getId()+" "+ride.getDriver().getId()+" "+String.format("%.2f",ride.getAmount()));
            System.out.println(sbOutput.toString());
        }
        return sbOutput.toString();
    }

    public boolean driverMatched(Rider rider,Driver driver){
        if(!driver.isAvailable())return false;
        double distance = CalcuationUtility.getDistance(rider.getxCoordinate(), rider.getyCoordinate()
                , driver.getxCoordinate(), driver.getyCoordinate());

        if(distance<=allowedDistance){
           // System.out.println(rider+" === "+driver+" === "+distance);
            List<Driver> matchedDrivers = rider.getMatchedDriver();
            driver.setDistanceFromRider(distance);
            matchedDrivers.add(driver);
          //  rider.setMatchedDriverMap(matchedDriverMap);
            return true;
        }
        return false;
    }
}
