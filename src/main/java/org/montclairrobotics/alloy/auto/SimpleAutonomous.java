package org.montclairrobotics.alloy.auto;

import org.montclairrobotics.alloy.auto.States.Drive;
import org.montclairrobotics.alloy.auto.States.Turn;

/**
 * Created by MHS Robotics on 1/26/2018.
 *
 * SimpleAutonomous abstracts away the concept of
 * adding states to a state machine, so that states
 * can be added as if they were commands to be executed
 * by the auto mode. This class only has the pre-written
 * states included, so another class extending this one
 * would need to be created to add any custom states
 *
 * @author Garrett Burroughs
 * @since
 */
public class SimpleAutonomous extends AlloyAutonomous{
    @Override
    public void setup() {
    
    }
    
    public void drive(double speed, double distance){
        auto.addState(new Drive(speed, distance));
    }
    
    public void turn(double speed, double degrees){
        auto.addState(new Turn(speed, degrees));
    }
}
