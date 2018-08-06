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

/**
 * A interface that defines the process of correcting an error
 *
 * <p>Error correction can be implemented in different ways but is very important in creating a
 * funcitonal robot. The most common
 *
 * <p>Most error corrections calculate the error based on the difference of the input and the target
 * and then return an output correction based on that.
 *
 * <p>Some common examples of error corrections are - PID Loop - Bang Bang control
 *
 * @see PID
 * @param <T> the type of error that will be corrected
 * @version 0.1
 * @since 0.1
 */
public interface ErrorCorrection<T> {

    /**
     * Set the input of the error correction the input should be the source of what correction is
     * correcting. For example in a motor the input would be the encoder
     *
     * @param input the input to the error correction
     */
    public ErrorCorrection<T> setInput(Input<T> input);

    /**
     * Set the target for the correction When the input is equal to the target the error is 0
     *
     * @param target the goal of the error correction
     */
    public ErrorCorrection setTarget(T target);

    /**
     * Get the value to apply the correction
     *
     * @return the correction
     */
    public T getCorrection();

    /** @return A copy of the error correction */
    public ErrorCorrection copy();
}
