package org.montclairrobotics.alloy.core;

/**
 * Created by MHS Robotics on 11/13/2017.
 *
 * Debugs are used for debugging information to the user
 * In ftc the debugs use Telemetry to read an output to the phone
 * The debug allows for easier usage of the telemetry methods
 * That are most used for debugging.
 *
 * @author Garrett Burroughs
 * @version 0.1
 * @since 0.1
 *
 */
public interface Debugger {
    /**
     * Debugs a value with a Key(Identifier)
     *
     * @param key   Name of the value
     * @param value Value to be debugged
     */
    public void log(String key, Object value);

    /**
     * Debugs any value or message(note) to the user with a default key
     *
     * @param value Value to be debugged
     */
    public void msg(Object value);


    /**
     * Debugs out a debug object
     *
     * @param debug the object to be debugged
     */
    public void debug(Debug debug);

    /**
     * Gives a warning to the user
     * @param warning the warning
     */
    public void warning(String warning);
}
