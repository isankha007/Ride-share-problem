package com.example.geektrust;

import com.example.geektrust.service.RideServiceImpl;
import com.example.geektrust.service.RideShareService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class RideShareServiceTest {
    private RideShareService rideShareService;

    @BeforeEach
    void setUp(){
        rideShareService=new RideServiceImpl();
    }

    @Test
    void processCommand(){

    }

}
