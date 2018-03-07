package org.montclairrobotics.alloy.ftc;

import com.qualcomm.robotcore.hardware.Gamepad;
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
    
    // BUTTON PAD
    public static FTCButton getAButton(Gamepad gamepad){
        return new FTCButton(() -> gamepad.a);
    }
    
    public static FTCButton getBButton(Gamepad gamepad){
        return new FTCButton(() -> gamepad.b);
    }
    
    public static FTCButton getXButton(Gamepad gamepad){
        return new FTCButton(() -> gamepad.x);
    }
    
    public static FTCButton getYButton(Gamepad gamepad){
        return new FTCButton(() -> gamepad.y);
    }
    
    // DPAD
    public static FTCButton getDPADUp(Gamepad gamepad){
        return new FTCButton(() -> gamepad.dpad_up);
    }
    
    public static FTCButton getDPADDown(Gamepad gamepad){
        return new FTCButton(() -> gamepad.dpad_down);
    }
    
    public static FTCButton getYDPADRight(Gamepad gamepad){
        return new FTCButton(() -> gamepad.dpad_right);
    }
    
    public static FTCButton getDPADLeft(Gamepad gamepad){
        return new FTCButton(() -> gamepad.dpad_left);
    }
    
    // Bumpers
    public static FTCButton getRightBumper(Gamepad gamepad){
        return new FTCButton(() -> gamepad.right_bumper);
    }
    
    public static FTCButton getLeftBumper(Gamepad gamepad){
        return new FTCButton(() -> gamepad.left_bumper);
    }
    
    // Triggers
    public static FTCButton getRightTrigger(Gamepad gamepad){
        return new FTCButton(() -> gamepad.right_trigger > .5);
    }
    
    public static FTCButton getLeftTrigger(Gamepad gamepad){
        return new FTCButton(() -> gamepad.left_trigger > .5);
    }
}
