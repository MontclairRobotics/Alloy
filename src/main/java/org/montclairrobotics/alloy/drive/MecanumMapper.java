package org.montclairrobotics.alloy.drive;

import org.montclairrobotics.alloy.motor.MotorModule;
import org.montclairrobotics.alloy.utils.Utils;

/**
 * Created by MHS Robotics on 1/24/2019.
 *
 * @author Jack Hymowitz
 * @since
 */
public class MecanumMapper implements DTMapper{
    /**
     * map the input to the modules
     *
     * @param input
     * @param modules
     */
    @Override
    public void map(DTInput input, MotorModule... modules) {
        double x = input.getTranslation().getX();
        double y = input.getTranslation().getY();
        double turn = input.getRotation().getDegrees();
        double maxPower = 1;

        double moduleSpeeds[] = new double[modules.length];

        for(int i = 0; i < modules.length; i++) {
            double xSign = Utils.sign(modules[i].getOffset().getX());
            double ySign = Utils.sign(modules[i].getOffset().getY());
            double directionSign = Utils.sign(modules[i].getOffset().cross(modules[i].getDirection()));

            double calculatedSpeed = (y * xSign * -1 + x * ySign + turn) * directionSign;
            moduleSpeeds[i] = calculatedSpeed;

            if(calculatedSpeed > maxPower) {
                maxPower= calculatedSpeed;
            }
        }
        for(int i = 0; i < modules.length; i++) {
            modules[i].setPower(moduleSpeeds[i] / maxPower);
        }
    }
}