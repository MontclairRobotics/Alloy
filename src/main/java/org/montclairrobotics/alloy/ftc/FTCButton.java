package org.montclairrobotics.alloy.ftc;

import org.montclairrobotics.alloy.core.Button;
import org.montclairrobotics.alloy.utils.Input;

/**
 * Created by MHS Robotics on 11/14/2017.
 *
 * An FTC button provides implementation for a button used in the FTC competition
 * @author Garrett Burroughs
 * @since 0.1
 */
public class FTCButton implements Button {


    /**
     * Will return the value of the button
     */
    Input<Boolean> buttonInput;

    /**
     * If true inverts the value of the button.
     */
    private boolean inverted;

    /**
     * Creates a new button using a buttonInput
     *
     * @param buttonInput an input that returns true if the button is being pressed, and false if unpressed
     */
    public FTCButton(Input<Boolean> buttonInput){
        this(buttonInput, false);
    }

    /**
     *  Creates a new button specifying a button input and an invert
     *
     * @param buttonInput an input that returns true if the button is being pressed, and false if unpressed
     * @param invert if true inverts the value of buttonInput
     */
    public FTCButton(Input<Boolean> buttonInput, boolean invert){
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
        if(inverted) {
            return !buttonInput.get();
        }else{
            return buttonInput.get();
        }
    }
}
