package org.montclairrobotics.Alloy.Vector;

/**
 * Created by MHS Robotics on 11/13/2017.
 *
 * @author Garrett Burroughs
 *
 * The angle class allows for easy managment of angles aswell as easy conversion between degree and radian
 * angle measure
 */
public class Angle {


    /**
     * AngleMeasure, is used for keeping track of what type of angle measure the angle is being created with
     */
    enum AngleMeasure{
        /**
         * Radian Angle Measure
         */
        RADIAN,

        /**
         * Degree Angle Measure
         */
        DEGREE
    }

    private double degrees;

    public final Angle ZERO = new Angle(AngleMeasure.DEGREE, 0);

    public Angle(AngleMeasure angleMeasure, double angle){
        if(angleMeasure == AngleMeasure.DEGREE){
            degrees = angle;
        }else{
            degrees = angle * (Math.PI / 180);
        }
    }

    public Angle(double degrees){
        this(AngleMeasure.DEGREE, degrees);
    }


    public double getDegrees(){
        return degrees;
    }

    public double getRadians(){
        return degrees * (Math.PI / 180);
    }

    public void setDegrees(double degrees){
        this.degrees = degrees;
    }

    public void setRadians(double radians){
        this.degrees = radians * (180/Math.PI);
    }

    public double sin(){
        return Math.sin(getRadians());
    }

    public double cos(){
        return Math.cos(getRadians());
    }

    public double tan(){
        return Math.tan(getRadians());
    }

}
