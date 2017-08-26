package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.DcMotorSimple;
import com.qualcomm.robotcore.hardware.HardwareMap;

/**
 * Created by kawaiiPlat on 8/26/2017.
 */

public class CapBallHardware {

    public DcMotor capLifter = null;

    public HardwareMap hwMap = null;


    public CapBallHardware() {

    }

    public void init(HardwareMap ahwMap) {
        hwMap = ahwMap;

        capLifter = hwMap.dcMotor.get("balllifter");

        capLifter.setDirection(DcMotorSimple.Direction.FORWARD);

        capLifter.setPower(0.0);

        capLifter.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
    }
}
