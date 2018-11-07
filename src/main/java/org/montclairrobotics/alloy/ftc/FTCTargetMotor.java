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
import org.montclairrobotics.alloy.core.Debug;
import org.montclairrobotics.alloy.core.Encoder;
import org.montclairrobotics.alloy.core.TargetMotor;
import org.montclairrobotics.alloy.utils.ErrorCorrection;
import org.montclairrobotics.alloy.utils.Input;

/**
 * Implementation of a target motor for the FTC competition
 *
 * <p>A target motor is a motor that can use encoders to be set to a certain position Since FTC
 * motors have their own PIDs that they are controlled with by default, the user has the ability to
 * override this and use custom PIDs if need be. By default, the user does not have to worry about
 * any of this. Do not attempt to change the PID if you do not know what you are doing You can read
 * more about PIDs here <link>https://en.wikipedia.org/wiki/PID_controller</link>
 *
 * @author Garrett Burroughs
 * @version 0.1
 * @since 0.1
 */
public class FTCTargetMotor extends FTCMotor implements TargetMotor {

    /** The power that the motor will aim to be, a value between (-1, 1) */
    private double targetPower;

    /** PID being used to control the motor in custom mode */
    private ErrorCorrection<Double> correction;

    /** Current target motor runmode NOTE: not equal to the DCMotor runmode */
    private Mode runmode = Mode.DEFAULT;

    /**
     * Creates a new FTC Target motor, using the motor id from the FTC configuration
     *
     * <p>The configuration must be set on the phone before accessing it in code
     *
     * <p>A target motor created like this will have the default FTC motor regulation, to set a
     * custom error correction, you can do this by calling 'setErrorCorrection(ErrorCorrection)' You
     * should only do this if you know what you are doing
     *
     * @see ErrorCorrection
     * @param motorConfiguration
     */
    public FTCTargetMotor(String motorConfiguration) {
        super(motorConfiguration);
        motor.setMode(DcMotor.RunMode.RUN_TO_POSITION);
        addDebug(new Debug(motorConfiguration + " Motor Power: ", (Input<Double>) () -> power));
        addDebug(
                new Debug(
                        motorConfiguration + " Motor Position: ",
                        (Input<Integer>) () -> getPosition()));
        addDebug(
                new Debug(
                        motorConfiguration + " Target Motor Power: ",
                        (Input<Double>) () -> targetPower));
    }

    enum Mode {
        /**
         * Defualt target motor runmode The default mode will run using the build in Motor PIDs to
         * control the motor
         */
        DEFAULT,

        /**
         * Custom target motor runmode The custom mode will control the motor with a user defined
         * PID controller
         */
        CUSTOM
    }

    /**
     * Sets the motor position
     *
     * @param position the position the motor will be set to (in encoder ticks)
     */
    @Override
    public void setPosition(int position) {
        if (runmode == Mode.DEFAULT) {
            motor.setMode(DcMotor.RunMode.RUN_TO_POSITION);
            motor.setTargetPosition(position);
        } else {
            correction.setTarget((double) position);
        }
    }

    /**
     * Gets the motors position
     *
     * @return the position that the motor is at (in encoder ticks)
     */
    @Override
    public int getPosition() {
        return motor.getCurrentPosition();
    }

    /**
     * Sets the motor Power
     *
     * @param power the power that the motor will be set to (0-1 inclusive )
     */
    @Override
    public void setTargetPower(double power) {
        motor.setMode(DcMotor.RunMode.RUN_TO_POSITION);
        this.targetPower = power;
    }

    /**
     * Gets the motor power
     *
     * @return the current motor power, a value between (0-1)
     */
    @Override
    public double getTargetPower() {
        return motor.getPower();
    }

    /**
     * Set the motor to run using a custom Error Correction
     *
     * @param correction the correction that the motor will be controlled with
     */
    public void setErrorCorrection(ErrorCorrection correction) {
        this.correction = correction.copy();
        runmode = runmode.CUSTOM;
    }

    /** Stop using the custom Error Correction and return to using the default mode */
    public void disableErrorCorrection() {
        this.correction = null;
        runmode = Mode.DEFAULT;
    }

    /**
     * If the mode is custom, then the update method will set the motor power using the custom PID
     */
    @Override
    public void updateMotor() {
        if (status.isEnabled()) { // Check if enabled
            if (motor.getMode() == DcMotor.RunMode.RUN_TO_POSITION) {
                if (runmode == Mode.CUSTOM) {
                    setTargetPower(
                            targetPower
                                    * correction
                                            .getCorrection()); // If running using custom PID mode, set power to PID output
                } else {
                    motor.setPower(
                            targetPower); // If running in default target mode, set the target power
                }
            } else {
                motor.setPower(power); // If running by power, set the power
            }
        } else {
            motor.setPower(0); // If disabled, set power to 0
        }
    }

    /** @return the Error Correction being used to control the motor */
    public ErrorCorrection<Double> getErrorCorrection() {
        return correction;
    }

    /** @return the motor being controlled */
    public DcMotor getMotor() {
        return motor;
    }

    /**
     * NOTE: this is not the same as the DCMotor runmode
     *
     * @return the current target motor runmode
     */
    public Mode getRunmode() {
        return runmode;
    }

    /**
     * Allows for the creation of an encoder object that is aware of the amount of ticks the motor
     * has gone
     *
     * @return a new encoder object that will return the amount of encoder ticks the motor is at
     */
    public Encoder getEncoder() {
        return new Encoder() {
            @Override
            public int getRawTicks() {
                return getPosition();
            }
        };
    }
}
