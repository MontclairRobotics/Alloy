package org.montclairrobotics.alloy.components;

import org.montclairrobotics.alloy.core.Debug;
import org.montclairrobotics.alloy.core.Debugger;
import org.montclairrobotics.alloy.update.Update;
import org.montclairrobotics.alloy.utils.Toggleable;

import java.util.ArrayList;

/**
 * The main component class that all components of alloy should extend
 *
 * Alloy is built of a system of components that all work together to operate a robot
 * Components can be broken down into 2 simple parts
 * -Physical Component
 * -Input Component
 *
 * A physical component would include any part, feature, or function that is physically on the robot
 * This can include things like motors, manipulators shooters, drivetrain, etc.
 *
 * An input component is more control based and includes things like buttons, joysticks, triggers,
 * sensors, flow camera, optical control and anything else that would send an input to a physical component
 * @see InputComponent
 *
 * All components have the ability to be toggled on and off as well as built in debug capability
 * for easier testing and debugging of specific components.
 *
 * There is also a static list of all components that are created so that the updater can
 * reference them. This means that you can have a method in a component be updated just by
 * addint the @Update annotation
 *
 * @Author Garrett Burroughs
 * @Version 0.1
 * @Since 0.1
 */
public abstract class Component extends Toggleable{

    public static Debugger debugger;
    private boolean debugMode = true; //

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

    public void enableDebug(){
        debugMode = false;
    }

    public void disableDebug(){
        debugMode = true;
    }

    @Update
    public void debug(){
        if(status.booleanValue() && debugMode) {
            for (Debug debug : debugs) {
                debugger.debug(debug);
            }
        }
    }

    @Override
    public void enableAction(){}

    @Override
    public void disableAction(){}

}
