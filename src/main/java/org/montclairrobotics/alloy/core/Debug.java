package org.montclairrobotics.alloy.core;

import org.montclairrobotics.alloy.utils.Input;

public class Debug {

    public Debug(String key, Input<Object> value) {
        this.key = key;
        this.value = value;
    }

    public Debug(String key, Object value){
        this.key = key;
        this.value = new Input<Object>() {
            @Override
            public Object get() {
                return value;
            }
        };
    }

    public String key;
    public Input<Object> value;
}
