package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.hardware.AnalogInput;
import com.qualcomm.robotcore.hardware.ColorSensor;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.DcMotorSimple;
import com.qualcomm.robotcore.hardware.HardwareMap;
import com.qualcomm.robotcore.hardware.OpticalDistanceSensor;
import com.qualcomm.robotcore.hardware.Servo;
import com.qualcomm.robotcore.hardware.TouchSensor;
import com.qualcomm.robotcore.util.ElapsedTime;

/**
 * Created by kawaiiPlat on 6/10/2017.
 */

public class TileRunner {
    // Speed control variables
    public final double SLOW    = 0.25;
    public final double NORMAL  = 0.4;
    public final double FAST    = 1.0;
//    public final double POWER_THRESHOLD = 0.95;

    public final double DECEL_STEP = 0.05;

//    public final double POWER_CONVERSION = 10.0;

    public double driverSpeedMod       = NORMAL;
    public double utilitySpeedMod      = NORMAL;

//    public double leftPower = 0;
//    public double rightPower = 0;

//    private double leftPrevMotorInput = 0;
//    private double rightPrevMotorInput = 0;
//
//    private double leftMotorInput = 0;
//    private double rightMotorInput = 0;
//
//    private double leftStep = 0.0;
//    private double rightStep = 0.0;

    // Motor objects
    public DcMotor leftDrive1   = null;
    public DcMotor leftDrive2   = null;

    public DcMotor rightDrive1  = null;
    public DcMotor rightDrive2  = null;

    public DcMotor lifter       = null;


    // Servo objects
    public Servo gripperServo   = null;
    public Servo jewelServo     = null;


    // Sensor objects
//    public TouchSensor frontTouch           = null;
//    public TouchSensor debugTouch           = null;
//
//    public AnalogInput sparkfunLeft         = null;
//    public AnalogInput sparkfunRight        = null;
//
//    public OpticalDistanceSensor leftODS    = null;
//    public OpticalDistanceSensor rightODS   = null;
//
//    public ColorSensor colorSensor          = null;


    // Hardware map
    HardwareMap hwMap = null;


    // Elapsed time
    private ElapsedTime elapsedTime = null;


    // Constructor
    public TileRunner() {
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
        gripperServo    = hwMap.servo.get("gripperservo");
        jewelServo      = hwMap.servo.get("jewelservo");

        // Get the sensors
//        frontTouch      = hwMap.touchSensor.get("fronttouch");
//        debugTouch      = hwMap.touchSensor.get("debugtouch");
//
//        sparkfunLeft    = hwMap.analogInput.get("sparkfunLightSensor1");
//        sparkfunRight   = hwMap.analogInput.get("sparkfunLightSensor2");
//
//        leftODS         = hwMap.opticalDistanceSensor.get("leftODS");
//        rightODS        = hwMap.opticalDistanceSensor.get("rightODS");
//
//        colorSensor     = hwMap.colorSensor.get("colorsensor");


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

    public void linearDriveDecel(float leftStickY, float rightStickY) {
        if(leftStickY == 0.0) {
            if(leftDrive1.getPower() > DECEL_STEP) {
                setLeftPower((float) (leftDrive1.getPower() - DECEL_STEP));
            }
            else if(leftDrive1.getPower() < DECEL_STEP) {
                setLeftPower((float) (leftDrive1.getPower() + DECEL_STEP));
            } else setLeftPower((float)0.0);
        } else setLeftPower(leftStickY);

        if(rightStickY == 0.0) {
            if(rightDrive1.getPower() > DECEL_STEP) {
                setRightPower((float) (rightDrive1.getPower() - DECEL_STEP));
            }
            else if(rightDrive1.getPower() < DECEL_STEP) {
                setRightPower((float) (rightDrive1.getPower() + DECEL_STEP));
            } else setRightPower((float)0.0);
        } else setRightPower(rightStickY);
    }

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

        // Half step acceleration
//        leftPower = ((leftStickY + leftDrive1.getPower()) / 2);
//        rightPower = ((rightStickY + rightDrive1.getPower()) / 2);
//
//        if( (leftPower / leftStickY ) <= POWER_THRESHOLD ||
//                (leftPower / leftStickY ) >= -POWER_THRESHOLD) {
//            leftDrive1.setPower(leftPower);
//            leftDrive2.setPower(leftPower);
//        }
//
//        if( (rightPower / rightStickY ) <= POWER_THRESHOLD ||
//                (rightPower / rightStickY ) >= POWER_THRESHOLD) {
//            rightDrive1.setPower(rightPower);
//            rightDrive2.setPower(rightPower);
//        }

        // Linear ramp acceleration
//        if(leftStickY > leftPrevMotorInput) {
//            leftStep ++;
//        } else if(leftStickY < leftPrevMotorInput) {
//            leftStep --;
//        }
//
//        if(rightStickY > rightPrevMotorInput) {
//            rightStep ++;
//        } else if(rightStickY < rightPrevMotorInput) {
//            rightStep --;
//        }
//
//        leftMotorInput = leftStep / POWER_CONVERSION;
//        rightMotorInput = rightStep / POWER_CONVERSION;
//
//        leftPrevMotorInput = leftMotorInput;
//        rightPrevMotorInput = rightMotorInput;
//
//        leftDrive1.setPower(leftMotorInput * driverSpeedMod);
//        leftDrive2.setPower(leftMotorInput * driverSpeedMod);
//
//        rightDrive1.setPower(rightMotorInput * driverSpeedMod);
//        rightDrive2.setPower(rightMotorInput * driverSpeedMod);
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