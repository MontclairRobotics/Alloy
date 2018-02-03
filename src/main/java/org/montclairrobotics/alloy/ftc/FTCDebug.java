package org.montclairrobotics.alloy.ftc;

import org.firstinspires.ftc.robotcore.external.Telemetry;
import org.montclairrobotics.alloy.core.Debug;
import org.montclairrobotics.alloy.core.RobotCore;

/**
 * Created by Garrett on 11/14/2017.
 */
public class FTCDebug implements Debug {

    public static Telemetry telemetry;

    public FTCDebug(){
        this.telemetry = RobotCore.getTelemetry();
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
