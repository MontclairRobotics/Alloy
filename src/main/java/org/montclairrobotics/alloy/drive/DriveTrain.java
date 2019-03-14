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
package org.montclairrobotics.alloy.drive;

import java.util.ArrayList;
import org.montclairrobotics.alloy.motor.Mapper;
import org.montclairrobotics.alloy.motor.MotorGroup;
import org.montclairrobotics.alloy.motor.MotorModule;
import org.montclairrobotics.alloy.utils.Input;

/**
 * A more specialized motor group specified as a drive train
 *
 * <p>Each robot can only
 *
 * @author Garrett Burroughs
 * @since 0.1
 */
public class DriveTrain extends MotorGroup<DTInput> {

    private final Input<DTInput> defaultInput;
    private static DriveTrain autoDriveTrain;

    public DriveTrain(Input<DTInput> input, Mapper mapper, MotorModule... modules) {
        super(input, mapper, modules);
        defaultInput = input;
    }

    public static DriveTrain getAutoDriveTrain() {
        return autoDriveTrain;
    }

    public static void setAutoDriveTrain(DriveTrain autoDriveTrain) {
        DriveTrain.autoDriveTrain = autoDriveTrain;
    }

    public void setDefaultInput() {
        setInput(defaultInput);
    }

    public int[] getEncoderValues() {
        ArrayList<MotorModule> modules = getModules();
        int[] values = new int[modules.size()];
        for (int i = 0; i < values.length; i++) {
            values[i] = modules.get(i).getEncoder().getTicks();
        }
        return values;
    }

    public int[] getRightEncoderValues() {
        ArrayList<MotorModule> modules = getModules();
        int total = 0;
        for (MotorModule m : modules) {
            if (m.getOffset().getX() > 0) {
                total++;
            }
        }
        int[] values = new int[total];
        for (int i = 0; i < values.length; i++) {
            if (modules.get(i).getOffset().getX() > 0) {
                values[i] = modules.get(i).getEncoder().getTicks();
            }
        }
        return values;
    }

    public int[] getLeftEncoderValues() {
        ArrayList<MotorModule> modules = getModules();
        int total = 0;
        for (MotorModule m : modules) {
            if (m.getOffset().getX() < 0) {
                total++;
            }
        }
        int[] values = new int[total];
        for (int i = 0; i < values.length; i++) {
            if (modules.get(i).getOffset().getX() < 0) {
                values[i] = modules.get(i).getEncoder().getTicks();
            }
        }
        return values;
    }
}
