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
package org.montclairrobotics.alloy.frc;

import com.kauailabs.navx.frc.AHRS;
import org.montclairrobotics.alloy.components.InputComponent;
import org.montclairrobotics.alloy.core.Gyro;

public class NavxGyro extends InputComponent<Double> implements Gyro {

    private AHRS navx;

    public NavxGyro(AHRS navx) {
        this.navx = navx;
    }

    @Override
    public double getRoll() {
        return navx.getRoll();
    }

    @Override
    public double getYaw() {
        return navx.getYaw();
    }

    @Override
    public double getPitch() {
        return navx.getPitch();
    }

    @Override
    public Double get() {
        if (status.isEnabled()) {
            return getYaw();
        } else {
            return 0D;
        }
    }

    public AHRS getNavx() {
        return navx;
    }

    @Override
    public void reset() {
        navx.reset();
    }
}
