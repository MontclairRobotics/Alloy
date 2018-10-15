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
package org.montclairrobotics.alloy.control;

import org.montclairrobotics.alloy.core.Button;
import org.montclairrobotics.alloy.update.Update;

/**
 * Every Button action is tied to a button, and controls what happens when the button is pressed,
 * held, unpressed, and released
 *
 * @author Garrett Burroughs
 * @version 0.1
 * @since 0.1
 */
public abstract class ButtonAction {
    /** The button that controls the action */
    public Button button;

    /**
     * Keeps track of if the button was pressed in the previous loop, used for telling when the
     * button is pressed/unpressed
     */
    public boolean wasPressed = false;

    /** Creates a button action tied to a button */
    public ButtonAction(Button button) {
        this.button = button;
    }

    /** On pressed is called once, when the button goes from being unpressed, to pressed */
    public abstract void onPressed();

    /** On released is called once, when the button goes from being pressed, to unpressed */
    public abstract void onReleased();

    /** While pressed is called every loop while the button is pressed */
    public abstract void whilePressed();

    /** While released is called every loop while the button is unpressed */
    public abstract void whileReleased();

    @Update
    public void update() {
        if (button.getValue()) { // If the button is pressed, call whilePressed()
            whilePressed();
        } else { // If the button is not pressed call whileReleased()
            whileReleased();
        }
        if (wasPressed
                && !button
                        .getValue()) { // If the button was just pressed, but is no longer pressed, call onReleased()
            onReleased();
        }
        if (!wasPressed
                && button
                        .getValue()) { // If the button was just not pressed, but is now pressed, call onPressed()
            onPressed();
        }

        wasPressed = button.getValue();
    }
}
