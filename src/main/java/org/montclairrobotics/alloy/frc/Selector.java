package org.montclairrobotics.alloy.frc;

import edu.wpi.first.wpilibj.smartdashboard.SendableChooser;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

import java.util.ArrayList;

/**
 * Created by MHS Robotics on 10/6/2018.
 *
 * @author Garrett Burroughs
 * @since
 */
public class Selector<E>  {
    private static ArrayList<Selector> selectors;
    private SendableChooser<E> selector;
    private String name;
    
    public Selector(String name, SendableChooser selector){
        this.selector = selector;
        this.name = name;
        selectors.add(this);
    }
    
    public Selector addOption(String name, E option){
        selector.addObject(name, option);
        return this;
    }
    
    public static ArrayList<Selector> getSelectors(){
        return selectors;
    }
    
    public void send(){
        SmartDashboard.putData(name, selector);
    }
}
