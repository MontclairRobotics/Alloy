package org.montclairrobotics.alloy.test;

import org.montclairrobotics.alloy.components.Component;
import org.montclairrobotics.alloy.core.Motor;
import org.montclairrobotics.alloy.ftc.FTCMotor;

public class Shooter extends Component {
    Motor motorR = new FTCMotor("Right Motor");
    Motor motorL = new FTCMotor("Left Motor");

    public Shooter(){
        super();
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
