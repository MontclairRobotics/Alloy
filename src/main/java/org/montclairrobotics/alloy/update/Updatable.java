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
import org.montclairrobotics.alloy.components.Component;

/**
 * Created by MHS Robotics on 2/11/2018.
 *
 * @author Garrett Burroughs
 * @since 0.1
 */
public class Updatable {
    private Method update;
    private int updateRate;
    private Class clazz;
    private Parameter[] parameters;

    Updatable(Method update, int updateRate, Class clazz, Parameter[] parameters) {
        this.update = update;
        this.updateRate = updateRate;
        this.clazz = clazz;
        this.parameters = parameters;
    }

    public void run() {
        if (parameters.length != 0) {
            throw new RuntimeException("UPDATED METHODS CAN NOT HAVE PARAMETERS");
        }
        try {
            for (Component component : Component.getComponents()) {
                if (component.getClass().equals(clazz)) {
                    update.invoke(component, (Object[]) parameters);
                }
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
