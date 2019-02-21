package org.montclairrobotics.alloy.frc;

import edu.wpi.first.wpilibj.Compressor;
import org.montclairrobotics.alloy.components.Component;
import org.montclairrobotics.alloy.update.Update;

public class PressureRegulator extends Component {
    Compressor compressor;
    
    public PressureRegulator(Compressor c){
        this.compressor = c;
    }

    @Update
    public void regulatePressure() {
        if(!compressor.getPressureSwitchValue() && status.isEnabled()){
            compressor.setClosedLoopControl(true);
        }else{
            compressor.setClosedLoopControl(false);
        }
    }
}
