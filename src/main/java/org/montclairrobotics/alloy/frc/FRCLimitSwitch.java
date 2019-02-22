package org.montclairrobotics.alloy.frc;

import edu.wpi.first.wpilibj.DigitalInput;
import org.montclairrobotics.alloy.components.InputComponent;

public class FRCLimitSwitch extends InputComponent<Boolean> {
    DigitalInput limiSwitch;

    public FRCLimitSwitch(DigitalInput limiSwitch) {
        this.limiSwitch = limiSwitch;
        setInput(limiSwitch::get);
    }

    public FRCLimitSwitch(int channel){
        this(new DigitalInput(channel));
    }
}
