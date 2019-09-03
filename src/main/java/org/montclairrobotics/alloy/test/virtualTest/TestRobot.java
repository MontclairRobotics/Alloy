/*
MIT License

Copyright (c) 2019 Garrett Burroughs

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
package org.montclairrobotics.alloy.test.virtualTest;

import org.montclairrobotics.alloy.virtualRobot.AlloyTestBot;

/**
 * Created by MHS Robotics on 4/3/2018.
 *
 * @author Garrett Burroughs
 * @since
 */
public class TestRobot extends AlloyTestBot {

    /**
     * The robotSetup is where all code specific to robot setup is placed If you only have one
     * teleop this can be done in the initialization Method. robotSetup is called right after the
     * robot core is initialized
     */
    @Override
    public void robotSetup() {}

    /**
     * The initialization method is were everything specific to the OpMode Should be set up.
     * Initialization will be the first thing called after The robot setup.
     */
    @Override
    public void initialization() {}

    /**
     * Although most of the periodic actions are taken care by the updater, the user may want to add
     * their own methods and code that need to be updated or run periodically, this can be done in
     * the periodic() method periodic will be run every loop.
     */
    @Override
    public void periodic() {}

    @Override
    public String getName() {
        return "Test Robot 1";
    }
}
