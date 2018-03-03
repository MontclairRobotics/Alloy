package org.montclairrobotics.alloy.ftc;

import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.DcMotorSimple;
import org.montclairrobotics.alloy.core.Debug;
import org.montclairrobotics.alloy.core.PoweredMotor;
import org.montclairrobotics.alloy.core.RobotCore;
import org.montclairrobotics.alloy.update.Update;
import org.montclairrobotics.alloy.utils.Input;

import java.util.ArrayList;
import java.util.Arrays;

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
public class FTCPoweredMotor extends FTCMotorBase implements PoweredMotor {

    private double power;

    public FTCPoweredMotor(String motorConfiguration) {
        super(motorConfiguration);
    }
    
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
        return motor.getPower();
    }

    /**
     * gets the debug information for the motor
     *
     * @return the motor debugs
     */
    @Override
    public ArrayList<Debug> getDebugs() {
        ArrayList<Debug> motorDebugs = new ArrayList<>();

        motorDebugs.add(new Debug("Motor Power", new Input<Double>(){

            @Override
            public Double get() {
                return getMotorPower();
            }
        }));

        return motorDebugs;
    }

    @Update
    public void motorUpdate(){
        if(status.booleanValue()){
            motor.setPower(power);
        }else{
            motor.setPower(0);
        }
    }
    

    /**
     * @return the motor being controlled
     */
    public DcMotor getMotor() {
        return motor;
    }
}
