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
package org.montclairrobotics.alloy.motor;

import java.util.ArrayList;
import java.util.Arrays;
import org.montclairrobotics.alloy.components.Component;
import org.montclairrobotics.alloy.update.Update;
import org.montclairrobotics.alloy.utils.Input;

/**
 * A motor group is a group of motor modules that run together
 *
 * <p>A motor group should control any collection of motors that take an input, and then maps the
 * output to a set of motors that run together. For example in a lift mechanism there may be 2 motor
 * that both run in the same direction but one should be inverted to lift the lift up. The modules
 * would define what direction the motors are running in the mapper would define that they need to
 * run in opposite directions and the MotorGroup would run the motors together fully operating the
 * lift. This could work with other things like an intake, shooter or other manipulator that
 * requires multiple motors running together from one input
 */
public class MotorGroup<T> extends Component {

  /** The mapper that will be used for power assignment and calculation */
  private final Mapper mapper;

  /** The modules contained in the motor group */
  private ArrayList<MotorModule> modules;

  /** The control input for the motor group */
  private Input<T> input;

  public MotorGroup(Input input, Mapper mapper, MotorModule... modules) {
    this.input = input;
    this.mapper = mapper;
    this.modules = (ArrayList) Arrays.asList(modules);
  }

  /**
   * Creates a motor group with a default mapper that simply
   *
   * @param input
   * @param modules
   */
  public MotorGroup(Input<Double> input, MotorModule... modules) {
    this(input, new DefaultMapper(), modules);
  }

  @Update
  public void controlPower() {
    mapper.map(input, modules.toArray(new MotorModule[modules.size()]));
  }

  public Mapper getMapper() {
    return mapper;
  }

  public Input<T> getInput() {
    return input;
  }

  public void setInput(Input<T> input) {
    this.input = input;
  }

  public ArrayList<MotorModule> getModules() {
    return modules;
  }
}
