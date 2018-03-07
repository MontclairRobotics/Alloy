package org.montclairrobotics.alloy.update;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.lang.reflect.Parameter;

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

    public void run(){
        try {
            update.invoke(clazz);
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        } catch (InvocationTargetException e) {
            e.printStackTrace();
        }
    }

    public int getUpdateRate(){
        return updateRate;
    }
}
