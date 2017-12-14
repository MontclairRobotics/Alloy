package org.montclairrobotics.alloy.core;

/**
 * Created by MHS Robotics on 11/13/2017.
 *
 * The button interface is implemented in ftc button //Todo implement FTCButton
 * The user can get the value of the button,
 * In most cases this will be a Boolean (True if pressed, false if not),
 * But for example, the triggers on an ftc gamepad return a float from 0-1.
 *
 * @author Garrett Burroughs
 * @version 0.1
 * @since 0.1
 *
 */
public interface  Button<T>{
    /**
     * Gets the value of a button
     * @return returns the value of the button, in most case True(pressed) or False(unpressed)
     */
    public T getValue();
}
