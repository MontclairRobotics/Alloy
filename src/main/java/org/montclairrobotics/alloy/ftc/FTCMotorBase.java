package org.montclairrobotics.alloy.ftc;

import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.DcMotorSimple;
import org.montclairrobotics.alloy.core.RobotCore;

/**
 * Created by MHS Robotics on 2/25/2018.
 *
 * FTCMotorBase is the base class that contains all of the methods
 * that every motor has
 *
 * @author Garrett Burroughs
 * @since
 */
public class FTCMotorBase {
    DcMotor motor;
    
    public FTCMotorBase(String motorConfiguration) {
        motor = RobotCore.getHardwareMap().dcMotor.get(motorConfiguration);
    }
    
    /**
     * Sets weather the motor runs the default way , or inverted
     *
     * @param inverted true for inverted, false for normal
     */
    public void setInverted(boolean inverted) {
        if(inverted) {
            motor.setDirection(DcMotorSimple.Direction.REVERSE);
        } else{
            motor.setDirection(DcMotorSimple.Direction.FORWARD);
        }
    }
    
    /**
     * Gets weather the motor is inverted
     *
     * @return true if the motor is inverted
     */
    public boolean getInverted() {
        return motor.getDirection() == DcMotorSimple.Direction.REVERSE ? true : false;
    }
}
