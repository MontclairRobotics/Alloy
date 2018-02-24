package org.montclairrobotics.alloy.test;

import com.sun.org.apache.xpath.internal.operations.Bool;
import org.montclairrobotics.alloy.control.ToggleButton;
import org.montclairrobotics.alloy.core.Alloy;
import org.montclairrobotics.alloy.ftc.FTCButton;
import org.montclairrobotics.alloy.utils.Input;

public class TestRobot extends Alloy {

    Shooter shooter;

    @Override
    public void robotSetup() {
        shooter = new Shooter();
    }

    @Override
    public void initialization() {

    }

    @Override
    public void periodic() {
        Input<Boolean> shooterButton = new Input<Boolean>() {
            @Override
            public Boolean get() {
                return gamepad1.a;
            }
        };

        new ToggleButton(new FTCButton(shooterButton), shooter);
    }
}
