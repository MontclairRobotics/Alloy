package org.montclairrobotics.alloy.virtualRobot;

import org.montclairrobotics.alloy.core.Alloy;
import org.reflections.Reflections;
import org.reflections.scanners.MethodAnnotationsScanner;
import org.reflections.scanners.SubTypesScanner;

import java.util.ArrayList;
import java.util.Set;

/**
 * Created by MHS Robotics on 4/2/2018.
 *
 * @author Garrett Burroughs
 * @since
 */
public class Main {
    
    public boolean enabled = false;
    
    private static ArrayList<AlloyTestBot> robots;
    
    private static Alloy robot;
    
    public static void main(String[] args) {
        Main.robots = new ArrayList<>();
        
        Reflections alloyReflections = new Reflections("org.montclairrobotics", new MethodAnnotationsScanner(), new SubTypesScanner());
        Set<Class<? extends AlloyTestBot>> robots = alloyReflections.getSubTypesOf(AlloyTestBot.class);
        for(Class robot : robots){
            try {
                Main.robots.add((AlloyTestBot) robot.newInstance());
            } catch (InstantiationException e) {
                e.printStackTrace();
            } catch (IllegalAccessException e) {
                e.printStackTrace();
            }
            System.out.println(robot.getName());
        }
        for(AlloyTestBot robot : Main.robots) {
            System.out.println();
        }
    }
    
    public static void start(){
    
    }
    
    public static void autonomousStart(){
    
    }
    
    public static void autonomousLoop(){
    
    }
    
    public static void autonomousStop(){
    
    }
    
    public static void teleopStart(){
    
    }
    
    public static void teleopLoop(){
    
    }
    
    public static void teleopStop(){
    
    }
    
    public static void disabled(){
    
    }
    
    
    
}
