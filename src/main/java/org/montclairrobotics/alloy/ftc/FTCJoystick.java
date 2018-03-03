package org.montclairrobotics.alloy.ftc;

import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.hardware.Gamepad;
import org.montclairrobotics.alloy.core.Joystick;
import org.montclairrobotics.alloy.utils.Input;
import org.montclairrobotics.alloy.vector.Vector;
import org.montclairrobotics.alloy.vector.XY;

/**
 * Created by MHS Robotics on 11/14/2017.
 *
 * There are a total of 4 accessible joysticks when controlling an FTC robot,
 * There are 2 controllers and each one has 2 joysticks, (One right, one left).
 * A joystick can be defined using a controller and a side
 *
 * Joysticks can also be used as inputs that return a vector
 *
 * @author Garrett Burroughs
 * @since 0.1
 */
public class FTCJoystick implements Joystick, Input<Vector>{

    /**
     * The side in a FTCJoystick is referring to the side of the controller is on,
     * there are 2 joysticks, one on the right, one on the left
     */
    public enum Side{
        RIGHT,
        LEFT
    }

    /**
     * The gamepad that the joystick is attached to,
     * this will either be joystick 1, or joystick2, these can be accessed from the RobotCore class
     * @see org.montclairrobotics.alloy.core.RobotCore
     */
    Gamepad gamepad;

    /**
     * The side that the joystick is on
     */
    Side side;

    /**
     * Create a new Joystick using the gamepad and side
     * @param gamepad The gamepad the joystick is on
     * @param side The side the joystick is on (Right/Left)
     */
    public FTCJoystick(Gamepad gamepad, Side side) {
        this.gamepad = gamepad;
        this.side = side;
    }

    /**
     * Gets the position of the joystick as a vector
     * @return the position of the joystick as a vector
     */
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

    @Override
    public Vector get() {
        return getValue();
    }
}
