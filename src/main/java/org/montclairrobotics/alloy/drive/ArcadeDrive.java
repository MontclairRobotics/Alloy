package org.montclairrobotics.alloy.drive;

import org.montclairrobotics.alloy.components.InputComponent;
import org.montclairrobotics.alloy.core.Joystick;
import org.montclairrobotics.alloy.vector.Angle;
import org.montclairrobotics.alloy.vector.XY;

/**
 * Created by MHS Robotics on 10/20/2018.
 *
 * @author Garrett Burroughs
 * @since
 */
public class ArcadeDrive extends InputComponent<DTInput> {
    public ArcadeDrive(Joystick translationalStick, Joystick rotationalStick){
        setInput(() -> new DTInput(
                new XY(translationalStick.getValue().getX(), translationalStick.getValue().getY()),
                new Angle(rotationalStick.getValue().getX())
        ));
    }
}
