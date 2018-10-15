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
import org.montclairrobotics.alloy.components.InputComponent;
import org.montclairrobotics.alloy.core.*;
import org.montclairrobotics.alloy.drive.DriveTrain;
import org.montclairrobotics.alloy.drive.TankDrive;
import org.montclairrobotics.alloy.ftc.FTCJoystick;
import org.montclairrobotics.alloy.ftc.FTCMotor;
import org.montclairrobotics.alloy.motor.MotorGroup;
import org.montclairrobotics.alloy.motor.MotorModule;
import org.montclairrobotics.alloy.steps.Deadzone;
import org.montclairrobotics.alloy.utils.PID;
import org.montclairrobotics.alloy.vector.XY;

/*
This class is solely for testing and example of the Alloy framework

This is an implementation of a basic robot using the fundamental
features of Alloy, much more is possible, and this example is far
from all encompassing of the features that you can use, and how to
use them, but meant as a basic start to get an Idea of what Alloy is
and how to use it.

Also keep in mind, that this is not necessarily how a robot "should"
be implemented. Some of this code is written purely to show that something
is possible, while not being necessary for a basic application. As well as
the fact that each robot is different and the code will be heavily dependant
on that, and alloy allows for the design of many different robots and parts.
 */

@TeleOp
public class TestRobot extends Alloy {
    // Lower level hardware abstractions
    FTCMotor rightIntakeMotor;
    FTCMotor leftIntakeMotor;
    FTCMotor d_rightFront;
    FTCMotor d_rightBack;
    FTCMotor d_leftFront;
    FTCMotor d_leftBack;

    // Higher Level Hardware abstractions
    MotorGroup intake;

    // Global motor control
    PID motorCorrection;

    // Physical control systems
    InputComponent intakeController;
    InputComponent driveStick;

    @Override
    public void robotSetup() {

        // =============================================================================================================
        // EXAMPLE DRIVETRAIN SETUP
        // =============================================================================================================

        // Create Drivetrain Modules (wheels)
        // By making it a tank drive, we use the default tank drive mapper
        DriveTrain dt =
                new TankDrive(
                        new MotorModule(new XY(1, 0), d_rightFront, d_rightBack),
                        new MotorModule(new XY(-1, 0), d_leftFront, d_leftBack));

        // Create the Drivetrain input
        driveStick = new FTCJoystick(RobotCore.getGamepad1(), FTCJoystick.Side.RIGHT);

        // Add modifiers toe the controls
        driveStick.addStep(new Deadzone());

        // Set the Drivetrain Input
        dt.setInput(driveStick);

        // =============================================================================================================
        // EXAMPLE INTAKE SETUP
        // =============================================================================================================

        // Define the PID Correction
        motorCorrection = new PID(1, 0, 0);

        // Create the input
        intakeController = new FTCJoystick(RobotCore.getGamepad2(), FTCJoystick.Side.RIGHT);
        // Modify the input
        intakeController.addStep(new Deadzone());

        // Get the encoders from the motors
        Encoder intakeRightEncoder =
                rightIntakeMotor
                        .getEncoder()
                        .setMaxSpeed(100)
                        .setDistancePerTick(30); // These 100, and 30, are experimental values
        Encoder intakeLeftEncoder =
                rightIntakeMotor.getEncoder().setMaxSpeed(100).setDistancePerTick(30);

        // Define the right and left modules
        MotorModule intakeRightSide =
                new MotorModule(
                        new XY(0, 1), intakeRightEncoder, motorCorrection, rightIntakeMotor);
        MotorModule intakeLeftSide =
                new MotorModule(new XY(0, 1), intakeLeftEncoder, motorCorrection, leftIntakeMotor);

        // Create the intake
        intake =
                new MotorGroup(
                        intakeController, new IntakeMapper(), intakeRightSide, intakeLeftSide);
    }

    @Override
    public void initialization() {}

    @Override
    public void periodic() {}
}
