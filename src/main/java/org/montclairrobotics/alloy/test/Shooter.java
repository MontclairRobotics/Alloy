package org.montclairrobotics.alloy.test;

import org.montclairrobotics.alloy.core.PoweredMotor;
import org.montclairrobotics.alloy.ftc.FTCMotor;
import org.montclairrobotics.alloy.utils.Toggleable;

public class Shooter extends Toggleable {
    PoweredMotor motorR = new FTCMotor("Right PoweredMotor");
    PoweredMotor motorL = new FTCMotor("Left PoweredMotor");


    @Override
    public void enableAction() {
        motorR.setPower(1);
        motorL.setPower(1);
    }

    @Override
    public void disableAction() {
        motorR.setPower(0);
        motorL.setPower(0);
    }
}
