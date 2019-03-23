package org.montclairrobotics.alloy.frc;

import edu.wpi.first.wpilibj.AnalogInput;
import org.montclairrobotics.alloy.core.UltrasonicSensor;
import org.montclairrobotics.alloy.utils.Input;
import org.montclairrobotics.alloy.utils.Utils;

/**
 * An implementation of an ultrasonic sensor using an analog channel
 *
 * Analog rangefinders return a voltage on the signal port of an analog
 * channel that is proportional to the distance that the sensor is
 * detecting. By scaling this by a scaling factor, it is possible to
 * find the absolute distance from the object or surface.
 * This scaling factor can normally be obtained from the manufacturer
 * and is different for each rangefinder
 *
 * @see <a href="https://wpilib.screenstepslive.com/s/currentCS/m/java/l/599715-ultrasonic-sensors-measuring-robot-distance-to-a-surface">WPI Analog Rangefinders</>
 *
 * Analog inputs can also be oversampled to create a higher resolution
 * (more accurate) reading, although this causes the reading to be updated
 * slower. This can be toggled on and off by setting the sensor to
 * get the average or absolute reading of the analog channel
 *
 * @see <a href="https://wpilib.screenstepslive.com/s/currentCS/m/java/l/599718-analog-inputs"> Analog inputs and oversampling</>
 *
 * @author Garrett Burroughs
 * @version 0.1
 * @since 0.1
 */
public class AnalogUltrasonic implements UltrasonicSensor, Input<Double> {
    private final int analogChannel;
    private final AnalogInput sensor;
    private final double scalingFactor;

    private boolean average = false;

    /**
     * Create a new ultrasonic sensor on a specified port
     *
     * Be careful when using this constructor as it has the potential
     * to cause errors due to already assigned ports, for this reason,
     * make sure this constructor is called at most ONCE in your robot code,
     * for each sensor or the code will cause an error
     *
     * Analog ultrasonic sensors work by returning a voltage value that is proportional
     * to the range value, you will need to find out what the scaling factor is
     * for your specific ultrasonic sensor, this is most often found on the datasheet
     * released by the manufacturer,
     *
     * @see <a href="https://wpilib.screenstepslive.com/s/currentCS/m/java/l/599715-ultrasonic-sensors-measuring-robot-distance-to-a-surface">WPI Analog Rangefinders</>
     *
     * @param analogChannel the channel to initialize the ultrasonic sensor on
     * @param scalingFactor the scaling factor for the ultrasonic sensor
     */
    public AnalogUltrasonic(int analogChannel, double scalingFactor) {
        this.analogChannel = analogChannel;
        this.sensor = new AnalogInput(analogChannel);
        this.scalingFactor = scalingFactor;
    }

    /**
     * Create a ultrasonic sensor using a analog input
     *
     * Analog ultrasonic sensors work by returning a voltage value that is proportional
     * to the range value, you will need to find out what the scaling factor is
     * for your specific ultrasonic sensor, this is most often found on the datasheet
     * released by the manufacturer
     *
     * The scaling factor should convert from V (Volts) to mm (millimeters)
     *
     * @see <a href="https://wpilib.screenstepslive.com/s/currentCS/m/java/l/599715-ultrasonic-sensors-measuring-robot-distance-to-a-surface">WPI Analog Rangefinders</>
     *
     * @param sensor the analog input the sensor is connected to
     * @param scalingFactor the scaling factor for the ultrasonic sensor
     */
    public AnalogUltrasonic(AnalogInput sensor, double scalingFactor) {
        this.analogChannel = sensor.getChannel();
        this.sensor = sensor;
        this.scalingFactor = scalingFactor;
    }

    @Override
    public double getInches() {
        return getCentimeters() * Utils.CENTIMETERS_TO_INCHES;
    }

    @Override
    public double getCentimeters() {
        return get() * 10;
    }

    @Override
    public Double get() {
        return average ? sensor.getAverageVoltage() * scalingFactor : sensor.getVoltage() * scalingFactor;
    }

    /**
     * Set the sensor to average the oversampled reading
     *
     * When setting to this mode, it is also important to
     * specify the amount of bits used to oversample
     *
     * @param oversampleBits the number of bits used in oversampling
     */
    public void setModeAverage(int oversampleBits){
        sensor.setOversampleBits(oversampleBits);
        average = false;
    }

    /**
     * Set the sensor to read the absolute value (this is enabled by default)
     */
    public void setModeAbsolute(){
        average = true;
    }

    /** get the analog input of the sensor */
    public AnalogInput getSensor() {
        return sensor;
    }
}
