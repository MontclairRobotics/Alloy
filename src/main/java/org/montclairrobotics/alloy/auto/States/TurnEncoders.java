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
import org.montclairrobotics.alloy.vector.Vector;

/**
 * An auto state that turns the robot based on encoders
 *
 * <p>Although encoder turning is more accurate and consistent than time turning, it is still
 * suggested that you use a Gyro if possible for the most accurate turning
 *
 * <p>The turning in this state can only be relative
 *
 * @author Garrett Burroughs
 * @since 0.1
 */
public class TurnEncoders extends State {

  /** The speed the robot will turn at */
  private final double speed;

  /** The angle the robot will turn, this is relative */
  private final double degrees;

  /** Right starting values, used in turn calculation */
  private int[] rightSideStartValues;

  /** Right starting values, used in turn calculation */
  private int[] leftSideStartValues;

  /** The drive train the state will use to turn */
  private final DriveTrain driveTrain;

  /**
   * The "Turning constant". a conversion factor used for turning calculations
   *
   * <p>This value can be computed mathematically, or experimentally
   *
   * <p>The mathematical form is: (Robot Diameter)*(PI)/360 * (ticks per inch)
   */
  private static double ticksPerDegree = 1;

  /**
   * Set the turning constant This value can be computed mathematically, or experimentally
   *
   * <p>The mathematical form is: (Robot Diameter)*(PI)/360 * (ticks per inch)
   *
   * @param ticksPerDegree the conversion rate from ticks to degrees
   */
  public static void setTicksPerDegree(double ticksPerDegree) {
    TurnEncoders.ticksPerDegree = ticksPerDegree;
  }

  /** The tolerance for detecting if the robot has reached its final rotation */
  private static double tolerance = 5;

  /** set the turning tolerance */
  public static void setTolerance(double tolerance) {
    TurnEncoders.tolerance = tolerance;
  }

  /**
   * Create a new TurnEncoders state
   *
   * <p>This state will turn the robot determining if it has reached its final position based on
   * encoders
   *
   * @param speed the speed to turn the robot
   * @param degrees how many degrees to turn the robot (Relative)
   */
  public TurnEncoders(double speed, double degrees) {
    driveTrain = DriveTrain.getAutoDriveTrain();
    this.speed = speed;
    this.degrees = degrees;
  }

  /**
   * Create a new TurnEncoders state
   *
   * <p>This state will turn the robot determining if it has reached its final position based on
   * encoders
   *
   * <p>This constructor specifies the next state to go to
   *
   * @param speed the speed to turn the robot
   * @param degrees how many degrees to turn the robot (relative)
   * @param nextState the state to go to when this state is done
   */
  public TurnEncoders(double speed, double degrees, int nextState) {
    super(nextState);
    driveTrain = DriveTrain.getAutoDriveTrain();
    this.speed = speed;
    this.degrees = degrees;
  }

  @Override
  public void start() {
    rightSideStartValues = driveTrain.getRightEncoderValues();
    leftSideStartValues = driveTrain.getLeftEncoderValues();
  }

  @Override
  public void run() {
    if (degrees > 0) {
      driveTrain.setInput(new ConstantInput<DTInput>(new DTInput(Vector.ZERO, new Angle(speed))));
    } else {
      driveTrain.setInput(new ConstantInput<DTInput>(new DTInput(Vector.ZERO, new Angle(-speed))));
    }
  }

  @Override
  public void stop() {
    driveTrain.setInput(new ConstantInput<DTInput>(new DTInput(Vector.ZERO, Angle.ZERO)));
  }

  @Override
  public boolean isDone() {
    int rightTicks = 0;
    int[] currentRight = driveTrain.getRightEncoderValues();
    for (int i = 0; i < rightSideStartValues.length; i++) {
      rightTicks += Math.abs(currentRight[i] - rightSideStartValues[i]);
    }

    int leftTicks = 0;
    int[] currentLeft = driveTrain.getLeftEncoderValues();
    for (int i = 0; i < leftSideStartValues.length; i++) {
      leftTicks += Math.abs(currentLeft[i] - leftSideStartValues[i]);
    }

    int ticksMoved = rightTicks + leftTicks;

    return Math.abs(ticksMoved - degrees * ticksPerDegree) < tolerance;
  }
}
