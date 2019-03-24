/*
MIT License

Copyright (c) 2019 Garrett Burroughs

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
package org.montclairrobotics.alloy.auto.States;

import org.montclairrobotics.alloy.auto.State;
import org.montclairrobotics.alloy.drive.DTInput;
import org.montclairrobotics.alloy.drive.DriveTrain;
import org.montclairrobotics.alloy.utils.ConstantInput;
import org.montclairrobotics.alloy.vector.Angle;
import org.montclairrobotics.alloy.vector.Vector;

/**
 * Turns the robot autonomously for a given amount of time
 *
 * <p>This method of autonomous movement is not recommended, as it is very susceptible to outside
 * interference and tends to be very unreliable and inaccurate
 *
 * <p>For more accurate autonomous turning see
 *
 * @see TurnGyro
 * @see TurnEncoders
 * @author Garrett Burroughs
 * @since 0.1
 */
public class TurnTime extends State {
    /** the time in seconds the robot will turn for */
    private final double time;

    /** the power the robot wil turn at */
    private final double power;

    /** the drive train this state will use, this is set by default to be the alloy drive train */
    private final DriveTrain driveTrain;

    /** the time the state starts at */
    private long startTimeMillis;

    /**
     * Creates an auto state that turns the robot for a specified time at a specified power
     *
     * <p>This constructor does not indicate the next state
     *
     * @param time the time (in seconds) to turn the robot
     * @param power the power to turn the robot
     */
    public TurnTime(double time, double power) {
        this.time = time;
        this.power = power;
        this.driveTrain = DriveTrain.getAutoDriveTrain();
    }

    /**
     * Creates an auto state that turns the robot for a specified time at a specified power
     *
     * <p>This constructor indicates the following state
     *
     * @param time the time (in seconds) to turn the robot
     * @param power the power to turn the robot
     * @param nextState the state to go to when this state is done
     */
    public TurnTime(double time, double power, int nextState) {
        super(nextState);
        this.time = time;
        this.power = power;
        this.driveTrain = DriveTrain.getAutoDriveTrain();
    }

    @Override
    public void start() {
        startTimeMillis = System.currentTimeMillis();
    }

    @Override
    public void run() {
        driveTrain.setInput(new ConstantInput<DTInput>(new DTInput(Vector.ZERO, new Angle(power))));
    }

    @Override
    public void stop() {
        driveTrain.setInput(new ConstantInput<>(new DTInput(Vector.ZERO, Angle.ZERO)));
    }

    @Override
    public boolean isDone() {
        return startTimeMillis / 1000 > time;
    }
}
