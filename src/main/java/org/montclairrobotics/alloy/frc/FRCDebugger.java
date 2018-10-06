package org.montclairrobotics.alloy.frc;

import edu.wpi.first.wpilibj.Sendable;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import org.montclairrobotics.alloy.core.Debugger;
import org.montclairrobotics.alloy.exceptions.DebugException;

/**
 * Created by MHS Robotics on 10/6/2018.
 *
 * @author Garrett Burroughs
 * @since
 */
public class FRCDebugger extends Debugger {
    /**
     * The most basic debug that simply outputs information given a key and value
     *
     * @param key   Key of the value being debugged
     * @param value Value to be debugged
     */
    @Override
    public void out(String key, Object value) {
        try {
            SmartDashboard.putData(key, (Sendable) value);
        }catch(Exception e){
            throw new DebugException("Objects being debugged must be able to be sent over the network tables");
        }
    }
}
