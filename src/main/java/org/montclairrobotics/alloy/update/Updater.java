package org.montclairrobotics.alloy.update;

import org.reflections.Reflections;
import org.reflections.scanners.MethodAnnotationsScanner;
import org.reflections.scanners.SubTypesScanner;

import java.lang.annotation.Annotation;
import java.lang.reflect.Method;
import java.util.Comparator;
import java.util.Set;
import java.util.TreeMap;

/**
 * Created by MHS Robotics on 12/5/2017.
 *
 * @author Garrett Burroughs
 * @since 0.1
 */

//I'm making my life so much harder than it has to be because I hate myself
public class Updater {

    private static int counter;

    private static TreeMap<Integer, Updatable> updateables = new TreeMap<>(new Comparator<Integer>() {
        @Override
        public int compare(Integer o1, Integer o2) {
            return o2 - o1;
        }
    });


    public static void  registerUpdatables() {
        String userFiles = "";
        Reflections alloyReflections = new Reflections("org.montclairrobotics", new MethodAnnotationsScanner(), new SubTypesScanner());
        // Reflections userRefelecitons = new Reflections(userFiles);
        System.out.println(alloyReflections.getMethodsAnnotatedWith(Update.class));

        Set<Method> methods = alloyReflections.getMethodsAnnotatedWith(Update.class);
        for(Method method : methods){
            int updateRate = 1;
            int priority = 0;
            for(Annotation annotation : method.getDeclaredAnnotations()){
                if(annotation instanceof Update){
                    updateRate = ((Update) annotation).updateRate();
                    priority = ((Update) annotation).priority();
                }
            }
            updateables.put(priority, new Updatable(method, updateRate, method.getClass(), method.getParameters()));
        }
    }


    public static void update() {
        for (Updatable updatable : updateables.values()){
            if(counter % updatable.getUpdateRate() == 0){
                updatable.run();
            }
        }
    }
}
