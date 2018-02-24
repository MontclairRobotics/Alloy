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
    
    Input<Boolean> buttonInput;
    private boolean inverted;
    
    public FTCButton(Input<Boolean> buttonInput){
        this(buttonInput, false);
    }

    public FTCButton(Input<Boolean> buttonInput, boolean invert){
        this.buttonInput = buttonInput;
        this.inverted = invert;
    }

    @Override
    public boolean getValue() {
        if(inverted) {
            return !buttonInput.get();
        }else{
            return buttonInput.get();
        }
    }
}
