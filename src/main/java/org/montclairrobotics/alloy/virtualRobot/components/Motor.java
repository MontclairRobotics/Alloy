package org.montclairrobotics.alloy.virtualRobot.components;

import org.montclairrobotics.alloy.components.Component;

/**
 * Created by MHS Robotics on 4/2/2018.
 *
 * @author Garrett Burroughs
 * @since
 */
public class Motor extends Component implements org.montclairrobotics.alloy.core.Motor {
    private int ID;
    private int position;
    private double maxSpeed;
    private double speed;
    private double power;
    
    /**
     * Sets the motor Power
     *
     * @param power the power that the motor will be set to (0-1 inclusive )
     */
    @Override
    public void setMotorPower(double power) {
        this.power = power;
    }
    
    /**
     * Gets the motor power
     *
     * @return the current motor power, a value between (0-1)
     */
    @Override
    public double getMotorPower() {
        return 0;
    }
    
    /**
     * Sets weather the motor runs the default way , or inverted
     *
     * @param inverted true for inverted, false for normal
     */
    @Override
    public void setInverted(boolean inverted) {
    
    }
    
    /**
     * Gets weather the motor is inverted
     *
     * @return true if the motor is inverted
     */
    @Override
    public boolean getInverted() {
        return false;
    }

    
    /**
     * Method to be called when the toggleable is enabled
     */
    @Override
    public void enableAction() {
    
    }
    
    /**
     * Method to be called when the toggleable is disabled
     */
    @Override
    public void disableAction() {
    
    }
}
