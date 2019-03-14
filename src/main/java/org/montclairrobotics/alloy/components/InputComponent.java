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
package org.montclairrobotics.alloy.components;

import java.util.ArrayList;
import org.montclairrobotics.alloy.utils.Input;

/**
 * An input component is an extension of a regular component, used for input and control systems
 *
 * <p>Input components take in a standard input, and have the ability to manipulate them using a
 * number of steps that can be added. Steps take in the input, perform some sort of manipulation or
 * calculation and then return the new value. Steps are applied in order of them being added, and
 * the output of one step gets passed in as the input of the next step. The steps are only applied
 * if the applySteps() method is called
 *
 * <p>Input components can also be enabled, or disabled just like regular components, and will
 * return their calculated value as a regular input
 *
 * @param <T> the type of the input
 * @author Garrett Burroughs
 * @version 0.1
 * @since 0.1
 */
public abstract class InputComponent<T> extends Component implements Input {

    /** The initial input value */
    public Input<T> input;

    /** The value after steps have been applied */
    public T output;

    /** An arraylist to store all of the steps */
    public ArrayList<Step<T>> steps = new ArrayList<>();

    @Override
    public T get() {
        return output;
    }

    /**
     * Apply all of the steps, in order, taking the output of one step and passing it as the input
     * to the next
     */
    public void applySteps() {
        // Start off with the input
        T calculation = input.get();
        // Apply steps
        for (Step<T> step : steps) {
            calculation = step.getOutput(calculation);
        }
        // Set the output
        output = calculation;
    }

    /** A daisy-chainable method that adds a step to the input component */
    public InputComponent addStep(Step s) {
        // addDebug(new Debug(), );
        steps.add(s);
        return this;
    }

    /** A daisy-chainable method that sets the initial input of the input component */
    public InputComponent<T> setInput(Input<T> input) {
        this.input = input;
        return this;
    }
}
