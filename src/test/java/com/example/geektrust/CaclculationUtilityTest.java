package com.example.geektrust;

import com.example.geektrust.utility.CalcuationUtility;
import junit.framework.Assert;
import org.junit.jupiter.api.Test;

public class CaclculationUtilityTest {
    @Test
    void calculateFeeTest(){
        double distance = CalcuationUtility.getDistance(2, 7, 4, 15);
        double totalAmout = CalcuationUtility.calculateAmount(distance, 60);
        Assert.assertEquals(268.36,totalAmout);
    }
    @Test
    void getDistanceTest(){
        double distance = CalcuationUtility.getDistance(0, 0, 4, 5);
        Assert.assertEquals(6.40,distance);
    }
}
