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
import org.montclairrobotics.alloy.drive.DTInput;
import org.montclairrobotics.alloy.drive.DriveTrain;
import org.montclairrobotics.alloy.utils.ConstantInput;
import org.montclairrobotics.alloy.vector.Angle;
import org.montclairrobotics.alloy.vector.Polar;
import org.montclairrobotics.alloy.vector.Vector;

/**
 * Drives the robot for a specified amount of time at a specified power
 *
 * <p>This auto state allows the robot to drive for a given time Auto movement like this is not
 * recommended, as there are many different variables that can impact the movement of the robot.
 * Including but not limited to: - Battery Power - Friction on surfaces - Motor malfunctions
 *
 * <p>It is recommended that you use sensors for accurate measurements and/or closed loop control
 *
 * @see DriveEncoders
 */
public class DriveTime extends State {
  /** the time in seconds the robot will drive for */
  private final double time;

  /** the power the robot wil drive at */
  private final double power;

  /** the drive train this state will use, this is set by defualt to be the alloy drive train */
  private final DriveTrain driveTrain;

  /** the time the state starts at */
  private long startTimeMillis;

  /**
   * Create a new DriveTime state
   *
   * <p>This constructor will set the next state to be the sequential state
   *
   * @param time how long the robot wll drive for
   * @param power how fast the robot will drive
   */
  public DriveTime(double time, double power) {
    this.driveTrain = DriveTrain.getAutoDriveTrain();
    this.time = time;
    this.power = power;
  }

  /**
   * Create a new DriveTime state specifing the next state
   *
   * <p>This constructor will set the next state
   *
   * @param time how long the robot iwll drive for
   * @param power how fast the robot will drive
   * @param nextState the state to go to after this state is done
   */
  public DriveTime(double time, double power, int nextState) {
    super(nextState);
    this.driveTrain = DriveTrain.getAutoDriveTrain();
    this.time = time;
    this.power = power;
  }

  @Override
  public void start() {
    startTimeMillis = System.currentTimeMillis();
  }

  @Override
  public void run() {
    driveTrain.setInput(
        new ConstantInput<DTInput>(new DTInput(new Polar(power, Angle.ZERO), Angle.ZERO)));
  }

  @Override
  public void stop() {
    driveTrain.setInput(new ConstantInput<>(new DTInput(Vector.ZERO, Angle.ZERO)));
  }

  @Override
  public boolean isDone() {
    return startTimeMillis / 1000 > time;
  }
}
