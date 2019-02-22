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
package org.montclairrobotics.alloy.vector;

/**
 * Created by MHS Robotics on 11/14/2017.
 *
 * <p>A vector can be defined in rectangular(XY), or polar form. <br>
 * This class is implementation for vectors in XY form<br>
 * It controls the conversions from rectangular to polar and vice versa
 *
 * <p><link>https://en.wikipedia.org/wiki/Vector_notation</link>
 *
 * @see Vector
 * @author Garrett Burroughs
 * @version 0.1
 * @since 0.1
 */
public class XY implements Vector {
    private final double x;
    private final double y;

    public XY(double x, double y) {
        this.x = x;
        this.y = y;
    }

    public XY(XY vector) {
        x = vector.getY();
        y = vector.getY();
    }

    /**
     * Gets the X component of the vector
     *
     * @return the X component of the vector
     */
    @Override
    public double getX() {
        return x;
    }

    /**
     * Gets the Y component of the vector
     *
     * @return the Y component of the vector
     */
    @Override
    public double getY() {
        return y;
    }

    /**
     * Gets the magnitude of the vector
     *
     * @return the magnitude of the vector
     */
    @Override
    public double getManitude() {
        return Math.sqrt(Math.pow(x, 2) + Math.pow(y, 2));
    }


    /**
     * Gets the angle in standard position that the vector makes
     *
     * @return the angle in standard position that the vector makes
     */
    @Override
    public Angle getAngle() {
        return new Angle(Angle.AngleMeasure.RADIAN, Math.atan2(y, x));
    }


    /**
     * Adds two vectors together and returns the result
     *
     * <p>
     *
     * <p>The result of a vector addition will be another vector, One way of adding two vectors
     * together is splitting it up into it's x and y components and adding them together and making
     * a new vector out of those components <br>
     * EX: <br>
     * V1 = 3i + 4j <br>
     * V2 = 7i + 5j <br>
     * <br>
     * V1 + V2 = 3i + 7i + 4j + 5j = 10i + 9j <br>
     * Geometric Visualization: <br>
     * Vectors can also be added in a geometric way through the "Tip-To-Tail" method, In this method
     * both the vectors are drawn using arrow vector notation
     * <link>https://en.wikipedia.org/wiki/Vector_notation</link> one vector is then drawn at the
     * edge of the first vector. The resultant vector is then drawn from the start of the first
     * vector to the tip of the second vector This is where the name tip to tail comes from
     *
     * <p>
     *
     * @param vector The vector to be added
     * @return The result of the two vectors being added
     */
    @Override
    public Vector add(Vector vector) {
        return new XY(x + vector.getX(), y + vector.getY());
    }

    /**
     * Subtracts a vector and returns the result
     *
     * <p>
     *
     * <p>vector subtraction is similar to vector addition but the direction of the vector being
     * subtracted is simply reversed <br>
     * EX: <br>
     * V1 = 3i + 4j <br>
     * V2 = 7i + 5j <br>
     * <br>
     * V1 + V2 = 3i + 7i + 4j + 5j = 10i + 9j <br>
     * V1 - V2 = 3i + (-7i) + 4j + (-5j) = -4i + -j <br>
     *
     * @param vector The vector to be subtracted
     * @return The result of the passed in vector being subtracted from the vector object
     */
    @Override
    public Vector subtract(Vector vector) {
        return add(vector.scale(-1));
    }

    /**
     * Scales the vectors magnitude by a scalar value
     *
     * @param scalar double to scale the vector by
     * @return The result of the vector being
     */
    @Override
    public Vector scale(double scalar) {
        return new XY(x * scalar, y * scalar);
    }

    /**
     * Returns the product of two vectors that have been crossed
     *
     * <p>
     *
     * <p>The result of the cross product is a vector, The cross product of a vector is the vector
     * that is perpendicular to both vectors When crossing two 2dimensional vectors the result will
     * be a 3dimensional vector Because of this this method only returns the magnitude (Z component)
     * of the cross product The magnitude of the cross product is same as the area of the
     * parallelogram that the two vectors make in the X/Y plane. <br>
     * <link>https://en.wikipedia.org/wiki/Cross_product</link>
     *
     * @param vector the vector to be multiplied using the cross product
     * @return the magnitude of the cross product of the two vectors
     */
    @Override
    public double cross(Vector vector) {
        return this.getManitude() * vector.getManitude() * angleBetween(vector).sin();
    }

    /**
     * Returns the dot product of two vectors
     *
     * <p>
     *
     * <p>The result of the dot product is a scalar value (number), The dot product can be viewed as
     * the projection of one vector on to another times the magnitude of the original vector <br>
     * This can be written mathematically as |A||B|cos(theta), where theta is the angle between A
     * and B Another mathematical representation of the dot product using its components is: <br>
     * A = {@literal <a1, a2>} <br>
     * B = {@literal <b1, b2>} <br>
     * A . B = {@literal <a1*b1, a2*b2>} <br>
     * <link>https://en.wikipedia.org/wiki/Dot_product</link>
     *
     * @param vector The vector to be multiplied using the dot product
     * @return the scalar result of the dot of the two vectors
     */
    @Override
    public double dot(Vector vector) {
        return this.getX() * vector.getX() + this.getY() * vector.getY();
    }

    /**
     * returns a vector rotated by a given angle
     *
     * @param angle the angle that the vector is to be rotated by
     * @return the result of the rotated vector
     */
    @Override
    public Vector rotate(Angle angle) {
        return new Polar(getManitude(), new Angle(getAngle().getDegrees() + angle.getDegrees()));
    }

    /**
     * gets the angle between two vectors
     *
     * @param vector the vector that will be used to find the angle between
     * @return the angle between the vectors
     */
    @Override
    public Angle angleBetween(Vector vector) {
        return new Angle(Math.abs(vector.getAngle().getDegrees() - getAngle().getDegrees()));
    }

    /**
     * Normalizes a vector and returns the result
     *
     * <p>
     *
     * <p>A normalized vector is a vector with magnitude 1. Normalized vectors are also called unit
     * vectors. A normalized vector keeps it's direction as well as the same proportions of its
     * components. When a vector is defined by its components it can be normalized it can be
     * expressed as <br>
     * A = {@literal <a1, a2>} <br>
     * A' = {@literal <a1/|A|, a2/|A|>} <br>
     *
     * @return the normalized vector
     */
    @Override
    public Vector normalize() {
        return new XY(x / getManitude(), y / getManitude());
    }

    /**
     * Creates a copy of the vector
     *
     * @return a copy of the vector
     */
    @Override
    public Vector copy() {
        return new XY(x, y);
    }
}
