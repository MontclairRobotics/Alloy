package org.montclairrobotics.alloy.control;


import org.montclairrobotics.alloy.core.Button;
import org.montclairrobotics.alloy.utils.Toggleable;

/**
 * Created by MHS Robotics on 2/11/2018.
 *
 * A ToggleButton is tied to a button and a toggleable,
 * when the button is pressed the toggleable will
 * toggle between states
 * @see Toggleable
 * @see ButtonAction
 *
 * @author Garrett Burroughs
 * @since 0.1
 */
public class ToggleButton extends ButtonAction{
    Toggleable toggleable;
    
    public ToggleButton(Button button, Toggleable toggleable) {
        super(button);
        this.toggleable = toggleable;
    }
    
    /**
     * On pressed is called once, when the button goes from being unpressed, to pressed
     */
    @Override
    public void onPressed() {
        toggleable.toggle();
    }
    
    /**
     * On released is called once, when the button goes from being pressed, to unpressed
     */
    @Override
    public void onReleased() {
    
    }
    
    /**
     * While pressed is called every loop while the button is pressed
     */
    @Override
    public void whilePressed() {
    
    }
    
    /**
     * While released is called every loop while the button is unpressed
     */
    @Override
    public void whileReleased() {
    
    }
}
