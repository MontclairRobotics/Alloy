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
package org.montclairrobotics.alloy.example.virtualTest;

import org.montclairrobotics.alloy.auto.State;
import org.montclairrobotics.alloy.auto.StateMachine;
import org.montclairrobotics.alloy.virtualRobot.AlloyTestAutonomous;

/**
 * Created by MHS Robotics on 4/17/2018.
 *
 * @author Garrett Burroughs
 * @since
 */
public class TestAuto extends AlloyTestAutonomous {
    /**
     * This is where the user should define all their code and where "Auto", should be instantiated
     */
    @Override
    public void setup() {
        System.out.println("Setting up auto mode");
        auto =
                new StateMachine(
                        new State() {
                            @Override
                            public void start() {
                                System.out.println("State Started");
                            }

                            @Override
                            public void run() {
                                System.out.println("State Running");
                            }

                            @Override
                            public void stop() {
                                System.out.println("State Stopped");
                            }

                            @Override
                            public boolean isDone() {
                                return true;
                            }
                        });
    }

    @Override
    public String getName() {
        return "Test Auto";
    }
}
