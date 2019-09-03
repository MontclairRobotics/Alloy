package org.montclairrobotics.alloy.test.virtualTest;

import org.montclairrobotics.alloy.virtualRobot.AlloyTestBot;

/**
 * Created by MHS Robotics on 4/3/2018.
 *
 * @author Garrett Burroughs
 * @since
 */
public class TestRobot extends AlloyTestBot {
    
    /**
     * The robotSetup is where all code specific to robot setup is placed
     * If you only have one teleop this can be done in the initialization
     * Method. robotSetup is called right after the robot core is initialized
     */
    @Override
    public void robotSetup() {
    
    }
    
    /**
     * The initialization method is were everything specific to the OpMode
     * Should be set up. Initialization will be the first thing called after
     * The robot setup.
     */
    @Override
    public void initialization() {
    
    }
    
    /**
     * Although most of the periodic actions are taken care by the updater, the user may want to
     * add their own methods and code that need to be updated or run periodically, this can be done in
     * the periodic() method
     * periodic will be run every loop.
     */
    @Override
    public void periodic() {
    
    }
    
    @Override
    public String getName(){
        return "Test Robot 1";
    }
}
