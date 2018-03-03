package org.montclairrobotics.alloy.core;

import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import org.montclairrobotics.alloy.components.Component;
import org.montclairrobotics.alloy.ftc.FTCDebugger;
import org.montclairrobotics.alloy.update.Updater;

/**
 * Created by MHS Robotics on 11/13/2017.
 *
 * The main purpose behind the alloy class is to controll how and when all of the mehotds in Robot are run
 * as well as initialize all global variables
 *
 * @author Garrett Burroughs
 * @version 0.1
 * @since 0.1
 *
 */
public abstract class Alloy extends OpMode{
    
    
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


    @Override
    public void init() {
        // Set Up the core robot components, This allows them to be accessed throughout the project
        new RobotCore(telemetry, hardwareMap, gamepad1, gamepad2);
        Component.debugger = new FTCDebugger();
        robotSetup();
        initialization();
    }

    @Override
    public void loop()
    {
        Updater.update();
        periodic();
    }
}
