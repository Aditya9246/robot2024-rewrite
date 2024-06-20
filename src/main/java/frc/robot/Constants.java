// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot;

import frc.robot.SushiLib.Control.PIDConfig;
import frc.robot.SushiLib.Motor.MotorConfig;
import frc.robot.SushiLib.Motor.MotorConfig.Mode;
import frc.robot.SushiLib.Sensors.BeamBreak.BeamBreakConfig;

public final class Constants {
    public static final boolean TUNING_MODE = false;

    public static class Ports {
        public static final String CANIVORE_NAME = ""; // TODO: Set later
    }

    public static class Intake {
        public static MotorConfig OUTER_MOTOR = new MotorConfig(-1); // TODO: set later
        public static MotorConfig INNER_MOTOR = new MotorConfig(-1); // TODO: set later
        public static BeamBreakConfig BEAM_BREAK = new BeamBreakConfig(-1); // TODO: set later
    }

    public static class Wrist {
        public final static MotorConfig LEFT_MOTOR = new MotorConfig(
            -1,
            20,
            false,
            new PIDConfig(0.0, 0.0, 0.0, 0.0),
            Mode.BRAKE
        );

        public final static MotorConfig RIGHT_MOTOR = new MotorConfig(
            -1,
            20,
            false,
            new PIDConfig(0.0, 0.0, 0.0, 0.0),
            Mode.BRAKE
        );

        public static final double G = 0.0;
        public static final double S = 0.0;
        public static final double V = 0.0;

        public static final double GEAR_RATIO = 1.0;
    }

    public static class Launcher{
        public final static MotorConfig FLYWHEEL_LEFT_MOTOR = new MotorConfig(
            -1,
            40,
            false,
            new PIDConfig(0.0, 0.0, 0.0, 0.0),
            Mode.COAST
        );

        public final static MotorConfig FLYWHEEL_RIGHT_MOTOR = new MotorConfig(
            -1,
            40,
            false,
            new PIDConfig(0.0, 0.0, 0.0, 0.0),
            Mode.COAST
        );

        public final static MotorConfig INDEXER_MOTOR = new MotorConfig(
            -1,
            35,
            false,
            new PIDConfig(0.0, 0.0, 0.0, 0.0),
            Mode.COAST
        );

        public static final double G = 0.0;
        public static final double S = 0.0;
        public static final double V = 0.0;

        public static final double indexerG = 0.0;
        public static final double indexerS = 0.0;
        public static final double indexerV = 0.0;
    }

    public static class Climb{
        public final static MotorConfig CLIMB_LEFT_MOTOR = new MotorConfig(
            -1,
            60,
            false,
            new PIDConfig(0.0, 0.0, 0.0, 0.0),
            Mode.COAST
        );

        public final static MotorConfig CLIMB_RIGHT_MOTOR = new MotorConfig(
            -1,
            60,
            false,
            new PIDConfig(0.0, 0.0, 0.0, 0.0),
            Mode.COAST
        );

        public static final double GEAR_RATIO = 1.0;
    }
}
