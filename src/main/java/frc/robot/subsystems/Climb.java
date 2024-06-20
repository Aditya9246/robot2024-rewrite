// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.subsystems;

import com.revrobotics.CANSparkMax;
import edu.wpi.first.wpilibj2.command.Command;
import edu.wpi.first.wpilibj2.command.SubsystemBase;
import frc.robot.Constants;
import frc.robot.SushiLib.Motor.MotorHelper;
import frc.robot.SushiLib.Sensors.BeamBreak.BeamBreak;
import frc.robot.SushiLib.SmartDashboard.PIDTuning;
import frc.robot.SushiLib.SmartDashboard.TunableNumber;

public class Climb extends SubsystemBase {
    private final CANSparkMax leftMotor;
    private final CANSparkMax rightMotor;

    private final TunableNumber setpoint;

    private final PIDTuning pid;

    private static Climb instance;

    public static Climb getInstance() {
        if (instance == null) {
            instance = new Climb();
        }
        return instance;
    }

    private Climb() {
        leftMotor = Constants.Climb.CLIMB_LEFT_MOTOR.createSparkMax();
        rightMotor = Constants.Climb.CLIMB_RIGHT_MOTOR.createSparkMax();

        setpoint = new TunableNumber("Climb Setpoint", 0, Constants.TUNING_MODE);
        pid = Constants.Climb.CLIMB_RIGHT_MOTOR.genPIDTuning("Climb Right Motor", Constants.TUNING_MODE);

        MotorHelper.setConversionFactor(leftMotor, Constants.Climb.GEAR_RATIO);
        MotorHelper.setConversionFactor(rightMotor, Constants.Climb.GEAR_RATIO);

    }

    @Override
    public void periodic() {}

    public Command climb(int input) {
        return runOnce(() -> {
            leftMotor.set(input);
            rightMotor.set(input);
        });
    }
}
