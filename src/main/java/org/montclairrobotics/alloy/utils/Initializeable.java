package org.montclairrobotics.alloy.utils;

import org.montclairrobotics.alloy.core.Alloy;

public abstract class Initializeable {

    public Initializeable(){
        Alloy.initObjects.add(this);
    }

    public abstract void init();
}
