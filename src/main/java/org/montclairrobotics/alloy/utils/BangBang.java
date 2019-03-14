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
 * Simple error correction, that returns one value if the error is high, and another if it is low
 *
 * <p>Bang bang error correction, checks if the error is higher or lower than a target, and returns
 * an output based on that result. This is not a very good, accurate or reliable way of error
 * correcting but can be useful in some cases where more exact error correction is not needed
 *
 * @author Garrett Burroughs
 * @version 0.1
 * @since 0.1
 */
public class BangBang extends InputComponent<Double> implements ErrorCorrection<Double> {

    public double target;
    public double lowOut;
    public double highOut;
    public double tolerance;

    public BangBang(double lowOut, double highOut, double tolerance) {
        this.lowOut = lowOut;
        this.highOut = highOut;
        this.tolerance = tolerance;
    }

    public BangBang(double correction, double tolerance) {
        this(-correction, correction, tolerance);
    }

    public BangBang(double correction) {
        this(correction, 0);
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
    public BangBang setInput(Input input) {
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

    /** @return A copy of the error correction */
    @Override
    public BangBang copy() {
        return new BangBang(lowOut, highOut).setTarget(target).setInput(input);
    }

    /** @return the current target that the error correction is trying to correct to */
    @Override
    public Double getTarget() {
        return target;
    }

    @Override
    public Double get() {
        if (input.get() > target + tolerance) {
            return highOut;
        } else if (input.get() < target - tolerance) {
            return lowOut;
        } else {
            return 0d;
        }
    }
}
