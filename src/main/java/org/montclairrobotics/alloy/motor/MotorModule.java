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
import org.montclairrobotics.alloy.components.InputComponent;
import org.montclairrobotics.alloy.components.Step;
import org.montclairrobotics.alloy.core.Encoder;
import org.montclairrobotics.alloy.core.Motor;
import org.montclairrobotics.alloy.exceptions.InvalidConfigurationException;
import org.montclairrobotics.alloy.update.Update;
import org.montclairrobotics.alloy.utils.BangBang;
import org.montclairrobotics.alloy.utils.ConstantInput;
import org.montclairrobotics.alloy.utils.ErrorCorrection;
import org.montclairrobotics.alloy.vector.Vector;

/**
 * Highly Functional set of motors that run together
 *
 * <p>A motor module consists of multiple motors that run together in the same direction. Modules
 * are aware of what direction the run in so they can be used in MoroGroups to be run together with
 * other modules. Modules can also be controlled with an encoder and a ErrorCorrection to ensure
 * that the are going the right speed.
 *
 * <p>You can also add steps to the Module that can alter the amount of power that the module will
 * receive. This can be useful for modules that are being controlled by a separate entity such as a
 * motor group.
 *
 * @author Garrett Burroughs
 * @version 0.1
 * @since 0.1
 */
public class MotorModule extends Component {
    /** The motors that the module will control */
    private ArrayList<Motor> motors;

    /** The direction that the modules run for use in a motor group */
    private Vector direction;

    /** the position of the motor, relative to the center of the motor group */
    private Vector offset;

    /** An error correction that will control the power */
    private ErrorCorrection<Double> powerControl;

    /** The encoder that keeps track of the position and controls the motors */
    private Encoder encoder;

    /** how fast the module should be running */
    private double targetPower;

    /**
     * An input component, that takes in the motor input, and runs it to the output (setting the
     * motor powers) this way, steps can be added to this modifier, and the input of the module can
     * be changed
     */
    private InputComponent<Double> modifier;

    /** True when the module is being set to a position */
    private boolean toPotsition = false;

    /** If running to a position, the target position the motor will aim to go to */
    private double targetPosition;

    /** Error Corrector to get to the right position */
    private ErrorCorrection<Double> positionCorrection;

    /** How fast the motors move when they are having their position be set */
    private double movementSpeed;

    /**
     * Create a fully functioning motor module
     *
     * <p>Modules created like this will be able to adjust their speed based on an error correction
     * to maintain a more accurate speed.
     *
     * @param direction the direction that the module runs (for use in motor groups)
     * @param encoder the encoder that keeps track of the motors position
     * @param powerControl an error correction to adjust the speed of the module
     * @param motors the motors that the module controls
     */
    public MotorModule(
            Vector direction,
            Vector offset,
            Encoder encoder,
            ErrorCorrection<Double> powerControl,
            Motor... motors) {
        this.direction = direction;
        this.motors = new ArrayList<>(Arrays.asList(motors));
        this.powerControl = powerControl.copy();
        this.encoder = encoder;
        this.offset = offset;
        try {
            powerControl.setInput(() -> encoder.getScaledVelocity());
        } catch (NullPointerException e) {
            powerControl.setInput(new ConstantInput<>(0.0));
        }

        modifier = new InputComponent<Double>() {};

        positionCorrection = new BangBang(1);

        positionCorrection.setInput(encoder);
    }

    /**
     * Create motor module without the use of an encoder
     *
     * <p>Modules created like this will not be abe to monitor or adjust their speed to maintain a
     * constant and more exact speed.
     *
     * @param direction what direction the module runs
     * @param motors the motors the module controls
     */
    public MotorModule(Vector direction, Vector offset, Motor... motors) {
        this(direction, offset, null, null, motors);
    }

    /**
     * Set an encoder for the module
     *
     * @param encoder the encoder that keeps track of the position of the module
     */
    public MotorModule setEncoder(Encoder encoder) {
        this.encoder = encoder;
        return this;
    }

    /**
     * set the power control for the module
     *
     * @param powerControl an error correction to adjust the speed of the module
     */
    public MotorModule setErrorCorrection(ErrorCorrection powerControl) {
        this.powerControl = powerControl.copy();
        return this;
    }

    public MotorModule setPositionCorrection(ErrorCorrection positionCorrection) {
        this.positionCorrection = positionCorrection;
        return this;
    }

    @Update
    public void powerCorrection() {
        if (status.isEnabled()) { // Check if its enabled
            if (!toPotsition) { // Manual operation
                for (Motor m : motors) {
                    if (powerControl != null) {
                        m.setMotorPower(targetPower + powerControl.getCorrection());
                    } else {
                        m.setMotorPower(targetPower);
                    }
                }
            } else { // Autonomous operation
                for (Motor m : motors) {
                    if (encoder == null)
                        throw new InvalidConfigurationException(
                                "Modules must have an encoder to set their position");
                    m.setMotorPower(positionCorrection.getCorrection() * movementSpeed);
                }
            }
        } else { // If disabled, set the power to 0
            for (Motor m : motors) {
                m.setMotorPower(0);
            }
        }
    }

    /**
     * Set the target power of the module
     *
     * @param power how fast the module will go (-1 to 1)
     */
    public void setPower(double power) {
        modifier.setInput(new ConstantInput<>(power));
        modifier.applySteps();
        targetPower = modifier.get();
        powerControl.setTarget(modifier.get());
        toPotsition = false;
    }

    public void setMovementSpeed(double speed) {
        this.movementSpeed = speed;
    }

    public void setTargetPosition(double targetPosition) {
        positionCorrection.setTarget(targetPosition);
        toPotsition = true;
    }

    public MotorModule setPowerModifier(InputComponent<Double> modifier) {
        this.modifier = modifier;
        return this;
    }

    public MotorModule addStep(Step s) {
        modifier.addStep(s);
        return this;
    }

    /** Method to be called when the toggleable is disabled */
    @Override
    public void disableAction() {
        for (Motor m : motors) {
            m.setMotorPower(0);
        }
    }

    // Autonomous operation

    /** @return the motor arraylist in the module */
    public ArrayList<Motor> getMotors() {
        return motors;
    }

    /** @return the direction the module runs in */
    public Vector getDirection() {
        return direction;
    }

    /** @return the error correction used for the module */
    public ErrorCorrection<Double> getPowerControl() {
        return powerControl;
    }

    /** @return the encoder that monitors the position of the monitor */
    public Encoder getEncoder() {
        return encoder;
    }

    /** @return the current target power of the module */
    public double getTargetPower() {
        return targetPower;
    }

    /** @return the power modifier of the motor */
    public InputComponent<Double> getModifier() {
        return modifier;
    }

    public MotorModule setOffset(Vector offset) {
        this.offset = offset;
        return this;
    }

    /** @return the relative position to the center of the motor group */
    public Vector getOffset() {
        return offset;
    }

    public boolean atTargetPosition(double tolerance) {
        return Math.abs(encoder.getTicks() - targetPosition) < tolerance;
    }

    public void toPosition() {
        toPotsition = true;
    }

    public void usingPower() {
        toPotsition = false;
    }
}
