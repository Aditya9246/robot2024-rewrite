// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.subsystems;

import com.ctre.phoenix6.hardware.TalonFX;
import com.revrobotics.CANSparkMax;
import edu.wpi.first.math.controller.ArmFeedforward;
import edu.wpi.first.math.controller.SimpleMotorFeedforward;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import edu.wpi.first.wpilibj2.command.Command;
import edu.wpi.first.wpilibj2.command.SubsystemBase;
import frc.robot.Constants;
import frc.robot.SushiLib.Sensors.BeamBreak.BeamBreak;
import frc.robot.SushiLib.SmartDashboard.PIDTuning;
import frc.robot.SushiLib.SmartDashboard.TunableNumber;
import com.ctre.phoenix6.controls.CoastOut;
import com.ctre.phoenix6.controls.Follower;

public class Launcher extends SubsystemBase {
    private final TalonFX flywheelLeftMotor;
    private final TalonFX flywheelRightMotor;
    private final CANSparkMax indexerMotor;
    private final BeamBreak beamBreak;
    private final TunableNumber flywheelSetPoint;
    private final TunableNumber indexerSetpoint;

    private static Launcher instance;

    private final PIDTuning launcherPID;
    private final SimpleMotorFeedforward launcherFF;

    private final PIDTuning indexerPID;
    private final SimpleMotorFeedforward indexerFF;

    public static Launcher getInstance() {
        if (instance == null) {
            instance = new Launcher();
        }
        return instance;
    }

    private Launcher() {
        flywheelLeftMotor = Constants.Launcher.FLYWHEEL_LEFT_MOTOR.createTalon();
        flywheelRightMotor = Constants.Launcher.FLYWHEEL_RIGHT_MOTOR.createTalon();
        indexerMotor = Constants.Launcher.INDEXER_MOTOR.createSparkMax();
        beamBreak = Constants.Intake.BEAM_BREAK.createBeamBreak();

        flywheelSetPoint = new TunableNumber("Flywheels Setpoint", 0, false);
        indexerSetpoint = new TunableNumber("Indexer Setpoint", 0, false);

        launcherPID = Constants.Launcher.FLYWHEEL_RIGHT_MOTOR.genPIDTuning("Launcher Right Motor", Constants.TUNING_MODE);

        launcherFF = new SimpleMotorFeedforward(
            Constants.Launcher.S, 
            Constants.Launcher.G, 
            Constants.Launcher.V
        );

        indexerPID = Constants.Launcher.INDEXER_MOTOR.genPIDTuning("Indexer Right Motor", Constants.TUNING_MODE);

        indexerFF = new SimpleMotorFeedforward(
            Constants.Launcher.indexerS,
            Constants.Launcher.indexerG,
            Constants.Launcher.indexerV
        );

        flywheelLeftMotor.setControl(new Follower(flywheelRightMotor.getDeviceID(), false));
    }

    public Command runFlywheel() {
        return runOnce(() -> {
            flywheelLeftMotor.set(1.0);
            flywheelRightMotor.set(1.0);
        });
    }

    public Command stopFlywheel() {
        return runOnce(() -> {
            flywheelLeftMotor.set(0.0);
            flywheelRightMotor.set(0.0);
        }).unless(beamBreak.getBlockedSupplier());
    }

    public Command runIndexer(){
        return runOnce(() -> {
            indexerMotor.set(0.3);
        }).until(beamBreak.getBlockedSupplier()
        ).andThen(stopIndexer());
    }

    public Command stopIndexer(){
        return runOnce(() -> {
            indexerMotor.set(0.0);
        });
    }

    public boolean flywheelsVelocityAtSetpoint(){
        return Math.abs(flywheelRightMotor.get() - flywheelSetPoint.get()) < 10;
    }

    public boolean indexerVelocityAtSetpoint(){
        return Math.abs(indexerMotor.get() - indexerSetpoint.get()) < 10;
    }

    @Override
    public void periodic() {
        SmartDashboard.putNumber("Flywheel Speed", flywheelRightMotor.get());
        SmartDashboard.putNumber("Indexer Speed", indexerMotor.get());

        launcherPID.updatePID(flywheelRightMotor);

        // add code to meet setpoint
        // idk how to do this for a talon
    }
}
