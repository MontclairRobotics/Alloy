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
package org.montclairrobotics.alloy.steps;

import org.montclairrobotics.alloy.components.Step;
import org.montclairrobotics.alloy.drive.DTInput;
import org.montclairrobotics.alloy.utils.Toggleable;
import org.montclairrobotics.alloy.vector.Angle;

public class Sensitivity extends Toggleable implements Step<DTInput> {
    public double driveSensitivity;
    public double turnSensitivity;

    public Sensitivity(double driveSensitivity, double turnSensitivity) {
        this.driveSensitivity = driveSensitivity;
        this.turnSensitivity = turnSensitivity;
    }

    public Sensitivity(double sensitivity) {
        this(sensitivity, sensitivity / 2);
    }

    /** Method to be called when the toggleable is enabled */
    @Override
    public void enableAction() {
        // Sensitivity does not have to do anything when enabled
    }

    /** Method to be called when the toggleable is disabled */
    @Override
    public void disableAction() {
        // Sensitivity does not have to do anything when disabled
    }

    /**
     * The operation to be performed on the input, to get the output
     *
     * @param input
     */
    @Override
    public DTInput getOutput(DTInput input) {
        if (status.isEnabled()) {
            return new DTInput(
                    input.getTranslation().scale(driveSensitivity),
                    new Angle(input.getRotation().getDegrees() * turnSensitivity));
        } else {
            return input;
        }
    }

    public void setDriveSensitivity(double driveSensitivity) {
        this.driveSensitivity = driveSensitivity;
    }

    public void setTurnSensitivity(double turnSensitivity) {
        this.turnSensitivity = turnSensitivity;
    }

    public void setSensitivity(double driveSensitivity, double turnSensitivity){
        this.driveSensitivity = driveSensitivity;
        this.turnSensitivity = turnSensitivity;
    }
}
