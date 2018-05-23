package org.montclairrobotics.alloy.test;

import com.qualcomm.robotcore.hardware.DcMotor;
import org.montclairrobotics.alloy.core.RobotCore;
import org.montclairrobotics.alloy.utils.Initializeable;

public class Hardware {

    // ============
    //     IDS
    // ============

    static class DeviceID{
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
