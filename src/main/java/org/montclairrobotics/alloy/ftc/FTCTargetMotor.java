package org.montclairrobotics.alloy.ftc;

import com.qualcomm.robotcore.hardware.DcMotor;
import org.montclairrobotics.alloy.core.Debug;
import org.montclairrobotics.alloy.core.Encoder;
import org.montclairrobotics.alloy.core.TargetMotor;
import org.montclairrobotics.alloy.update.Update;
import org.montclairrobotics.alloy.utils.Input;
import org.montclairrobotics.alloy.utils.PID;

import java.util.ArrayList;


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
public class FTCTargetMotor extends FTCMotor implements TargetMotor {

    private double targetPower;

    public FTCTargetMotor(String motorConfiguration) {
        super(motorConfiguration);
        motor.setMode(DcMotor.RunMode.RUN_TO_POSITION);
        addDebug(new Debug(motorConfiguration + " Motor Power: ", (Input<Double>) () -> power));
        addDebug(new Debug(motorConfiguration + " Motor Position: ", (Input<Integer>) () -> getPosition()));
        addDebug(new Debug(motorConfiguration + " Target Motor Power: ", (Input<Double>) () -> targetPower));
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
            pid.setTarget((double)position);
        }
    }

    /**
     * Gets the motors position
     *
     * @return the position that the motor is at (in encoder ticks)
     */
    @Override
    public int getPosition() {
        return motor.getCurrentPosition();
    }

    /**
     * Sets the motor Power
     *
     * @param power the power that the motor will be set to (0-1 inclusive )
     */
    @Override
    public void setTargetPower(double power) {
        motor.setMode(DcMotor.RunMode.RUN_TO_POSITION);
        this.targetPower = power;
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
        if(status.booleanValue()) { // Check if enabled
            if(motor.getMode() == DcMotor.RunMode.RUN_TO_POSITION){
                if (runmode == Mode.CUSTOM) {
                    setTargetPower(pid.get()); // If running using custom PID mode, set power to PID output
                }else{
                    motor.setPower(targetPower); // If running in default target mode, set the target power
                }
            }else{
                motor.setPower(power); // If running by power, set the power
            }
        }else{
            motor.setPower(0); // If disabled, set power to 0
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

    /**
     * Allows for the creation of an encoder object that is aware of the amount of ticks the motor has gone
     *
     * @return a new encoder object that will return the amount of encoder ticks the motor is at
     */
    public Encoder getEncoder(){
        return new Encoder() {
            @Override
            public int getTicks() {
                return getPosition();
            }
        };
    }
}
