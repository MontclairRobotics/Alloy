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
import org.montclairrobotics.alloy.utils.Toggleable;

/**
 * A ToggleButton is tied to a button and a toggleable, when the button is pressed the toggleable
 * will toggle between states
 *
 * @see Toggleable
 * @see ButtonAction
 * @author Garrett Burroughs
 * @version 0.1
 * @since 0.1
 */
public class ToggleButton extends ButtonAction {
    private Toggleable toggleable;

    public ToggleButton(Button button, Toggleable toggleable) {
        super(button);
        this.toggleable = toggleable;
    }

    /** On pressed is called once, when the button goes from being unpressed, to pressed */
    @Override
    public void onPressed() {
        toggleable.toggle();
    }

    /** On released is called once, when the button goes from being pressed, to unpressed */
    @Override
    public void onReleased() {
        // Will be run right after the button is released
    }

    /** While pressed is called every loop while the button is pressed */
    @Override
    public void whilePressed() {
        // Will be run while the button is held
    }

    /** While released is called every loop while the button is unpressed */
    @Override
    public void whileReleased() {
        // Wil be run while the button is unpressed
    }
}
