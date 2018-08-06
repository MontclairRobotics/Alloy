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
package org.montclairrobotics.alloy.ftc;

import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.DcMotorSimple;
import java.awt.*;
import org.montclairrobotics.alloy.components.Component;
import org.montclairrobotics.alloy.core.*;
import org.montclairrobotics.alloy.update.Update;
import org.montclairrobotics.alloy.utils.Input;

/**
 * Created by MHS Robotics on 11/14/2017.
 *
 * <p>The basic motor for use in FTC. Basic motors are not aware of encoders and are not recommended
 * for use in FTC as all motors come with encoders
 *
 * @see FTCTargetMotor
 * @author Garrett Burroughs
 * @since 0.1
 */
public class FTCMotor extends Component implements Motor {

    /** The physical hardware motor reference to the motor being controlled */
    public DcMotor motor;

    /** The power that the motor should be running at */
    public double power;

    public FTCMotor(String motorConfiguration) {
        motor = RobotCore.getHardwareMap().dcMotor.get(motorConfiguration);
        addDebug(new Debug(motorConfiguration + " Motor Power: ", (Input<Double>) () -> power));
    }

    /**
     * Sets the motor Power
     *
     * @param power the power that the motor will be set to (0-1 inclusive )
     */
    @Override
    public void setMotorPower(double power) {
        motor.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
        this.power = power;
    }

    /**
     * Gets the motor power
     *
     * @return the current motor power, a value between (0-1)
     */
    @Override
    public double getMotorPower() {
        return motor.getPower();
    }

    /**
     * Sets whether the motor runs the default way , or inverted
     *
     * @param inverted true for inverted, false for normal
     */
    @Override
    public void setInverted(boolean inverted) {
        if (inverted) {
            motor.setDirection(DcMotorSimple.Direction.REVERSE);
        } else {
            motor.setDirection(DcMotorSimple.Direction.FORWARD);
        }
    }

    /**
     * Gets weather the motor is inverted
     *
     * @return true if the motor is inverted
     */
    @Override
    public boolean getInverted() {
        return motor.getDirection() == DcMotorSimple.Direction.REVERSE ? true : false;
    }

    public Encoder getEncoder() {
        return new Encoder() {
            @Override
            public int getTicks() {
                return motor.getCurrentPosition();
            }
        };
    }

    @Update
    public void updateMotor() {

        // Set Motor Power if it is enabled
        if (status.booleanValue()) {
            motor.setPower(power);
        } else {
            motor.setPower(0);
        }
    }

    @Override
    public void disableAction() {
        motor.setPower(0);
    }
}
