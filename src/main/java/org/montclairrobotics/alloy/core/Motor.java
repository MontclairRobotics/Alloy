package org.montclairrobotics.alloy.core;

/**
 * Created by MHS Robotics on 11/13/2017.
 *
 *
 *
 * @author Garrett Burroughs
 * @version 0.1
 * @since 0.1
 *
 */
public interface Motor {
    /**
     * Sets the motor Power
     *
     * @param power the power that the motor will be set to (0-1 inclusive )
     */
    public void setPower(double power);

    /**
     * Gets the motor power
     *
     * @return the current motor power, a value between (0-1)
     */
    public double getPower();


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


    /**
     * Sets weather the motor runs the default way , or inverted
     *
     * @param inverted true for inverted, false for normal
     */
    public void setInverted(boolean inverted);

    /**
     * Gets weather the motor is inverted 
     *
     * @return true if the motor is inverted
     */
    public boolean getInverted();
}