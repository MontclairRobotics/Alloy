package org.montclairrobotics.alloy.auto;

import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.util.ElapsedTime;
import org.montclairrobotics.alloy.core.RobotCore;

/**
 * Created by MHS Robotics on 12/5/2017.
 *
 * @author Garrett Burroughs
 */
public abstract class AlloyAutonomous extends OpMode{
    /**
     * Keeps track of when the auto mode is running (True after started and before finished)
     */
    boolean running;

    /**
     * The actual auto mode that should be instantiated in setup
     */
    public StateMachine auto;

    /**
     * A timer to keep track of time in the autoMode
     */
    public ElapsedTime timer;


    /**
     * This is where the user should define all their code
     * and where "Auto", should be instantiated
     */
    public abstract void setup();

    /**
     * Runs when the play button is pressed
     * Start will set up everything that the auto mode needs to run
     */
    @Override
    public void start(){
        auto.start(); // run the start method of the state machine
        running = true; // the state machine has started running
        timer.reset(); // reset the timer
    }

    /**
     * Init is called when the INIT button is pressed on the drivers station.
     * The init method takes care of setting up global robot variables and running the user setup method
     */
    @Override
    public void init() {
        new RobotCore(telemetry, hardwareMap, gamepad1, gamepad2);
        setup();

    }

    /**
     * the Loop method takes care of running the state machine
     */
    @Override
    public void loop() {
        if(running){
            auto.run(); // Run the state machine, which will in turn run the states
        }
        if(auto.isDone()){ // check if the state machine has finished (Last state achieved)
            running = false; // stop the state machine
            auto.stop(); // Finally stop the state machine
        }
    }
}
