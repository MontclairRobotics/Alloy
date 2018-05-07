package org.montclairrobotics.alloy.core;

import org.montclairrobotics.alloy.components.InputComponent;
import org.montclairrobotics.alloy.utils.Differential;
import org.montclairrobotics.alloy.utils.Input;


/**
 * A motor encoder that keeps track of kinematic information about the motor
 *
 * At a basic level hardware encoders are able to get how far a motor has gone
 * as a unit of encoder ticks. The encoder class also calculates the acceleration
 * anc velocity of the motor in terms of encoder ticks, and then the user can
 * define the distance of unit to measure the motor in
 */
public abstract class Encoder extends InputComponent<Integer> {

    /**
     * An Object that calculates the change in position with respect to time (Velocity)
     * Measured in Ticks per Second
     */
    Differential calcVelocity;

    /**
     * An Object that calculates the change in velocity with respect to time (Acceleration)
     * Measured in Ticks per Second per Second
     */
    Differential calcAcceleration;

    /**
     * How far the motor goes for each encoder tick
     * NOTE : It is very important that distances are consistent
     */
    private double distancePerTick;

    /**
     * Max speed that the motor can go
     * Measured in Ticks per Second
     */
    private double maxSpeed;

    /**
     * A method that should be overridden by the encoder
     *
     * @return the raw value of encoder ticks that the encoder reads
     */
    public abstract int getTicks();

    public Encoder(double distancePerTick, double maxSpeed) {
        this.distancePerTick = distancePerTick;
        setInput(() -> getTicks());
        calcVelocity = new Differential((Input<Double>) () -> (double)getTicks());
        calcAcceleration = new Differential(calcVelocity);
    }

    /**
     * Create a new encoder with default values
     */
    public Encoder(){
        this(1.0, 1.0);
    }

    /**
     * Set the distance per tick
     *
     * The distance per tick is the amount of distance the motor will move (or
     * what the motor is attached to, eg wheel/lift)
     * The distance unit should stay consistent throughout the robot project
     * This method can also be daisychained as it returns a reference to itself
     *
     * @param distancePerTick how far the motor moves for 1 encoder tick
     * @return the encoder
     */
    public Encoder setDistancePerTick(double distancePerTick) {
        this.distancePerTick = distancePerTick;
        return  this;
    }

    /**
     * Set the max speed (In Ticks per Second) that the motor can run
     *
     * @param maxSpeed max speed that the motor can run
     * @return the encoder
     */
    public Encoder setMaxSpeed(double maxSpeed){
        this.maxSpeed = maxSpeed;
        return this;
    }

    /**
     * Get the velocity in Ticks per Second
     *
     * @return velocity in ticks per second
     */
    public double getRawVelocity(){
        return calcVelocity.get();
    }

    /**
     * Get the acceleration in ticks per second per second
     *
     * @return acceleration in ticks per second per second
     */
    private double getRawAcceleration(){
        return calcAcceleration.get();
    }

    /**
     * Get the velocity in distance per second
     * The distance is set by the distance per tick method,
     * it is important to keep distances consistent throughout
     * the project.
     *
     * @return velocity in distance per second
     */
    public double getVelocity(){
        return getRawVelocity() / distancePerTick;
    }

    /**
     * Get the acceleration in distance per second
     * The distance is set by the distance per tick method,
     * it is important to keep distances consistent throughout
     *
     * @return acceleration in distance per second
     */
    public double getAcceleration(){
        return getRawAcceleration() / distancePerTick;
    }


    /**
     * Get a scaled value (0 - 1) of how fast the motor is going
     *
     * @return a value 0 - 1 depending on how fast the motor is going
     */
    public double getScaledVelocity(){
        return getRawVelocity() / maxSpeed;
    }

}
