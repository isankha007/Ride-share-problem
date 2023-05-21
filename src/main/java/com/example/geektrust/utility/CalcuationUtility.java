package com.example.geektrust.utility;

import com.example.geektrust.constants.FareList;

import java.math.RoundingMode;
import java.text.DecimalFormat;

public class CalcuationUtility {
     private static final double roundOffMultiplier=100.0;
    public static double getDistance(int x1,int y1,int x2,int y2){
        //d = √[ (x2–x1)2 + (y2–y1)2]
        double distance= Math.sqrt((x2-x1)*(x2-x1) + (y2-y1)*(y2-y1));
       // System.out.println("****** "+distance+" === "+Math.round(distance * 100) / roundOffMultiplier);
        return Math.round(distance * 100) / roundOffMultiplier;

    }


    /*
 A base fare of ₹50 is charged for every ride.
 An additional ₹6.5 is charged for every kilometer traveled.
 An additional ₹2 is charged for every minute spent in the ride.
 A service tax of 20% is added to the final amount.
 */
    public static double calculateAmount(double distance,int timeTaken){
        double amount=FareList.BASE_FARE+(distance*FareList.ADDITIONAL_FARE_PER_KM)+(timeTaken*FareList.ADDITIONAL_FARE_PER_MIN);
        amount=Math.round(amount * roundOffMultiplier) / roundOffMultiplier;
        amount+=(amount*FareList.SERVICE_TAX_MULTIPLIER);

//        DecimalFormat df_obj = new DecimalFormat("#.###");
//
//        // Rounding number to the next lowest value
//        // using setRoundingMode()
//        df_obj.setRoundingMode(RoundingMode.FLOOR);
//        String formatStr = df_obj.format(amount);
//        System.out.println("Format +++ %0.2f "+formatStr+" "+Math.round(amount * 100.0) / 100.0);
        return Math.round(amount * roundOffMultiplier) / roundOffMultiplier;
    }

}
