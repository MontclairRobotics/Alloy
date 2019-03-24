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
package org.montclairrobotics.alloy.core;

import org.montclairrobotics.alloy.utils.Input;

public class LimitedMotor implements Motor {
    private final Input<Boolean> bottomLimit;
    private final Input<Boolean> topLimit;

    private final Motor motor;

    public LimitedMotor(Motor motor, Input<Boolean> bottomLimit, Input<Boolean> topLimit) {
        this.motor = motor;
        this.bottomLimit = bottomLimit;
        this.topLimit = topLimit;
    }

    @Override
    public void setMotorPower(double power) {
        if (power > 0 && bottomLimit.get()) {
            motor.setMotorPower(0);
        } else if (power < 0 && topLimit.get()) {
            motor.setMotorPower(0);
        } else {
            motor.setMotorPower(power);
        }
    }

    @Override
    public double getMotorPower() {
        return getMotorPower();
    }

    @Override
    public void setInverted(boolean inverted) {
        motor.setInverted(inverted);
    }

    @Override
    public boolean getInverted() {
        return motor.getInverted();
    }
}
