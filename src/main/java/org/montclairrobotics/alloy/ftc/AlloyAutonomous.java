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
package org.montclairrobotics.alloy.ftc;

import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.util.ElapsedTime;
import org.montclairrobotics.alloy.auto.State;
import org.montclairrobotics.alloy.auto.StateMachine;
import org.montclairrobotics.alloy.core.RobotCore;

/**
 * Created by MHS Robotics on 12/5/2017.
 *
 * AlloyAutonomous is the core framework for coding auto modes
 * When an auto mode extends AlloyAutonomous, it needs to override
 * the setup() method, and has the option of overriding the userLoop()
 * method. In the setup method, <code>auto<code/> needs to be set to a
 * state machine, that will be run during the auto mode
 *
 * The optional userLoop() method can be used to update or perform
 * actions every loop, it is called before the state in the state machine
 *
 * @author Garrett Burroughs
 * @version 0.1
 * @since 0.1
 */
public abstract class AlloyAutonomous extends OpMode {

  /** Keeps track of when the auto mode is running (True after started and before finished) */
  boolean running;

  /** The actual auto mode that should be instantiated in setup */
  public StateMachine auto;

  /** A timer to keep track of time in the autoMode */
  public ElapsedTime timer;

  /**
   * This is where the user should define all their code and where "Auto", should be instantiated
   */
  public abstract void setup();

  /**
   * Runs when the play button is pressed Start will set up everything that the auto mode needs to
   * run
   */
  @Override
  public void start() {
    auto.start(); // run the start method of the state machine
    running = true; // the state machine has started running
    timer.reset(); // reset the timer
  }

  /**
   * Init is called when the INIT button is pressed on the drivers station. The init method takes
   * care of setting up global robot variables and running the user setup method
   */
  @Override
  public void init() {
    new RobotCore(telemetry, hardwareMap, gamepad1, gamepad2);
    setup();
  }

  /**
   * The user loop can be overridden in the auto mode and is called every loop before the state is
   * run. This can be useful for updating information used in states.
   */
  public void userLoop() {}

  /** the Loop method takes care of running the state machine */
  @Override
  public void loop() {
    userLoop();
    if (running) {
      auto.run(); // Run the state machine, which will in turn run the states
    }
    if (auto.isDone()) { // check if the state machine has finished (Last state achieved)
      running = false; // stop the state machine
      auto.stop(); // Finally stop the state machine
    }
  }

  /**
   * A wrapper around the add state method to allow the user to add states to the auto mode
   *
   * @param state the state to be added
   */
  public void newState(State state) {
    auto.addState(state);
  }
}
