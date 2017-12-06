package org.montclairrobotics.Alloy.Vector;

/**
 * Created by Garrett on 11/14/2017.
 */
public interface Vector {
    public double getX();
    public double getY();
    public double getManitude();
    public Vector cross(Vector vector);
    public double dot(Vector vector);
}
