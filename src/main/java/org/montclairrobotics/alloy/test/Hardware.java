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
package org.montclairrobotics.alloy.test;

import com.qualcomm.robotcore.hardware.DcMotor;
import org.montclairrobotics.alloy.core.RobotCore;

public class Hardware {

    // ============
    //     IDS
    // ============

    static class DeviceID {
        public static final String shooterMotorRight = "shoot_right";
        public static final String shooterMotorLeft = "shoot_left";

        public static final String intakeMotorRight = "intake_right";
        public static final String intakeMotorLeft = "intake_left";
    }

    // ============
    //   Devices
    // ============

    // Shooter
    static DcMotor shooterMotorRight;
    static DcMotor shooterMotorLeft;

    // Intake
    static DcMotor intakeMotorRight;
    static DcMotor intakeMotorLeft;

    public static void init() {
        shooterMotorRight = RobotCore.getHardwareMap().dcMotor.get(DeviceID.shooterMotorRight);
        shooterMotorLeft = RobotCore.getHardwareMap().dcMotor.get(DeviceID.shooterMotorLeft);

        intakeMotorRight = RobotCore.getHardwareMap().dcMotor.get(DeviceID.intakeMotorRight);
        intakeMotorLeft = RobotCore.getHardwareMap().dcMotor.get(DeviceID.intakeMotorLeft);
    }
}
