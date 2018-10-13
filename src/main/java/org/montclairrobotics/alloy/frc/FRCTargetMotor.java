package org.montclairrobotics.alloy.frc;

import edu.wpi.first.wpilibj.SpeedController;
import org.montclairrobotics.alloy.core.Encoder;
import org.montclairrobotics.alloy.core.TargetMotor;
import org.montclairrobotics.alloy.ftc.FTCTargetMotor;
import org.montclairrobotics.alloy.update.Update;
import org.montclairrobotics.alloy.utils.ErrorCorrection;

/**
 * Created by MHS Robotics on 10/9/2018.
 *
 * @author Garrett Burroughs
 * @since
 */
public class FRCTargetMotor extends FRCMotor implements TargetMotor {
    
    private Encoder encoder;
    
    private ErrorCorrection<Double> powerCorrection;
    
    private ErrorCorrection<Double> positionCorrection;
    
    private Mode mode = Mode.POWER;
    
    private double targetPower;
    
    private double power;
    
    public FRCTargetMotor(SpeedController controller, Encoder encoder) {
        super(controller);
        this.encoder = encoder;
    }
    
    private enum Mode{
        POSITION,
        POWER
    }
    
    public FRCMotor runPower(){
        mode = Mode.POWER;
        return this;
    }
    
    public FRCMotor runPosition(){
        mode = Mode.POSITION;
        return this;
    }
    
    
    /**
     * Sets the motor Power
     *
     * @param power the power that the motor will be set to (0-1 inclusive )
     */
    @Override
    public void setTargetPower(double power) {
        runPosition();
        this.targetPower = power;
    }
    
    /**
     * Sets the motor Power
     *
     * @param power the power that the motor will be set to [-1, 1]
     */
    @Override
    public void setMotorPower(double power) {
        runPower();
        this.power = power;
    }
    
    /**
     * Gets the motor power
     *
     * @return the current motor power, a value between (0-1)
     */
    @Override
    public double getTargetPower() {
        return targetPower;
    }
    
    public double getPower(){
        return encoder.getScaledVelocity();
    }
    
    
    /**
     * Sets the motor position
     *
     * @param position the position the motor will be set to (in encoder ticks)
     */
    @Override
    public void setPosition(int position) {
        positionCorrection.setTarget(new Double(position));
    }
    
    /**
     * Gets the motors position
     *
     * @return the position that the motor is at (in encoder ticks)
     */
    @Override
    public int getPosition() {
        return encoder.getTicks();
    }
    
    @Override
    public void updateMotor(){
        if(status.isEnabled()){
            switch (mode){
                case POSITION:
                    controller.set(targetPower * positionCorrection.getCorrection());
                    break;
                case POWER:
                    controller.set(power + powerCorrection.getCorrection());
                    break;
            }
        }else {
            controller.set(0);
        }
    }
    
}
