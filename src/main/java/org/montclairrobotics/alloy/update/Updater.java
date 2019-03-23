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

import java.lang.annotation.Annotation;
import java.lang.reflect.Method;
import java.util.*;
import org.montclairrobotics.alloy.components.Component;
import org.reflections.Reflections;
import org.reflections.scanners.MethodAnnotationsScanner;
import org.reflections.scanners.SubTypesScanner;

/**
 * Created by MHS Robotics on 12/5/2017.
 *
 * @author Garrett Burroughs
 * @since 0.1
 */
public class Updater {

    /**
     * Keeps track of the number of times the updater has run Used in diagnostics and calculating
     * when to update methods
     */
    private static int counter;

    private static TreeMap<Integer, Updateable> updateables =
            new TreeMap<>(
                    new Comparator<Integer>() {
                        @Override
                        public int compare(Integer o1, Integer o2) {
                            return o2 - o1;
                        }
                    });

    public static void registerUpdatables() {
        // Create an arraylist to store all package names
        ArrayList<String> packageReferences = new ArrayList<>();
        // Create an arraylist of all the reflections that will later be used to get annotated
        // methods
        ArrayList<Reflections> reflections = new ArrayList<>();
        // Create a set of all the methods
        Set<Method> methods = new HashSet<>();

        // Iterate through all of the components to get the package
        for (Object o : Component.getComponents()) {
            System.out.println(o.getClass().getPackage().getName());
            // Check if the package has already been added
            if (!packageReferences.contains(o.getClass().getPackage().getName())) {
                packageReferences.add(o.getClass().getPackage().getName()); // add the package
            }
        }

        // Iterate through all of the package referneces to create a reflections object
        for (String p : packageReferences) {
            reflections.add(
                    new Reflections(p, new MethodAnnotationsScanner(), new SubTypesScanner()));
        }

        // Get all update methods
        for (Reflections r : reflections) {
            methods.addAll(r.getMethodsAnnotatedWith(Update.class));
        }
        // Loop through all methods and create Updateable objects
        for (Method method : methods) {
            int updateRate = 1;
            int priority = 0;
            for (Annotation annotation : method.getDeclaredAnnotations()) {
                if (annotation instanceof Update) {
                    updateRate = ((Update) annotation).updateRate();
                    priority = ((Update) annotation).priority();
                }
            }
            updateables.put(
                    priority,
                    new Updateable(
                            method,
                            updateRate,
                            method.getDeclaringClass(),
                            method.getParameters()));
        }
        for (Updateable u : updateables.values()) {
            u.getReferences();
        }
    }

    /**
     * The update method takes care of updating all of the methods in the project that are within a
     * component and are annotated with @Update It will also check the Update rate and decide
     * weather to update the method accordingly
     */
    public static void update() {
        for (Updateable updateable : updateables.values()) {
            if (counter % updateable.getUpdateRate() == 0) {
                updateable.run();
            }
        }
    }
}
