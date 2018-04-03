package org.montclairrobotics.alloy.core;

/**
 * Created by MHS Robotics on 11/14/2017.
 *
 * Mode stores all the different run modes that a robot can run in
 *
 * @author Garrett Burroughs
 * @version 0.1
 * @since 0.1
 *
 */
public enum Mode {
    /**
     * ftc Telemetry Operated Mode
     */
    FTCTELEOP,
    /**
     * ftc autonomous mode
     */
    FTCAUTONOMOUS,
    
    /**
     * Mode for the robot simulation teleop
     */
    TESTTELEOP,
    
    /**
     * Mode for the robot simulation autonomous
     */
    TESTAUTONOMOUS
}
