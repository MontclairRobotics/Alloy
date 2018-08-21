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
package org.montclairrobotics.alloy.core;

import org.montclairrobotics.alloy.components.InputComponent;
import org.montclairrobotics.alloy.utils.Differential;
import org.montclairrobotics.alloy.utils.Input;

/**
 * A motor encoder that keeps track of kinematic information about the motor
 *
 * <p>At a basic level hardware encoders are able to get how far a motor has gone as a unit of
 * encoder ticks. The encoder class also calculates the acceleration anc velocity of the motor in
 * terms of encoder ticks, and then the user can define the distance of unit to measure the motor in
 *
 * @author Garrett Burroughs
 * @version 0.1
 * @since 0.1
 */
public abstract class Encoder extends InputComponent<Integer> {

    /**
     * An Object that calculates the change in position with respect to time (Velocity) Measured in
     * Ticks per Second
     */
    private Differential calcVelocity;

    /**
     * An Object that calculates the change in velocity with respect to time (Acceleration) Measured
     * in Ticks per Second per Second
     */
    private Differential calcAcceleration;

    /**
     * How far the motor goes for each encoder tick NOTE : It is very important that distances are
     * consistent
     */
    private double distancePerTick;

    /** Max speed that the motor can go Measured in Ticks per Second */
    private double maxSpeed;

    /**
     * A method that should be overridden by the encoder
     *
     * @return the raw value of encoder ticks that the encoder reads
     */
    public abstract int getTicks();

    public Encoder(double distancePerTick, double maxSpeed) {
        this.distancePerTick = distancePerTick;
        this.maxSpeed = maxSpeed;
        setInput(() -> getTicks());
        calcVelocity = new Differential((Input<Double>) () -> (double) getTicks());
        calcAcceleration = new Differential(calcVelocity);
    }

    /** Create a new encoder with default values */
    public Encoder() {
        this(1.0, 1.0);
    }

    /**
     * Set the distance per tick
     *
     * <p>The distance per tick is the amount of distance the motor will move (or what the motor is
     * attached to, eg wheel/lift) The distance unit should stay consistent throughout the robot
     * project This method can also be daisychained as it returns a reference to itself
     *
     * @param distancePerTick how far the motor moves for 1 encoder tick
     * @return the encoder
     */
    public Encoder setDistancePerTick(double distancePerTick) {
        this.distancePerTick = distancePerTick;
        return this;
    }

    /**
     * Set the max speed (In Ticks per Second) that the motor can run
     *
     * @param maxSpeed max speed that the motor can run
     * @return the encoder
     */
    public Encoder setMaxSpeed(double maxSpeed) {
        this.maxSpeed = maxSpeed;
        return this;
    }

    /**
     * Get the velocity in Ticks per Second
     *
     * @return velocity in ticks per second
     */
    public double getRawVelocity() {
        return calcVelocity.get();
    }

    /**
     * Get the acceleration in ticks per second per second
     *
     * @return acceleration in ticks per second per second
     */
    private double getRawAcceleration() {
        return calcAcceleration.get();
    }

    /**
     * Get the velocity in distance per second The distance is set by the distance per tick method,
     * it is important to keep distances consistent throughout the project.
     *
     * @return velocity in distance per second
     */
    public double getVelocity() {
        return getRawVelocity() / distancePerTick;
    }

    /**
     * Get the acceleration in distance per second The distance is set by the distance per tick
     * method, it is important to keep distances consistent throughout
     *
     * @return acceleration in distance per second
     */
    public double getAcceleration() {
        return getRawAcceleration() / distancePerTick;
    }

    /**
     * Get a scaled value (0 - 1) of how fast the motor is going
     *
     * @return a value 0 - 1 depending on how fast the motor is going
     */
    public double getScaledVelocity() {
        return getRawVelocity() / maxSpeed;
    }

    /** @return the differential object that calculates the velocity */
    public Differential getVelocityDifferential() {
        return calcVelocity;
    }

    /** @return the differential object that calculates the acceleration */
    public Differential getAccelerationDifferential() {
        return calcAcceleration;
    }
}
