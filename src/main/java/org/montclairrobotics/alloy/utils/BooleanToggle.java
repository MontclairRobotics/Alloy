package org.montclairrobotics.alloy.utils;

public class BooleanToggle extends Toggleable implements Input<Boolean>{


    @Override
    public Boolean get() {
        return status.booleanValue();
    }

    /**
     * Method to be called when the toggleable is enabled
     */
    @Override
    public void enableAction() {}

    /**
     * Method to be called when the toggleable is disabled
     */
    @Override
    public void disableAction() {}
}
