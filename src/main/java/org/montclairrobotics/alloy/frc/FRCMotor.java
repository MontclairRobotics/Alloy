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
package org.montclairrobotics.alloy.frc;

import edu.wpi.first.wpilibj.SpeedController;
import org.montclairrobotics.alloy.components.Component;
import org.montclairrobotics.alloy.core.Motor;

/**
 * Created by MHS Robotics on 10/6/2018.
 *
 * @author Garrett Burroughs
 * @since
 */
public class FRCMotor extends Component implements Motor {
  protected final SpeedController controller;

  private double power;

  public FRCMotor(SpeedController controller) {
    this.controller = controller;
  }

  public FRCMotor(SpeedController controller, boolean inverted) {
    this(controller);
    setInverted(inverted);
  }

  /**
   * Sets the motor Power
   *
   * @param power the power that the motor will be set to [-1, 1]
   */
  @Override
  public void setMotorPower(double power) {
    this.power = power;
    if (status.isEnabled()) {
      controller.set(power);
    }
  }

  /**
   * Gets the motor power
   *
   * @return the current motor power, a value between (0-1)
   */
  @Override
  public double getMotorPower() {
    return controller.get();
  }

  /**
   * Sets weather the motor runs the default way , or inverted
   *
   * @param inverted true for inverted, false for normal
   */
  @Override
  public void setInverted(boolean inverted) {
    controller.setInverted(inverted);
  }

  /**
   * Gets weather the motor is inverted
   *
   * @return true if the motor is inverted
   */
  @Override
  public boolean getInverted() {
    return controller.getInverted();
  }

  /** The action that is taken when the component is disabled, should be overridden by the user */
  @Override
  public void disableAction() {
    controller.set(0);
  }

  /** Disables the toggleable */
  @Override
  public void disable() {
    controller.set(power);
  }

  public SpeedController getController() {
    return controller;
  }
}
