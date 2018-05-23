package org.montclairrobotics.alloy.drive;

import org.montclairrobotics.alloy.core.Encoder;
import org.montclairrobotics.alloy.core.Motor;
import org.montclairrobotics.alloy.motor.MotorModule;
import org.montclairrobotics.alloy.utils.ErrorCorrection;
import org.montclairrobotics.alloy.vector.Vector;

public class DriveModule extends MotorModule {
    /**
     * Create a fully functioning motor module
     * <p>
     * Modules created like this will be able to adjust
     * their speed based on an error correction to
     * maintain a more accurate speed.
     *
     * @param direction    the direction that the module runs (for use in motor groups)
     * @param encoder      the encoder that keeps track of the motors position
     * @param powerControl an error correction to adjust the speed of the module
     * @param motors       the motors that the module controls
     */
    public DriveModule(Vector direction, Encoder encoder, ErrorCorrection<Double> powerControl, Motor... motors) {
        super(direction, encoder, powerControl, motors);
    }
}
