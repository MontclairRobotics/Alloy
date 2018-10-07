package org.montclairrobotics.alloy.test;

import org.montclairrobotics.alloy.auto.SimpleAutonomous;
import org.montclairrobotics.alloy.auto.States.Turn;
import org.montclairrobotics.alloy.vector.Angle;

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
        for(int i = 0; i < 4; i++){
            // These functions come from SimpleAutonomous, they just add a new state to the state machine
            drive(1, 100);
            turn(1, new Angle(90));
        }
    }
}
