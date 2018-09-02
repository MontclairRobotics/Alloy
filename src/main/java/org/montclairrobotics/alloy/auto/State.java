package org.montclairrobotics.alloy.auto;

import org.montclairrobotics.alloy.components.Component;
import org.montclairrobotics.alloy.core.Debug;

/**
 * Created by MHS Robotics on 12/16/2017.
 *
 * States are designed to be passed in and run in a state machine. States can be used for
 * Autonomous modes as well as autonomously doing actions in teleop.
 * @see StateMachine
 * @see AlloyAutonomous
 *
 * @author Garrett Burroughs
 * @since 0.1
 * @version 0.1
 */
public abstract class State extends Component{

    Integer nextState = null;
    String debug = "Running State: ";
    String description = "None";

    /**
     * The start method is the first thing called when the state is run
     */
    public abstract void start();

    /**
     * The run method is called every loop while the state is running
     */
    public abstract void run();

    /**
     * The Stop method is the last thing called once the state is done
     */
    public abstract void stop();

    /**
     * IsDone should return true when the state is finished
     * @return true if the state is done
     */
    public abstract boolean isDone();

    /**
     * In order to have a non linear state machine, the state machine must know what state
     * to go to when it is done with the previous state.
     * The state also sometimes needs to know the current state for example if it just wanted
     * to increment the state by one.
     * @param currentState the state the state machine is currently running
     * @return the state the state machine should go to
     */
    public int getNextState(int currentState){
        if(nextState != null){
            return nextState;
        }
        return currentState + 1;
    }

    /**
     * When a state machine is running, it will debug out information about
     * the state it is running. It will debug the result of debugInfo
     *
     * @param currentState the current state so that it can be used in the debug
     * @return debug information about the state
     */
    public String stateInfo(int currentState){
        return debug + currentState + "\n Description" + description;
    }
    
    public abstract Debug[] getDebugs();

}
