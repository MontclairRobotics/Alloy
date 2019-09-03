package org.montclairrobotics.alloy.virtualRobot;

import org.montclairrobotics.alloy.components.Component;
import org.montclairrobotics.alloy.update.Updater;
import org.montclairrobotics.alloy.virtualRobot.components.TestDebugger;

/**
 * Created by MHS Robotics on 4/2/2018.
 *
 * @author Garrett Burroughs
 * @since
 */
public abstract class AlloyTestBot {
    
    /**
     * The robotSetup is where all code specific to robot setup is placed
     * If you only have one teleop this can be done in the initialization
     * Method. robotSetup is called right after the robot core is initialized
     */
    public abstract void robotSetup();
    
    /**
     * The initialization method is were everything specific to the OpMode
     * Should be set up. Initialization will be the first thing called after
     * The robot setup.
     */
    public abstract void initialization();
    
    /**
     * Although most of the periodic actions are taken care by the updater, the user may want to
     * add their own methods and code that need to be updated or run periodically, this can be done in
     * the periodic() method
     * periodic will be run every loop.
     */
    public abstract void periodic();
    
    
    public void init() {
        Component.debugger = new TestDebugger();
        robotSetup();
        initialization();
    }
    
    public void loop()
    {
        Updater.update();
        periodic();
    }
    
    /**
     * For use in the virtual robot, should return the name of the robot so it can be selected
     * @return the name of the robot
     */
    public abstract String getName();
}
