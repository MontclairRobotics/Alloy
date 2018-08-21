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
package org.montclairrobotics.alloy.update;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.lang.reflect.Parameter;
import java.util.ArrayList;
import org.montclairrobotics.alloy.components.Component;
import org.montclairrobotics.alloy.exceptions.UpdateException;

/**
 * A class used for creating objects that can be updated by the Updater
 *
 * <p>An updateable contains a method to be updates along with any extra information needed for it
 * to be properly called and updated like the update rate, parameters and objects that it should be
 * invoked on
 *
 * @see Updater
 * @author Garrett Burroughs
 * @since 0.1
 */
public class Updateable {

    /** The method that will be called when it is updated */
    private Method update;

    /** How often the updateable should be updated */
    private int updateRate;

    /** The class that contains the method */
    private Class clazz;

    /** Any parameters for the method (This should be empty) */
    private Parameter[] parameters;

    /** All objects that the method needs to be called on */
    private ArrayList<Object> objects;

    Updateable(Method update, int updateRate, Class clazz, Parameter[] parameters) {
        this.update = update;
        this.updateRate = updateRate;
        this.clazz = clazz;
        this.parameters = parameters;
    }

    /** Gets references to all of the object that the update method should be called on */
    public void getReferences() {
        for (Component c : Component.getComponents()) {
            if (c.getClass().equals(clazz)) {
                objects.add(c);
            }
        }
    }

    public void run() {
        if (parameters.length != 0) {
            throw new UpdateException("UPDATED METHODS CAN NOT HAVE PARAMETERS");
        }
        try {
            for (Object o : objects) {
                update.invoke(o, (Object[]) parameters);
            }
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        } catch (InvocationTargetException e) {
            e.printStackTrace();
        }
    }

    public int getUpdateRate() {
        return updateRate;
    }
}
