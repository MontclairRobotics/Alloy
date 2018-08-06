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
package org.montclairrobotics.alloy.core;

import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import java.util.ArrayList;
import org.montclairrobotics.alloy.components.Component;
import org.montclairrobotics.alloy.ftc.FTCDebugger;
import org.montclairrobotics.alloy.update.Updater;
import org.montclairrobotics.alloy.utils.Initializeable;

/**
 * Created by MHS Robotics on 11/13/2017.
 *
 * <p>The main purpose behind the alloy class is to controll how and when all of the mehotds in
 * Robot are run as well as initialize all global variables
 *
 * @author Garrett Burroughs
 * @version 0.1
 * @since 0.1
 */
public abstract class Alloy extends OpMode {

    public static ArrayList<Initializeable> initObjects = new ArrayList<>();

    /**
     * The robotSetup is where all code specific to robot setup is placed If you only have one
     * teleop this can be done in the initialization Method. robotSetup is called right after the
     * robot core is initialized
     */
    public abstract void robotSetup();

    /**
     * The initialization method is were everything specific to the OpMode Should be set up.
     * Initialization will be the first thing called after The robot setup.
     */
    public abstract void initialization();

    /**
     * Although most of the periodic actions are taken care by the updater, the user may want to add
     * their own methods and code that need to be updated or run periodically, this can be done in
     * the periodic() method periodic will be run every loop.
     */
    public abstract void periodic();

    @Override
    public void init() {
        // Set Up the core robot components, This allows them to be accessed throughout the project
        new RobotCore(telemetry, hardwareMap, gamepad1, gamepad2);
        Component.debugger = new FTCDebugger();

        for (Initializeable i : initObjects) {
            i.init();
        }

        robotSetup();
        initialization();
    }

    @Override
    public void loop() {
        Updater.update();
        periodic();
    }
}
