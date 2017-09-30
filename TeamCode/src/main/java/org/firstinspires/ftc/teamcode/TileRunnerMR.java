package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.DcMotorSimple;
import com.qualcomm.robotcore.hardware.HardwareMap;
import com.qualcomm.robotcore.hardware.Servo;
import com.qualcomm.robotcore.util.ElapsedTime;

/**
 * Created by kawaiiPlat on 6/10/2017.
 */

public class TileRunnerMR {
    // Speed control variables
    public final double SLOW    = 0.25;
    public final double NORMAL  = 0.4;
    public final double FAST    = 1.0;


    public double driverSpeedMod       = NORMAL;
    public double utilitySpeedMod      = NORMAL;


    // Motor objects
    public DcMotor leftDrive1   = null;
    public DcMotor leftDrive2   = null;

    public DcMotor rightDrive1  = null;
    public DcMotor rightDrive2  = null;

    public DcMotor lifter       = null;


    // Servo objects
    public Servo wheelIntake    = null;
    public Servo wheelIntake2   = null;


    // Hardware map
    HardwareMap hwMap = null;


    // Elapsed time
    private ElapsedTime elapsedTime = null;


    // Constructor
    public TileRunnerMR() {
    }


    // Hardware map initialization
    public void init(HardwareMap ahwMap) {

        // Get the hardware map
        hwMap = ahwMap;


        // Get the motors
        leftDrive1  = hwMap.dcMotor.get("xmotor1");
        leftDrive2  = hwMap.dcMotor.get("xmotor2");

        rightDrive1 = hwMap.dcMotor.get("ymotor1");
        rightDrive2 = hwMap.dcMotor.get("ymotor2");

        lifter      = hwMap.dcMotor.get("lifter");


        // Get the servos
        wheelIntake     = hwMap.servo.get("wheelintake");
        wheelIntake2    = hwMap.servo.get("wheelintake2");


        // Set the motor directions
        leftDrive1.setDirection (DcMotorSimple.Direction.REVERSE);
        leftDrive2.setDirection (DcMotorSimple.Direction.REVERSE);

        rightDrive1.setDirection(DcMotorSimple.Direction.FORWARD);
        rightDrive2.setDirection(DcMotorSimple.Direction.FORWARD);

        lifter.setDirection     (DcMotorSimple.Direction.REVERSE);


        // Set the motor powers to zero
        leftDrive1.setPower(0);
        leftDrive2.setPower(0);

        rightDrive1.setPower(0);
        rightDrive2.setPower(0);

        lifter.setPower(0);

        // Set the behavior of the motors when the power is set to zero.
        leftDrive1.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.FLOAT);
        leftDrive2.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.FLOAT);

        rightDrive1.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.FLOAT);
        rightDrive2.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.FLOAT);

        lifter.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);

        // Set the motors to not use encoders
        leftDrive1.setMode  (DcMotor.RunMode.RUN_WITHOUT_ENCODER);
        leftDrive2.setMode  (DcMotor.RunMode.RUN_WITHOUT_ENCODER);

        rightDrive1.setMode (DcMotor.RunMode.RUN_WITHOUT_ENCODER);
        rightDrive2.setMode (DcMotor.RunMode.RUN_WITHOUT_ENCODER);

        lifter.setMode      (DcMotor.RunMode.RUN_WITHOUT_ENCODER);
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

    /***
     *
     * waitForTick implements a periodic delay. However, this acts like a metronome with a regular
     * periodic tick.  This is used to compensate for varying processing times for each cycle.
     * The function looks at the elapsed cycle time, and sleeps for the remaining time interval.
     *
     * @param periodMs  Length of wait cycle in mSec.
     */
    public void waitForTick(long periodMs) {
        long  remaining = periodMs - (long)elapsedTime.milliseconds();

        // sleep for the remaining portion of the regular cycle period.
        if (remaining > 0) {
            try {
                Thread.sleep(remaining);
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
            }
        }

        // Reset the cycle clock for the next pass.
        elapsedTime.reset();
    }

}