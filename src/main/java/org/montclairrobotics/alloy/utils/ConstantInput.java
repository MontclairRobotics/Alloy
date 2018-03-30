package org.montclairrobotics.alloy.utils;

/**
 * Created by MHS Robotics on 3/30/2018.
 *
 * The constant input can be used when a object or method requires an Input,
 * but the input will not change. This can be useful in testing and debugging,
 * As well as certain component implementations
 *
 * @author Garrett Burroughs
 * @since
 */
public class ConstantInput<T> implements Input<T> {
    
    /**
     * The constant to be returned by the input
     */
    T constant;
    
    public ConstantInput(T constant){
        this.constant = constant;
    }
    
    @Override
    public T get() {
        return null;
    }
}
