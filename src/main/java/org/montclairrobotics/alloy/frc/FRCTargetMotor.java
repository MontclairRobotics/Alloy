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
package org.montclairrobotics.alloy.frc;

import edu.wpi.first.wpilibj.SpeedController;
import org.montclairrobotics.alloy.core.Encoder;
import org.montclairrobotics.alloy.core.TargetMotor;
import org.montclairrobotics.alloy.utils.ErrorCorrection;

/**
 * Created by MHS Robotics on 10/9/2018.
 *
 * @author Garrett Burroughs
 * @since
 */
public class FRCTargetMotor extends FRCMotor implements TargetMotor {

    private Encoder encoder;

    private ErrorCorrection<Double> powerCorrection;

    private ErrorCorrection<Double> positionCorrection;

    private Mode mode = Mode.POWER;

    private double targetPower;

    private double power;

    public FRCTargetMotor(SpeedController controller, Encoder encoder) {
        super(controller);
        this.encoder = encoder;
    }

    private enum Mode {
        POSITION,
        POWER
    }

    public FRCMotor runPower() {
        mode = Mode.POWER;
        return this;
    }

    public FRCMotor runPosition() {
        mode = Mode.POSITION;
        return this;
    }

    /**
     * Sets the motor Power
     *
     * @param power the power that the motor will be set to (0-1 inclusive )
     */
    @Override
    public void setTargetPower(double power) {
        runPosition();
        this.targetPower = power;
    }

    /**
     * Sets the motor Power
     *
     * @param power the power that the motor will be set to [-1, 1]
     */
    @Override
    public void setMotorPower(double power) {
        runPower();
        this.power = power;
    }

    /**
     * Gets the motor power
     *
     * @return the current motor power, a value between (0-1)
     */
    @Override
    public double getTargetPower() {
        return targetPower;
    }

    public double getPower() {
        return encoder.getScaledVelocity();
    }

    /**
     * Sets the motor position
     *
     * @param position the position the motor will be set to (in encoder ticks)
     */
    @Override
    public void setPosition(int position) {
        positionCorrection.setTarget(new Double(position));
    }

    /**
     * Gets the motors position
     *
     * @return the position that the motor is at (in encoder ticks)
     */
    @Override
    public int getPosition() {
        return encoder.getTicks();
    }

    @Override
    public void updateMotor() {
        if (status.isEnabled()) {
            switch (mode) {
                case POSITION:
                    controller.set(targetPower * positionCorrection.getCorrection());
                    break;
                case POWER:
                    controller.set(power + powerCorrection.getCorrection());
                    break;
            }
        } else {
            controller.set(0);
        }
    }
}
