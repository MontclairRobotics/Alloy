package org.montclairrobotics.alloy.utils;

/**
 * Created by MHS Robotics on 3/12/2018.
 *
 * A monitor is a class that takes in 2 inputs, and returns true when they are within a certain tolerance
 * This can be good for monitoring if certain systems are working, for example if a motor is spinning at a desired speed
 * and can also be used to monitor a sensor to see if it has reached a certain value.
 *
 * @author Garrett Burroughs
 * @since 0.1
 */
public class Monitor implements Input<Boolean> {
    /**
     * The desired target for the input
     */
    public Input<Double> target;
    
    /**
     * The input to be checked
     */
    public Input<Double> input;
    
    /**
     * How far the input can be away from the target while still being true
     */
    public double tolerance;
    
    public Monitor(Input<Double> target, Input<Double> input, double tolerance) {
        this.target = target;
        this.input = input;
        this.tolerance = tolerance;
    }
    
    @Override
    public Boolean get() {
        return Math.abs(target.get() - input.get()) < tolerance;
    }
}
