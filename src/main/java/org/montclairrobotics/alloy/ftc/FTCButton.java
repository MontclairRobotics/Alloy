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
import org.montclairrobotics.alloy.core.Button;
import org.montclairrobotics.alloy.utils.Input;

/**
 * Created by MHS Robotics on 11/14/2017.
 *
 * <p>An FTC button provides implementation for a button used in the FTC competition
 *
 * @author Garrett Burroughs
 * @since 0.1
 */
public class FTCButton implements Button {

    /** Will return the value of the button */
    private Input<Boolean> buttonInput;

    /** If true inverts the value of the button. */
    private boolean inverted;

    /**
     * Creates a new button using a buttonInput
     *
     * @param buttonInput an input that returns true if the button is being pressed, and false if
     *     unpressed
     */
    public FTCButton(Input<Boolean> buttonInput) {
        this(buttonInput, false);
    }

    /**
     * Creates a new button specifying a button input and an invert
     *
     * @param buttonInput an input that returns true if the button is being pressed, and false if
     *     unpressed
     * @param invert if true inverts the value of buttonInput
     */
    public FTCButton(Input<Boolean> buttonInput, boolean invert) {
        this.buttonInput = buttonInput;
        this.inverted = invert;
    }

    /**
     * Gets the values for a button
     *
     * @return the value of the button if inverted is false, or returns the opposite if it is true
     */
    @Override
    public boolean getValue() {
        if (inverted) {
            return !buttonInput.get();
        } else {
            return buttonInput.get();
        }
    }

    // BUTTON PAD
    public static FTCButton getAButton(Gamepad gamepad) {
        return new FTCButton(() -> gamepad.a);
    }

    public static FTCButton getBButton(Gamepad gamepad) {
        return new FTCButton(() -> gamepad.b);
    }

    public static FTCButton getXButton(Gamepad gamepad) {
        return new FTCButton(() -> gamepad.x);
    }

    public static FTCButton getYButton(Gamepad gamepad) {
        return new FTCButton(() -> gamepad.y);
    }

    // DPAD
    public static FTCButton getDPADUp(Gamepad gamepad) {
        return new FTCButton(() -> gamepad.dpad_up);
    }

    public static FTCButton getDPADDown(Gamepad gamepad) {
        return new FTCButton(() -> gamepad.dpad_down);
    }

    public static FTCButton getYDPADRight(Gamepad gamepad) {
        return new FTCButton(() -> gamepad.dpad_right);
    }

    public static FTCButton getDPADLeft(Gamepad gamepad) {
        return new FTCButton(() -> gamepad.dpad_left);
    }

    // Bumpers
    public static FTCButton getRightBumper(Gamepad gamepad) {
        return new FTCButton(() -> gamepad.right_bumper);
    }

    public static FTCButton getLeftBumper(Gamepad gamepad) {
        return new FTCButton(() -> gamepad.left_bumper);
    }

    // Triggers
    public static FTCButton getRightTrigger(Gamepad gamepad) {
        return new FTCButton(() -> gamepad.right_trigger > .5);
    }

    public static FTCButton getLeftTrigger(Gamepad gamepad) {
        return new FTCButton(() -> gamepad.left_trigger > .5);
    }
}
