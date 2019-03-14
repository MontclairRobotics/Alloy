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

import org.montclairrobotics.alloy.components.Step;
import org.montclairrobotics.alloy.utils.Toggleable;
import org.montclairrobotics.alloy.vector.Vector;

/**
 * A step that returns 0 if the inputs magnitude is under a certain threshold
 *
 * @author Garrett Burroughs
 * @version 0.1
 * @since 0.1
 */
public class VectorDeadzone extends Toggleable implements Step<Vector> {

    /** the threshold value that the input has to be lower than for the step to return 0 */
    private double tolerance;

    /** Create a VectorDeadzone specifying the tolerance */
    public VectorDeadzone(double tolerance) {
        this.tolerance = tolerance;
    }

    /** Create a VectorDeadzone with a default tolerance of 0.05 */
    public VectorDeadzone() {
        this(0.05);
    }

    @Override
    public Vector getOutput(Vector input) {
        if (status.isEnabled()) {
            return input.getManitude() < tolerance ? Vector.ZERO : input;
        } else {
            return input;
        }
    }

    @Override
    public void enableAction() {
        // Do nothing because all action is taken care of
    }

    @Override
    public void disableAction() {
        // Do nothing because all action is taken care of
    }

    /** @return the threshold value */
    public double getTolerance() {
        return tolerance;
    }

    /** set the threshold value */
    public void setTolerance(double tolerance) {
        this.tolerance = tolerance;
    }
}
