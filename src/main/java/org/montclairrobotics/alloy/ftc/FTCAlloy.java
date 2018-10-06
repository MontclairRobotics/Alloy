package org.montclairrobotics.alloy.ftc;

import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import org.montclairrobotics.alloy.components.Component;
import org.montclairrobotics.alloy.core.Alloy;
import org.montclairrobotics.alloy.core.RobotCore;
import org.montclairrobotics.alloy.drive.DriveTrain;
import org.montclairrobotics.alloy.update.Updater;
import org.montclairrobotics.alloy.utils.Initializeable;

import java.util.ArrayList;

/**
 * The main class that takes care of setting up the environment, and running all of the main methods
 *
 * <p>The alloy class is an extension of the FTC OpMode class, and takes care of preliminary setup
 * of: <br>
 * - Setting up the global FTC variables in RobotCore <br>
 * - Setting up the global Debugger <br>
 * - Initializing any "initializeables" in the project <br>
 * - Running the users defined methods <br>
 *
 * @author Garrett Burroughs
 * @version 0.1
 * @since 0.1
 */
public abstract class FTCAlloy extends OpMode implements Alloy {
    public static DriveTrain driveTrain;
    
    @Override
    public void init() {
        // Set Up the core robot components, This allows them to be accessed throughout the project
        new RobotCore(telemetry, hardwareMap, gamepad1, gamepad2);
        Component.debugger = new FTCDebugger();
        
        for (Initializeable i : Initializeable.getInitObjects()) {
            i.init();
        }
        
        robotSetup();
        initialization();
    }
    
    @Override
    public void loop() {
        Updater.update();
        periodic();
    }
    
    
    @Override
    public void setDriveTrain(DriveTrain driveTrain) {
        FTCAlloy.driveTrain = driveTrain;
    }
    
    @Override
    public DriveTrain getDriveTrain() {
        return driveTrain;
    }
}
