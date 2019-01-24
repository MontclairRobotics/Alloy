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
import org.montclairrobotics.alloy.core.Alloy;
import org.montclairrobotics.alloy.drive.DTInput;
import org.montclairrobotics.alloy.drive.DriveTrain;
import org.montclairrobotics.alloy.utils.ConstantInput;
import org.montclairrobotics.alloy.vector.Angle;
import org.montclairrobotics.alloy.vector.Polar;
import org.montclairrobotics.alloy.vector.Vector;

/**
 * Created by MHS Robotics on 1/26/2018.
 *
 * @author Garrett Burroughs
 * @since
 */
public class Drive extends State {

    DriveTrain driveTrain;
    private double speed;
    private double distance;
    private int[] startValues;
    private static int tolerance = 5;

    public static void setTolerance(int tolerance) {
        Drive.tolerance = tolerance;
    }

    public Drive(double speed, double distance) {
        driveTrain = Alloy.getDriveTrain();
        this.speed = speed;
        this.distance = distance;
    }

    public Drive setDriveTrain(DriveTrain driveTrain) {
        this.driveTrain = driveTrain;
        return this;
    }

    @Override
    public void start() {
        startValues = driveTrain.getEncoderValues();
    }

    @Override
    public void run() {
        driveTrain.setInput(
                new ConstantInput<DTInput>(new DTInput(new Polar(speed, Angle.ZERO), Angle.ZERO)));
    }

    @Override
    public void stop() {
        driveTrain.setInput(new ConstantInput<DTInput>(new DTInput(Vector.ZERO, Angle.ZERO)));
    }

    @Override
    public boolean isDone() {
        int[] currentValues = driveTrain.getEncoderValues();
        double total = 0;
        for (int i = 0; i < startValues.length; i++) {
            total += currentValues[i] - startValues[i];
        }
        double average = total / (double) currentValues.length;
        return Math.abs(distance - average) < tolerance;
    }
}
