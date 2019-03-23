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
import org.montclairrobotics.alloy.utils.GyroCorrection;
import org.montclairrobotics.alloy.vector.Angle;
import org.montclairrobotics.alloy.vector.Vector;

/**
 * Turns the robot using the Gyro to correct the angle
 *
 * <p>This is one of the more accurate ways of turning a robot, as it is resistant to outside
 * interruptions and robot error.
 *
 * <p>The turn gyro state uses a gyro correction object for all of its turn calculations, this is
 * gotten automatically from the drive train class, but first needs to be specified by the user in
 * the robot init class.
 *
 * <p>This is written this way, so that the user can define a gyro correction object once, and not
 * have to worry about using it for all turning operations, as if they are using it for one thing
 * (indicating that there is a working and reliable gyro on the robot, and that the team is aware
 * and capable of gyroscope enhancement) then the gyro should be used for all autonomous turning
 * commands and correction.
 *
 * @see GyroCorrection
 * @author Garrett Burroughs
 * @since 0.1
 */
public class TurnGyro extends State {

    /**
     * The gyro correction used for turning the robot
     *
     * <p>This is defined to be the default gyro correction that can be set using the
     * "GyroCorrection.setGeneralCorrection()" method in the robotSetup() class
     */
    private final GyroCorrection turnCorrection;

    /** the target angle the robot will try to be at */
    private final Angle angle;

    /**
     * If true, the robot will turn the specified number of degrees
     *
     * <p>If false, the robot will turn to the absolute angle (based on the most recent gyro reset)
     * NOTE: The gyro is generally reset when you power on the robot, you may want to reset the gyro
     * at the start of the autonomous mode to ensure proper calibration
     */
    private final boolean relative;

    /** the drive train this state will use, this is set by default to be the alloy drive train */
    private final DriveTrain driveTrain;

    /**
     * Creates a new turn gyro state
     *
     * <p>This constructor does not specify the following state
     *
     * @param angle the angle to move, either absolute or relative
     * @param relative if false, the robot will turn to the absolute gyro angle (based off the most
     *     recent reset) if true, the robot will turn the angle relative to its current angle
     *     (standard turning)
     */
    public TurnGyro(double angle, boolean relative) {
        turnCorrection = GyroCorrection.getGeneralCorrection();
        driveTrain = DriveTrain.getAutoDriveTrain();
        this.angle = new Angle(angle);
        this.relative = relative;
    }

    /**
     * Creates a new turn gyro state
     *
     * <p>This constructor does not specify the following state
     *
     * @param angle the angle to move, either absolute or relative
     * @param relative if false, the robot will turn to the absolute gyro angle (based off the most
     *     recent reset) if true, the robot will turn the angle relative to its current angle
     *     (standard turning)
     */
    public TurnGyro(Angle angle, boolean relative) {
        turnCorrection = GyroCorrection.getGeneralCorrection();
        driveTrain = DriveTrain.getAutoDriveTrain();
        this.angle = angle;
        this.relative = relative;
    }

    /**
     * Creates a new turn gyro state
     *
     * <p>This constructor specifies the following state
     *
     * @param angle the angle to move, either absolute or relative
     * @param relative if false, the robot will turn to the absolute gyro angle (based off the most
     *     recent reset) if true, the robot will turn the angle relative to its current angle
     *     (standard turning)
     * @param nextState the state to go to when this state is done
     */
    public TurnGyro(Angle angle, boolean relative, int nextState) {
        super(nextState);
        turnCorrection = GyroCorrection.getGeneralCorrection();
        driveTrain = DriveTrain.getAutoDriveTrain();
        this.angle = angle;
        this.relative = relative;
    }

    @Override
    public void start() {
        if (relative) {
            turnCorrection.setRelativeTargetAngle(angle);
        } else {
            turnCorrection.setTargetAngle(angle);
        }
    }

    @Override
    public void run() {
        driveTrain.setInput(() -> new DTInput(Vector.ZERO, new Angle(turnCorrection.get())));
    }

    @Override
    public void stop() {
        driveTrain.setDefaultInput();
    }

    @Override
    public boolean isDone() {
        return false;
    }
}
