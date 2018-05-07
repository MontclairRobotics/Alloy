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
package org.montclairrobotics.alloy.auto;

/**
 * Created by MHS Robotics on 12/16/2017.
 *
 * <p>States are designed to be passed in and run in a state machine. States can be used for
 * Autonomous modes as well as autonomously doing actions in teleop.
 *
 * @see StateMachine
 * @see AlloyAutonomous
 * @author Garrett Burroughs
 * @since 0.1
 * @version 0.1
 */
public abstract class State {

    Integer nextState = null;

    /** The start method is the first thing called when the state is run */
    public abstract void start();

    /** The run method is called every loop while the state is running */
    public abstract void run();

    /** The Stop method is the last thing called once the state is done */
    public abstract void stop();

    /**
     * IsDone should return true when the state is finished
     *
     * @return true if the state is done
     */
    public abstract boolean isDone();

    public int getNextState(int currentState) {
        if (nextState != null) {
            return nextState;
        }
        return currentState + 1;
    }
}
