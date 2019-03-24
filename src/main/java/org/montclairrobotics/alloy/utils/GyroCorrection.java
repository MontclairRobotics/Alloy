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
package org.montclairrobotics.alloy.utils;

import org.montclairrobotics.alloy.components.InputComponent;
import org.montclairrobotics.alloy.core.Gyro;
import org.montclairrobotics.alloy.exceptions.InvalidConfigurationException;
import org.montclairrobotics.alloy.vector.Angle;

/**
 * A correction based on a gyroscope, to keep a consistent heading
 *
 * <p>The Gyro correction class uses a gyro, and an error correction to output a correction angle to
 * change the robots heading.
 *
 * <p>The gyro correction is used in various different turning functions throughout Alloy, and
 * therefore, a general correction has been defined in this class.
 *
 * <p>Since the specifics of gyro correction depends on each specific robot (because of different
 * wheels, motors, robot size etc.), the user does have to specify the correction on a per robot
 * basis (therefore going in the "robotSetup" method), but that same correction can and should be
 * used for the rest of the turning operations and corrections. Most built in classes in alloy that
 * use a Gyro Correction will automatically grab the "General Correction", and throw an error if it
 * has not been defined
 *
 * @author Garrett Burroughs
 * @version 0.1
 * @since 0.1
 */
public class GyroCorrection extends InputComponent<Double> {

  /**
   * The global robot gyro correction
   *
   * <p>Since the specifics of gyro correction depends on each specific robot (because of different
   * wheels, motors, robot size etc.), the user does have to specify the correction on a per robot
   * basis (therefore going in the "robotSetup" method), but that same correction can and should be
   * used for the rest of the turning operations and corrections. Most built in classes in alloy
   * that use a Gyro Correction will automatically grab the "General Correction", and throw an error
   * if it has not been defined
   */
  private static GyroCorrection generalCorrection;

  /** The gyro to get the robots current heading (and therefore used in correction calculation) */
  private Gyro gyro;

  /**
   * The Error Correction used to correct the heading
   *
   * <p>Although any type of error correction is able to be used, it is highly recommended that a
   * PID loop is used to control turing.
   */
  private ErrorCorrection<Double> correction;

  /** Set the general correction */
  public static void setGeneralCorrection(GyroCorrection correction) {
    GyroCorrection.generalCorrection = correction;
  }

  public static GyroCorrection getGeneralCorrection() {
    if (generalCorrection == null) {
      throw new InvalidConfigurationException(
          "Universal gyro correction has not been defined, but a part of "
              + "Alloy is attempting to use it. Set it using \"GyroCorrection.setGeneralCorrection(correction)\""
              + "or stop using gyro based turning/correction functions");
    }
    return GyroCorrection.generalCorrection;
  }

  /**
   * Create a new Gyro correction with a PID controller
   *
   * @param gyro the gyro used to get heading for correction calculation
   * @param correction the controller to fix the heading error
   */
  public GyroCorrection(Gyro gyro, PID correction) {
    this.gyro = gyro;
    this.correction = correction;
    correction.setInputConstraints(-180, 180);
    correction.setOutputConstraints(-1, 1);
  }

  /**
   * Set the target angle relative
   *
   * <p>Relative angles are based on the robots current position, this is mainly used for autonomous
   * movement, when the task is to move "x" degrees
   *
   * @param a the relative angle that the correction will target
   */
  public void setRelativeTargetAngle(Angle a) {
    correction.setTarget(correction.getTarget() + a.getDegrees());
  }

  /**
   * Set the absolute target angle
   *
   * <p>Absolute angles are in reference to the last time the gyro has been reset. This is mainly
   * used for keeping a constant heading
   *
   * @param a the absolute angle that the correction will target
   */
  public void setTargetAngle(Angle a) {
    correction.setTarget(a.getDegrees());
  }

  /** Return the error correction used in gyro correction calculations */
  public ErrorCorrection<Double> getCorrection() {
    return correction;
  }

  /**
   * reset the gyro
   *
   * <p>Note: This will reset the gyro the correction uses itself
   */
  public GyroCorrection reset() {
    gyro.reset();
    return this;
  }

  /** get the gyro used in gyro correction calculation */
  public Gyro getGyro() {
    return gyro;
  }

  @Override
  public Double get() {
    return correction.getCorrection();
  }
}
