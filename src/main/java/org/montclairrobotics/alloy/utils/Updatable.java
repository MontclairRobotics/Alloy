package org.montclairrobotics.alloy.utils;

/**
 * Created by MHS Robotics on 2/11/2018.
 *
 * @author Garrett Burroughs
 * @since 0.1
 */
public interface Updatable {

    /**
     * The update method should be defined for every updatable, and is called every loop if added to the updater
     */
    public void update();
}
