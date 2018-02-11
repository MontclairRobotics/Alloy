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
    
    public FTCButton(Input<Boolean> buttonInput){
        this.buttonInput = buttonInput;
    }
    
    @Override
    public boolean getValue() {
        return buttonInput.get();
    }
}
