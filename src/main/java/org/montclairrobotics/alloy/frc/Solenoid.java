package org.montclairrobotics.alloy.frc;

import org.montclairrobotics.alloy.utils.Toggleable;

public class Solenoid extends Toggleable {
    edu.wpi.first.wpilibj.Solenoid solenoid;

    public Solenoid(edu.wpi.first.wpilibj.Solenoid solenoid) {
        this.solenoid = solenoid;
    }

    @Override
    public void enableAction() {
        solenoid.set(true);
    }

    @Override
    public void disableAction() {
        solenoid.set(false);
    }

    public edu.wpi.first.wpilibj.Solenoid getSolenoid(){
        return solenoid;
    }
}
