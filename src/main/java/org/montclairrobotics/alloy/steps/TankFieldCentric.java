package org.montclairrobotics.alloy.steps;

import org.montclairrobotics.alloy.components.Step;
import org.montclairrobotics.alloy.drive.DTInput;
import org.montclairrobotics.alloy.utils.Toggleable;

public class TankFieldCentric extends Toggleable implements Step<DTInput> {
    @Override
    public DTInput getOutput(DTInput input) {
        return null;
    }

    @Override
    public void enableAction() {

    }

    @Override
    public void disableAction() {

    }
}
