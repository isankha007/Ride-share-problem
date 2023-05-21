package com.example.geektrust;

import com.example.geektrust.utility.CalcuationUtility;
import junit.framework.Assert;
import org.junit.jupiter.api.Test;

public class CaclculationUtilityTest {
    @Test
    void calculateFeeTest(){
        double distance = CalcuationUtility.getDistance(0, 0, 4, 5);
        double totalAmout = CalcuationUtility.calculateAmount(distance, 32);
        Assert.assertEquals(186.72,totalAmout);
    }
    @Test
    void getDistanceTest(){
        double distance = CalcuationUtility.getDistance(0, 0, 4, 5);
        Assert.assertEquals(6.40,distance);
    }
}
