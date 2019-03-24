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
package org.montclairrobotics.alloy.frc;

import edu.wpi.first.wpilibj.AnalogInput;
import org.montclairrobotics.alloy.components.InputComponent;
import org.montclairrobotics.alloy.core.UltrasonicSensor;
import org.montclairrobotics.alloy.utils.Utils;

/**
 * An implementation of an ultrasonic sensor using an analog channel
 *
 * <p>Analog rangefinders return a voltage on the signal port of an analog channel that is
 * proportional to the distance that the sensor is detecting. By scaling this by a scaling factor,
 * it is possible to find the absolute distance from the object or surface. This scaling factor can
 * normally be obtained from the manufacturer and is different for each rangefinder
 *
 * <p>Analog inputs can also be oversampled to create a higher resolution (more accurate) reading,
 * although this causes the reading to be updated slower. This can be toggled on and off by setting
 * the sensor to get the average or absolute reading of the analog channel
 *
 * @see <a
 *     href="https://wpilib.screenstepslive.com/s/currentCS/m/java/l/599715-ultrasonic-sensors-measuring-robot-distance-to-a-surface">WPI
 *     Analog Rangefinders</>
 * @see <a href="https://wpilib.screenstepslive.com/s/currentCS/m/java/l/599718-analog-inputs">
 *     Analog inputs and oversampling</>
 * @author Garrett Burroughs
 * @version 0.1
 * @since 0.1
 */
public class AnalogUltrasonic extends InputComponent<Double> implements UltrasonicSensor {

  /** The analog input that the ultrasonic sensor is in */
  private final AnalogInput sensor;

  /** Sensor voltage to millimeter scaling factor */
  private final double scalingFactor;

  /** true if averaging oversample bits */
  private boolean average = false;

  /**
   * Create a new ultrasonic sensor on a specified port
   *
   * <p>Be careful when using this constructor as it has the potential to cause errors due to
   * already assigned ports, for this reason, make sure this constructor is called at most ONCE in
   * your robot code, for each sensor or the code will cause an error
   *
   * <p>Analog ultrasonic sensors work by returning a voltage value that is proportional to the
   * range value, you will need to find out what the scaling factor is for your specific ultrasonic
   * sensor, this is most often found on the datasheet released by the manufacturer,
   *
   * @see <a
   *     href="https://wpilib.screenstepslive.com/s/currentCS/m/java/l/599715-ultrasonic-sensors-measuring-robot-distance-to-a-surface">WPI
   *     Analog Rangefinders</>
   * @param analogChannel the channel to initialize the ultrasonic sensor on
   * @param scalingFactor the scaling factor for the ultrasonic sensor
   */
  public AnalogUltrasonic(int analogChannel, double scalingFactor) {
    this.sensor = new AnalogInput(analogChannel);
    this.scalingFactor = scalingFactor;
    setInput(
        () ->
            average
                ? sensor.getAverageVoltage() * scalingFactor
                : sensor.getVoltage() * scalingFactor);
  }

  /**
   * Create a ultrasonic sensor using a analog input
   *
   * <p>Analog ultrasonic sensors work by returning a voltage value that is proportional to the
   * range value, you will need to find out what the scaling factor is for your specific ultrasonic
   * sensor, this is most often found on the datasheet released by the manufacturer
   *
   * <p>The scaling factor should convert from V (Volts) to mm (millimeters)
   *
   * @see <a
   *     href="https://wpilib.screenstepslive.com/s/currentCS/m/java/l/599715-ultrasonic-sensors-measuring-robot-distance-to-a-surface">WPI
   *     Analog Rangefinders</>
   * @param sensor the analog input the sensor is connected to
   * @param scalingFactor the scaling factor for the ultrasonic sensor
   */
  public AnalogUltrasonic(AnalogInput sensor, double scalingFactor) {
    this.sensor = sensor;
    this.scalingFactor = scalingFactor;
    setInput(
        () ->
            average
                ? sensor.getAverageVoltage() * scalingFactor
                : sensor.getVoltage() * scalingFactor);
  }

  @Override
  public double getInches() {
    return getCentimeters() * Utils.CENTIMETERS_TO_INCHES;
  }

  @Override
  public double getCentimeters() {
    return get() * 10;
  }

  /**
   * Set the sensor to average the oversampled reading
   *
   * <p>When setting to this mode, it is also important to specify the amount of bits used to
   * oversample
   *
   * @param oversampleBits the number of bits used in oversampling
   */
  public void setModeAverage(int oversampleBits) {
    sensor.setOversampleBits(oversampleBits);
    average = false;
  }

  /** Set the sensor to read the absolute value (this is enabled by default) */
  public void setModeAbsolute() {
    average = true;
  }

  /** get the analog input of the sensor */
  public AnalogInput getSensor() {
    return sensor;
  }
}
