package org.montclairrobotics.alloy.test;

import org.montclairrobotics.alloy.components.Component;
import org.montclairrobotics.alloy.core.Debug;
import org.montclairrobotics.alloy.core.PoweredMotor;
import org.montclairrobotics.alloy.ftc.FTCMotor;
import org.montclairrobotics.alloy.utils.Toggleable;

import java.util.ArrayList;

public class Shooter extends Component {
    PoweredMotor motorR = new FTCMotor("Right PoweredMotor");
    PoweredMotor motorL = new FTCMotor("Left PoweredMotor");

    public Shooter(){
        super();
        addDebugs(motorL.getDebugs());
        addDebugs(motorR.getDebugs());
    }

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
