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
 * Created by MHS Robotics on 3/12/2018.
 *
 * <p>A monitor is a class that takes in 2 inputs, and returns true when they are within a certain
 * tolerance This can be good for monitoring if certain systems are working, for example if a motor
 * is spinning at a desired speed and can also be used to monitor a sensor to see if it has reached
 * a certain value.
 *
 * @author Garrett Burroughs
 * @since 0.1
 */
public class Monitor implements Input<Boolean> {
  /** The desired target for the input */
  public final Input<Double> target;

  /** The input to be checked */
  public final Input<Double> input;

  /** How far the input can be away from the target while still being true */
  public double tolerance;

  public Monitor(Input<Double> target, Input<Double> input, double tolerance) {
    this.target = target;
    this.input = input;
    this.tolerance = tolerance;
  }

  @Override
  public Boolean get() {
    return Math.abs(target.get() - input.get()) < tolerance;
  }
}
