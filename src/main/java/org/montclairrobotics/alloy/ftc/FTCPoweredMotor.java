package org.montclairrobotics.alloy.ftc;

import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.DcMotorSimple;
import org.montclairrobotics.alloy.core.PoweredMotor;
import org.montclairrobotics.alloy.core.RobotCore;

/**
 * Created by MHS Robotics on 2/24/2018.
 *
 * A powered motor is a motor that is controlled by setting motor voltage
 * A powered motor takes care of setting the motor configuration as well
 * as setting the motor runmode do not change the robot runmode, if you
 * plan to use a motor that is both controlled by voltage and position
 * use FTCMotor
 * @see FTCMotor
 *
 * @author Garrett Burroughs
 * @since 0.1
 */
public class FTCPoweredMotor implements PoweredMotor {
    /**
     * The DCMotor being controlled
     */
    DcMotor motor;

    public FTCPoweredMotor(String motorConfiguration){
        motor = RobotCore.getHardwareMap().dcMotor.get(motorConfiguration);
        motor.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
    }

    /**
     * Sets the motor Power
     *
     * @param power the power that the motor will be set to (0-1 inclusive )
     */
    @Override
    public void setMotorPower(double power) {
        motor.setPower(power);
    }

    /**
     * Gets the motor power
     *
     * @return the current motor power, a value between (0-1)
     */
    @Override
    public double getMotorPower() {
        return motor.getPower();
    }

    /**
     * Sets weather the motor runs the default way , or inverted
     *
     * @param inverted true for inverted, false for normal
     */
    @Override
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
    @Override
    public boolean getInverted() {
        return motor.getDirection() == DcMotorSimple.Direction.REVERSE ? true : false;
    }

    /**
     * @return the motor being controlled
     */
    public DcMotor getMotor() {
        return motor;
    }
}
