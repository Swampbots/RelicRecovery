package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;

/**
 * Created by kawaiiPlat on 9/16/2017.
 */

@TeleOp(name = "TileRunnerMR Test Op", group = "TeleOp")
public class TileRunnerTestOp extends OpMode {

    private TileRunnerMR hardware = new TileRunnerMR();

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

        // Handle drive motors
        hardware.rampDrive(gamepad1.left_stick_y, gamepad1.right_stick_y);

        // Handle utility motors
        hardware.lifter.setPower(gamepad2.left_stick_y * hardware.utilitySpeedMod);

        // Handle servos
        if(gamepad2.x)
            hardware.wheelIntake.setPosition(0.2);
        else if(gamepad2.y)
            hardware.wheelIntake.setPosition(1.0);
        else;

        // Update telemetry
        telemetry.addData("Driver Speed Mod",  hardware.driverSpeedMod);
        telemetry.addData("Utility Speed Mod",  hardware.utilitySpeedMod);
        telemetry.update();
    }
}
