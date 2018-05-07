package org.montclairrobotics.alloy.motor;

import org.montclairrobotics.alloy.components.Component;
import org.montclairrobotics.alloy.core.Motor;

import java.util.ArrayList;

public interface Mapper<T> {
    public void map(T input, MotorModule modules);
}
