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
import org.montclairrobotics.alloy.exceptions.InvalidConfigurationException;
import org.montclairrobotics.alloy.update.Update;

/**
 * Created by Montclair robotics on 2/27/2018
 *
 * <p>A PID controller is able to control a component that has a target, current state, and can be
 * controlled for example: - A robots heading using a Gryo - A motors position using encoders
 *
 * <p>PID stands for Proportion, Integral and Derivative control. It uses the error of a component
 * and then calculates the correction the PID values must be tuned in order to have a working PID
 * loop A properly tuned implementation of a PID controller should result in a correction that goes
 * fast, and accurate to its target, and does not overshoot it.
 *
 * <p>You can read more about implementing PID control here
 * <link>https://github.com/GarrettBurroughs/Alloy/wiki/Using-PID-control</link> You can read more
 * about how PID works here <link>https://en.wikipedia.org/wiki/PID_controller</link>
 *
 * @author Garrett Burroughs
 * @since 0.1
 * @version 0.1
 */
public class PID extends InputComponent<Double> implements ErrorCorrection<Double> {
    private double p;
    private double i;
    private double d;
    private double target;

    /** The error of the PID, calculated by the target - input */
    private double error;
    /**
     * The rate that the error is changing on a certain interval (Slope of the error if it were
     * graphed) AKA derivative
     */
    private double errorRate;

    /**
     * The total error that has accumulated over time (Area under the graph if the error were
     * graphed) AKA Integral
     */
    private double totalError;

    /** The error of the previous calculation, used for calculating the rate of error */
    private double prevError;

    /** The time of the previous calculation, used for calculating the rate of error */
    private double prevTime;

    /** The difference in time between update loops */
    private double timeDifference;

    /** The difference in error between update loops */
    private double errorDifference;

    /**
     * Create a new PID
     *
     * @param p proportional constant
     * @param i integral constant
     * @param d derivative constant
     */
    public PID(double p, double i, double d) {
        this.p = p;
        this.i = i;
        this.d = d;
    }

    /**
     * Create a new PID with the input and target defined
     *
     * @param p proportional constan
     * @param i integral constant
     * @param d derivative constant
     * @param input the input of the component being controlled (current status)
     * @param target the desired target (status) of the component being controlled
     */
    public PID(double p, double i, double d, Input<Double> input, double target) {
        this.p = p;
        this.i = i;
        this.d = d;
        super.input = input;
        this.target = target;
    }

    @Override
    public PID setTarget(Double target) {
        this.target = target;
        return this;
    }

    @Override
    public PID setInput(Input<Double> input) {
        this.input = input;
        return this;
    }

    @Update
    public void update() {

        // Calculate Error
        try {
            error = target - input.get();
        } catch (NullPointerException e) {
            throw new InvalidConfigurationException(
                    "PID input has not been defined use pid.setInput(Input<Double> input), to set it");
        }

        // calculate time difference in time since the last update
        timeDifference = prevTime - System.currentTimeMillis();

        // calculate the difference in error since the last update
        errorDifference = prevError - error;

        // Calculate slope of the error (Derivative) change in error / change in time
        try {
            errorRate = errorDifference / timeDifference;
        } catch (ArithmeticException e) {
            errorRate = 0;
        }

        // Calculate Error Integral
        totalError += error  * timeDifference;

        // Calculate Correction and set the output
        if (status.isEnabled()) {
            output = p * error + i * totalError + d * errorRate;
        } else {
            output = 0d;
        }
        prevError = error;
        prevTime = System.currentTimeMillis() / 1000d;
    }

    /** @return the calculated correction */
    @Override
    public Double getCorrection() {
        return output;
    }

    /** @return A copy of the error correction */
    @Override
    public ErrorCorrection copy() {
        return new PID(p, i, d).setTarget(target).setInput(input);
    }
}
