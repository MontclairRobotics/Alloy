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
package org.montclairrobotics.alloy.utils;

import org.montclairrobotics.alloy.components.InputComponent;
import org.montclairrobotics.alloy.core.Gyro;
import org.montclairrobotics.alloy.vector.Angle;

/**
 * A correction based on a gyroscope, to keep a consistent heading
 *
 * @author Garrett Burroughs
 * @version 0.1
 * @since 0.1
 */
public class GyroCorrection extends InputComponent<Double> {

    private static GyroCorrection generalCorrection;

    private Gyro gyro;
    private PID correction;

    public static void setGeneralCorrection(GyroCorrection correction) {
        GyroCorrection.generalCorrection = correction;
    }

    public static GyroCorrection getGeneralCorrection() {
        return GyroCorrection.generalCorrection;
    }

    public GyroCorrection(Gyro gyro, PID correction) {
        this.gyro = gyro;
        this.correction = correction;
        correction.setInputConstraints(-180, 180);
        correction.setOutputConstraints(-1, 1);
    }

    public void setRelativeTargetAngle(Angle a) {
        correction.setTarget(correction.getTarget() + a.getDegrees());
    }

    public void setTargetAngle(Angle a) {
        correction.setTarget(a.getDegrees());
    }

    public PID getCorrection() {
        return correction;
    }

    public GyroCorrection reset() {
        gyro.reset();
        return this;
    }

    public Gyro getGyro() {
        return gyro;
    }

    @Override
    public Double get() {
        return correction.getCorrection();
    }
}
