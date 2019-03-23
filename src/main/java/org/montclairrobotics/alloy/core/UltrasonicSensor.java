package org.montclairrobotics.alloy.core;

/**
 * An interface defining the features of a ultrasonic rangefinder
 *
 * ultrasonic sensors are able to reutrn the distance from an
 * object. This is useful for lining up with targets, and ensuring
 * absolute distances.
 */
public interface UltrasonicSensor {

    /** @return The distance in inches that the rangefinder returns */
    double getInches();

    /** @return The distance in centimeters that the rangefinder returns */
    double getCentimeters();
}
