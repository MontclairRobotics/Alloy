package org.montclairrobotics.alloy.ftc;

import org.montclairrobotics.alloy.core.Button;
import org.montclairrobotics.alloy.utils.Input;

/**
 * Created by MHS Robotics on 11/14/2017.
 *
 * An FTC button is basically a wrapper around the
 *
 * @author Garrett Burroughs
 * @since 0.1
 */
public class FTCButton<T> implements Button {
    
    Input<T> buttonInput;
    
    public FTCButton(Input<T> buttonInput){
        this.buttonInput = buttonInput;
    }
    
    @Override
    public T getValue() {
        return buttonInput.get();
    }
}
