package org.montclairrobotics.alloy.ftc;

import org.firstinspires.ftc.robotcore.external.Telemetry;
import org.montclairrobotics.alloy.core.Debug;
import org.montclairrobotics.alloy.core.RobotCore;

/**
 * Created by MHS Robotics on 11/14/2017.
 *
 * The FTCDebug class is a basic wrapper around the
 * ftc telemetry framework and allows telemetry to be
 * used in any class as well as making debugging information
 * easier
 *
 * @author Garrett Burroughs
 * @since 0.1
 */
public class FTCDebug implements Debug {

    public static Telemetry telemetry;


    public FTCDebug() {
        try {
            this.telemetry = RobotCore.getTelemetry();
        }catch (NullPointerException e){
            e.printStackTrace();
        }

    }
    @Override
    public void log(String key, Object value) {
        telemetry.addData(key, value);
    }

    @Override
    public void msg(Object value) {
        telemetry.addData("Debug", value);
    }
}
