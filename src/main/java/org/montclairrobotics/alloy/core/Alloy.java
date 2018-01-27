package org.montclairrobotics.alloy.core;

import com.qualcomm.robotcore.eventloop.opmode.OpMode;

/**
 * Created by MHS Robotics on 11/13/2017.
 *
 * The main purpose behind the alloy class is to control how and when all of the methods in Robot are run
 * as well as initialize all global variables
 *
 * @author Garrett Burroughs
 * @version 0.1
 * @since 0.1
 *
 */
public abstract class Alloy extends OpMode{

    /**
     * The initialization method is were everything for the robot should be set up
     * This includes driveTrain, motors, buttons, sensors etc.
     * The initialization method will be run right after the core components are set up (RobotCore)
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
        initialization();
        FTCDebug.init();
    }

    @Override
    public void loop() {
        periodic();
    }
}
