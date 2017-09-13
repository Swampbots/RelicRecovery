package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;

/**
 * Created by kawaiiPlat on 8/26/2017.
 */

@TeleOp(name = "Cap Ball Lift", group = "TeleOp")
public class CapLift extends OpMode {

    // Hardware map initialization.
    CapBallHardware hardware = new CapBallHardware();

    // Speed modifer variables
    double utilitySpeedMod              = NORMAL;

    public static final double SLOW     = 0.4;
    public static final double NORMAL   = 0.7;
    public static final double FAST     = 1.0;

    public void init() {
        telemetry.addLine("Initializing hardware... do not press play!");
        telemetry.update();

        hardware.init(hardwareMap);

        telemetry.addLine("Hardware initialized.");
        telemetry.update();
    }

    public void init_loop() {}

    public void start() {}

    public void loop() {

        if(gamepad2.left_bumper)
            utilitySpeedMod = FAST;
        else if(gamepad2.right_bumper)
            utilitySpeedMod = SLOW;
        else utilitySpeedMod = NORMAL;

        hardware.capLifter.setPower(gamepad2.right_stick_y * utilitySpeedMod);

        telemetry.addData("Cap Lifter power", hardware.capLifter.getPower());
        telemetry.update();
    }
}
