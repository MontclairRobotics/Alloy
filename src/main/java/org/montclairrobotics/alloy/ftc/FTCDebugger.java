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
public class FTCDebugger implements Debugger {

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
     * Debugger out information
     * @param key   Name of the value
     * @param value Value to be debugged
     */
    @Override
    public void log(String key, Object value) {
        telemetry.addData(key, value);
    }

    /**
     * Debugger out a message
     * @param value Value to be debugged
     */
    @Override
    public void msg(Object value) {
        telemetry.addData("Debugger", value);
    }

    /**
     * Debugs out a debug object
     *
     * @param debug the object to be debugged
     */
    @Override
    public void debug(Debug debug) {
        log(debug.key, debug.value.get());
    }

    /**
     * Gives a warning to the user
     *
     * @param warning the warning
     */
    @Override
    public void warning(String warning) {
        log("WARNING", warning);
    }

}
