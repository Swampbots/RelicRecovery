package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;

/**
 * Created by captainFlareon 6/10/2017.
 */

@TeleOp(name = "Driver Control", group = "TeleOp")
public class TileRunnerTeleOp extends OpMode {
    // Hardware map initialization.
    private TileRunner hardware = new TileRunner();

    @Override
    public void init() {
        telemetry.addLine("Initializing hardware... do not press play!");
        telemetry.update();

        hardware.init(hardwareMap);

        telemetry.addLine("Hardware initialized.");
        telemetry.update();
    }

    @Override
    public void init_loop() {}

    @Override
    public void start() {}


    @Override
    public void loop() {
        // Handle speed modifiers
        if(gamepad1.left_bumper)        hardware.driverSpeedMod = hardware.FAST;
        else if(gamepad1.right_bumper)  hardware.driverSpeedMod = hardware.SLOW;
        else                            hardware.driverSpeedMod = hardware.NORMAL;

        if(gamepad2.left_bumper)        hardware.utilitySpeedMod = hardware.FAST;
        else if(gamepad2.right_bumper)  hardware.utilitySpeedMod = hardware.SLOW;
        else                            hardware.utilitySpeedMod = hardware.NORMAL;

        hardware.lifter.setPower(gamepad2.left_stick_y * hardware.utilitySpeedMod);

        hardware.linearDrive(gamepad1.left_stick_y, gamepad1.right_stick_y);


        // Handle servos
        if(gamepad2.x) {
            hardware.wheelIntake.setPosition(0.2);
            hardware.wheelIntake2.setPosition(1.0);
        }
        else if(gamepad2.y) {
            hardware.wheelIntake.setPosition(1.0);
            hardware.wheelIntake2.setPosition(0.2);
        }
        else;
            //hardware.particleServo.setPosition(0.4);

        // Update telemetry
        telemetry.addData("Driver Speed Mod",  hardware.driverSpeedMod);
        telemetry.addData("Utility Speed Mod",  hardware.utilitySpeedMod);
        telemetry.update();
    }
}