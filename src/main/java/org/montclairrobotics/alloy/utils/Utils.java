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
   * <p>The constrain method will return the passed in value if it is between the boundaries (min &
   * max), amd will return the min or the max depending on which side it runs out on
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
    double scaled = (in - inputMin) / (inputMax - inputMin); // scale the input to a value from 0-1
    double output =
        (outputMax - outputMin) * scaled + outputMin; // adjust the scaled value to fit the output
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

  /**
   * Wraps a value from a minimum to a maximum
   *
   * <p>When wrapping, if a value exceeds the maximum threshold, it will wrap back around to the
   * minimum threshold
   *
   * <p>Wrapping is essentially circular (modular) around the 2 given bounds
   *
   * <p>for example, 4 wrapped between 1 and 3 would result in 1
   *
   * <p>wrapping a from 0 to b is also the same operation as a MOD b
   *
   * <p>The input for wrapping is considered to be all real numbers and the output is considered to
   * be [min, max]
   *
   * @param in the value to be wrapped
   * @param min the maximum threshold for wrapping
   * @param max the minimum threshold for wrapping
   * @return the inputted value wrapped between the min and the max
   */
  public static double wrap(double in, double min, double max) {
    double diff = min - max;
    return ((in - min) % diff + diff) % diff + max;
  }

  // CONSTANTS AND CONVERSIONS
  /** conversion of an angle from degrees to radians */
  public static final double DEGREES_TO_RADIANS = Math.PI / 180;

  /** Conversion of an angle from radians to degrees */
  public static final double RADIANS_TO_DEGREES = 180 / Math.PI;

  /** Conversion of a distance from inches to centimeters */
  public static final double INCHEES_TO_CENTIMETERS = 2.54;

  /** conversion of a distance from centimeters to inches */
  public static final double CENTIMETERS_TO_INCHES = .3937;

  /** converts a temperature from fahrenheit to celsius */
  public static double fahrenheitToCelsius(double degreesFahrenheit) {
    return (degreesFahrenheit - 32) * 5 / 9;
  }

  /** converst a temperature from celsius to fahrenheit */
  public static double celsiusToFahrenheit(double degreesCelsius) {
    return (degreesCelsius * 9 / 5) + 32;
  }
}
