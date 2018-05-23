package org.montclairrobotics.alloy.motor.steps;

import org.montclairrobotics.alloy.components.Step;
import org.montclairrobotics.alloy.vector.Vector;

public class VectorDeadzone implements Step<Vector> {
    double tolerance;

    public VectorDeadzone(double tolerance){
        this.tolerance = tolerance;
    }

    @Override
    public Vector getOutput(Vector input) {
        return input.getManitude() < tolerance ? Vector.ZERO : input;
    }
}
