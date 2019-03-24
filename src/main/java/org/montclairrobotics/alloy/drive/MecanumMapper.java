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
package org.montclairrobotics.alloy.drive;

import org.montclairrobotics.alloy.motor.MotorModule;
import org.montclairrobotics.alloy.utils.Utils;

/**
 * Created by MHS Robotics on 1/24/2019.
 *
 * @author Jack Hymowitz
 * @since
 */
public class MecanumMapper implements DTMapper {
  /**
   * map the input to the modules
   *
   * @param input
   * @param modules
   */
  @Override
  public void map(DTInput input, MotorModule... modules) {
    double x = input.getTranslation().getX();
    double y = input.getTranslation().getY();
    double turn = input.getRotation().getDegrees();
    double maxPower = 1;

    double moduleSpeeds[] = new double[modules.length];

    for (int i = 0; i < modules.length; i++) {
      double xSign = Utils.sign(modules[i].getOffset().getX());
      double ySign = Utils.sign(modules[i].getOffset().getY());
      double directionSign = Utils.sign(modules[i].getOffset().cross(modules[i].getDirection()));

      double calculatedSpeed = (y * xSign * -1 + x * ySign + turn) * directionSign;
      moduleSpeeds[i] = calculatedSpeed;

      if (calculatedSpeed > maxPower) {
        maxPower = calculatedSpeed;
      }
    }
    for (int i = 0; i < modules.length; i++) {
      modules[i].setPower(moduleSpeeds[i] / maxPower);
    }
  }
}
