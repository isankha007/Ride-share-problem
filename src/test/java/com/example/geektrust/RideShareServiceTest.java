package com.example.geektrust;

import com.example.geektrust.entity.Driver;
import com.example.geektrust.entity.Ride;
import com.example.geektrust.entity.Rider;
import com.example.geektrust.service.RideServiceImpl;
import com.example.geektrust.service.RideShareService;
import junit.framework.Assert;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.Optional;

public class RideShareServiceTest {
    private RideShareService rideShareService;

    

    @BeforeEach
    void setUp(){
        rideShareService=new RideServiceImpl();
    }

    @Test
    void processCommand(){

    }

    @Test
    void addRiderTest(){
        List<String>  token= Arrays.asList("R1","1","1");
        Map<String, Rider> stringRiderMap = rideShareService.addRider(token);
        Assert.assertEquals("R1",stringRiderMap.get("R1").getId());
    }

    @Test
    void addDriverTest(){
        List<String>  token= Arrays.asList("D1","1","1");
        Map<String, Driver> stringDriverMap = rideShareService.addDriver(token);
        Assert.assertEquals("D1",stringDriverMap.get("D1").getId());
    }

    @Test
    void matchRiderTest(){

        rideShareService.addRider(Arrays.asList("R1","2","7"));
        List<String>  token= Arrays.asList("R1");
        String outputstr = rideShareService.matchRider(token);
        Assert.assertEquals("NO_DRIVERS_AVAILABLE",outputstr);
        rideShareService.addDriver(Arrays.asList("D1","3","1"));
        rideShareService.addDriver(Arrays.asList("D2","5","6"));
        rideShareService.addDriver(Arrays.asList("D13","1","8"));
        rideShareService.addDriver(Arrays.asList("D4","3","6"));

        outputstr = rideShareService.matchRider(token);
         Assert.assertEquals("D13 D4 D2",outputstr);
    }


    @Test
    void startRideTest(){
        List<String>  token= Arrays.asList("RIDE-001","1","R1");
        rideShareService.addRider(Arrays.asList("R1","1","1"));
        rideShareService.addDriver(Arrays.asList("D1","1","1"));
        rideShareService.addDriver(Arrays.asList("D2","1","1"));
        rideShareService.addDriver(Arrays.asList("D3","1","1"));

        List<String>  matchToken= Arrays.asList("R1");

        String outputstr = rideShareService.matchRider(matchToken);
       // System.out.println("==="+outputstr);

        Optional<Ride> ride = rideShareService.startRide(token);
        Assert.assertEquals("RIDE-001",ride.get().getId());
    }

    @Test
    void printBillTest(){
        List<String>  token= Arrays.asList("RIDE-001","1","R1");
        rideShareService.addRider(Arrays.asList("R1","3","5"));
        rideShareService.addDriver(Arrays.asList("D1","2","3"));
        rideShareService.addDriver(Arrays.asList("D2","0","1"));
        rideShareService.addDriver(Arrays.asList("D3","4","2"));

        List<String>  matchToken= Arrays.asList("R1");

        String outputstr = rideShareService.matchRider(matchToken);
        System.out.println("==="+outputstr);

        Optional<Ride> ride = rideShareService.startRide(token);

        List<String>  stopRideToken= Arrays.asList("RIDE-001","10","2","48");
        rideShareService.stopRide(stopRideToken);

        List<String>  billToken= Arrays.asList("RIDE-001");

        String printedBill = rideShareService.printBill(billToken);
        Assert.assertEquals("BILL RIDE-001 D1 234.64",printedBill);
    }

}
