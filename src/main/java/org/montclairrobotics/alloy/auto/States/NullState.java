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
package org.montclairrobotics.alloy.auto.States;

import org.montclairrobotics.alloy.auto.State;

/**
 * A state that does nothing
 *
 * <p>This null state is put in place to prevent the code from crashing when no state is defined
 *
 * <p>This state will not do anything
 *
 * @author Garrett Burroughs
 * @since
 */
public class NullState extends State {
    /** The start method is the first thing called when the state is run */
    @Override
    public void start() {}

    /** The run method is called every loop while the state is running */
    @Override
    public void run() {}

    /** The Stop method is the last thing called once the state is done */
    @Override
    public void stop() {}

    /**
     * IsDone should return true when the state is finished
     *
     * @return true if the state is done
     */
    @Override
    public boolean isDone() {
        return true;
    }
}
