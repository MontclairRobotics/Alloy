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

import org.montclairrobotics.alloy.vector.Angle;
import org.montclairrobotics.alloy.vector.Vector;

/**
 * An input for a drivetrain that takes a rate of translation, and rotation
 *
 * <p>A DTInput contains the information for controlling a drivetrain. This information is the
 * translation, or the rate at which the robot wil move and the rotation, the rate that the robot
 * will turn
 *
 * @author Garrett Burroughs
 * @version 0.1
 * @since 0.1
 */
public class DTInput {
    /**
     * A translation vector that controls the rate at which the robot will move In a tank drivetrain
     * only the Y component will be taken into account In omnidirectional drivetrains, both the x
     * and y components will be used in drive calculations
     */
    private final Vector translation;

    /** The rate at which the drivetrain will turn */
    private final Angle rotation;

    public DTInput(Vector translation, Angle rotation) {
        this.translation = translation;
        this.rotation = rotation;
    }

    /** @return the rate at which the robot will move */
    public Vector getTranslation() {
        return translation;
    }

    /** @return the rate at which the robot will rotate */
    public Angle getRotation() {
        return rotation;
    }

    public DTInput addAngle(double angle) {
        return new DTInput(translation, new Angle(rotation.getDegrees() + angle));
    }

    public DTInput addVector(Vector vector) {
        return new DTInput(translation.add(vector), rotation);
    }

    public DTInput multiplyAngle(double multiplier) {
        return new DTInput(translation, rotation.multiply(multiplier));
    }

    public DTInput scale(double scalar) {
        return new DTInput(translation.scale(scalar), rotation);
    }
}
