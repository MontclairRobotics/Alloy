package org.montclairrobotics.alloy.auto.States;

import org.montclairrobotics.alloy.auto.State;
import org.montclairrobotics.alloy.utils.Input;

/**
 * A conditional state runs a state if the condition is true
 *
 * A conditional state takes in a boolean input, and evaluates it when the state starts.
 * If the input is true when the state starts, the passed in state will execute.
 */
public class  ConditionalState extends State {
    /**
     * The boolean input that will determine if the state is run
     */
    Input<Boolean> condition;

    /**
     * The state that will be run, if the input is true
     */
    State state;

    /**
     * A variable to keep track whether or not the state should be run
     */
    boolean run;


    public ConditionalState(Input<Boolean> condition, State state){
        this.condition = condition;
        this.state = state;
    }

    @Override
    public void start() {
        run = condition.get(); // Evaluate the condition
        if(run){
            state.start();
        }
    }

    @Override
    public void run() {
        if(run){
            state.run();
        }
    }

    @Override
    public void stop() {
        if(run){
            state.stop();
        }
    }

    @Override
    public boolean isDone() {
        if (run){
            return state.isDone();
        }else{
            return true;
        }
    }
}
