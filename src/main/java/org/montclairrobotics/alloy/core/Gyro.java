package org.montclairrobotics.alloy.core;

public interface Gyro {
    public double getRoll();
    public double getYaw();
    public double getPitch();

    public void reset();
}
