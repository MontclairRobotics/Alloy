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

/**
 * Created by MHS Robotics on 11/13/2017.
 *
 * <p>Debugs are used for debugging information to the user In ftc the debugs use Telemetry to read
 * an output to the phone The debug allows for easier usage of the telemetry methods That are most
 * used for debugging.
 *
 * @author Garrett Burroughs
 * @version 0.1
 * @since 0.1
 */
public interface Debug {
    /**
     * Debugs a value with a Key(Identifier)
     *
     * @param key Name of the value
     * @param value Value to be debugged
     */
    public void log(String key, Object value);

    /**
     * Debugs any value or message(note) to the user with a default key
     *
     * @param value Value to be debugged
     */
    public void msg(Object value);
}
