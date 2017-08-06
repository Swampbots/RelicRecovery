package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;

/**
 * Created by kawaiiPlat on 7/29/2017.
 */

@TeleOp(name = "Beetle Drive", group = "TeleOp")
public class BeetleBotDriverControl extends OpMode {

    // Hardware map initialization.
    BeetleBotHardware hardware = new BeetleBotHardware();

    // Speed modifer variables
    double driverSpeedMod               = NORMAL;
    double utilitySpeedMod              = NORMAL;

    public static final double SLOW     = 0.4;
    public static final double NORMAL   = 0.7;
    public static final double FAST     = 1.0;


    // Runs once when the driver presses init.
    @Override
    public void init() {

        telemetry.addLine("Initializing hardware... do not press play!");
        telemetry.update();

        hardware.init(hardwareMap);

        telemetry.addLine("Hardware initialized.");
        telemetry.update();
    }


    // Runs repeatedly after the driver presses init.
    @Override
    public void init_loop() {}


    // Runs once when the driver presses start.
    @Override
    public void start() {}


    // Runs repeatedly after the driver presses start.
    @Override
    public void loop() {

        // Handle speed modifiers
        if(gamepad1.left_bumper)        driverSpeedMod = FAST;
        else if(gamepad1.right_bumper)  driverSpeedMod = SLOW;
        else                            driverSpeedMod = NORMAL;

        if(gamepad2.left_bumper)        utilitySpeedMod = FAST;
        else if(gamepad2.right_bumper)  utilitySpeedMod = SLOW;
        else                            utilitySpeedMod = NORMAL;

        // Handle drive motors
        hardware.leftDrive1.setPower(gamepad1.left_stick_y * driverSpeedMod);

        hardware.rightDrive1.setPower(gamepad1.right_stick_y * utilitySpeedMod);

        // Update telemetry
        telemetry.addData("Runtime", getRuntime());
        telemetry.addLine();
        telemetry.addData("Driver Speed Mod",  driverSpeedMod);
        telemetry.addData("Utility Speed Mod",  utilitySpeedMod);
        telemetry.addLine();
        telemetry.addData("Touch sensor", hardware.debugTouch.isPressed());
        telemetry.addLine();
        telemetry.addData("Test ODS", hardware.testODS.getLightDetected());

    }
}
