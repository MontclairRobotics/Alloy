package org.montclairrobotics.alloy.steps;

import org.montclairrobotics.alloy.components.Step;
import org.montclairrobotics.alloy.core.Gyro;
import org.montclairrobotics.alloy.drive.DTInput;
import org.montclairrobotics.alloy.utils.Toggleable;
import org.montclairrobotics.alloy.vector.Angle;

public class FieldCentric extends Toggleable implements Step<DTInput> {
    private Gyro gyro;

    @Override
    public DTInput getOutput(DTInput input) {
        if(status.isEnabled()){
            return new DTInput(
                    input.getTranslation().rotate(new Angle(gyro.getYaw() * -1)),
                    input.getRotation()
            );
        }else{
            return input;
        }
    }

    @Override
    public void enableAction() {

    }

    @Override
    public void disableAction() {

    }
}
