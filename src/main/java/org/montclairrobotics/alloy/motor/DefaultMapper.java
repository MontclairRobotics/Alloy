package org.montclairrobotics.alloy.motor;

import org.montclairrobotics.alloy.utils.Input;
import org.montclairrobotics.alloy.utils.Utils;

/**
 * A simple default mapper
 *
 * This mapper will run all of the motors in the group in the same direction
 */
public class DefaultMapper implements Mapper<Input<Double>> {
    @Override
    public void map(Input<Double> input, MotorModule... modules) {
        for(MotorModule m : modules){
            m.setPower(input.get() * Utils.sign(m.getDirection().getManitude()));
        }
    }
}
