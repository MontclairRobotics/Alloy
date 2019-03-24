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
package org.montclairrobotics.alloy.frc;

import org.montclairrobotics.alloy.components.InputComponent;
import org.montclairrobotics.alloy.core.Joystick;
import org.montclairrobotics.alloy.update.Update;
import org.montclairrobotics.alloy.vector.Vector;
import org.montclairrobotics.alloy.vector.XY;

/**
 * Created by MHS Robotics on 10/6/2018.
 *
 * @author Garrett Burroughs
 * @since
 */
public class FRCJoystick extends InputComponent<Vector> implements Joystick {
    edu.wpi.first.wpilibj.Joystick stick;

    public FRCJoystick(int port) {
        this.stick = new edu.wpi.first.wpilibj.Joystick(port);
        setInput(this::getValue);
    }

    public FRCJoystick(edu.wpi.first.wpilibj.Joystick joystick) {
        this.stick = joystick;
        setInput(this::getValue);
    }

    /**
     * Gets the position of the joystick
     *
     * @return a vector made up of the X and Y values of the Joysticks Position
     */
    @Override
    public Vector getValue() {
        return new XY(stick.getX(), stick.getY());
    }

    @Update
    public void updateControls() {
        if (status.isEnabled()) {
            output = getValue();
        } else {
            output = Vector.ZERO;
        }
    }

    public edu.wpi.first.wpilibj.Joystick getStick() {
        return stick;
    }
}
