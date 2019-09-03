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
package org.montclairrobotics.alloy.test;

import org.montclairrobotics.alloy.auto.SimpleAutonomous;
import org.montclairrobotics.alloy.auto.States.Turn;

public class SquareAuto extends SimpleAutonomous {

    @Override
    public void setup() {
        Turn.setTicksPerDegree(10); //  This is a dummy value, this value will need to be tested for

        /*
        Keep in mind, even though states can be added like this, because they are created by methods inherited from
        Simple autonomous, they are not actually run right here.
        This means that you can not conditional add states here, for example

        if(sensor.value > 5){
            drive(1, 5);
        }
        a
        would not work, because it would be reading the sensor value on startup.
        If you want to run states conditionally, use ConditionalState
         */
        for (int i = 0; i < 4; i++) {
            // These functions come from SimpleAutonomous, they just add a new state to the state
            // machine
            drive(1, 100);
            turn(1, 90);
        }
    }
}
