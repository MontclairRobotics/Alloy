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

import java.lang.Double;
import org.montclairrobotics.alloy.components.InputComponent;
import org.montclairrobotics.alloy.update.Update;

public class BangBang extends InputComponent<Double> implements ErrorCorrection<Double> {

    public double target;
    public double lowOut;
    public double highOut;

    public BangBang(double lowOut, double highOut) {
        this.lowOut = lowOut;
        this.highOut = highOut;
    }

    public BangBang(double correction) {
        this(-correction, correction);
    }

    /**
     * Set the target for the correction When the input is equal to the target the error is 0
     *
     * @param target the goal of the error correction
     */
    @Override
    public BangBang setTarget(Double target) {
        this.target = target;
        return this;
    }

    @Override
    public BangBang setInput(Input input){
        this.input = input;
        return this;
    }

    /**
     * Get the value to apply the correction
     *
     * @return the correction
     */
    @Override
    public Double getCorrection() {
        return output;
    }

    /**
     * @return A copy of the error correction
     */
    @Override
    public BangBang copy() {
        return new BangBang(lowOut, highOut).setTarget(target).setInput(input);
    }

    @Update
    public void calculateCorrection() {
        if (input.get() > target) {
            output = highOut;
        } else if (input.get() < target) {
            output = lowOut;
        } else {
            output = 0d;
        }
    }
}
