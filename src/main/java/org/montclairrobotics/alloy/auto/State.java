package org.montclairrobotics.alloy.auto;

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
public abstract class State {

    Integer nextState = null;

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

    public int getNextState(int currentState){
        if(nextState != null){
            return nextState;
        }
        return currentState + 1;
    }
}
