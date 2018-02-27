package org.montclairrobotics.alloy.update;

import java.util.ArrayList;

/**
 * Created by MHS Robotics on 12/5/2017.
 *
 * @author Garrett Burroughs
 * @since 0.1
 */
public class Updater {

    static ArrayList<Updatable> updatables;

    public static void add(Updatable updatable){
        updatables.add(updatable);
    }



    public static void update(){
        for(Updatable updatable : updatables){
            updatable.update();
        }
    }
}
