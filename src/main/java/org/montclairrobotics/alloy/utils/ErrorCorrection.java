package org.montclairrobotics.alloy.utils;

public interface ErrorCorrection<T> {
    public void setInput(Input<T> input);
    public void setTarget(T target);
    public T getCorrection();
}
