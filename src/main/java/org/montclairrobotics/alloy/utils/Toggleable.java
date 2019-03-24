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
 * Created by MHS Robotics on 2/11/2018.
 *
 * <p>Toggleables are used for objects of mechanisms that can either be on (Enabled), or off
 * toggleables can have actions defined for when they are enabled and disabled, as well as be tied
 * to different parts of alloy like buttons and auto states
 *
 * @see org.montclairrobotics.alloy.control.ToggleButton
 * @author Garrett Burroughs
 * @since 0.1
 */
public abstract class Toggleable {

  /** The status of the toggleable, to keep track of weather it is enabled, or disabled */
  public Status status;

  /** Method to be called when the toggleable is enabled */
  public abstract void enableAction();

  /** Method to be called when the toggleable is disabled */
  public abstract void disableAction();

  public enum Status {
    ENABLED(true),
    DISABLED(false);

    boolean enabled;

    Status(boolean enabled) {
      this.enabled = enabled;
    }

    public boolean isEnabled() {
      return enabled;
    }
  }

  /** Enables the toggleable */
  public void enable() {
    status = Status.ENABLED;
    enableAction();
  }

  /** Disables the toggleable */
  public void disable() {
    status = Status.DISABLED;
    disableAction();
  }

  /**
   * Switches(Toggles), between the two states, If the toggleable is disabled, enable it If the
   * toggleable is enabled, disable it
   */
  public void toggle() {
    switch (status) {
      case ENABLED:
        disable();
        break;
      case DISABLED:
        enable();
        break;
    }
  }
}
