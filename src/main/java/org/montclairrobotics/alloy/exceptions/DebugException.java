package org.montclairrobotics.alloy.exceptions;

import org.montclairrobotics.alloy.components.Component;

/**
 * Created by MHS Robotics on 10/6/2018.
 *
 * @author Garrett Burroughs
 * @since
 */
public class DebugException extends RuntimeException {
    public DebugException(String message) {
        super(message);
        Component.debugger.error(message);
    }
}
