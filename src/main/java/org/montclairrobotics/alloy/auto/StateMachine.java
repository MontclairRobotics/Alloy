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

import com.qualcomm.robotcore.util.ElapsedTime;
import java.util.ArrayList;
import java.util.Arrays;
import org.montclairrobotics.alloy.components.Component;

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
 * <p>State machines are also states themselves so a state machine can run another state machine.
 * This allows for the reuse of auto code
 *
 * @author Garrett Burroughs
 * @since 0.1
 * @version 0.1
 */
public class StateMachine extends State {

  /** The states list keeps track of all of the states that will be run in the state machine */
  ArrayList<State> states;

  /**
   * Since state machines can run in a non linear fashion, the last state in the array may not be
   * the last state in the state machine, finalState keeps track of what signifies the end of the
   * state machine. The final state should not be in reference to an actual state, but a number that
   * is outside the index of the state machine.<br>
   * For example, if a state machine had 5 states in it, a valid final state would could be 6, and
   * to end the state machine, you would simply have the last state you want to run, go to state 6
   * once it is done.<br>
   * Note: even if your state machine is running in a linear fashion, it needs to have a final
   * state.
   */
  Integer finalState;

  /** The name of the state machine, this is purely for debugging purposes */
  private String name;

  /**
   * A boolean that keeps track of weather or not the current state has been run yet, this is used
   * to determine it the start() method of the state should be run
   */
  boolean stateStarted;

  /** the time spent in the state that has most recently finished */
  double timeInLastState;

  /** A timer object to keep track of the time in states. */
  private ElapsedTime timer;

  /** Keeps track of weather or not the state machine has finished, True if it has */
  boolean done = false;

  /** Keeps track of the current state */
  int state;

  public StateMachine(String name, int finalState, State... states) {
    this.name = name;
    this.states = new ArrayList<>(Arrays.asList(states));
    this.finalState = finalState;
    description = "A state machine";
  }

  public StateMachine(String name, State... states) {
    this(name, states.length, states);
  }

  public StateMachine(String name, String description, int finalState, State... states) {
    this(name, finalState, states);
    super.description = description;
  }

  /** Read out that the state has started and reset the timer */
  @Override
  public void start() {
    Component.debugger.driverInfo("Running", name);
    timer.reset();
  }

  /** The run method takes care of actually running the states */
  @Override
  public void run() {
    State currentState = states.get(state);

    // Check If the state has started, if it hasn't run the 'start()' method
    if (!stateStarted) {
      timer.reset();
      currentState.start();
      stateStarted = true; // State has been started
    }

    currentState.run(); // run the state

    if (currentState.isDone()) { // check if the state has finished
      timeInLastState = timer.nanoseconds(); // Update the last state time
      currentState.stop(); // Run the stop() method on the state
      if (currentState.getNextState(state) == finalState) { // Check if the state machine is done
        done = true; // set the state machine as done
        return; // return (exit) out of the run() method
      }
      if (currentState.getNextState(state)
          < states.size()) { // make sure there is a next state to go to
        state = currentState.getNextState(state); // go to the next state
      } else {
        Component.debugger.error(
            "STATE MACHINE OUT OF BOUNDS"); // Give the user an error if there is no
        // next state to go to
        done = true; // stop the state machine
        return; // exit the run method to ensure nothing else runs
      }
      stateStarted = false; // The next state has not started yet.
    }
    Component.debugger.test(
        name + " | State: " + state, currentState.debugInfo(state)); // Debug info about the state
  }

  /** When the state machine is finished, read out it has finished */
  @Override
  public void stop() {
    Component.debugger.test("Status", name + ", Finished ");
  }

  /**
   * determine if the state machine is done
   *
   * @return true if the state machine is done
   */
  @Override
  public boolean isDone() {
    return done;
  }

  /**
   * Adds a state to the state machine. States can be added to a state machine but should not be
   * added after the state machine
   *
   * @param state state that will be added to the state machine
   */
  public void addState(State state) {
    states.add(state);
  }

  /**
   * Gets the time in the most recently completed state
   *
   * @return The time in last state
   */
  public double getTimeInLastState() {
    return timeInLastState;
  }

  public String getName() {
    return name;
  }
}
