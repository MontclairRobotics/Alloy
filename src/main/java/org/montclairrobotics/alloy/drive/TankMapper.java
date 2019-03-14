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
package org.montclairrobotics.alloy.drive;

import org.montclairrobotics.alloy.components.Component;
import org.montclairrobotics.alloy.motor.MotorModule;
import org.montclairrobotics.alloy.utils.Utils;
import org.montclairrobotics.alloy.vector.Angle;
import org.montclairrobotics.alloy.vector.Polar;
import org.montclairrobotics.alloy.vector.Vector;

/**
 * @author Garrett Burroughs
 * @version 0.1
 * @since 0.1
 */
public class TankMapper extends Component implements DTMapper {

    private Vector maxSpeed;
    private Angle maxRotation;

    public TankMapper() {
        this(1, 1);
    }

    public TankMapper(Vector maxSpeed, Angle maxRotation) {
        this.maxSpeed = maxSpeed;
        this.maxRotation = maxRotation;
    }

    public TankMapper(double maxSpeed, double maxRotation) {
        this.maxSpeed = new Polar(maxSpeed, new Angle(0));
        this.maxRotation = new Angle(maxRotation);
    }

    @Override
    public void map(DTInput input, MotorModule... modules) {
        double inputSpeed = input.getTranslation().getManitude();
        double inputRotation = input.getRotation().getDegrees();

        // The values have a possibility of being from the maximum value, to their negative maximum
        // values and should be scaled to (-1,1) to be applied to the motors
        double scaledSpeed =
                Utils.map(inputSpeed, -maxSpeed.getManitude(), maxSpeed.getManitude(), -1, 1);
        double scaledRotation =
                Utils.map(
                        inputRotation, -maxRotation.getDegrees(), maxRotation.getDegrees(), -1, 1);

        // Calculate the right and left powers and constrain them to (-1, 1)
        double rightPower = Utils.constrain(scaledSpeed + scaledRotation, -1, 1);
        double leftPower = Utils.constrain(scaledSpeed - scaledRotation, -1, 1);

        for (MotorModule m : modules) {
            // Determine whether the module is on the right side or the left
            if (m.getOffset().getX() > 0) {
                m.setPower(rightPower);
            } else {
                m.setPower(leftPower);
            }
        }
    }
}
