package org.montclairrobotics.alloy.utils;

public class PID implements Updatable{
    private double P;
    private double I;
    private double D;
    
    public PID(double p, double i, double d) {
        P = p;
        I = i;
        D = d;
    }
    
    
    /**
     * The update method should be defined for every updatable, and is called every loop if added to the updater
     */
    @Override
    public void update() {
    
    }
}
