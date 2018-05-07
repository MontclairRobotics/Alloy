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
import java.util.Comparator;
import java.util.Set;
import java.util.TreeMap;
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

    private static int counter;

    private static TreeMap<Integer, Updatable> updateables =
            new TreeMap<>(
                    new Comparator<Integer>() {
                        @Override
                        public int compare(Integer o1, Integer o2) {
                            return o2 - o1;
                        }
                    });

    public static void registerUpdatables() {
        String userFiles = "";
        Reflections alloyReflections =
                new Reflections(
                        "org.montclairrobotics",
                        new MethodAnnotationsScanner(),
                        new SubTypesScanner());
        // Reflections userRefelecitons = new Reflections(userFiles);
        System.out.println(alloyReflections.getMethodsAnnotatedWith(Update.class));

        Set<Method> methods = alloyReflections.getMethodsAnnotatedWith(Update.class);
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
                    new Updatable(
                            method,
                            updateRate,
                            method.getDeclaringClass(),
                            method.getParameters()));
        }
    }

    public static void update() {
        for (Updatable updatable : updateables.values()) {
            if (counter % updatable.getUpdateRate() == 0) {
                updatable.run();
            }
        }
    }
}
