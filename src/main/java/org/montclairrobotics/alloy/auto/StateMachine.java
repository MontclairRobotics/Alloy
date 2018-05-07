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

import java.util.ArrayList;

/**
 * Created by MHS Robotics on 12/16/2017.
 *
 * <p>A state machine takes in states and runs them in a controlled order.<br>
 * By default the states will run in a linear fashion and the next state will start when the
 * previous state has finished.<br>
 * <br>
 * State machines can be used for controlling auto modes, but can also be ran in teleop modes for
 * pre coded instructions that make driving easier.
 *
 * @author Garrett Burroughs
 * @since
 */
public class StateMachine extends State {

    ArrayList<State> states;

    /**
     * Since state machines can run in a non linear fashion, the last state in the array may not be
     * the last state in the state machine, finalState keeps track of what signifies the end of the
     * state machine. The final state should not be in reference to an actual state, but a number
     * that is outside the index of the state machine.<br>
     * For example, if a state machine had 5 states in it, a valid final state would could be 6, and
     * to end the state machine, you would simply have the last state you want to run, go to state 6
     * once it is done.<br>
     * Note: even if your state machine is running in a linear fashion, it needs to have a final
     * state.
     */
    Integer finalState;

    public StateMachine(State... states) {}

    @Override
    public void start() {}

    @Override
    public void run() {}

    @Override
    public void stop() {}

    @Override
    public boolean isDone() {
        return false;
    }

    public void addState(State state) {
        states.add(state);
    }
}
