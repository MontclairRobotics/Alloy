package org.montclairrobotics.alloy.core;

/**
 * Created by MHS Robotics on 2/25/2018.
 *
 * A universal motor is a motor that has the ability to both be controlled by motor power
 * As well as controlled by encoder position. In FTC this also means dynamically switching
 * the modes that the motor is in
 * @see TargetMotor
 * @see PoweredMotor
 *
 * @author Garrett Burroughs
 * @since
 */
public interface UniversalMotor extends TargetMotor, PoweredMotor {
}
