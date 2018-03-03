package org.montclairrobotics.alloy.ftc;

import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.DcMotorSimple;
import org.montclairrobotics.alloy.core.*;
import org.montclairrobotics.alloy.utils.Input;

import java.util.ArrayList;

/**
 * Created by MHS Robotics on 11/14/2017.
 *
 * FTCMotor is a class that dynamically switches a motor's runmode
 * depending on what functionality is being used
 *
 * @author Garrett Burroughs
 * @since 0.1
 */
public class FTCMotor extends FTCMotorBase implements UniversalMotor {
    
    public FTCMotor(String motorConfiguration) {
        super(motorConfiguration);
    }
    
    /**
     * Sets the motor Power
     *
     * @param power the power that the motor will be set to (0-1 inclusive )
     */
    @Override
    public void setMotorPower(double power) {
        motor.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
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
     * Sets the power at which the motor will move when set to a position
     *
     * @param power the power that the motor will be set to (0-1 inclusive )
     */
    @Override
    public void setTargetPower(double power) {
        motor.setMode(DcMotor.RunMode.RUN_TO_POSITION);
        motor.setPower(power);
    }
    
    /**
     * Gets the motor power
     *
     * @return the current motor power, a value between (0-1)
     */
    @Override
    public double getTargetPower() {
        return motor.getPower();
    }
    
    /**
     * Sets the motor position
     *
     * @param position the position the motor will be set to (in encoder ticks)
     */
    @Override
    public void setPosition(int position) {
        motor.setMode(DcMotor.RunMode.RUN_TO_POSITION);
        motor.setTargetPosition(position);
    }
    
    /**
     * Gets the motors position
     *
     * @return the position that the motor is at (in encoder ticks)
     */
    @Override
    public double getPosition() {
        return motor.getCurrentPosition();
    }

    /**
     * @return the motor position, and powers
     */
    @Override
    public ArrayList<Debug> getDebugs(){
        ArrayList<Debug> motorDebugs = new ArrayList<>();

        motorDebugs.add(new Debug("Motor Power", new Input<Double>(){

            @Override
            public Double get() {
                return getMotorPower();
            }
        }));

        motorDebugs.add(new Debug("Motor Position", new Input<Double>(){

            @Override
            public Double get() {
                return getPosition();
            }
        }));

        motorDebugs.add(new Debug("Target Power", new Input<Double>(){

            @Override
            public Double get() {
                return getTargetPower();
            }
        }));

        return motorDebugs;
    }
}
