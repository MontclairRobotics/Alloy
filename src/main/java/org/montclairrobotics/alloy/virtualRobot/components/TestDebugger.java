package org.montclairrobotics.alloy.virtualRobot.components;

import org.montclairrobotics.alloy.core.Debug;
import org.montclairrobotics.alloy.core.Debugger;

import java.util.logging.Logger;

/**
 * Created by MHS Robotics on 4/2/2018.
 *
 * @author Garrett Burroughs
 * @since 0.1
 */
public class TestDebugger extends Debugger{
    /**
     * The most basic debug that simply outputs information given a key and value
     *
     * @param key   Key of the value being debugged
     * @param value Value to be debugged
     */
    @Override
    public void out(String key, Object value) {
        System.out.println(key + " : " + value.toString());
    }
}
