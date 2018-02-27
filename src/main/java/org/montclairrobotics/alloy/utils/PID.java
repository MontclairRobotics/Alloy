package org.montclairrobotics.alloy.utils;

import org.montclairrobotics.alloy.update.Updatable;

public class PID implements Updatable, Input<Double> {
    private double p;
    private double i;
    private double d;
    private Input<Double> input;
    private double target;
    private double correction;

    /** The error of the PID, calculated by the target - input*/
    private double error;
    /** The rate that the error is changing on a certain interval (Slope of the error if it were graphed) AKA derivative*/
    private double errorRate;
    /** The total error that has accumulated over time (Area under the graph if the error were graphed) AKA Integral*/
    private double totalError;
    /** The error of the previous calculation, used for calculating the rate of error*/
    private double prevError;
    /** The time of the previous calculation, used for calculating the rate of error*/
    private double prevTime;
    /** The difference in time between update loops*/
    private double timeDifference;
    /** The difference in error between update loops*/
    private double errorDifference;


    public PID(double p, double i, double d){
        this.p = p;
        this.i = i;
        this.d = d;
    }

    public PID(double p, double i, double d, Input<Double> input, double target) {
        this.p = p;
        this.i = i;
        this.d = d;
        this.input = input;
        this.target = target;
    }

    public PID setTarget(double target){
        this.target = target;
        return this;
    }

    public PID setInput(Input<Double> input){
        this.input = input;
        return this;
    }

    @Override
    public Double get() {
        return correction;
    }

    /**
     * The update method should be defined for every updatable, and is called every loop if added to the updater
     */
    @Override
    public void update() {

        // Calculate Error
        try {
            error = target - input.get();
        }catch(NullPointerException e){
            throw new RuntimeException("PID input has not been defined use pid.setInput(Input<Double> input), to set it");
        }

        // calculate time difference in time since the last update
        timeDifference = prevTime - System.currentTimeMillis();

        // calculate the difference in error since the last update
        errorDifference = prevError - error;

        // Calculate slope of the error (Derivative) change in error / change in time
        try {
            errorRate = errorDifference / timeDifference;
        }catch(ArithmeticException e){
            errorRate = 0;
        }

        // Calculate Error Integral
        totalError += error;

        // Calculate Correction
        correction = p * error + i * totalError + d * errorRate;

        prevError = error;
        prevTime = System.currentTimeMillis()/1000d;
    }
}
