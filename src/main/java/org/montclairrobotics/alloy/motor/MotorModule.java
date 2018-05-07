/*
MIT License

Copyright (c) 2018 Garrett Burroughs

Permission is hereby granted, free of charge, to any person obtaining a copy
of this software and associated documentation files (the "Software"), to deal
in the Software without restriction, including without limitation the rights
to use, copy, modify, merge, publish, distribute, sublicense, and/or sell
copies of the Software, and to permit persons to whom the Software is
furnished to do so, subject to the following conditions:

The above copyright notice and this permission notice shall be included in all
copies or substantial portions of the Software.

THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR
IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY,
FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE
AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER
LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM,
OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN THE
SOFTWARE.
*/
package org.montclairrobotics.alloy.motor;

import java.util.ArrayList;
import java.util.Arrays;
import org.montclairrobotics.alloy.components.Component;
import org.montclairrobotics.alloy.core.Encoder;
import org.montclairrobotics.alloy.core.Motor;
import org.montclairrobotics.alloy.update.Update;
import org.montclairrobotics.alloy.utils.ConstantInput;
import org.montclairrobotics.alloy.utils.PID;
import org.montclairrobotics.alloy.vector.Vector;

/**
 * Highly Functional set of motors that run together
 *
 * <p>A motor module consists of multiple motors that run together in the same direction. Modules
 * are aware of what direction the run in so they can be used in MoroGroups to be run together with
 * other modules. Modules can also be controlled with an encoder and a PID to ensure that the are
 * going the right speed. @Author Garrett Burroughs @Version 0.1 @Since 0.1
 */
public class MotorModule extends Component {
    /** The motors that the module will control */
    public ArrayList<Motor> motors;

    /**
     * The direction that the modules run This is for use in a motor group running with different
     * modules
     */
    public Vector direction;

    /** A PID controller that will control the */
    public PID powerControl;

    public Encoder encoder;
    public double targetPower;

    public MotorModule(Vector direction, Encoder encoder, PID powerControl, Motor... motors) {
        this.direction = direction;
        this.motors = new ArrayList<>(Arrays.asList(motors));
        this.powerControl = powerControl;
        this.encoder = encoder;
        try {
            powerControl.setInput(() -> encoder.getScaledVelocity());
        } catch (NullPointerException e) {
            powerControl.setInput(new ConstantInput<Double>(0.0));
        }
    }

    public MotorModule(Vector direction, Motor... motors) {
        this(direction, null, null, motors);
    }

    public MotorModule setEncoder(Encoder encoder) {
        this.encoder = encoder;
        return this;
    }

    public MotorModule setPID(PID powerControl) {
        this.powerControl = powerControl;
        return this;
    }

    @Update
    public void powerCorrection() {
        if (status.booleanValue()) { // Check if its enabled
            for (Motor m : motors) {
                if (powerControl != null) {
                    m.setMotorPower(targetPower + powerControl.get());
                } else {
                    m.setMotorPower(targetPower);
                }
            }
        } else { // If disabled, set the power to 0
            for (Motor m : motors) {
                m.setMotorPower(0);
            }
        }
    }

    public void setPower(double power) {
        targetPower = power;
        powerControl.setTarget(power);
    }

    /** Method to be called when the toggleable is disabled */
    @Override
    public void disableAction() {
        for (Motor m : motors) {
            m.setMotorPower(0);
        }
    }
}
