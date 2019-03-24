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
import org.montclairrobotics.alloy.drive.DTInput;
import org.montclairrobotics.alloy.drive.DriveTrain;
import org.montclairrobotics.alloy.utils.ConstantInput;
import org.montclairrobotics.alloy.vector.Angle;
import org.montclairrobotics.alloy.vector.Polar;
import org.montclairrobotics.alloy.vector.Vector;

/**
 * An auto state that drives the robot for a given distance
 *
 * <p>This auto state uses encoders on the motors to determine when the motors have reached their
 * position
 *
 * @author Garrett Burroughs
 * @since 0.1
 */
public class DriveEncoders extends State {

  /** The drive train the auto state uses to drive */
  private final DriveTrain driveTrain;

  /** the speed the motors will run at */
  private final double speed;

  /** The distance (encoder distance) */
  private final double distance;

  /** Storing the starting encoder values */
  private int[] startValues;

  /** The tolerance for detecting if the robot has reached its final position */
  private static int tolerance = 5;

  /**
   * Sets the global tolerance
   *
   * <p>The tolerance is used in the calculation to determine if the motor has reached its final
   * position
   *
   * <p>For encoders that have more ticks per rotation, you may want to choose a higher tolerance,
   * as smaller movements will cause a greater change in encoder ticks
   *
   * @param tolerance
   */
  public static void setTolerance(int tolerance) {
    DriveEncoders.tolerance = tolerance;
  }

  /**
   * Create a new state that drives a certain distance
   *
   * <p>A drive encoder state will drive at the specified speed until the encoders reach the
   * specified distance
   *
   * @param speed the speed to drive at
   * @param distance the distance to drive (determined by encoders)
   */
  public DriveEncoders(double speed, double distance) {
    driveTrain = DriveTrain.getAutoDriveTrain();
    this.speed = speed;
    this.distance = distance;
  }

  /**
   * Create a new state that drives a certain distance, specifing the next state
   *
   * <p>A drive encoder state will drive at the specified speed until the encoders reach the
   * specified distance
   *
   * <p>This constructor will set the next state
   *
   * @param speed the speed to drive at
   * @param distance the distance to drive (determined by encoders)
   * @param nextState the next state to go to when this state is finished
   */
  public DriveEncoders(double speed, double distance, int nextState) {
    super(nextState);
    driveTrain = DriveTrain.getAutoDriveTrain();
    this.speed = speed;
    this.distance = distance;
  }

  @Override
  public void start() {
    startValues = driveTrain.getEncoderValues();
  }

  @Override
  public void run() {
    driveTrain.setInput(
        new ConstantInput<DTInput>(new DTInput(new Polar(speed, Angle.ZERO), Angle.ZERO)));
  }

  @Override
  public void stop() {
    driveTrain.setInput(new ConstantInput<DTInput>(new DTInput(Vector.ZERO, Angle.ZERO)));
  }

  @Override
  public boolean isDone() {
    // Done when the average encoder values are within
    // a tolerance range of the specified value
    int[] currentValues = driveTrain.getEncoderValues();
    double total = 0;
    for (int i = 0; i < startValues.length; i++) {
      total += currentValues[i] - startValues[i];
    }
    double average = total / (double) currentValues.length;
    return Math.abs(distance - average) < tolerance;
  }
}
