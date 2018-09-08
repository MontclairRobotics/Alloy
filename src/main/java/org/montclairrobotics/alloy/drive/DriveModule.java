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

import org.montclairrobotics.alloy.core.Encoder;
import org.montclairrobotics.alloy.core.Motor;
import org.montclairrobotics.alloy.motor.MotorModule;
import org.montclairrobotics.alloy.utils.ErrorCorrection;
import org.montclairrobotics.alloy.vector.Vector;

public class DriveModule extends MotorModule {
    /**
     * Create a fully functioning motor module
     *
     * <p>Modules created like this will be able to adjust their speed based on an error correction
     * to maintain a more accurate speed.
     *
     * @param direction the direction that the module runs (for use in motor groups)
     * @param encoder the encoder that keeps track of the motors position
     * @param powerControl an error correction to adjust the speed of the module
     * @param motors the motors that the module controls
     */
    public DriveModule(
            Vector direction,
            Encoder encoder,
            ErrorCorrection<Double> powerControl,
            Motor... motors) {
        super(direction, encoder, powerControl, motors);
    }
}
