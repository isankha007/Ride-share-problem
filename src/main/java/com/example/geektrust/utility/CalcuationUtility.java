package com.example.geektrust.utility;

public class CalcuationUtility {

    public static double getDistance(int x1,int y1,int x2,int y2){
        //d = √[ (x2–x1)2 + (y2–y1)2]
       return Math.sqrt((x2-x1)*(x2-x1) + (y2-y1)*(y2-y1));
    }

}
