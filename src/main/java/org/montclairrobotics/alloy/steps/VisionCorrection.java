package org.montclairrobotics.alloy.steps;

import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import org.montclairrobotics.alloy.components.Component;
import org.montclairrobotics.alloy.components.Step;
import org.montclairrobotics.alloy.drive.DTInput;
import org.montclairrobotics.alloy.utils.ErrorCorrection;
import org.montclairrobotics.alloy.utils.Input;
import org.montclairrobotics.alloy.utils.PID;
import org.montclairrobotics.alloy.utils.Toggleable;

/**
 * A generic class for correcting angle based on a vision target
 *
 * This class takes in an input and, when enabled, will apply a 
 * turn to the drivetrain so that it is in line with vision target
 * at a specified coordinate.
 *
 * This is also a toggleable so it should be attached to a button to allow
 * it to be enabled and disabled
 */
public class VisionCorrection extends Toggleable implements Step<DTInput> {
    /** Whether or not the correction is active */
    private boolean enabled;

    /** The PID controller used to correct to the specified target */
    private ErrorCorrection<Double> correction;
    /** An input (usually from the network tables) */
    private Input<Double> visionIn;

    private double target;

    /**
     * Create a new Vision correction specifying the target
     */
    public VisionCorrection(Input<Double> visionIn, PID correction, double target){
        this(visionIn, correction);
        setTarget(target);
    }

    public VisionCorrection(Input<Double> visionIn, PID correction){
        this.correction = correction;
        this.visionIn = visionIn;
        correction.setInput(visionIn);
    }

    /**
     * Set the target coordinate for the vision target
     *
     * This method is in place so that the target can be changed during runtime 
     * to allow for easier tuing and debugging 
     *
     * @param target the specified target that the robot will aim to correct to 
     */
    public void setTarget(double target){
        this.target = target;
        correction.setTarget(target);
    }



    @Override
    public DTInput getOutput(DTInput dtInput) {
        if(enabled && SmartDashboard.getBoolean("Hatch Detected",false)){
            return dtInput.addAngle(correction.getCorrection());
        }
        debug();
        return dtInput;
    }

    @Override
    public void disable() {
        enabled = false;
    }

    @Override
    public void enableAction() {
        // Do nothing on enable
    }

    @Override
    public void disableAction() {
        // Do nothing on disable
    }

    @Override
    public void enable() {
        enabled = true;
    }

    /**
     * Debugs out all relevant information about the correction. 
     */
    public void debug(){
        Component.debugger.test("Current Vision Value", visionIn.get());
        Component.debugger.test("Target Vision Value", target);
        Component.debugger.test("Vision Turn Correction", enabled ? correction.getCorrection() : 0);
        Component.debugger.driverInfo("Vision Correction Enabled", enabled);

    }
}