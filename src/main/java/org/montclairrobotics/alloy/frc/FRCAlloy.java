package org.montclairrobotics.alloy.frc;

import edu.wpi.first.wpilibj.IterativeRobot;
import edu.wpi.first.wpilibj.smartdashboard.SendableChooser;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import org.montclairrobotics.alloy.auto.StateMachine;
import org.montclairrobotics.alloy.components.Component;
import org.montclairrobotics.alloy.core.Alloy;
import org.montclairrobotics.alloy.core.RobotCore;
import org.montclairrobotics.alloy.drive.DriveTrain;
import org.montclairrobotics.alloy.ftc.FTCDebugger;
import org.montclairrobotics.alloy.utils.Initializeable;

import java.util.ArrayList;

/**
 * Created by MHS Robotics on 10/6/2018.
 *
 * @author Garrett Burroughs
 * @since
 */
public abstract class FRCAlloy extends IterativeRobot implements Alloy{
    public static DriveTrain driveTrain;
    private static Selector<StateMachine> autoSelector;
    
    /**
     * Robot-wide initialization code should go here.
     * <p>
     * <p>Users should override this method for default Robot-wide initialization which will be called
     * when the robot is first powered on. It will be called exactly one time.
     * <p>
     * <p>Warning: the Driver Station "Robot Code" light and FMS "Robot Ready" indicators will be off
     * until RobotInit() exits. Code in RobotInit() that waits for enable will cause the robot to
     * never indicate that the code is ready, causing the robot to be bypassed in a match.
     */
    @Override
    public void robotInit() {
        Component.debugger = new FTCDebugger();
        autoSelector = new Selector<>("Auto Chooser", new SendableChooser());
        for (Initializeable i : Initializeable.getInitObjects()) {
            i.init();
        }
    
        robotSetup();
        initialization();
    }
    
    /**
     * Periodic code for teleop mode should go here.
     */
    @Override
    public void teleopPeriodic() {
        super.teleopPeriodic();
    }
    
    /**
     * Initialization code for autonomous mode should go here.
     * <p>
     * <p>Users should override this method for initialization code which will be called each time the
     * robot enters autonomous mode.
     */
    @Override
    public void autonomousInit() {
        super.autonomousInit();
    }
    
    /**
     * Periodic code for autonomous mode should go here.
     */
    @Override
    public void autonomousPeriodic() {
        super.autonomousPeriodic();
    }
    
    /**
     * Initialization code for disabled mode should go here.
     * <p>
     * <p>Users should override this method for initialization code which will be called each time the
     * robot enters disabled mode.
     */
    @Override
    public void disabledInit() {
        for (Selector selector : Selector.getSelectors()) {
            selector.send();
        }
    }
    
    /**
     * Periodic code for disabled mode should go here.
     */
    @Override
    public void disabledPeriodic() {
        for (Selector selector : Selector.getSelectors()) {
            selector.send();
        }
    }
    
    public void addAutoMode(StateMachine autoMode){
    
    }
}
