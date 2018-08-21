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
package org.montclairrobotics.alloy.ftc;

import org.firstinspires.ftc.robotcore.external.Telemetry;
import org.montclairrobotics.alloy.core.Debugger;
import org.montclairrobotics.alloy.core.RobotCore;
import org.montclairrobotics.alloy.exceptions.InvalidConfigurationException;

/**
 * implementation of the debugger for the FTC competition
 *
 * @author Garrett Burroughs
 * @version 0.1
 * @since 0.1
 */
public class FTCDebugger extends Debugger {

    /** The telemetry used for debugging */
    private Telemetry telemetry;

    /** Create a new FTCDebugger, and throw an exception if it has not been initialized */
    public FTCDebugger() {
        try {
            this.telemetry = RobotCore.getTelemetry();
        } catch (NullPointerException e) {
            e.printStackTrace();
            throw new InvalidConfigurationException(
                    "You tried to access the telemetry before the robotcore has been initialized");
        }
    }

    public Telemetry getTelemetry() {
        return telemetry;
    }

    /**
     * The most basic debug that simply outputs information given a key and value
     *
     * @param key Key of the value being debugged
     * @param value Value to be debugged
     */
    @Override
    public void out(String key, Object value) {
        telemetry.addData(key, value);
    }
}
