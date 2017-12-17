package org.montclairrobotics.alloy.ftc;

import org.firstinspires.ftc.robotcore.external.Telemetry;
import org.montclairrobotics.alloy.core.Debug;

/**
 * Created by Garrett on 11/14/2017.
 */
public class FTCDebug implements Debug {

    Telemetry telemetry;

    public FTCDebug(Telemetry telemetry){
        this.telemetry = telemetry;
    }

    @Override
    public void log(String key, Object value) {

    }

    @Override
    public void msg(Object value) {

    }
}
