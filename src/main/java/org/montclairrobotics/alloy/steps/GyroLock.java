package org.montclairrobotics.alloy.steps;

import org.montclairrobotics.alloy.components.InputComponent;
import org.montclairrobotics.alloy.drive.DTInput;
import org.montclairrobotics.alloy.utils.GyroCorrection;
import org.montclairrobotics.alloy.utils.Input;
import org.montclairrobotics.alloy.vector.Angle;

public class GyroLock extends InputComponent<DTInput> {
    private Input<Double> heading;
    private GyroCorrection correction;

    public GyroLock(GyroCorrection correction, Input<Double> gyro){
        heading = gyro;
        this.correction = correction;
    }

    @Override
    public void enableAction() {
        correction.setTargetAngle(new Angle(heading.get()));
    }

    public void disableAction(){
        correction.reset();
    }
}
