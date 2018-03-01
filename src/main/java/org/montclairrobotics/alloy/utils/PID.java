package org.montclairrobotics.alloy.utils;

import org.montclairrobotics.alloy.update.Update;

/**
 * Created by Montclair robotics on 2/27/2018
 *
 * A PID controller is able to control a component that has a target, current state, and can be controlled for example:
 * - A robots heading using a Gryo
 * - A motors position using encoders
 *
 * PID stands for Proportion, Integral and Derivative control.
 * It uses the error of a component and then calculates the correction
 * the PID values must be tuned in order to have a working PID loop
 * A properly tuned implementation of a PID controller should result in a correction that goes fast, and accurate to
 * its target, and does not overshoot it.
 *
 * You can read more about implementing PID control here <link>https://github.com/GarrettBurroughs/Alloy/wiki/Using-PID-control</link>
 * You can read more about how PID works here <link>https://en.wikipedia.org/wiki/PID_controller</link>
 *
 * @author Garrett Burroughs
 * @since 0.1
 */
public class PID implements Input<Double> {
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
    
    /**
     * Create a new PID
     * @param p proportional constant
     * @param i integral constant
     * @param d derivative constant
     */
    public PID(double p, double i, double d){
        this.p = p;
        this.i = i;
        this.d = d;
    }
    
    /**
     * Create a new PID with the input and target defined
     * @param p proportional constan
     * @param i integral constant
     * @param d derivative constant
     * @param input the input of the component being controlled (current status)
     * @param target the desired target (status) of the component being controlled
     */
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

    @Update
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
