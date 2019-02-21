package org.montclairrobotics.alloy.frc;

import edu.wpi.first.wpilibj.Joystick;
import org.montclairrobotics.alloy.core.Button;

public class POVButton implements Button {
    private int angle;
    private Joystick joystick;
    private int id;

    public POVButton(int angle, Joystick joystick, int id) {
        this.angle = angle;
        this.joystick = joystick;
        this.id = id;
    }

    public POVButton(int angle, Joystick joystick) {
        this(angle, joystick, 0);
    }

    @Override
    public boolean getValue() {
        return joystick.getPOV(id) == angle;
    }
}
