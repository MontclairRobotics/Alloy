/*
MIT License

Copyright (c) 2018 Garrett Burroughs

Permission is hereby granted, free of charge, to any person obtaining a copy
of this software and associated documentation files (the "Software"), to deal
in the Software without restriction, including without limitation the rights
to use, copy, modify, merge, publish, distribute, sublicense, and/or sell
copies of the Software, and to permit persons to whom the Software is
furnished to do so, subject to the following conditions:

The above copyright notice and this permission notice shall be included in all
copies or substantial portions of the Software.

THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR
IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY,
FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE
AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER
LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM,
OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN THE
SOFTWARE.
*/
package org.montclairrobotics.alloy.core;

/**
 * Controls the flow of how debug information is read out
 *
 * <p>Debugs are used for debugging information to the user In ftc the debugs use Telemetry to read
 * an output to the phone The debug allows for easier usage of the telemetry methods That are most
 * used for debugging.
 *
 * <p>The Debugger also has different debug levels, so that the user can easily switch the amount of
 * information they are getting. For example, the user would want a lot of information about a
 * particular part when they are fixing a problem or tuning the robot, but only important
 * information when they are driving in a competition
 *
 * @author Garrett Burroughs
 * @version 0.1
 * @since 0.1
 */
public abstract class Debugger {

    public enum Level {

        /**
         * The override can be used to override the level system and ouput any information Overrides
         * are always enabled
         */
        OVERRIDE(-1),

        /**
         * Error Debugs should never be Turned off. Error debugs will display when anything that is
         * critical to robot operation occurs
         */
        ERROR(0),

        /**
         * Competition debugs should contain information that is important to the drive team during
         * a competition match.
         */
        COMPETITION(1),

        /** Warnings should display when there is a potential problem with robot operation */
        WARNING(2),

        /**
         * Test debugs should be used when testing functionality of a process or mechanism, Tests
         * should contain simple and important information to the process rather than an in depth
         * look on how everything is working.
         */
        TEST(3),

        /**
         * Debugs should contain the most information about a mechanism or process. For example,
         * debug information for a motor would include its target speed, current speed, target
         * position, current position, direction, % error, and so on. Debugs should only be turned
         * on when fixing problems, as the amount of information can be quite cluttering
         */
        DEBUG(4);

        /**
         * Each debug level has a rank, the lower the rank, the more important. For example, if
         * running debug level 3, information from levels 3, 2, 1, and 0 will be debugged.
         *
         * <p>Ranks should not be lower than 0
         */
        int rank;

        private Level(int rank) {
            this.rank = rank;
        }

        public int getRank() {
            return rank;
        }
    }

    /** Current Debug Level */
    public static Level debugLevel = Level.DEBUG;

    /**
     * Set the debug level
     *
     * @param level The level to be set
     */
    public static void setDebugLevel(Level level) {
        if (level.getRank() < 0) {
            Debugger.debugLevel = level;
        } else {
            Debugger.debugLevel = Level.ERROR;
        }
    }

    /**
     * The most basic debug that simply outputs information given a key and value
     *
     * @param key Key of the value being debugged
     * @param value Value to be debugged
     */
    public abstract void out(String key, Object value);

    /**
     * Debugs a value if the level is lower than the current debug level
     *
     * @param key Name of the value
     * @param value Value to be debugged
     */
    public void log(String key, Object value, Level level) {
        if (level.getRank() < debugLevel.getRank()) {
            out(key, value);
        }
    }

    /**
     * Debugs out a debug object
     *
     * @param debug the object to be debugged
     */
    public void debug(Debug debug) {
        log(debug.key, debug.value, Level.DEBUG);
    }

    /**
     * Debug out verbose information
     *
     * @param key Name of the value
     * @param value Value to be displayed
     */
    public void debug(String key, Object value) {
        log(key, value, Level.DEBUG);
    }

    /**
     * Gives a warning to the user
     *
     * @param warning the warning
     */
    public void warn(String warning) {
        log("WARNING", warning, Level.WARNING);
    }

    /**
     * Gives an error message to the user
     *
     * @param error the error message to be displayed
     */
    public void error(String error) {
        log("ERROR", error, Level.ERROR);
    }

    /**
     * Give a message intended for the driver during practice or competition operation
     *
     * @param key Name of the value
     * @param value Value to be displayed
     */
    public void driverInfo(String key, Object value) {
        log(key, value, Level.COMPETITION);
    }

    /**
     * Information to be displayed when testing a process or mechanism
     *
     * @param key Name of the value
     * @param value Value to be displayed
     */
    public void test(String key, Object value) {
        log(key, value, Level.TEST);
    }

    /**
     * A message that overrides the debug level system
     *
     * @param key Name of the value
     * @param value Value to be displayed
     */
    public void override(String key, Object value) {
        log(key, value, Level.OVERRIDE);
    }
}
