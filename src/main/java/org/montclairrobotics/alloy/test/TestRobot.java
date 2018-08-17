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

import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import org.montclairrobotics.alloy.core.Alloy;
import org.montclairrobotics.alloy.core.Button;
import org.montclairrobotics.alloy.ftc.FTCButton;
import org.montclairrobotics.alloy.ftc.FTCJoystick;
import org.montclairrobotics.alloy.ftc.FTCMotor;
import org.montclairrobotics.alloy.motor.MotorGroup;
import org.montclairrobotics.alloy.motor.MotorModule;
import org.montclairrobotics.alloy.steps.Deadzone;
import org.montclairrobotics.alloy.utils.PID;
import org.montclairrobotics.alloy.vector.XY;

@TeleOp
public class TestRobot extends Alloy {
    // Lower level hardware abstractions
    FTCMotor rightIntakeMotor;
    FTCMotor leftIntakeMotor;

    // High Level Hardware abstractions
    MotorGroup shooter;
    MotorGroup intake;

    PID motorCorrection;

    @Override
    public void robotSetup() {

        rightIntakeMotor = new FTCMotor(Hardware.DeviceID.intakeMotorRight);
        leftIntakeMotor = new FTCMotor(Hardware.DeviceID.intakeMotorLeft);

        motorCorrection = new PID(1, 0, 0);

        intake =
                new MotorGroup(
                        new FTCJoystick(gamepad1, FTCJoystick.Side.RIGHT).addStep(new Deadzone()),
                        new ShooterMapper(),
                        new MotorModule(
                                new XY(0, 1),
                                rightIntakeMotor
                                        .getEncoder()
                                        .setMaxSpeed(100)
                                        .setDistancePerTick(30),
                                motorCorrection,
                                rightIntakeMotor),
                        new MotorModule(
                                new XY(0, -1),
                                leftIntakeMotor
                                        .getEncoder()
                                        .setMaxSpeed(100)
                                        .setDistancePerTick(30),
                                motorCorrection,
                                leftIntakeMotor));
    }

    @Override
    public void initialization() {}

    @Override
    public void periodic() {
        Button shooterButton = FTCButton.getAButton(gamepad1);
    }
}
