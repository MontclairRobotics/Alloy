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

/**
 * A PID class that takes in tunable inputs to make PID tuning easier
 *
 * <p>Using a standard PID model, the user would have to re-deploy an test code which can end up
 * becoming a very long and drawn out process. By making them inputs, it is able to change these
 * values at runtime This could be done easily in FRC using smartdashboard or in FTC by using
 * buttons or joysticks to change values
 */
public class TuneablePID extends InputComponent<Double> implements ErrorCorrection {

    /**
     * Set the input of the error correction the input should be the source of what correction is
     * correcting. For example in a motor the input would be the encoder
     *
     * @param input the input to the error correction
     */
    @Override
    public void setInput(Input input) {}

    /**
     * Set the target for the correction When the input is equal to the target the error is 0
     *
     * @param target the goal of the error correction
     */
    @Override
    public void setTarget(Object target) {}

    /**
     * Get the value to apply the correction
     *
     * @return the correction
     */
    @Override
    public Object getCorrection() {
        return null;
    }
}
