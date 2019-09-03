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
package org.montclairrobotics.alloy.auto.States;

import org.montclairrobotics.alloy.auto.State;
import org.montclairrobotics.alloy.drive.DTInput;
import org.montclairrobotics.alloy.drive.DriveTrain;
import org.montclairrobotics.alloy.utils.ConstantInput;
import org.montclairrobotics.alloy.vector.Angle;
import org.montclairrobotics.alloy.vector.Vector;

/**
 * Created by MHS Robotics on 1/26/2018.
 *
 * @author Garrett Burroughs
 * @since
 */
public class Turn extends State {

    private double speed;
    private double degrees;
    private int[] rightSideStartValues;
    private int[] leftSideStartValues;
    private DriveTrain driveTrain;

    private static double ticksPerDegree = 1;

    public static void setTicksPerDegree(double ticksPerDegree) {
        Turn.ticksPerDegree = ticksPerDegree;
    }

    private static double tolerance = 5;

    public static void setTolerance(double tolerance) {
        Turn.tolerance = tolerance;
    }

    public Turn(double speed, double degrees) {
        this.speed = speed;
        this.degrees = degrees;
        driveTrain = DriveTrain.getAutoDriveTrain();
    }

    @Override
    public void start() {
        rightSideStartValues = driveTrain.getRightEncoderValues();
        leftSideStartValues = driveTrain.getLeftEncoderValues();
    }

    @Override
    public void run() {
        if (degrees > 0) {
            driveTrain.setInput(
                    new ConstantInput<DTInput>(new DTInput(Vector.ZERO, new Angle(speed))));
        } else {
            driveTrain.setInput(
                    new ConstantInput<DTInput>(new DTInput(Vector.ZERO, new Angle(-speed))));
        }
    }

    @Override
    public void stop() {
        driveTrain.setInput(new ConstantInput<DTInput>(new DTInput(Vector.ZERO, Angle.ZERO)));
    }

    @Override
    public boolean isDone() {
        int rightTicks = 0;
        int[] currentRight = driveTrain.getRightEncoderValues();
        for (int i = 0; i < rightSideStartValues.length; i++) {
            rightTicks += Math.abs(currentRight[i] - rightSideStartValues[i]);
        }

        int leftTicks = 0;
        int[] currentLeft = driveTrain.getLeftEncoderValues();
        for (int i = 0; i < leftSideStartValues.length; i++) {
            leftTicks += Math.abs(currentLeft[i] - leftSideStartValues[i]);
        }

        int ticksMoved = rightTicks + leftTicks;

        return Math.abs(ticksMoved - degrees * ticksPerDegree) < tolerance;
    }
}
