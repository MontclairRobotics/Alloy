package org.montclairrobotics.alloy.virtualRobot;

import com.qualcomm.robotcore.util.ElapsedTime;
import org.montclairrobotics.alloy.auto.AlloyAutonomous;
import org.montclairrobotics.alloy.core.Alloy;
import org.montclairrobotics.alloy.core.Mode;
import org.montclairrobotics.alloy.update.Update;
import org.montclairrobotics.alloy.update.Updater;
import org.reflections.Reflections;
import org.reflections.scanners.MethodAnnotationsScanner;
import org.reflections.scanners.SubTypesScanner;

import java.util.ArrayList;
import java.util.Scanner;
import java.util.Set;
import java.util.Timer;

/**
 * Created by MHS Robotics on 4/2/2018.
 *
 * @author Garrett Burroughs
 * @since
 */
public class Main {
    
    public static boolean enabled = false;
    public static Mode opMode;
    private static long startTime; // Store the start time in nanoseconds
    private static int autoRunTime; // How long auto modes can run for, this is usually 30 seconds in FTC and 15 in FRC
    private static int teleopRunTime;
    private static int tickRate; // How many times the robot will run per second
    
    private static ArrayList<AlloyTestBot> robots;
    private static AlloyTestBot robot;
    
    private static ArrayList<AlloyTestAutonomous> autoModes;
    private static AlloyTestAutonomous autoMode;
    
    private static Scanner input;
    
    public static void main(String[] args) {
        // Create a scanner so that the user can set parameters
        input = new Scanner(System.in);
        
        // Get basic runtime parameters
        autoRunTime = intInput("Auto Run Time in seconds: ");
        teleopRunTime = intInput("Teleop Run Time in seconds: ");
        tickRate = intInput("Tick Rate (how many times the program should run a second): ");
        
        // Create array lists to store the robots and auto modes
        Main.robots = new ArrayList<>();
        Main.autoModes = new ArrayList<>();
        
        // Set the current operation mode to Autonomous
        opMode = Mode.TESTAUTONOMOUS;
        
        Reflections alloyReflections = new Reflections("org.montclairrobotics", new MethodAnnotationsScanner(), new SubTypesScanner());
        
        // Get all classes that extend AlloyTestBot and AlloyTestAutonomous
        Set<Class<? extends AlloyTestBot>> robots = alloyReflections.getSubTypesOf(AlloyTestBot.class);
        Set<Class<? extends AlloyTestAutonomous>> autoModes = alloyReflections.getSubTypesOf(AlloyTestAutonomous.class);
        
        // Create a new instance of each test robot and add it to the ArrayList
        for(Class robot : robots){
            try {
                Main.robots.add((AlloyTestBot) robot.newInstance());
            } catch (InstantiationException e) {
                e.printStackTrace();
            } catch (IllegalAccessException e) {
                e.printStackTrace();
            }
            System.out.println("Registered: " + robot.getName());
        }
        
        // Create a new instance of each test autonomous and add it to the Array List
        for(Class auto : autoModes){
            try {
                Main.autoModes.add((AlloyTestAutonomous) auto.newInstance());
            } catch (InstantiationException e) {
                e.printStackTrace();
            } catch (IllegalAccessException e) {
                e.printStackTrace();
            }
        }
        
        for(Integer i = 0; i < Main.robots.size(); i++){
            System.out.println((i + 1) + ": " + Main.robots.get(i).getName());
        }
        
        int selection = intInput("Select the robot you want to run");
        robot = Main.robots.get(selection - 1);
        
        
        
        start();
        
        autonomousStart();
        while(opMode == Mode.TESTAUTONOMOUS && enabled){
            autonomousLoop();
            
            System.out.println(getSeconds());
            
            if(getSeconds() > autoRunTime){
                opMode = Mode.TESTTELEOP;
            }
            try {
                Thread.sleep(1000 / tickRate);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        autonomousStop();
        teleopStart();
        while(opMode == Mode.TESTTELEOP && enabled){
            teleopLoop();
    
            System.out.println(getSeconds());
            
            if(getSeconds() - autoRunTime > teleopRunTime){
                enabled = false;
                break;
            }
            try {
                Thread.sleep(1000 / tickRate);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        teleopStop();
    }
    
    //Helper methods to make programming the virtual robot easier
    
    /**
     * Get the time since the robot has started in seconds
     *
     * @return time in seconds since the robot has started
     */
    public static int getSeconds(){
        return getMillis()/1000;
    }
    
    /**
     * Get the time since the robot has started in milliseconds
     *
     * @return time in milliseconds since the robot has started
     */
    public static int getMillis() {
        return (int) ((System.nanoTime() - startTime) / 1000000);
    }
    
    private static int intInput(String message){
        System.out.println(message);
        return input.nextInt();
    }
    
    /**
     * Start the robot
     */
    public static void start(){
        startTime = System.nanoTime();
        enabled = true;
        robot.robotSetup();
        robot.initialization();
    }
    
    
    
    public static void autonomousStart(){
        autoMode.init();
    }
    
    public static void autonomousLoop(){
        Updater.update();
        autoMode.loop();
    }
    
    public static void autonomousStop(){}
    
    public static void teleopStart(){}
    
    public static void teleopLoop(){
        Updater.update();
        robot.periodic();
    }
    
    public static void teleopStop(){}
    
    public static void disabled(){}
    
    
    
}
