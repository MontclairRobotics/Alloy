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
package org.montclairrobotics.alloy.utils;

/**
 * A set of common methods that are useful in writing robot code
 *
 * <p>The Utils class contains static methods that can perform basic functions that can be useful
 *
 * @author garrettburroughs
 * @version 0.1
 * @since 0.1
 */
public class Utils {
    /**
     * Makes sure that a value is between 2 different values
     *
     * <p>The constrain method will return the passed in value if it is between the boundaries (min
     * & max), amd will return the min or the max depending on which side it runs out on
     *
     * @param in the value being passed in
     * @param min minimum the value can be
     * @param max maximum the value can be
     * @return a value between the maximum and minimum value
     */
    public static double constrain(double in, double min, double max) {
        if (in > max) {
            return max;
        } else if (in < min) {
            return min;
        }
        return in;
    }

    /**
     * The map function takes an input value that can be between a certain range and maps it to a
     * specified output range
     *
     * @param in the value being passed in
     * @param inputMin the minimum value the input can be
     * @param inputMax the maximum value the input can be
     * @param outputMin the minimum value the output can be
     * @param outputMax the maximum value the output can be
     * @return a mapped value from the input range to the output range
     */
    public static double map(
            double in, double inputMin, double inputMax, double outputMin, double outputMax) {
        double scaled =
                (in - inputMin) / (inputMax - inputMin); // scale the input to a value from 0-1
        double output =
                (outputMax - outputMin) * scaled
                        + outputMin; // adjust the scaled value to fit the output
        return output;
    }

    /**
     * Returns the sign of a number
     *
     * @param in the number whose sign will be calculated
     * @return 1 if the number is positive and -1 if it is negative
     */
    public static int sign(double in) {
        return in > 0 ? 1 : -1;
    }

    /**
     * A modified power function that conserves the sign
     *
     * @param in the number to be raised to a power
     * @param pow the power the number will be raised to
     * @return in^pow while keeping the sign of in
     */
    public static double pow2(double in, int pow) {
        double regularResult = Math.pow(in, pow);
        return pow % 2 == 0 ? regularResult * sign(in) : regularResult;
    }
}
