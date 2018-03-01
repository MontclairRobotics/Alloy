package org.montclairrobotics.alloy.control;

import org.montclairrobotics.alloy.core.Button;
import org.montclairrobotics.alloy.update.Updatable;
import org.montclairrobotics.alloy.update.Updater;

/**
 * Created by MHS Robotics on 2/11/2018.
 *
 * Every Button action is tied to a button, and controls what
 * happens when the button is pressed, held, unpressed, and released
 *
 * @author Garrett Burroughs
 * @since 0.1
 */
public abstract class ButtonAction implements Updatable {
    
    public Button button;
    
    public ButtonAction(Button button){
        this.button = button;
        Updater.add(this);
        // TODO: add to updater
    }
    
    
    /**
     * On pressed is called once, when the button goes from being unpressed, to pressed
     */
    public abstract void onPressed();
    
    /**
     * On released is called once, when the button goes from being pressed, to unpressed
     */
    public abstract void onReleased();
    
    /**
     * While pressed is called every loop while the button is pressed
     */
    public abstract void whilePressed();
    
    /**
     * While released is called every loop while the button is unpressed
     */
    public abstract void whileReleased();
    
    public boolean wasPressed = false;
    
    @Override
    public void update() {
        if(button.getValue()){
            whilePressed();
        }else{
            whileReleased();
        }
        if(wasPressed && !button.getValue()){
            onReleased();
        }
        if(!wasPressed && button.getValue()){
            onPressed();
        }
        
        
        wasPressed = button.getValue();
    }
}
