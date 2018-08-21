package org.montclairrobotics.alloy.utils;

import org.montclairrobotics.alloy.components.InputComponent;
import org.montclairrobotics.alloy.update.Update;

public class GyroCorrection extends InputComponent {
    private double correction;

    @Update
    public void calculateCorrection(){
        output = correction;
    }

    public double getCorrection(){
        return correction;
    }

}
