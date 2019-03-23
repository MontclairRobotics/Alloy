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
package org.montclairrobotics.alloy.vector;

/**
 * A class to keep track of angles
 *
 * <p>The angle class allows for easy management of angles as well as easy conversion between degree
 * and radian angle measure The class keeps track of the angle in degrees but can easily be
 * converted to radians
 *
 * @author Garrett Burroughs
 * @version 0.1
 * @since 0.1
 */
public class Angle {

    public static final Angle ZERO = new Angle(0);
    public static final Angle HALF_PI = new Angle(90);
    public static final Angle PI = new Angle(180);
    public static final Angle TWO_PI = new Angle(360);

    /** Keeps Track of the amount of degrees that the angle is */
    private double degrees;

    /**
     * AngleMeasure, is used for keeping track of what type of angle measure the angle is being
     * created with
     */
    enum AngleMeasure {
        /** Radian Angle Measure */
        RADIAN,

        /** Degree Angle Measure */
        DEGREE
    }

    /**
     * Creating a new angle with an angle measure unit and the measure of the angle itself
     *
     * @param angleMeasure What unit to be used for the angle measure
     * @param angle The angle
     */
    public Angle(AngleMeasure angleMeasure, double angle) {
        // If angle is in degrees set degrees to the angle, no conversion is needed because they are
        // the
        // same unit
        if (angleMeasure == AngleMeasure.DEGREE) {
            degrees = angle;
        } else {
            // Convert the radian measure to degrees (radians to degrees: (angle * PI)/180)
            degrees = angle * (Math.PI / 180);
        }
    }

    /**
     * Creating an angle with degrees
     *
     * @param degrees Degree angle measure
     */
    public Angle(double degrees) {
        this(AngleMeasure.DEGREE, degrees);
    }

    /**
     * Get the angle measure in degrees
     *
     * @return the degree angle measure
     */
    public double getDegrees() {
        return degrees;
    }

    /**
     * Get the angle measure in radians
     *
     * @return the radian angle measure
     */
    public double getRadians() {
        return degrees * (Math.PI / 180);
    }

    /**
     * set the angle in degrees
     *
     * @param degrees the degree angle measure
     */
    public void setDegrees(double degrees) {
        this.degrees = degrees;
    }

    /**
     * set the angle in radians
     *
     * @param radians the degree angle measure
     */
    public void setRadians(double radians) {
        this.degrees = radians * (180 / Math.PI);
    }

    /**
     * Multiply the angle by some amount
     *
     * <p>Immutable operation
     *
     * @param multiplier
     */
    public Angle multiply(double multiplier) {
        return new Angle(degrees * multiplier);
    }

    /**
     * get the sine of the angle
     *
     * @return the sine of the angle
     */
    public double sin() {
        return Math.sin(getRadians());
    }

    /**
     * get the cosine of the angle
     *
     * @return the cosine of the angle
     */
    public double cos() {
        return Math.cos(getRadians());
    }

    /**
     * get the tangent of the angle
     *
     * @return the tangent of the angle
     */
    public double tan() {
        return Math.tan(getRadians());
    }

    /**
     * Creates a new angle using degrees
     *
     * @param degrees the amount of degrees the new angle will be created with
     * @return a new angle
     */
    public static Angle createDegrees(double degrees) {
        return new Angle(AngleMeasure.DEGREE, degrees);
    }

    /**
     * Creates a new angle using radians
     *
     * @param radians the amount of radians the new angle will be created with
     * @return a new angle
     */
    public static Angle createRadians(double radians) {
        return new Angle(AngleMeasure.RADIAN, radians);
    }
}
