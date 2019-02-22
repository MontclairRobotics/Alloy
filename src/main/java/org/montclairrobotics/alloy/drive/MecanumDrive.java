package org.montclairrobotics.alloy.drive;

import org.montclairrobotics.alloy.motor.Mapper;
import org.montclairrobotics.alloy.motor.MotorModule;
import org.montclairrobotics.alloy.utils.Input;

public class MecanumDrive extends DriveTrain {
    public MecanumDrive(Input<DTInput> input, MotorModule... modules) {
        super(input, new MecanumMapper(), modules);
    }

    public MecanumDrive(MotorModule ... modules){
        this(null, modules);
    }
}
