package org.montclairrobotics.alloy.motor;

import org.montclairrobotics.alloy.components.Component;

import java.util.ArrayList;


/**
 * A motor group is a group of motor modules that run together
 *
 * A motor group should control any collection of motors that
 * take an input, and then maps the output to a set of motors
 * that run together. For example in a lift mechanism there
 * may be 2 motor that both run in the same direction but
 * one should be inverted to lift the lift up. The modules
 * would define what direction the motors are running in
 * the mapper would define that they need to run in opposite
 * directions and the MotorGroup would run the motors together
 * fully operating the lift. This could work with other things
 * like an intake, shooter or other manipulator that requires
 * multiple motors running together from one input
 */
public class MotorGroup extends Component {

    public Mapper mapper;
    public ArrayList<MotorModule> modules;
}
