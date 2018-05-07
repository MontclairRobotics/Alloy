/*
MIT License

Copyright (c) 2018 Garrett Burroughs

Permission is hereby granted, free of charge, to any person obtaining a copy
of this software and associated documentation files (the "Software"), to deal
in the Software without restriction, including without limitation the rights
to use, copy, modify, merge, publish, distribute, sublicense, and/or sell
copies of the Software, and to permit persons to whom the Software is
furnished to do so, subject to the following conditions:

The above copyright notice and this permission notice shall be included in all
copies or substantial portions of the Software.

THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR
IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY,
FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE
AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER
LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM,
OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN THE
SOFTWARE.
*/
package org.montclairrobotics.alloy.ftc;

import com.qualcomm.robotcore.hardware.Gamepad;
import org.montclairrobotics.alloy.core.Joystick;
import org.montclairrobotics.alloy.utils.Input;
import org.montclairrobotics.alloy.vector.Vector;
import org.montclairrobotics.alloy.vector.XY;

/**
 * Created by MHS Robotics on 11/14/2017.
 *
 * <p>There are a total of 4 accessible joysticks when controlling an FTC robot, There are 2
 * controllers and each one has 2 joysticks, (One right, one left). A joystick can be defined using
 * a controller and a side
 *
 * <p>Joysticks can also be used as inputs that return a vector
 *
 * @author Garrett Burroughs
 * @since 0.1
 */
public class FTCJoystick implements Joystick, Input<Vector> {

    /**
     * The side in a FTCJoystick is referring to the side of the controller is on, there are 2
     * joysticks, one on the right, one on the left
     */
    public enum Side {
        RIGHT,
        LEFT
    }

    /**
     * The gamepad that the joystick is attached to, this will either be joystick 1, or joystick2,
     * these can be accessed from the RobotCore class
     *
     * @see org.montclairrobotics.alloy.core.RobotCore
     */
    Gamepad gamepad;

    /** The side that the joystick is on */
    Side side;

    /**
     * Create a new Joystick using the gamepad and side
     *
     * @param gamepad The gamepad the joystick is on
     * @param side The side the joystick is on (Right/Left)
     */
    public FTCJoystick(Gamepad gamepad, Side side) {
        this.gamepad = gamepad;
        this.side = side;
    }

    /**
     * Gets the position of the joystick as a vector
     *
     * @return the position of the joystick as a vector
     */
    @Override
    public Vector getValue() {
        switch (side) {
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
