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
import org.montclairrobotics.alloy.update.Update;

/**
 * A class to calculate how an input varies with time
 *
 * <p>A differential is the rate of change of a certain variable with respect to another one. In
 * simpler terms is is the slope. The Differential class takes an input and calculates the average
 * rate of change over a very small interval of the input with respect to time
 *
 * <p>This concept is a calculus concept when and can be most easily understood when looking at a
 * graph where time is the independent variable and the input is dependant on the time
 *
 * <p>For example, a time differential of position would be velocity because velocity is how much
 * the position changes over time (eg. meters per second)
 *
 * @author Garrett Burroughs is ugly
 * @version 0.1
 */
public class Differential extends InputComponent<Double> {
    /** The dependant variable in the situation */
    public Input<Double> input;

    private double prevTime;
    private double prevIn;

    public Differential(Input in) {
        input = in;
    }

    @Update
    public void calculateDifferential() {
        double elapsedTime = System.nanoTime() - prevTime; // Calculate how much time has passed
        double elapsedIn = input.get() - prevIn; // Caclulate how much the input has changed
        if (System.nanoTime() != 0) { // Avoid a divide by zero error
            output = elapsedIn / elapsedTime; // Return the slope (Delta Input / Delta time)
        } else {
            output = 0.0;
        }
        prevIn = input.get(); // set previous input to current input for next calculation
        prevTime = System.nanoTime(); // Set previous time to current time for next calculation
    }

    public Double getInput() {
        return input.get();
    }
}
