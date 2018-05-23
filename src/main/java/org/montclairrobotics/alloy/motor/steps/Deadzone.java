package org.montclairrobotics.alloy.motor.steps;

import org.montclairrobotics.alloy.components.Step;
import org.montclairrobotics.alloy.vector.Vector;

public class Deadzone implements Step<Double> {
    double tolerance;

    public Deadzone(double tolerance){
        this.tolerance = tolerance;
    }

    public Deadzone(){
        this(0.05);
    }

    @Override
    public Double getOutput(Double input) {
        return (double)input < tolerance ? 0 : input;
    }
}
