package org.montclairrobotics.alloy.frc;

import com.kauailabs.navx.frc.AHRS;
import org.montclairrobotics.alloy.components.InputComponent;
import org.montclairrobotics.alloy.core.Gyro;

public class NavxGyro extends InputComponent<Double> implements Gyro {

    private AHRS navx;

    public NavxGyro(AHRS navx){
        this.navx = navx;
    }

    @Override
    public double getRoll() {
        return navx.getRoll();
    }

    @Override
    public double getYaw() {
        return navx.getYaw();
    }

    @Override
    public double getPitch() {
        return navx.getPitch();
    }

    @Override
    public Double get() {
        if(status.isEnabled()){
            return getYaw();
        }else{
            return 0D;
        }
    }

    public AHRS getNavx(){
        return navx;
    }

    @Override
    public void reset() {
        navx.reset();
    }
}
