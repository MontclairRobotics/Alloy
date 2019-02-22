package org.montclairrobotics.alloy.core;

import org.montclairrobotics.alloy.utils.Input;

public class LimitedMotor implements Motor{
    private Input<Boolean> bottomLimit;
    private Input<Boolean> topLimit;

    Motor motor;

    public LimitedMotor(Motor motor, Input<Boolean> bottomLimit, Input<Boolean> topLimit) {
        this.motor = motor;
        this.bottomLimit = bottomLimit;
        this.topLimit = topLimit;
    }

    @Override
    public void setMotorPower(double power) {
        if(power > 0 && bottomLimit.get()){
            motor.setMotorPower(0);
        }else if(power < 0 && topLimit.get()){
            motor.setMotorPower(0);
        }else{
            motor.setMotorPower(power);
        }
    }

    @Override
    public double getMotorPower() {
        return getMotorPower();
    }

    @Override
    public void setInverted(boolean inverted) {
        motor.setInverted(inverted);
    }

    @Override
    public boolean getInverted() {
        return motor.getInverted();
    }
}
