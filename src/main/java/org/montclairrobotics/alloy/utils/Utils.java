package org.montclairrobotics.alloy.utils;

public class Utils {
    public static double constrain(double in, double min, double max){
        if(in > max){
            return  max;
        }else if(in < min){
            return min;
        }
        return in;
    }
}
