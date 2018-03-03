package org.montclairrobotics.alloy.components;

import org.montclairrobotics.alloy.utils.Input;

import java.util.ArrayList;

public abstract class InputComponent<T> extends Component implements Input{

    public Input<T> input;
    public T output;

    public ArrayList<Step<T>> steps = new ArrayList<>();

    @Override
    public T get() {
        return output;
    }

    public void applySteps(){
        // Start off with the input
        T calculation = input.get();
        // Apply steps
        for(Step<T> step : steps){
            calculation = step.getOutput(calculation);
        }
        // Set the output
        output = calculation;
    }

    public void setInput(Input<T> input){
        this.input = input;
    }
}
