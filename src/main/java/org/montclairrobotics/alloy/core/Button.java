package org.montclairrobotics.alloy.core;

/**
 * Created by MHS Robotics on 11/13/2017.
 *
 * The button interface is implemented in ftc button
 * The user can get the value of the button,
 * In most cases this will be a Boolean (True if pressed, false if not),
 *
 * @author Garrett Burroughs
 * @version 0.1
 * @since 0.1
 *
 */
public interface  Button{
    /**
     * Gets the value of a button
     * @return returns the value of the button, in most cases True(pressed) or False(unpressed)
     */
    public boolean getValue();
}
