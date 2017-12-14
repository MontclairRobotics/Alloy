package org.montclairrobotics.alloy.core;

import org.montclairrobotics.alloy.vector.Vector;

/**
 * Created by MHS Robotics on 11/14/2017.
 *
 * Joysticks should return a vector made up of the X and Y values of their position
 *
 * @author Garrett Burroughs
 * @version 0.1
 * @since 0.1
 *
 */
public interface Joystick {
    /**
     * Gets the position of the joystick
     *
     * @return a vector made up of the X and Y values of the Joysticks Position
     */
    public Vector getValue();
}
