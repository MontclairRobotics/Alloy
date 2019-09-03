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
package org.montclairrobotics.alloy.ftc;

import org.montclairrobotics.alloy.auto.States.DriveEncoders;
import org.montclairrobotics.alloy.auto.States.TurnEncoders;

/**
 * Created by MHS Robotics on 1/26/2018.
 *
 * <p>SimpleAutonomous abstracts away the concept of adding states to a state machine, so that
 * states can be added as if they were commands to be executed by the auto mode. This class only has
 * the pre-written states included, so another class extending this one would need to be created to
 * add any custom states
 *
 * @author Garrett Burroughs
 * @since
 */
public class SimpleAutonomous extends AlloyAutonomous {
    @Override
    public void setup() {}

    public void drive(double speed, double distance) {
        auto.addState(new DriveEncoders(speed, distance));
    }

    public void turn(double speed, double degrees) {
        auto.addState(new TurnEncoders(speed, degrees));
    }
}
