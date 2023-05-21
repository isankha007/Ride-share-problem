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
        List<String>  token= Arrays.asList("R1");
        rideShareService.addRider(Arrays.asList("R1","1","1"));
        rideShareService.addDriver(Arrays.asList("D1","1","1"));
        rideShareService.addDriver(Arrays.asList("D2","4","1"));

        String outputstr = rideShareService.matchRider(token);
         Assert.assertEquals("D1 D2",outputstr);
    }

}
