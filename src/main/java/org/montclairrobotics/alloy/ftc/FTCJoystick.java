package org.montclairrobotics.alloy.ftc;

import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.hardware.Gamepad;
import org.montclairrobotics.alloy.core.Joystick;
import org.montclairrobotics.alloy.vector.Vector;
import org.montclairrobotics.alloy.vector.XY;

/**
 * Created by MHS Robotics on 11/14/2017.
 *
 * @author Garrett Burroughs
 * @since 0.1
 */
public class FTCJoystick implements Joystick{
    
    public enum Side{
        RIGHT,
        LEFT
    }
    
    Gamepad gamepad;
    Side side;
    
    public FTCJoystick(Gamepad gamepad, Side side) {
        this.gamepad = gamepad;
        this.side = side;
    }
    
    @Override
    public Vector getValue(){
        switch (side){
            case RIGHT:
                return new XY(gamepad.right_stick_x, gamepad.right_stick_y);
            case LEFT:
                return new XY(gamepad.left_stick_x, gamepad.left_stick_y);
            default:
                return new XY(0, 0);
        }
    }
    
    
}
