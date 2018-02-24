package org.montclairrobotics.alloy.core;


/**
 * Target motor is a motor interface for motors that have encoders.
 * Target motors can set encoder positions using PID control
 */
public interface TargetMotor extends PoweredMotor{
    /**
     * Sets the motor position
     *
     * @param position the position the motor will be set to (in encoder ticks)
     */
    public void setPosition(int position);

    /**
     * Gets the motors position
     *
     * @return the position that the motor is at (in encoder ticks)
     */
    public double getPosition();
}
