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

public abstract class InputComponent<T> extends Component implements Input {

    public Input<T> input;
    public T output;

    public ArrayList<Step<T>> steps = new ArrayList<>();

    @Override
    public T get() {
        return output;
    }

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

    public InputComponent addStep(Step s) {
        // addDebug(new Debug(), );
        steps.add(s);
        return this;
    }

    public InputComponent<T> setInput(Input<T> input) {
        this.input = input;
        return this;
    }
}
