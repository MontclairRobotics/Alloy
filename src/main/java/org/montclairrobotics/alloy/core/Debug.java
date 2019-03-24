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
package org.montclairrobotics.alloy.core;

import org.montclairrobotics.alloy.utils.ConstantInput;
import org.montclairrobotics.alloy.utils.Input;

/**
 * Contains the necessary information for a debug
 *
 * <p>Every debug has a key, which is a title or label of the information being debugged This can be
 * the type of information ex. 'ERROR', where the information is coming from and what it is ex.
 * "Right Left Motor encoder value", or any other descriptor of the information
 *
 * <p>Every debug also has the actual value that is being debugged The part where it is actually
 * debugged is taken care of the Debugger
 *
 * @see Debugger
 * @author Garrett Burroughs
 * @version 0.1
 * @since 0.1
 */
public class Debug {
  /** The key, label, or name of the information */
  public final String key;
  /** The information, or object to be debugged */
  public final Input<Object> value;

  /** debug a changing value */
  public Debug(String key, Input<Object> value) {
    this.key = key;
    this.value = value;
  }

  /** debug a constant value */
  public Debug(String key, Object value) {
    this.key = key;
    this.value = new ConstantInput<>(value);
  }
}
