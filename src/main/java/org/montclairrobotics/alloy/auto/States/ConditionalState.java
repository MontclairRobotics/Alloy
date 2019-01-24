/*
MIT License

Copyright (c) 2019 Garrett Burroughs

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
package org.montclairrobotics.alloy.auto.States;

import org.montclairrobotics.alloy.auto.State;
import org.montclairrobotics.alloy.utils.Input;

/**
 * A conditional state runs a state if the condition is true
 *
 * <p>A conditional state takes in a boolean input, and evaluates it when the state starts. If the
 * input is true when the state starts, the passed in state will execute.
 */
public class ConditionalState extends State {
    /** The boolean input that will determine if the state is run */
    Input<Boolean> condition;

    /** The state that will be run, if the input is true */
    State state;

    /** A variable to keep track whether or not the state should be run */
    boolean run;

    public ConditionalState(Input<Boolean> condition, State state) {
        this.condition = condition;
        this.state = state;
    }

    @Override
    public void start() {
        run = condition.get(); // Evaluate the condition
        if (run) {
            state.start();
        }
    }

    @Override
    public void run() {
        if (run) {
            state.run();
        }
    }

    @Override
    public void stop() {
        if (run) {
            state.stop();
        }
    }

    @Override
    public boolean isDone() {
        if (run) {
            return state.isDone();
        } else {
            return true;
        }
    }
}
