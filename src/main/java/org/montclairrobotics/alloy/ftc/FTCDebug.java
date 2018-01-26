package org.montclairrobotics.alloy.ftc;

import org.firstinspires.ftc.robotcore.external.Telemetry;
import org.montclairrobotics.alloy.core.Debug;

/**
 * Created by Garrett on 11/14/2017.
 */
public class FTCDebug implements Debug {

    public static Telemetry telemetry;

    public static init(){
        this.telemetry = RobotCore.getTelemetry();
    }

    @Override
    public static void log(String key, Object value) {
        telemetry.addData(key, object);
    }

    @Override
    public static void msg(Object value) {
        telemetry.addData("Debug", value);
    }
}
