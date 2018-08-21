/*
MIT License

Copyright (c) 2018 Garrett Burroughs

Permission is hereby granted, free of charge, to any person obtaining a copy
of this software and associated documentation files (the "Software"), to deal
in the Software without restriction, including without limitation the rights
to use, copy, modify, merge, publish, distribute, sublicense, and/or sell
copies of the Software, and to permit persons to whom the Software is
furnished to do so, subject to the following conditions:

The above copyright notice and this permission notice shall be included in all
copies or substantial portions of the Software.

THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR
IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY,
FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE
AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER
LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM,
OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN THE
SOFTWARE.
*/
package org.montclairrobotics.alloy.components;

import java.util.ArrayList;
import org.montclairrobotics.alloy.core.Debug;
import org.montclairrobotics.alloy.core.Debugger;
import org.montclairrobotics.alloy.update.Update;
import org.montclairrobotics.alloy.utils.Toggleable;

/**
 * The main component class that all components of alloy should extend
 *
 * <p>Alloy is built of a system of components that all work together to operate a robot Components
 * can be broken down into 2 simple parts -Physical Component -Input Component
 *
 * <p>A physical component would include any part, feature, or function that is physically on the
 * robot This can include things like motors, manipulators shooters, drivetrain, etc.
 *
 * <p>An input component is more control based and includes things like buttons, joysticks,
 * triggers, sensors, flow camera, optical control and anything else that would send an input to a
 * physical component
 *
 * @see InputComponent
 *     <p>All components have the ability to be toggled on and off as well as built in debug
 *     capability for easier testing and debugging of specific components.
 *     <p>There is also a static list of all components that are created so that the updater can
 *     reference them. This means that you can have a method in a component be updated just by
 *     addint the @Update annotation @Author Garrett Burroughs @Version 0.1 @Since 0.1
 */
public abstract class Component extends Toggleable {
    /** The object used for debugging information about all components */
    public static Debugger debugger;

    /** Will debug out information if true */
    private boolean debugMode = true;

    /** The debugs that will be printed for this component out each loop */
    private ArrayList<Debug> debugs;

    /** a static reference of all the components that are made */
    public static ArrayList<Component> components;


    public Component() {
        components.add(this);
    }

    public static ArrayList<Component> getComponents() {
        return components;
    }

    /** Adds a debug to the component, this will be debugged out every loop if debug mode is enabled */
    public void addDebug(Debug debug) {
        debugs.add(debug);
    }

    /** Adds multiple debugs to the components, they will be debugged out every loop if debug mode is enabled*/
    public void addDebugs(Iterable<? extends Debug> debugs) {
        for (Debug debug : debugs) {
            this.debugs.add(debug);
        }
    }

    /** Enabled debug mode, causing any debugs added to the component to be debugged out */
    public void enableDebug() {
        debugMode = false;
    }

    /** Disabled debug mode, stopping all debugs */
    public void disableDebug() {
        debugMode = true;
    }

    @Update
    public void debug() {
        if (status.isEnabled() && debugMode) {
            for (Debug debug : debugs) {
                debugger.debug(debug);
            }
        }
    }

    @Override
    /** The action that is taken when the component is enabled should be overridden bu the user */
    public void enableAction() {}

    @Override
    /** The action that is taken when the component is disabled, should be overridden by the user */
    public void disableAction() {}
}
