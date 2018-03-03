package org.montclairrobotics.alloy.components;

public interface Step<T> {
    public T getOutput(T input);
}
