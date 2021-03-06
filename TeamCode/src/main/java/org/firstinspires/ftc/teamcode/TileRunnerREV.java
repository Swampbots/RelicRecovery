package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.hardware.ColorSensor;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.DcMotorSimple;
import com.qualcomm.robotcore.hardware.HardwareMap;

/**
 * Created by kawaiiPlat on 9/30/2017.
 */

public class TileRunnerREV {

    // Speed control variables
    public final double SLOW = 0.25;
    public final double NORMAL = 0.4;
    public final double FAST = 1.0;

    public double driverSpeedMod = NORMAL;
    public double utilitySpeedMod = NORMAL;

    HardwareMap hwMap = null;

    // Motor objects
    public DcMotor leftDrive1   = null;
    public DcMotor leftDrive2   = null;

    public DcMotor rightDrive1  = null;
    public DcMotor rightDrive2  = null;

    public DcMotor wheelIntake  = null;

    // Sensor objects
    public ColorSensor colorSensor  = null;

    public TileRunnerREV() {
    }

    public void init(HardwareMap ahwMap) {
        hwMap = ahwMap;

        // Get the motors
        leftDrive1  = hwMap.dcMotor.get("xmotor1");
        leftDrive2  = hwMap.dcMotor.get("xmotor2");

        rightDrive1 = hwMap.dcMotor.get("ymotor1");
        rightDrive2 = hwMap.dcMotor.get("ymotor2");

        wheelIntake = hwMap.dcMotor.get("lifter");

        // Set the motor directions
        leftDrive1.setDirection (DcMotorSimple.Direction.REVERSE);
        leftDrive2.setDirection (DcMotorSimple.Direction.REVERSE);

        rightDrive1.setDirection(DcMotorSimple.Direction.FORWARD);
        rightDrive2.setDirection(DcMotorSimple.Direction.FORWARD);

        wheelIntake.setDirection(DcMotorSimple.Direction.REVERSE);


        // Set the motor powers to zero
        leftDrive1.setPower(0);
        leftDrive2.setPower(0);

        rightDrive1.setPower(0);
        rightDrive2.setPower(0);

        wheelIntake.setPower(0);

        // Set the behavior of the motors when the power is set to zero.
        leftDrive1.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.FLOAT);
        leftDrive2.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.FLOAT);

        rightDrive1.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.FLOAT);
        rightDrive2.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.FLOAT);

        wheelIntake.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.FLOAT);

        // Set the motors to not use encoders
        leftDrive1.setMode  (DcMotor.RunMode.RUN_WITHOUT_ENCODER);
        leftDrive2.setMode  (DcMotor.RunMode.RUN_WITHOUT_ENCODER);

        rightDrive1.setMode (DcMotor.RunMode.RUN_WITHOUT_ENCODER);
        rightDrive2.setMode (DcMotor.RunMode.RUN_WITHOUT_ENCODER);

        wheelIntake.setMode (DcMotor.RunMode.RUN_WITHOUT_ENCODER);
    }

    /////////////////////////////////////
    // TeleOp methods
    /////////////////////////////////////

    public void linearDrive(float leftStickY, float rightStickY) {
        setLeftPower(leftStickY);
        setRightPower(rightStickY);
    }

    public void setLeftPower(float leftStickY) {
        leftDrive1.setPower(leftStickY * driverSpeedMod);
        leftDrive2.setPower(leftStickY * driverSpeedMod);
    }

    public void setRightPower(float rightStickY) {
        rightDrive1.setPower(rightStickY * driverSpeedMod);
        rightDrive2.setPower(rightStickY * driverSpeedMod);
    }


    public void rampDrive(float leftStickY, float rightStickY) {
        leftDrive1.setPower(leftStickY * leftStickY * leftStickY * driverSpeedMod);
        leftDrive2.setPower(leftStickY * leftStickY * leftStickY * driverSpeedMod);

        rightDrive1.setPower(rightStickY * rightStickY * rightStickY * driverSpeedMod);
        rightDrive2.setPower(rightStickY * rightStickY * rightStickY * driverSpeedMod);

    }
}
