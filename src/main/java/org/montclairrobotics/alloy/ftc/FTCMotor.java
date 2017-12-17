package org.montclairrobotics.alloy.ftc;

import com.qualcomm.robotcore.hardware.DcMotor;
import org.montclairrobotics.alloy.core.Motor;
import org.montclairrobotics.alloy.core.RobotCore;

/**
 * Created by Garrett on 11/14/2017.
 */
public class FTCMotor implements Motor {

    DcMotor motor;

    public FTCMotor(String motor){
        this.motor = RobotCore.getHardwareMap().dcMotor.get(motor);
    }

    /**
     * Sets the motor Power
     *
     * @param power the power that the motor will be set to (0-1 inclusive )
     */
    @Override
    public void setPower(double power) {

    }

    /**
     * Gets the motor power
     *
     * @return the current motor power, a value between (0-1)
     */
    @Override
    public double getPower() {
        return 0;
    }

    /**
     * Sets the motor position
     *
     * @param position the position the motor will be set to (in encoder ticks)
     */
    @Override
    public void setPosition(int position) {

    }

    /**
     * Gets the motors position
     *
     * @return the position that the motor is at (in encoder ticks)
     */
    @Override
    public double getPosition() {
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
}
