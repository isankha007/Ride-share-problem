package com.example.geektrust;

import com.example.geektrust.entity.Driver;
import com.example.geektrust.service.RideServiceImpl;
import com.example.geektrust.service.RideShareService;
import junit.framework.Assert;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.List;
import java.util.Map;

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

    }

    @Test
    void addDriver(){
        List<String>  token= Arrays.asList("D1","1","1");
        Map<String, Driver> stringDriverMap = rideShareService.addDriver(token);
        Assert.assertEquals("D1",stringDriverMap.get("D1").getId());
    }

}
