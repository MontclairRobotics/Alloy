package org.montclairrobotics.alloy.utils;

/**
 * Created by MHS Robotics on 2/11/2018.
 *
 * Toggleables are used for objects of mechanisms that can
 * either be on (Enabled), or off toggleables can have
 * actions defined for when they are enabled and disabled,
 * as well as be tied to different parts of alloy like
 * buttons and auto states
 * @see org.montclairrobotics.alloy.control.ToggleButton
 *
 * @author Garrett Burroughs
 * @since 0.1
 */
public abstract class Toggleable {
    
    /**
     * Method to be called when the toggleable is enabled
     */
    public abstract void enableAction();
    
    /**
     * Method to be called when the toggleable is disabled
     */
    public abstract void disableAction();
    
    /**
     * The status of the toggleable, to keep track of weather it is enabled, or disabled
     */
    Status status;
    
    
    enum Status{
        ENABLED(true),
        DISABLED(false);
        
        
        boolean enabled;
        private Status(boolean enabled){
            this.enabled = enabled;
        }
        
        public boolean booleanValue(){
            return enabled;
        }
    }
    
    /**
     * Enables the toggleable
     */
    public void enable(){
        status = Status.ENABLED;
        enableAction();
    }
    
    
    /**
     * Disables the toggleable
     */
    public void disable(){
        status = Status.DISABLED;
        disableAction();
    }
    
    /**
     * Switches(Toggles), between the two states,
     * If the toggleable is disabled, enable it
     * If the toggleable is enabled, disable it
     */
    public void toggle(){
        switch (status){
            case ENABLED:
                disable();
                break;
            case DISABLED:
                enable();
                break;
        }
    }
}
