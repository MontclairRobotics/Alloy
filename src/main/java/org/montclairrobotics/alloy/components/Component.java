package org.montclairrobotics.alloy.components;

import org.montclairrobotics.alloy.core.Debug;
import org.montclairrobotics.alloy.core.Debugger;
import org.montclairrobotics.alloy.update.Update;
import org.montclairrobotics.alloy.utils.Toggleable;

import java.util.ArrayList;

public abstract class Component extends Toggleable{

    public static Debugger debugger;

    // All components are added to the component arraylist so they can be updated
    public static ArrayList<Component> components;

    public Component() {
        components.add(this);
    }

    public static ArrayList<Component> getComponents(){
        return components;
    }

    private ArrayList<Debug> debugs;

    public void addDebug(Debug debug){
        debugs.add(debug);
    }

    public void addDebugs(Iterable<? extends Debug> debugs){
        for(Debug debug : debugs){
            this.debugs.add(debug);
        }
    }

    @Update
    public void debug(){
        if(status.booleanValue()) {
            for (Debug debug : debugs) {
                debugger.debug(debug);
            }
        }
    }
}
