package org.montclairrobotics.alloy.utils;

import org.montclairrobotics.alloy.components.InputComponent;
import org.montclairrobotics.alloy.update.Update;

public class Differential extends InputComponent<Double> {
    Input<Double> input;

    double prevTime;
    double prevIn;

    public Differential(Input in){
        input = in;
    }

    @Update
    public void calculateDifferential(){
        double elapsedTime = System.nanoTime() - prevTime;
        double elapsedIn = input.get() - prevIn;
        if(System.nanoTime() != 0) {
            output = elapsedIn / elapsedTime;
        }else{
            output = 0.0;
        }
        prevIn = input.get();
        prevTime = System.nanoTime();
    }
}
