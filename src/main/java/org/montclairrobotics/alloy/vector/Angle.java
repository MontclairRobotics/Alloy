package org.montclairrobotics.alloy.vector;

/**
 * Created by MHS Robotics on 11/13/2017.
 *
 *
 * The angle class allows for easy managment of angles aswell as easy conversion between degree and radian
 * angle measure
 * The class keeps track of the angle in degrees but can easily be converted to radians
 *
 * @author Garrett Burroughs
 * @version 0.1
 * @since 0.1
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

    /**
     * Keeps Track of the degrees that the angle is
     */
    private double degrees;

    // Zero Angle
    public final Angle ZERO = new Angle(AngleMeasure.DEGREE, 0);


    /**
     * Creating a new angle with an angle measure unit and the measure of the angle itself
     *
     * @param angleMeasure What unit to be used for the angle measure
     * @param angle        The angle
     */
    public Angle(AngleMeasure angleMeasure, double angle){
        // If angle is in degrees set degrees to the angle, no conversion is needed because they are the same unit
        if(angleMeasure == AngleMeasure.DEGREE){
            degrees = angle;
        }else{
            // Convert the radian measure to degrees (radians to degrees: (angle * PI)/180)
            degrees = angle * (Math.PI / 180);
        }
    }

    /**
     * Creating an angle with degrees
     *
     * @param degrees Degree angle measure
     */
    public Angle(double degrees){
        this(AngleMeasure.DEGREE, degrees);
    }




    /**
     *  @return the degree angle measure
     */
    public double getDegrees(){
        return degrees;
    }

    /**
     *  @return the radian angle measure
     */
    public double getRadians(){
        return degrees * (Math.PI / 180);
    }

    /**
     * @param degrees the degree angle measure
     */
    public void setDegrees(double degrees){
        this.degrees = degrees;
    }

    /**
     * @param radians the degree angle measure
     */
    public void setRadians(double radians){
        this.degrees = radians * (180/Math.PI);
    }

    /**
     * @return the sin of the angle
     */
    public double sin(){
        return Math.sin(getRadians());
    }

    /**
     * @return the sin of the angle
     */
    public double cos(){
        return Math.cos(getRadians());
    }

    /**
     * @return the sin of the angle
     */
    public double tan(){
        return Math.tan(getRadians());
    }

}
