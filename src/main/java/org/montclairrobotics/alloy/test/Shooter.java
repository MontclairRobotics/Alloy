package org.montclairrobotics.alloy.test;

import org.montclairrobotics.alloy.core.PoweredMotor;
import org.montclairrobotics.alloy.ftc.FTCMotor;
import org.montclairrobotics.alloy.utils.Toggleable;

public class Shooter extends Toggleable {
    PoweredMotor motorR = new FTCMotor("Right PoweredMotor");
    PoweredMotor motorL = new FTCMotor("Left PoweredMotor");


    @Override
    public void enableAction() {
        motorR.setMotorPower(1);
        motorL.setMotorPower(1);
    }

    @Override
    public void disableAction() {
        motorR.setMotorPower(0);
        motorL.setMotorPower(0);
    }
}
