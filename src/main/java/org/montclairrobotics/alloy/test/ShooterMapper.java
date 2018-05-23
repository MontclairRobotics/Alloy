package org.montclairrobotics.alloy.test;

import org.montclairrobotics.alloy.components.InputComponent;
import org.montclairrobotics.alloy.motor.Mapper;
import org.montclairrobotics.alloy.motor.MotorModule;
import org.montclairrobotics.alloy.utils.Utils;
import org.montclairrobotics.alloy.vector.Vector;

public class ShooterMapper implements Mapper<Vector> {
    @Override
    public void map(Vector input, MotorModule... modules) {
        for(MotorModule m : modules){
            if(m.offset.getX() > 0){
                m.setPower((input.getY() + input.getX()) * Utils.sign(m.direction.getY()));
            }else{
                m.setPower((input.getY() - input.getX()) * Utils.sign(m.direction.getY()));
            }
        }
    }
}
