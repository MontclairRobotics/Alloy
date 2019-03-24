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

import com.ctre.phoenix.motorcontrol.can.WPI_TalonSRX;
import org.montclairrobotics.alloy.core.Encoder;
import org.montclairrobotics.alloy.utils.Input;

/**
 * Created by MHS Robotics on 10/6/2018.
 *
 * @author Garrett Burroughs
 * @since
 */
public class FRCEncoder extends Encoder {
  private final Input<Integer> ticks;

  public FRCEncoder(edu.wpi.first.wpilibj.Encoder encoder) {
    ticks = () -> encoder.get();
  }

  public FRCEncoder(WPI_TalonSRX talon) {
    ticks = () -> talon.getSensorCollection().getQuadraturePosition();
  }

  /**
   * A method that should be overridden by the encoder
   *
   * @return the raw value of encoder ticks that the encoder reads
   */
  @Override
  public int getRawTicks() {
    return ticks.get();
  }
}
