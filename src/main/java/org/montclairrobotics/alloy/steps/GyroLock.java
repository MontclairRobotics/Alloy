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

import org.montclairrobotics.alloy.components.InputComponent;
import org.montclairrobotics.alloy.components.Step;
import org.montclairrobotics.alloy.core.Gyro;
import org.montclairrobotics.alloy.drive.DTInput;
import org.montclairrobotics.alloy.utils.GyroCorrection;
import org.montclairrobotics.alloy.utils.Input;
import org.montclairrobotics.alloy.utils.Toggleable;
import org.montclairrobotics.alloy.vector.Angle;

public class GyroLock extends Toggleable implements Step<DTInput> {
    private GyroCorrection correction;

    public GyroLock(GyroCorrection correction) {
        this.correction = correction;
    }

    public GyroLock(){
        if(GyroCorrection.getGeneralCorrection() != null){
            correction = GyroCorrection.getGeneralCorrection();
        }else{
            throw new RuntimeException("Global Gyro Correction has not been set, set it using GyroCorrection.setGeneralCorrection()");
        }
    }

    @Override
    public void enableAction() {
        correction.setTargetAngle(new Angle(correction.getGyro().getYaw()));
    }

    public void disableAction() {
        // Do nothing
    }

    @Override
    public DTInput getOutput(DTInput input) {
        if(status.isEnabled()) {
            return input.addAngle(correction.get());
        }else{
            return input;
        }
    }
}