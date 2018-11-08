package org.montclairrobotics.alloy.steps;

import org.montclairrobotics.alloy.components.Step;
import org.montclairrobotics.alloy.drive.DTInput;
import org.montclairrobotics.alloy.utils.Toggleable;
import org.montclairrobotics.alloy.vector.Angle;

public class Sensitivity extends Toggleable implements Step<DTInput>{
    public double driveSensitivity;
    public double turnSensitivity;

    public Sensitivity(double driveSensitivity, double turnSensitivity){
        this.driveSensitivity = driveSensitivity;
        this.turnSensitivity = turnSensitivity;
    }

    public Sensitivity(double sensitivity){
        this(sensitivity, sensitivity / 2);
    }

    /**
     * Method to be called when the toggleable is enabled
     */
    @Override
    public void enableAction() {
        // Sensitivity does not have to do anything when enabled
    }

    /**
     * Method to be called when the toggleable is disabled
     */
    @Override
    public void disableAction() {
        // Sensitivity does not have to do anything when disabled
    }

    /**
     * The operation to be performed on the input, to get the output
     *
     * @param input
     */
    @Override
    public DTInput getOutput(DTInput input) {
        if(status.isEnabled()) {
            return new DTInput(input.getTranslation().scale(driveSensitivity), new Angle(input.getRotation().getDegrees() * turnSensitivity));
        }else{
            return input;
        }
    }
}
