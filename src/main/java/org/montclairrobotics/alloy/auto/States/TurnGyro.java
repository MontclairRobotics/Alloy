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
 * @author Garrett Burroughs
 * @since 0.1
 */
public class TurnGyro extends State {
    private final GyroCorrection turnCorrection;
    private final Angle angle;
    private final boolean relative;
    private final DriveTrain driveTrain;

    public TurnGyro(double angle, boolean relative) {
        turnCorrection = GyroCorrection.getGeneralCorrection();
        driveTrain = DriveTrain.getAutoDriveTrain();
        this.angle = new Angle(angle);
        this.relative = relative;
    }

    public TurnGyro(Angle angle, boolean relative) {
        turnCorrection = GyroCorrection.getGeneralCorrection();
        driveTrain = DriveTrain.getAutoDriveTrain();
        this.angle = angle;
        this.relative = relative;
    }

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
