package org.montclairrobotics.alloy.ftc;

import com.qualcomm.robotcore.hardware.DcMotor;
import org.montclairrobotics.alloy.core.TargetMotor;
import org.montclairrobotics.alloy.update.Update;
import org.montclairrobotics.alloy.utils.PID;


/**
 * Created by MHS Robotics on 2/24/2018.
 *
 * A target motor is a motor that can use encoders to be set to a certain position
 * Since FTC motors have their own PIDs that they are controlled with by default,
 * the user has the ability to override this and use custom PIDs if need be.
 * By default, the user does not have to worry about any of this.
 * Do not attempt to change the PID if you do not know what you are doing
 * You can read more about PIDs here <link>https://en.wikipedia.org/wiki/PID_controller</link>
 *
 * @author Garrett Burroughs
 * @since 0.1
 */
public class FTCTargetMotor extends FTCMotorBase implements TargetMotor {
    public FTCTargetMotor(String motorConfiguration) {
        super(motorConfiguration);
        motor.setMode(DcMotor.RunMode.RUN_TO_POSITION);
    }
    
    enum Mode{
        /**
         * Defualt target motor runmode
         * The default mode will run using the build in Motor PIDs to control the motor
         */
        DEFAULT,

        /**
         * Custom target motor runmode
         * The custom mode will control the motor with a user defined PID controller
         */
        CUSTOM
    }

    /**
     * PID being used to control the motor in custom mode
     */
    private PID pid;
    
    /**
     * Current target motor runmode
     * NOTE: not equal to the DCMotor runmode
     */
    private Mode runmode = Mode.DEFAULT;
    
    /**
     * Sets the motor position
     *
     * @param position the position the motor will be set to (in encoder ticks)
     */
    @Override
    public void setPosition(int position) {
        if(runmode == Mode.DEFAULT){
            motor.setTargetPosition(position);
        }else{
        }
    }

    /**
     * Gets the motors position
     *
     * @return the position that the motor is at (in encoder ticks)
     */
    @Override
    public double getPosition() {
        return motor.getCurrentPosition();
    }

    /**
     * Sets the motor Power
     *
     * @param power the power that the motor will be set to (0-1 inclusive )
     */
    @Override
    public void setTargetPower(double power) {
        motor.setPower(power);
    }

    /**
     * Gets the motor power
     *
     * @return the current motor power, a value between (0-1)
     */
    @Override
    public double getTargetPower() {
        return motor.getPower();
    }

    /**
     *  Set the motor to run using a custom PID
     * @param pid the PID that the motor will be controlled with
     */
    public void setPID(PID pid){
        this.pid = pid;
        runmode = runmode.CUSTOM;
    }

    /**
     * Stop using the custom PID and return to using the default mode
     */
    public void disablePID(){
        this.pid = null;
        runmode = Mode.DEFAULT;
    }

    /**
     * If the mode is custom, then the update method will set the motor power using the custom PID
     */
    @Update
    public void update() {
        if(runmode == Mode.CUSTOM){

        }
    }

    /**
     * @return the PID being used to control the motor
     */
    public PID getPid() {
        return pid;
    }

    /**
     * @return the motor being controlled
     */
    public DcMotor getMotor() {
        return motor;
    }

    /**
     * NOTE: this is not the same as the DCMotor runmode
     * @return the current target motor runmode
     */
    public Mode getRunmode() {
        return runmode;
    }
}
