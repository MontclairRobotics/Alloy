package org.montclairrobotics.alloy.ftc;

import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.DcMotorSimple;
import org.montclairrobotics.alloy.components.Component;
import org.montclairrobotics.alloy.core.*;
import org.montclairrobotics.alloy.update.Update;
import org.montclairrobotics.alloy.utils.Input;

import java.awt.*;
import java.util.ArrayList;

/**
 * Created by MHS Robotics on 11/14/2017.
 *
 * The basic motor for use in FTC. Basic motors are not aware of encoders and
 * are not recommended for use in FTC as all motors come with encoders
 * 
 *
 * @author Garrett Burroughs
 * @since 0.1
 */
public class FTCMotor extends Component implements Motor {

    /**
     * The physical hardware motor reference to the motor being controlled
     */
    public DcMotor motor;

    /**
     * The power that the motor should be running at
     */
    public double power;

    public FTCMotor(String motorConfiguration) {
        motor = RobotCore.getHardwareMap().dcMotor.get(motorConfiguration);
        addDebug(new Debug(motorConfiguration + " Motor Power: ", (Input<Double>) () -> power));
    }
    
    /**
     * Sets the motor Power
     *
     * @param power the power that the motor will be set to (0-1 inclusive )
     */
    @Override
    public void setMotorPower(double power) {
        motor.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
        this.power = power;
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
     * Sets whether the motor runs the default way , or inverted
     *
     * @param inverted true for inverted, false for normal
     */
    @Override
    public void setInverted(boolean inverted) {
        if (inverted) {
            motor.setDirection(DcMotorSimple.Direction.REVERSE);
        } else {
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


    @Update
    public void updateMotor(){

        // Set Motor Power if it is enabled
        if(status.booleanValue()){
            motor.setPower(power);
        }else{
            motor.setPower(0);
        }

    }

    @Override
    public void disableAction() {
        motor.setPower(0);
    }
}
