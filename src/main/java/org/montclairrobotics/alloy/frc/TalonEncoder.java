package org.montclairrobotics.alloy.frc;

import com.ctre.phoenix.motorcontrol.can.WPI_TalonSRX;
import org.montclairrobotics.alloy.core.Encoder;

public class TalonEncoder extends Encoder {
    WPI_TalonSRX talonSRX;

    public TalonEncoder(WPI_TalonSRX talonSRX, double distancePerTick, double maxSpeed ) {
        super(distancePerTick, maxSpeed);
        this.talonSRX = talonSRX;
    }

    @Override
    public int getRawTicks() {
        return talonSRX.getSensorCollection().getQuadraturePosition();
    }

    public WPI_TalonSRX getTalonSRX() {
        return talonSRX;
    }
}
