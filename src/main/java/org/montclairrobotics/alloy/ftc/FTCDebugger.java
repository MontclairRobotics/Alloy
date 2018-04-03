package org.montclairrobotics.alloy.ftc;

import org.firstinspires.ftc.robotcore.external.Telemetry;
import org.montclairrobotics.alloy.core.Debug;
import org.montclairrobotics.alloy.core.Debugger;
import org.montclairrobotics.alloy.core.RobotCore;

/**
 * Created by MHS Robotics on 11/14/2017.
 *
 * The FTCDebugger class is a basic wrapper around the
 * ftc telemetry framework and allows telemetry to be
 * used in any class as well as making debugging information
 * easier
 *
 * @author Garrett Burroughs
 * @since 0.1
 */
public class FTCDebugger extends Debugger {

    /**
     * The telemetry used for debugging
     */
    Telemetry telemetry;

    /**
     * Create a new FTCDebugger, and throw an exception if it has not been initialized
     */
    public FTCDebugger() {
        try {
            this.telemetry = RobotCore.getTelemetry();
        }catch (NullPointerException e){
            e.printStackTrace();
            throw new RuntimeException("You tried to access the telemetry before the robotcore has been initialized");
        }
    }
    
    /**
     * The most basic debug that simply outputs information given a key and value
     *
     * @param key   Key of the value being debugged
     * @param value Value to be debugged
     */
    @Override
    public void out(String key, Object value) {
        telemetry.addData(key, value);
    }
}
