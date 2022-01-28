/* Copyright (c) 2017 FIRST. All rights reserved.
 *
 * Redistribution and use in source and binary forms, with or without modification,
 * are permitted (subject to the limitations in the disclaimer below) provided that
 * the following conditions are met:
 *
 * Redistributions of source code must retain the above copyright notice, this list
 * of conditions and the following disclaimer.
 *
 * Redistributions in binary form must reproduce the above copyright notice, this
 * list of conditions and the following disclaimer in the documentation and/or
 * other materials provided with the distribution.
 *
 * Neither the name of FIRST nor the names of its contributors may be used to endorse or
 * promote products derived from this software without specific prior written permission.
 *
 * NO EXPRESS OR IMPLIED LICENSES TO ANY PARTY'S PATENT RIGHTS ARE GRANTED BY THIS
 * LICENSE. THIS SOFTWARE IS PROVIDED BY THE COPYRIGHT HOLDERS AND CONTRIBUTORS
 * "AS IS" AND ANY EXPRESS OR IMPLIED WARRANTIES, INCLUDING, BUT NOT LIMITED TO,
 * THE IMPLIED WARRANTIES OF MERCHANTABILITY AND FITNESS FOR A PARTICULAR PURPOSE
 * ARE DISCLAIMED. IN NO EVENT SHALL THE COPYRIGHT OWNER OR CONTRIBUTORS BE LIABLE
 * FOR ANY DIRECT, INDIRECT, INCIDENTAL, SPECIAL, EXEMPLARY, OR CONSEQUENTIAL
 * DAMAGES (INCLUDING, BUT NOT LIMITED TO, PROCUREMENT OF SUBSTITUTE GOODS OR
 * SERVICES; LOSS OF USE, DATA, OR PROFITS; OR BUSINESS INTERRUPTION) HOWEVER
 * CAUSED AND ON ANY THEORY OF LIABILITY, WHETHER IN CONTRACT, STRICT LIABILITY,
 * OR TORT (INCLUDING NEGLIGENCE OR OTHERWISE) ARISING IN ANY WAY OUT OF THE USE
 * OF THIS SOFTWARE, EVEN IF ADVISED OF THE POSSIBILITY OF SUCH DAMAGE.
 */

package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.Disabled;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.util.ElapsedTime;

import org.firstinspires.ftc.robotcore.external.ClassFactory;
import org.firstinspires.ftc.robotcore.external.hardware.camera.WebcamName;
import org.firstinspires.ftc.robotcore.external.navigation.VuforiaLocalizer;
import org.firstinspires.ftc.robotcore.external.tfod.Recognition;
import org.firstinspires.ftc.robotcore.external.navigation.VuforiaLocalizer.CameraDirection;
import org.firstinspires.ftc.robotcore.external.tfod.TFObjectDetector;

import java.util.List;

/**
 * This file illustrates the concept of driving a path based on time.
 * It uses the common Pushbot hardware class to define the drive on the robot.
 * The code is structured as a LinearOpMode
 *
 * The code assumes that you do NOT have encoders on the wheels,
 *   otherwise you would use: PushbotAutoDriveByEncoder;
 *
 *   The desired path in this example is:
 *   - Drive forward for 3 seconds
 *   - Spin right for 1.3 seconds
 *   - Drive Backwards for 1 Second
 *   - Stop and close the claw.
 * hi
 *  The code is written in a simple form with no optimizations.
 *  However, there are several ways that this type of sequence could be streamlined,
 *
 * Use Android Studios to Copy this Class, and Paste it into your team's code folder with a new name.
 * Remove or comment out the @Disabled line to add this opmode to the Driver Station OpMode list
 */

@Autonomous(name="encoder test auto yay woooo yeah ok", group="Pushbot")
public class RunByTimeAuto extends LinearOpMode {
    private static final String TFOD_MODEL_ASSET = "FreightFrenzy_DM.tflite";
    private ElapsedTime runtime = new ElapsedTime();
    private static final String[] LABELS = {
            //"Ball",
            //"Cube",
            "Duck",
            "Marker"
    };

    /*
     * IMPORTANT: You need to obtain your own license key to use Vuforia. The string below with which
     * 'parameters.vuforiaLicenseKey' is initialized is for illustration only, and will not function.
     * A Vuforia 'Development' license key, can be obtained free of charge from the Vuforia developer
     * web site at https://developer.vuforia.com/license-manager.
     *
     * Vuforia license keys are always 380 characters long, and look as if they contain mostly
     * random data. As an example, here is a example of a fragment of a valid key:
     *      ... yIgIzTqZ4mWjk9wd3cZO9T1axEqzuhxoGlfOOI2dRzKS4T0hQ8kT ...
     * Once you've obtained a license key, copy the string from the Vuforia web site
     * and paste it in to your code on the next line, between the double quotes.
     */
    private static final String VUFORIA_KEY =
            "AQ0rZzP/////AAABmTRIZi0yo0NXiSsea78S8wVqSI8v64D/rFfE8zOk70jx0HCdjmPYt8x4SD3+csUaQZbgVuMkVpCeZovQydoVuMPO5E0pffJFdlnss7dY8+ZneTdIPSe/PUFLDIdqIvmxIFlQalKSM95pLuhIoBOK9bKbPHIsB6U2YgLdkLUDbaemHbE2Umla15R9guvN+7PLKRT71SKFAZrfQOSI8FphIHk2YWz1jryflHMAiGwqwe78wkB7NOPNePkDV0y+wmLI5C3jSm1w+lkGYsKl2zGwwyUZAUJSoskFU+X0hdEtWY9/QZAPLfCYTUPCqsihkiX4L8MGeCqfY6xidfjquqfeIluXBeOw2by431akuO52xGZb";

    /**
     * {@link #vuforia} is the variable we will use to store our instance of the Vuforia
     * localization engine.
     */
    private VuforiaLocalizer vuforia;

    /**
     * {@link #tfod} is the variable we will use to store our instance of the TensorFlow Object
     * Detection engine.
     */
    private TFObjectDetector tfod;

    @Override
    public void runOpMode() {
        // The TFObjectDetector uses the camera frames from the VuforiaLocalizer, so we create that
        // first.
        //initVuforia();
        //initTfod();

        /**
         * Activate TensorFlow Object Detection before we wait for the start command.
         * Do it here so that the Camera Stream window will have the TensorFlow annotations visible.
         **/
        /*if (tfod != null) {
            tfod.activate();

            // The TensorFlow software will scale the input images from the camera to a lower resolution.
            // This can result in lower detection accuracy at longer distances (> 55cm or 22").
            // If your target is at distance greater than 50 cm (20") you can adjust the magnification value
            // to artificially zoom in to the center of image.  For best results, the "aspectRatio" argument
            // should be set to the value of the images used to create the TensorFlow Object Detection model
            // (typically 16/9).
            tfod.setZoom(1.0, 16 / 9.0);
        }*/

    /* Declare OpMode members. */
    HardwarePushbot         robot   = new HardwarePushbot();   // Use a Pushbot's hardware
        ElapsedTime     runtime = new ElapsedTime();


     final double     FORWARD_SPEED = 0.3;
     final double     TURN_SPEED    = 0.3;
     int markerPosition = 3;
     int frontRightPosition = 0;
     int frontLeftPosition = 0;
     int backRightPosition = 0;
     int backLeftPosition = 0;

        /*
         * Initialize the drive system variables.
         * The init() method of the hardware class does all the work here
         */
        robot.init(hardwareMap);

        // Send telemetry message to signify robot waiting;
        telemetry.addData("Status", "Ready to run");    //
        telemetry.update();

        // Wait for the game to start (driver presses PLAY)
        //List<Recognition> updatedRecognitions = tfod.getRecognitions();
        waitForStart();
        double currenttime = runtime.seconds();
        /*while(opModeIsActive() && (runtime.seconds() - currenttime < 2)){
            telemetry.addData("before", "listupdate");
            telemetry.update();
            sleep(1000);
           updatedRecognitions = tfod.getUpdatedRecognitions();
           telemetry.addData("after","listupdate");
           telemetry.update();
           sleep(1000);

           telemetry.addData("# Object Detected", updatedRecognitions.size());
            telemetry.update();

        }*/



        ///////////////////////////////////////////

        /*robot.frontLeft.setPower(-0.30);
        robot.frontRight.setPower(0.30);
        robot.backLeft.setPower(0.30);
        robot.backRight.setPower(-0.30);
        sleep(2300);
        robot.frontLeft.setPower(0);
        robot.frontRight.setPower(0);
        robot.backLeft.setPower(0);
        robot.backRight.setPower(0);

        robot.frontLeft.setPower(-0.30);
        robot.frontRight.setPower(0.30);
        robot.backLeft.setPower(-0.30);
        robot.backRight.setPower(0.30);
        sleep(1900);
        robot.frontLeft.setPower(0);
        robot.frontRight.setPower(0);
        robot.backLeft.setPower(0);
        robot.backRight.setPower(0);

        robot.frontLeft.setPower(-0.30);
        robot.frontRight.setPower(-0.30);
        robot.backLeft.setPower(-0.30);
        robot.backRight.setPower(-0.30);
        sleep(1700);
        robot.frontLeft.setPower(0);
        robot.frontRight.setPower(0);
        robot.backLeft.setPower(0);
        robot.backRight.setPower(0);

        sleep(100);

        robot.spinServo.setPower(-0.4);
        sleep(2600);
        robot.spinServo.setPower(0);

        robot.frontLeft.setPower(0.30);
        robot.frontRight.setPower(0.30);
        robot.backLeft.setPower(0.30);
        robot.backRight.setPower(0.30);
        sleep(1400);
        robot.frontLeft.setPower(0);
        robot.frontRight.setPower(0);
        robot.backLeft.setPower(0);
        robot.backRight.setPower(0);

        robot.frontLeft.setPower(0.30);
        robot.frontRight.setPower(-0.30);
        robot.backLeft.setPower(0.30);
        robot.backRight.setPower(-0.30);
        sleep(2590);
        robot.frontLeft.setPower(0);
        robot.frontRight.setPower(0);
        robot.backLeft.setPower(0);
        robot.backRight.setPower(0);

        robot.liftLeft.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        robot.liftRight.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);

        robot.liftLeft.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
        robot.liftRight.setMode(DcMotor.RunMode.RUN_USING_ENCODER);

        robot.liftLeft.setMode(DcMotor.RunMode.RUN_TO_POSITION);
        robot.liftRight.setMode(DcMotor.RunMode.RUN_TO_POSITION);


        if(markerPosition == 3) {
            robot.liftLeft.setTargetPosition(-270);
            robot.liftRight.setTargetPosition(-270);

            robot.liftLeft.setPower(0.30);
            robot.liftRight.setPower(0.30);

            sleep(2020);

            robot.frontLeft.setPower(-0.30);
            robot.frontRight.setPower(-0.30);
            robot.backLeft.setPower(-0.30);
            robot.backRight.setPower(-0.30);
            sleep(400);
            robot.frontLeft.setPower(0);
            robot.frontRight.setPower(0);
            robot.backLeft.setPower(0);
            robot.backRight.setPower(0);

            robot.gatherServo.setPower(-0.4);
            sleep(2200);
            robot.gatherServo.setPower(0);

            robot.liftLeft.setTargetPosition(0);
            robot.liftRight.setTargetPosition(0);

        } else if (markerPosition == 2){

            robot.liftLeft.setTargetPosition(-250);
            robot.liftRight.setTargetPosition(-250);

            robot.frontLeft.setPower(-0.30);
            robot.frontRight.setPower(-0.30);
            robot.backLeft.setPower(-0.30);
            robot.backRight.setPower(-0.30);
            sleep(80);
            robot.frontLeft.setPower(0);
            robot.frontRight.setPower(0);
            robot.backLeft.setPower(0);
            robot.backRight.setPower(0);

            robot.liftLeft.setPower(0.30);
            robot.liftRight.setPower(0.30);

            sleep(2020);

            robot.frontLeft.setPower(-0.30);
            robot.frontRight.setPower(-0.30);
            robot.backLeft.setPower(-0.30);
            robot.backRight.setPower(-0.30);
            sleep(700);
            robot.frontLeft.setPower(0);
            robot.frontRight.setPower(0);
            robot.backLeft.setPower(0);
            robot.backRight.setPower(0);

            robot.gatherServo.setPower(-0.4);
            sleep(2200);
            robot.gatherServo.setPower(0);

            robot.frontLeft.setPower(-0.30);
            robot.frontRight.setPower(0.30);
            robot.backLeft.setPower(-0.30);
            robot.backRight.setPower(0.30);
            sleep(500);
            robot.frontLeft.setPower(0);
            robot.frontRight.setPower(0);
            robot.backLeft.setPower(0);
            robot.backRight.setPower(0);

            robot.liftLeft.setTargetPosition(0);
            robot.liftRight.setTargetPosition(0);

        }else if(markerPosition == 1){
            robot.liftLeft.setTargetPosition(-200);
            robot.liftRight.setTargetPosition(-200);

            robot.frontLeft.setPower(-0.30);
            robot.frontRight.setPower(-0.30);
            robot.backLeft.setPower(-0.30);
            robot.backRight.setPower(-0.30);
            sleep(80);
            robot.frontLeft.setPower(0);
            robot.frontRight.setPower(0);
            robot.backLeft.setPower(0);
            robot.backRight.setPower(0);

            robot.liftLeft.setPower(0.30);
            robot.liftRight.setPower(0.30);

            sleep(2020);

            robot.frontLeft.setPower(-0.30);
            robot.frontRight.setPower(-0.30);
            robot.backLeft.setPower(-0.30);
            robot.backRight.setPower(-0.30);
            sleep(700);
            robot.frontLeft.setPower(0);
            robot.frontRight.setPower(0);
            robot.backLeft.setPower(0);
            robot.backRight.setPower(0);

            robot.gatherServo.setPower(-0.4);
            sleep(2200);
            robot.gatherServo.setPower(0);

            robot.frontLeft.setPower(-0.30);
            robot.frontRight.setPower(0.30);
            robot.backLeft.setPower(-0.30);
            robot.backRight.setPower(0.30);
            sleep(500);
            robot.frontLeft.setPower(0);
            robot.frontRight.setPower(0);
            robot.backLeft.setPower(0);
            robot.backRight.setPower(0);

            robot.liftLeft.setTargetPosition(0);
            robot.liftRight.setTargetPosition(0);

        } else if (markerPosition == 1){
            robot.liftLeft.setTargetPosition(-564);
            robot.liftRight.setTargetPosition(-564);

            robot.frontLeft.setPower(-0.30);
            robot.frontRight.setPower(-0.30);
            robot.backLeft.setPower(-0.30);
            robot.backRight.setPower(-0.30);
            sleep(60);
            robot.frontLeft.setPower(0);
            robot.frontRight.setPower(0);
            robot.backLeft.setPower(0);
            robot.backRight.setPower(0);

            robot.liftLeft.setPower(0.30);
            robot.liftRight.setPower(0.30);

            sleep(2020);

            robot.frontLeft.setPower(-0.30);
            robot.frontRight.setPower(-0.30);
            robot.backLeft.setPower(-0.30);
            robot.backRight.setPower(-0.30);
            sleep(700);
            robot.frontLeft.setPower(0);
            robot.frontRight.setPower(0);
            robot.backLeft.setPower(0);
            robot.backRight.setPower(0);

            robot.gatherServo.setPower(-0.4);
            sleep(2200);
            robot.gatherServo.setPower(0);

            robot.frontLeft.setPower(-0.30);
            robot.frontRight.setPower(0.30);
            robot.backLeft.setPower(-0.30);
            robot.backRight.setPower(0.30);
            sleep(500);
            robot.frontLeft.setPower(0);
            robot.frontRight.setPower(0);
            robot.backLeft.setPower(0);
            robot.backRight.setPower(0);

            robot.liftLeft.setTargetPosition(0);
            robot.liftRight.setTargetPosition(0);
        }

        robot.frontLeft.setPower(0.30);
        robot.frontRight.setPower(-0.30);
        robot.backLeft.setPower(0.30);
        robot.backRight.setPower(-0.30);
        sleep(700);
        robot.frontLeft.setPower(0);
        robot.frontRight.setPower(0);
        robot.backLeft.setPower(0);
        robot.backRight.setPower(0);

        robot.frontLeft.setPower(0.30);
        robot.frontRight.setPower(0.30);
        robot.backLeft.setPower(0.30);
        robot.backRight.setPower(0.30);
        sleep(1000);
        robot.frontLeft.setPower(0);
        robot.frontRight.setPower(0);
        robot.backLeft.setPower(0);
        robot.backRight.setPower(0);*/

        /////////////////////////////////////////////////////////////////////////////////////////////////////////////

        /*robot.frontLeft.setPower(-0.30);
        robot.frontRight.setPower(0.30);
        robot.backLeft.setPower(0.30);
        robot.backRight.setPower(-0.30);
        sleep(700);
        robot.frontLeft.setPower(0);
        robot.frontRight.setPower(0);
        robot.backLeft.setPower(0);
        robot.backRight.setPower(0);

        robot.frontLeft.setPower(-0.20);
        robot.frontRight.setPower(-0.20);
        robot.backLeft.setPower(-0.20);
        robot.backRight.setPower(-0.20);
        sleep(2000);
        robot.frontRight.setPower(0);
        robot.backLeft.setPower(0);
        robot.backRight.setPower(0);
        robot.frontLeft.setPower(0);
        sleep(2000);

        robot.spinServo.setPower(0.4);
        sleep(2600);
        robot.spinServo.setPower(0);

        robot.frontLeft.setPower(-0.40);
        robot.frontRight.setPower(0.40);
        robot.backLeft.setPower(0.40);
        robot.backRight.setPower(-0.40);
        sleep(1030);
        robot.frontLeft.setPower(0);
        robot.frontRight.setPower(0);
        robot.backLeft.setPower(0);
        robot.backRight.setPower(0);

        robot.frontLeft.setPower(-0.30);
        robot.frontRight.setPower(-0.30);
        robot.backLeft.setPower(-0.30);
        robot.backRight.setPower(-0.30);
        sleep(250);
        robot.frontLeft.setPower(0);
        robot.frontRight.setPower(0);
        robot.backLeft.setPower(0);
        robot.backRight.setPower(0);

        robot.frontLeft.setPower(0.30);
        robot.frontRight.setPower(0.30);
        robot.backLeft.setPower(0.30);
        robot.backRight.setPower(0.30);
        sleep(300);
        robot.frontLeft.setPower(0);
        robot.frontRight.setPower(0);
        robot.backLeft.setPower(0);
        robot.backRight.setPower(0);

        robot.frontLeft.setPower(0.30);
        robot.frontRight.setPower(0.30);
        robot.backLeft.setPower(0.30);
        robot.backRight.setPower(0.30);
        sleep(300);
        robot.frontLeft.setPower(0);
        robot.frontRight.setPower(0);
        robot.backLeft.setPower(0);
        robot.backRight.setPower(0);

        robot.frontLeft.setPower(0.30);
        robot.frontRight.setPower(0.30);
        robot.backLeft.setPower(0.30);
        robot.backRight.setPower(0.30);
        sleep(300);
        robot.frontLeft.setPower(0);
        robot.frontRight.setPower(0);
        robot.backLeft.setPower(0);
        robot.backRight.setPower(0);

        robot.frontLeft.setPower(-0.30);
        robot.frontRight.setPower(0.30);
        robot.backLeft.setPower(-0.30);
        robot.backRight.setPower(0.30);
        sleep(2000);
        robot.frontLeft.setPower(0);
        robot.frontRight.setPower(0);
        robot.backLeft.setPower(0);
        robot.backRight.setPower(0);


        robot.liftLeft.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        robot.liftRight.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);

        robot.liftLeft.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
        robot.liftRight.setMode(DcMotor.RunMode.RUN_USING_ENCODER);

        robot.liftLeft.setMode(DcMotor.RunMode.RUN_TO_POSITION);
        robot.liftRight.setMode(DcMotor.RunMode.RUN_TO_POSITION);


        if(markerPosition == 3) {
            robot.liftLeft.setTargetPosition(-200);
            robot.liftRight.setTargetPosition(-200);

            robot.liftLeft.setPower(0.30);
            robot.liftRight.setPower(0.30);

            sleep(2020);

            robot.frontLeft.setPower(-0.30);
            robot.frontRight.setPower(-0.30);
            robot.backLeft.setPower(-0.30);
            robot.backRight.setPower(-0.30);
            sleep(730);
            robot.frontLeft.setPower(0);
            robot.frontRight.setPower(0);
            robot.backLeft.setPower(0);
            robot.backRight.setPower(0);

            robot.gatherServo.setPower(-0.4);
            sleep(2200);
            robot.gatherServo.setPower(0);

            robot.liftLeft.setTargetPosition(0);
            robot.liftRight.setTargetPosition(0);

        } else if (markerPosition == 2){

            robot.liftLeft.setTargetPosition(-250);
            robot.liftRight.setTargetPosition(-250);

            robot.frontLeft.setPower(-0.30);
            robot.frontRight.setPower(-0.30);
            robot.backLeft.setPower(-0.30);
            robot.backRight.setPower(-0.30);
            sleep(80);
            robot.frontLeft.setPower(0);
            robot.frontRight.setPower(0);
            robot.backLeft.setPower(0);
            robot.backRight.setPower(0);

            robot.liftLeft.setPower(0.30);
            robot.liftRight.setPower(0.30);

            sleep(2020);

            robot.frontLeft.setPower(-0.30);
            robot.frontRight.setPower(-0.30);
            robot.backLeft.setPower(-0.30);
            robot.backRight.setPower(-0.30);
            sleep(700);
            robot.frontLeft.setPower(0);
            robot.frontRight.setPower(0);
            robot.backLeft.setPower(0);
            robot.backRight.setPower(0);

            robot.gatherServo.setPower(-0.4);
            sleep(2200);
            robot.gatherServo.setPower(0);

            robot.frontLeft.setPower(-0.30);
            robot.frontRight.setPower(0.30);
            robot.backLeft.setPower(-0.30);
            robot.backRight.setPower(0.30);
            sleep(500);
            robot.frontLeft.setPower(0);
            robot.frontRight.setPower(0);
            robot.backLeft.setPower(0);
            robot.backRight.setPower(0);

            robot.liftLeft.setTargetPosition(0);
            robot.liftRight.setTargetPosition(0);

        }else if(markerPosition == 1){
            robot.liftLeft.setTargetPosition(-200);
            robot.liftRight.setTargetPosition(-200);

            robot.frontLeft.setPower(-0.30);
            robot.frontRight.setPower(-0.30);
            robot.backLeft.setPower(-0.30);
            robot.backRight.setPower(-0.30);
            sleep(80);
            robot.frontLeft.setPower(0);
            robot.frontRight.setPower(0);
            robot.backLeft.setPower(0);
            robot.backRight.setPower(0);

            robot.liftLeft.setPower(0.30);
            robot.liftRight.setPower(0.30);

            sleep(2020);

            robot.frontLeft.setPower(-0.30);
            robot.frontRight.setPower(-0.30);
            robot.backLeft.setPower(-0.30);
            robot.backRight.setPower(-0.30);
            sleep(700);
            robot.frontLeft.setPower(0);
            robot.frontRight.setPower(0);
            robot.backLeft.setPower(0);
            robot.backRight.setPower(0);

            robot.gatherServo.setPower(-0.4);
            sleep(2200);
            robot.gatherServo.setPower(0);

            robot.frontLeft.setPower(-0.30);
            robot.frontRight.setPower(0.30);
            robot.backLeft.setPower(-0.30);
            robot.backRight.setPower(0.30);
            sleep(500);
            robot.frontLeft.setPower(0);
            robot.frontRight.setPower(0);
            robot.backLeft.setPower(0);
            robot.backRight.setPower(0);

            robot.liftLeft.setTargetPosition(0);
            robot.liftRight.setTargetPosition(0);

        } else if (markerPosition == 1){
            robot.liftLeft.setTargetPosition(-564);
            robot.liftRight.setTargetPosition(-564);

            robot.frontLeft.setPower(-0.30);
            robot.frontRight.setPower(-0.30);
            robot.backLeft.setPower(-0.30);
            robot.backRight.setPower(-0.30);
            sleep(60);
            robot.frontLeft.setPower(0);
            robot.frontRight.setPower(0);
            robot.backLeft.setPower(0);
            robot.backRight.setPower(0);

            robot.liftLeft.setPower(0.30);
            robot.liftRight.setPower(0.30);

            sleep(2020);

            robot.frontLeft.setPower(-0.30);
            robot.frontRight.setPower(-0.30);
            robot.backLeft.setPower(-0.30);
            robot.backRight.setPower(-0.30);
            sleep(700);
            robot.frontLeft.setPower(0);
            robot.frontRight.setPower(0);
            robot.backLeft.setPower(0);
            robot.backRight.setPower(0);

            robot.gatherServo.setPower(-0.4);
            sleep(2200);
            robot.gatherServo.setPower(0);

            robot.frontLeft.setPower(-0.30);
            robot.frontRight.setPower(0.30);
            robot.backLeft.setPower(-0.30);
            robot.backRight.setPower(0.30);
            sleep(500);
            robot.frontLeft.setPower(0);
            robot.frontRight.setPower(0);
            robot.backLeft.setPower(0);
            robot.backRight.setPower(0);

            robot.liftLeft.setTargetPosition(0);
            robot.liftRight.setTargetPosition(0);
        }


        robot.frontLeft.setPower(0.30);
        robot.frontRight.setPower(0.30);
        robot.backLeft.setPower(0.30);
        robot.backRight.setPower(0.30);
        sleep(1600);
        robot.frontLeft.setPower(0);
        robot.frontRight.setPower(0);
        robot.backLeft.setPower(0);
        robot.backRight.setPower(0);

        sleep(300);*/

        //////////////////////////////////////////////////////////////////////////////////////////////////////////////

        //new redduck spin

        robot.liftLeft.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        robot.liftRight.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        robot.frontLeft.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        robot.frontRight.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        robot.backLeft.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        robot.backRight.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);

        robot.liftLeft.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
        robot.liftRight.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
        robot.frontLeft.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
        robot.frontRight.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
        robot.backLeft.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
        robot.backRight.setMode(DcMotor.RunMode.RUN_USING_ENCODER);

        /*frontLeftPosition -= 250;
        frontRightPosition += 250;
        backLeftPosition += 250;
        backRightPosition -= 250;
        //going out from wall

        robot.frontLeft.setTargetPosition(frontLeftPosition);
        robot.frontRight.setTargetPosition(frontRightPosition);
        robot.backLeft.setTargetPosition(backLeftPosition);
        robot.backRight.setTargetPosition(backRightPosition);

        robot.frontLeft.setMode(DcMotor.RunMode.RUN_TO_POSITION);
        robot.frontRight.setMode(DcMotor.RunMode.RUN_TO_POSITION);
        robot.backLeft.setMode(DcMotor.RunMode.RUN_TO_POSITION);
        robot.backRight.setMode(DcMotor.RunMode.RUN_TO_POSITION);

        robot.frontLeft.setPower(0.30);
        robot.frontRight.setPower(0.30);
        robot.backLeft.setPower(0.30);
        robot.backRight.setPower(0.30);

        sleep(1200);

//        robot.frontRight.setPower(0);
//        robot.backLeft.setPower(0);
//        robot.backRight.setPower(0);
//        robot.frontLeft.setPower(0);


        frontLeftPosition += 600;
        frontRightPosition -= 600;
        backLeftPosition += 600;
        backRightPosition -= 600;
        //turing

        robot.frontLeft.setTargetPosition(frontLeftPosition);
        robot.frontRight.setTargetPosition(frontRightPosition);
        robot.backLeft.setTargetPosition(backLeftPosition);
        robot.backRight.setTargetPosition(backRightPosition);


        robot.frontLeft.setMode(DcMotor.RunMode.RUN_TO_POSITION);
        robot.frontRight.setMode(DcMotor.RunMode.RUN_TO_POSITION);
        robot.backLeft.setMode(DcMotor.RunMode.RUN_TO_POSITION);
        robot.backRight.setMode(DcMotor.RunMode.RUN_TO_POSITION);

//        robot.frontLeft.setPower(0.30);
//        robot.frontRight.setPower(0.30);
//        robot.backLeft.setPower(0.30);
//        robot.backRight.setPower(0.30);

        sleep(1200);

//        robot.frontRight.setPower(0);
//        robot.backLeft.setPower(0);
//        robot.backRight.setPower(0);
//        robot.frontLeft.setPower(0);

        frontLeftPosition += 850;
        frontRightPosition -= 850;
        backLeftPosition -= 850;
        backRightPosition += 850;
        //going toward the spinning


        robot.frontLeft.setTargetPosition(frontLeftPosition);
        robot.frontRight.setTargetPosition(frontRightPosition);
        robot.backLeft.setTargetPosition(backLeftPosition);
        robot.backRight.setTargetPosition(backRightPosition);


        robot.frontLeft.setMode(DcMotor.RunMode.RUN_TO_POSITION);
        robot.frontRight.setMode(DcMotor.RunMode.RUN_TO_POSITION);
        robot.backLeft.setMode(DcMotor.RunMode.RUN_TO_POSITION);
        robot.backRight.setMode(DcMotor.RunMode.RUN_TO_POSITION);

//        robot.frontLeft.setPower(0.30);
//        robot.frontRight.setPower(0.30);
//        robot.backLeft.setPower(0.30);
//        robot.backRight.setPower(0.30);

        sleep(2500);

//        robot.frontRight.setPower(0);
//        robot.backLeft.setPower(0);
//        robot.backRight.setPower(0);
//        robot.frontLeft.setPower(0);

        robot.spinServo.setPower(-0.4);
        sleep(2600);
        robot.spinServo.setPower(0);

        frontLeftPosition -= 800;
        frontRightPosition += 800;
        backLeftPosition += 800;
        backRightPosition -= 800;
        //going back to the square


        robot.frontLeft.setTargetPosition(frontLeftPosition);
        robot.frontRight.setTargetPosition(frontRightPosition);
        robot.backLeft.setTargetPosition(backLeftPosition);
        robot.backRight.setTargetPosition(backRightPosition);


        robot.frontLeft.setMode(DcMotor.RunMode.RUN_TO_POSITION);
        robot.frontRight.setMode(DcMotor.RunMode.RUN_TO_POSITION);
        robot.backLeft.setMode(DcMotor.RunMode.RUN_TO_POSITION);
        robot.backRight.setMode(DcMotor.RunMode.RUN_TO_POSITION);

//        robot.frontLeft.setPower(0.30);
//        robot.frontRight.setPower(0.30);
//        robot.backLeft.setPower(0.30);
//        robot.backRight.setPower(0.30);

        sleep(1200);

//        robot.frontRight.setPower(0);
//        robot.backLeft.setPower(0);
//        robot.backRight.setPower(0);
//        robot.frontLeft.setPower(0);

        frontLeftPosition += 350;
        frontRightPosition += 350;
        backLeftPosition += 350;
        backRightPosition += 350;
        //going backward


        robot.frontLeft.setTargetPosition(frontLeftPosition);
        robot.frontRight.setTargetPosition(frontRightPosition);
        robot.backLeft.setTargetPosition(backLeftPosition);
        robot.backRight.setTargetPosition(backRightPosition);


        robot.frontLeft.setMode(DcMotor.RunMode.RUN_TO_POSITION);
        robot.frontRight.setMode(DcMotor.RunMode.RUN_TO_POSITION);
        robot.backLeft.setMode(DcMotor.RunMode.RUN_TO_POSITION);
        robot.backRight.setMode(DcMotor.RunMode.RUN_TO_POSITION);

//        robot.frontLeft.setPower(0.30);
//        robot.frontRight.setPower(0.30);
//        robot.backLeft.setPower(0.30);
//        robot.backRight.setPower(0.30);

        sleep(1200);

//        robot.frontRight.setPower(0);
//        robot.backLeft.setPower(0);
//        robot.backRight.setPower(0);
//        robot.frontLeft.setPower(0);

       frontLeftPosition -= 850;
       frontRightPosition += 850;
       backLeftPosition -= 850;
       backRightPosition += 850;
       //turning to the shipping hub


       robot.frontLeft.setTargetPosition(frontLeftPosition);
       robot.frontRight.setTargetPosition(frontRightPosition);
       robot.backLeft.setTargetPosition(backLeftPosition);
       robot.backRight.setTargetPosition(backRightPosition);


       robot.frontLeft.setMode(DcMotor.RunMode.RUN_TO_POSITION);
       robot.frontRight.setMode(DcMotor.RunMode.RUN_TO_POSITION);
       robot.backLeft.setMode(DcMotor.RunMode.RUN_TO_POSITION);
       robot.backRight.setMode(DcMotor.RunMode.RUN_TO_POSITION);

//       robot.frontLeft.setPower(0.30);
//       robot.frontRight.setPower(0.30);
//       robot.backLeft.setPower(0.30);
//       robot.backRight.setPower(0.30);

       sleep(2500);

//       robot.frontRight.setPower(0);
//       robot.backLeft.setPower(0);
//       robot.backRight.setPower(0);
//       robot.frontLeft.setPower(0);

       robot.liftLeft.setTargetPosition(-290);
       robot.liftRight.setTargetPosition(-290);
       robot.liftLeft.setMode(DcMotor.RunMode.RUN_TO_POSITION);
       robot.liftRight.setMode(DcMotor.RunMode.RUN_TO_POSITION);


       robot.liftLeft.setPower(0.20);
       robot.liftRight.setPower(0.20);

       sleep(2020);

       frontLeftPosition -= 290;
       frontRightPosition -= 290;
       backLeftPosition -= 290;
       backRightPosition -= 290;
       //moving more toward the shipping hub


       robot.frontLeft.setTargetPosition(frontLeftPosition);
       robot.frontRight.setTargetPosition(frontRightPosition);
       robot.backLeft.setTargetPosition(backLeftPosition);
       robot.backRight.setTargetPosition(backRightPosition);


       robot.frontLeft.setMode(DcMotor.RunMode.RUN_TO_POSITION);
       robot.frontRight.setMode(DcMotor.RunMode.RUN_TO_POSITION);
       robot.backLeft.setMode(DcMotor.RunMode.RUN_TO_POSITION);
       robot.backRight.setMode(DcMotor.RunMode.RUN_TO_POSITION);

//       robot.frontLeft.setPower(0.30);
//       robot.frontRight.setPower(0.30);
//       robot.backLeft.setPower(0.30);
//       robot.backRight.setPower(0.30);

       sleep(1200);

//       robot.frontRight.setPower(0);
//       robot.backLeft.setPower(0);
//       robot.backRight.setPower(0);
//       robot.frontLeft.setPower(0);

       robot.gatherServo.setPower(0.4);
       sleep(2200);
       robot.gatherServo.setPower(0);

       sleep(2200);

        frontLeftPosition += 270;
        frontRightPosition -= 270;
        backLeftPosition += 270;
        backRightPosition -= 270;
        //turing back for the wall


        robot.frontLeft.setTargetPosition(frontLeftPosition);
        robot.frontRight.setTargetPosition(frontRightPosition);
        robot.backLeft.setTargetPosition(backLeftPosition);
        robot.backRight.setTargetPosition(backRightPosition);


        robot.frontLeft.setMode(DcMotor.RunMode.RUN_TO_POSITION);
        robot.frontRight.setMode(DcMotor.RunMode.RUN_TO_POSITION);
        robot.backLeft.setMode(DcMotor.RunMode.RUN_TO_POSITION);
        robot.backRight.setMode(DcMotor.RunMode.RUN_TO_POSITION);

        sleep(1200);

        robot.liftLeft.setTargetPosition(0);
        robot.liftRight.setTargetPosition(0);

        frontLeftPosition += 885;
        frontRightPosition += 885;
        backLeftPosition += 885;
        backRightPosition += 885;
        //going back


        robot.frontLeft.setTargetPosition(frontLeftPosition);
        robot.frontRight.setTargetPosition(frontRightPosition);
        robot.backLeft.setTargetPosition(backLeftPosition);
        robot.backRight.setTargetPosition(backRightPosition);


        robot.frontLeft.setMode(DcMotor.RunMode.RUN_TO_POSITION);
        robot.frontRight.setMode(DcMotor.RunMode.RUN_TO_POSITION);
        robot.backLeft.setMode(DcMotor.RunMode.RUN_TO_POSITION);
        robot.backRight.setMode(DcMotor.RunMode.RUN_TO_POSITION);

        sleep(2000);*/

        /////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
        //red duck spin with encoders
        frontLeftPosition -= 120;
        frontRightPosition += 120;
        backLeftPosition += 120;
        backRightPosition -= 120;
        //going out from wall

        robot.frontLeft.setTargetPosition(frontLeftPosition);
        robot.frontRight.setTargetPosition(frontRightPosition);
        robot.backLeft.setTargetPosition(backLeftPosition);
        robot.backRight.setTargetPosition(backRightPosition);

        robot.frontLeft.setMode(DcMotor.RunMode.RUN_TO_POSITION);
        robot.frontRight.setMode(DcMotor.RunMode.RUN_TO_POSITION);
        robot.backLeft.setMode(DcMotor.RunMode.RUN_TO_POSITION);
        robot.backRight.setMode(DcMotor.RunMode.RUN_TO_POSITION);

        robot.frontLeft.setPower(0.30);
        robot.frontRight.setPower(0.30);
        robot.backLeft.setPower(0.30);
        robot.backRight.setPower(0.30);

        sleep(1200);

        frontLeftPosition -= 600;
        frontRightPosition -= 600;
        backLeftPosition -= 600;
        backRightPosition -= 600;
        //moving toward the spinner

        robot.frontLeft.setTargetPosition(frontLeftPosition);
        robot.frontRight.setTargetPosition(frontRightPosition);
        robot.backLeft.setTargetPosition(backLeftPosition);
        robot.backRight.setTargetPosition(backRightPosition);

        robot.frontLeft.setMode(DcMotor.RunMode.RUN_TO_POSITION);
        robot.frontRight.setMode(DcMotor.RunMode.RUN_TO_POSITION);
        robot.backLeft.setMode(DcMotor.RunMode.RUN_TO_POSITION);
        robot.backRight.setMode(DcMotor.RunMode.RUN_TO_POSITION);

        robot.frontLeft.setPower(0.25);
        robot.frontRight.setPower(0.25);
        robot.backLeft.setPower(0.25);
        robot.backRight.setPower(0.25);

        sleep(1500);

        robot.spinServo.setPower(0.4);
        sleep(2600);
        robot.spinServo.setPower(0);

        frontLeftPosition -= 730;
        frontRightPosition += 730;
        backLeftPosition += 730;
        backRightPosition -= 730;
        //moving sideways to the box

        robot.frontLeft.setTargetPosition(frontLeftPosition);
        robot.frontRight.setTargetPosition(frontRightPosition);
        robot.backLeft.setTargetPosition(backLeftPosition);
        robot.backRight.setTargetPosition(backRightPosition);

        robot.frontLeft.setMode(DcMotor.RunMode.RUN_TO_POSITION);
        robot.frontRight.setMode(DcMotor.RunMode.RUN_TO_POSITION);
        robot.backLeft.setMode(DcMotor.RunMode.RUN_TO_POSITION);
        robot.backRight.setMode(DcMotor.RunMode.RUN_TO_POSITION);

        robot.frontLeft.setPower(0.30);
        robot.frontRight.setPower(0.30);
        robot.backLeft.setPower(0.30);
        robot.backRight.setPower(0.30);

        sleep(1200);


        frontLeftPosition += 420;
        frontRightPosition += 420;
        backLeftPosition += 420;
        backRightPosition += 420;
        //going backwards toward the shipping hub

        robot.frontLeft.setTargetPosition(frontLeftPosition);
        robot.frontRight.setTargetPosition(frontRightPosition);
        robot.backLeft.setTargetPosition(backLeftPosition);
        robot.backRight.setTargetPosition(backRightPosition);

        robot.frontLeft.setMode(DcMotor.RunMode.RUN_TO_POSITION);
        robot.frontRight.setMode(DcMotor.RunMode.RUN_TO_POSITION);
        robot.backLeft.setMode(DcMotor.RunMode.RUN_TO_POSITION);
        robot.backRight.setMode(DcMotor.RunMode.RUN_TO_POSITION);

        robot.frontLeft.setPower(0.30);
        robot.frontRight.setPower(0.30);
        robot.backLeft.setPower(0.30);
        robot.backRight.setPower(0.30);

        sleep(1200);

        frontLeftPosition -= 1150;
        frontRightPosition += 1150;
        backLeftPosition -= 1150;
        backRightPosition += 1150;
        //spinning toward the shipping

        robot.frontLeft.setTargetPosition(frontLeftPosition);
        robot.frontRight.setTargetPosition(frontRightPosition);
        robot.backLeft.setTargetPosition(backLeftPosition);
        robot.backRight.setTargetPosition(backRightPosition);

        robot.frontLeft.setMode(DcMotor.RunMode.RUN_TO_POSITION);
        robot.frontRight.setMode(DcMotor.RunMode.RUN_TO_POSITION);
        robot.backLeft.setMode(DcMotor.RunMode.RUN_TO_POSITION);
        robot.backRight.setMode(DcMotor.RunMode.RUN_TO_POSITION);

        robot.frontLeft.setPower(0.30);
        robot.frontRight.setPower(0.30);
        robot.backLeft.setPower(0.30);
        robot.backRight.setPower(0.30);

        sleep(1200);

        robot.liftLeft.setTargetPosition(-290);
        robot.liftRight.setTargetPosition(-290);
        robot.liftLeft.setMode(DcMotor.RunMode.RUN_TO_POSITION);
        robot.liftRight.setMode(DcMotor.RunMode.RUN_TO_POSITION);


        robot.liftLeft.setPower(0.20);
        robot.liftRight.setPower(0.20);

        sleep(2020);


       robot.liftLeft.setTargetPosition(-290);
       robot.liftRight.setTargetPosition(-290);
       robot.liftLeft.setMode(DcMotor.RunMode.RUN_TO_POSITION);
       robot.liftRight.setMode(DcMotor.RunMode.RUN_TO_POSITION);


       robot.liftLeft.setPower(0.20);
       robot.liftRight.setPower(0.20);

       sleep(2020);

       frontLeftPosition -= 430;
       frontRightPosition -= 430;
       backLeftPosition -= 430;
       backRightPosition -= 430;
       //going foward toward the shipping hub

       robot.frontLeft.setTargetPosition(frontLeftPosition);
       robot.frontRight.setTargetPosition(frontRightPosition);
       robot.backLeft.setTargetPosition(backLeftPosition);
       robot.backRight.setTargetPosition(backRightPosition);

       robot.frontLeft.setMode(DcMotor.RunMode.RUN_TO_POSITION);
       robot.frontRight.setMode(DcMotor.RunMode.RUN_TO_POSITION);
       robot.backLeft.setMode(DcMotor.RunMode.RUN_TO_POSITION);
       robot.backRight.setMode(DcMotor.RunMode.RUN_TO_POSITION);

       robot.frontLeft.setPower(0.30);
       robot.frontRight.setPower(0.30);
       robot.backLeft.setPower(0.30);
       robot.backRight.setPower(0.30);

       sleep(1200);

       robot.gatherServo.setPower(0.4);
       sleep(2200);
       robot.gatherServo.setPower(0);

       robot.liftLeft.setTargetPosition(0);
       robot.liftRight.setTargetPosition(0);

       frontLeftPosition -= 150;
       frontRightPosition += 150;
       backLeftPosition -= 150;
       backRightPosition += 150;
       //turing to go into red square

       robot.frontLeft.setTargetPosition(frontLeftPosition);
       robot.frontRight.setTargetPosition(frontRightPosition);
       robot.backLeft.setTargetPosition(backLeftPosition);
       robot.backRight.setTargetPosition(backRightPosition);

       robot.frontLeft.setMode(DcMotor.RunMode.RUN_TO_POSITION);
       robot.frontRight.setMode(DcMotor.RunMode.RUN_TO_POSITION);
       robot.backLeft.setMode(DcMotor.RunMode.RUN_TO_POSITION);
       robot.backRight.setMode(DcMotor.RunMode.RUN_TO_POSITION);

       robot.frontLeft.setPower(0.30);
       robot.frontRight.setPower(0.30);
       robot.backLeft.setPower(0.30);
       robot.backRight.setPower(0.30);

       sleep(1500);

       frontLeftPosition += 1000;
       frontRightPosition += 1000;
       backLeftPosition += 1000;
       backRightPosition += 1000;
       //going backwards to go into red square

       robot.frontLeft.setTargetPosition(frontLeftPosition);
       robot.frontRight.setTargetPosition(frontRightPosition);
       robot.backLeft.setTargetPosition(backLeftPosition);
       robot.backRight.setTargetPosition(backRightPosition);

       robot.frontLeft.setMode(DcMotor.RunMode.RUN_TO_POSITION);
       robot.frontRight.setMode(DcMotor.RunMode.RUN_TO_POSITION);
       robot.backLeft.setMode(DcMotor.RunMode.RUN_TO_POSITION);
       robot.backRight.setMode(DcMotor.RunMode.RUN_TO_POSITION);

       robot.frontLeft.setPower(0.30);
       robot.frontRight.setPower(0.30);
       robot.backLeft.setPower(0.30);
       robot.backRight.setPower(0.30);

       sleep(2000);






















        /*robot.frontLeft.setPower(-0.30);
        robot.frontRight.setPower(0.30);
        robot.backLeft.setPower(0.30);
        robot.backRight.setPower(-0.30);
        sleep(700);
        robot.frontLeft.setPower(0);
        robot.frontRight.setPower(0);
        robot.backLeft.setPower(0);
        robot.backRight.setPower(0);

        robot.frontLeft.setPower(-0.20);
        robot.frontRight.setPower(-0.20);
        robot.backLeft.setPower(-0.20);
        robot.backRight.setPower(-0.20);
        sleep(2000);
        robot.frontRight.setPower(0);
        robot.backLeft.setPower(0);
        robot.backRight.setPower(0);
        robot.frontLeft.setPower(0);
        sleep(2000);

        robot.spinServo.setPower(0.4);
        sleep(2600);
        robot.spinServo.setPower(0);

        robot.frontLeft.setPower(-0.40);
        robot.frontRight.setPower(0.40);
        robot.backLeft.setPower(0.40);
        robot.backRight.setPower(-0.40);
        sleep(1030);
        robot.frontLeft.setPower(0);
        robot.frontRight.setPower(0);
        robot.backLeft.setPower(0);
        robot.backRight.setPower(0);

        robot.frontLeft.setPower(-0.30);
        robot.frontRight.setPower(-0.30);
        robot.backLeft.setPower(-0.30);
        robot.backRight.setPower(-0.30);
        sleep(250);
        robot.frontLeft.setPower(0);
        robot.frontRight.setPower(0);
        robot.backLeft.setPower(0);
        robot.backRight.setPower(0);

        robot.frontLeft.setPower(0.30);
        robot.frontRight.setPower(0.30);
        robot.backLeft.setPower(0.30);
        robot.backRight.setPower(0.30);
        sleep(300);
        robot.frontLeft.setPower(0);
        robot.frontRight.setPower(0);
        robot.backLeft.setPower(0);
        robot.backRight.setPower(0);

        robot.frontLeft.setPower(0.30);
        robot.frontRight.setPower(0.30);
        robot.backLeft.setPower(0.30);
        robot.backRight.setPower(0.30);
        sleep(300);
        robot.frontLeft.setPower(0);
        robot.frontRight.setPower(0);
        robot.backLeft.setPower(0);
        robot.backRight.setPower(0);

        robot.frontLeft.setPower(0.30);
        robot.frontRight.setPower(0.30);
        robot.backLeft.setPower(0.30);
        robot.backRight.setPower(0.30);
        sleep(300);
        robot.frontLeft.setPower(0);
        robot.frontRight.setPower(0);
        robot.backLeft.setPower(0);
        robot.backRight.setPower(0);

        robot.frontLeft.setPower(-0.30);
        robot.frontRight.setPower(0.30);
        robot.backLeft.setPower(-0.30);
        robot.backRight.setPower(0.30);
        sleep(2000);
        robot.frontLeft.setPower(0);
        robot.frontRight.setPower(0);
        robot.backLeft.setPower(0);
        robot.backRight.setPower(0);


        robot.liftLeft.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        robot.liftRight.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);

        robot.liftLeft.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
        robot.liftRight.setMode(DcMotor.RunMode.RUN_USING_ENCODER);



        if(markerPosition == 3) {
            robot.liftLeft.setTargetPosition(-290);
            robot.liftRight.setTargetPosition(-290);
            robot.liftLeft.setMode(DcMotor.RunMode.RUN_TO_POSITION);
            robot.liftRight.setMode(DcMotor.RunMode.RUN_TO_POSITION);


            robot.liftLeft.setPower(0.20);
            robot.liftRight.setPower(0.20);

            sleep(2020);

            robot.frontLeft.setPower(-0.30);
            robot.frontRight.setPower(-0.30);
            robot.backLeft.setPower(-0.30);
            robot.backRight.setPower(-0.30);
            sleep(350);
            robot.frontLeft.setPower(0);
            robot.frontRight.setPower(0);
            robot.backLeft.setPower(0);
            robot.backRight.setPower(0);

            robot.gatherServo.setPower(-0.4);
            sleep(2200);
            robot.gatherServo.setPower(0);

            robot.liftLeft.setTargetPosition(0);
            robot.liftRight.setTargetPosition(0);

        } else if (markerPosition == 2){

            robot.liftLeft.setTargetPosition(-250);
            robot.liftRight.setTargetPosition(-250);
            robot.liftLeft.setMode(DcMotor.RunMode.RUN_TO_POSITION);
            robot.liftRight.setMode(DcMotor.RunMode.RUN_TO_POSITION);


            robot.frontLeft.setPower(-0.30);
            robot.frontRight.setPower(-0.30);
            robot.backLeft.setPower(-0.30);
            robot.backRight.setPower(-0.30);
            sleep(80);
            robot.frontLeft.setPower(0);
            robot.frontRight.setPower(0);
            robot.backLeft.setPower(0);
            robot.backRight.setPower(0);

            robot.liftLeft.setPower(0.30);
            robot.liftRight.setPower(0.30);

            sleep(2020);

            robot.frontLeft.setPower(-0.30);
            robot.frontRight.setPower(-0.30);
            robot.backLeft.setPower(-0.30);
            robot.backRight.setPower(-0.30);
            sleep(700);
            robot.frontLeft.setPower(0);
            robot.frontRight.setPower(0);
            robot.backLeft.setPower(0);
            robot.backRight.setPower(0);

            robot.gatherServo.setPower(-0.4);
            sleep(2200);
            robot.gatherServo.setPower(0);

            robot.frontLeft.setPower(-0.30);
            robot.frontRight.setPower(0.30);
            robot.backLeft.setPower(-0.30);
            robot.backRight.setPower(0.30);
            sleep(500);
            robot.frontLeft.setPower(0);
            robot.frontRight.setPower(0);
            robot.backLeft.setPower(0);
            robot.backRight.setPower(0);

            robot.liftLeft.setTargetPosition(0);
            robot.liftRight.setTargetPosition(0);

        }else if(markerPosition == 1){
            robot.liftLeft.setTargetPosition(-200);
            robot.liftRight.setTargetPosition(-200);
            robot.liftLeft.setMode(DcMotor.RunMode.RUN_TO_POSITION);
            robot.liftRight.setMode(DcMotor.RunMode.RUN_TO_POSITION);


            robot.frontLeft.setPower(-0.30);
            robot.frontRight.setPower(-0.30);
            robot.backLeft.setPower(-0.30);
            robot.backRight.setPower(-0.30);
            sleep(80);
            robot.frontLeft.setPower(0);
            robot.frontRight.setPower(0);
            robot.backLeft.setPower(0);
            robot.backRight.setPower(0);

            robot.liftLeft.setPower(0.30);
            robot.liftRight.setPower(0.30);

            sleep(2020);

            robot.frontLeft.setPower(-0.30);
            robot.frontRight.setPower(-0.30);
            robot.backLeft.setPower(-0.30);
            robot.backRight.setPower(-0.30);
            sleep(700);
            robot.frontLeft.setPower(0);
            robot.frontRight.setPower(0);
            robot.backLeft.setPower(0);
            robot.backRight.setPower(0);

            robot.gatherServo.setPower(-0.4);
            sleep(2200);
            robot.gatherServo.setPower(0);

            robot.frontLeft.setPower(-0.30);
            robot.frontRight.setPower(0.30);
            robot.backLeft.setPower(-0.30);
            robot.backRight.setPower(0.30);
            sleep(500);
            robot.frontLeft.setPower(0);
            robot.frontRight.setPower(0);
            robot.backLeft.setPower(0);
            robot.backRight.setPower(0);

            robot.liftLeft.setTargetPosition(0);
            robot.liftRight.setTargetPosition(0);

        } else if (markerPosition == 1){
            robot.liftLeft.setTargetPosition(-564);
            robot.liftRight.setTargetPosition(-564);
            robot.liftLeft.setMode(DcMotor.RunMode.RUN_TO_POSITION);
            robot.liftRight.setMode(DcMotor.RunMode.RUN_TO_POSITION);


            robot.frontLeft.setPower(-0.30);
            robot.frontRight.setPower(-0.30);
            robot.backLeft.setPower(-0.30);
            robot.backRight.setPower(-0.30);
            sleep(60);
            robot.frontLeft.setPower(0);
            robot.frontRight.setPower(0);
            robot.backLeft.setPower(0);
            robot.backRight.setPower(0);

            robot.liftLeft.setPower(0.30);
            robot.liftRight.setPower(0.30);

            sleep(2020);

            robot.frontLeft.setPower(-0.30);
            robot.frontRight.setPower(-0.30);
            robot.backLeft.setPower(-0.30);
            robot.backRight.setPower(-0.30);
            sleep(700);
            robot.frontLeft.setPower(0);
            robot.frontRight.setPower(0);
            robot.backLeft.setPower(0);
            robot.backRight.setPower(0);

            robot.gatherServo.setPower(-0.4);
            sleep(2200);
            robot.gatherServo.setPower(0);

            robot.frontLeft.setPower(-0.30);
            robot.frontRight.setPower(0.30);
            robot.backLeft.setPower(-0.30);
            robot.backRight.setPower(0.30);
            sleep(500);
            robot.frontLeft.setPower(0);
            robot.frontRight.setPower(0);
            robot.backLeft.setPower(0);
            robot.backRight.setPower(0);

            robot.liftLeft.setTargetPosition(0);
            robot.liftRight.setTargetPosition(0);
        }

        robot.frontLeft.setPower(-0.30);
        robot.frontRight.setPower(0.30);
        robot.backLeft.setPower(-0.30);
        robot.backRight.setPower(0.30);
        sleep(20);
        robot.frontLeft.setPower(0);
        robot.frontRight.setPower(0);
        robot.backLeft.setPower(0);
        robot.backRight.setPower(0);

        robot.frontLeft.setPower(0.30);
        robot.frontRight.setPower(0.30);
        robot.backLeft.setPower(0.30);
        robot.backRight.setPower(0.30);
        sleep(1100);
        robot.frontLeft.setPower(0);
        robot.frontRight.setPower(0);
        robot.backLeft.setPower(0);
        robot.backRight.setPower(0);

        sleep(300);*/

    }

    //}


        /*private void initVuforia() {
            /*
             * Configure Vuforia by creating a Parameter object, and passing it to the Vuforia engine.
             */
            VuforiaLocalizer.Parameters parameters = new VuforiaLocalizer.Parameters();

            //parameters.vuforiaLicenseKey = VUFORIA_KEY;
            //parameters.cameraName = hardwareMap.get(WebcamName.class, "Webcam 1");
            //parameters.cameraDirection = CameraDirection.BACK;
            //  Instantiate the Vuforia engine
            //vuforia = ClassFactory.getInstance().createVuforia(parameters);

            // Loading trackables is not necessary for the TensorFlow Object Detection engine.
        }

        /**
         * Initialize the TensorFlow Object Detection engine.
         */
        //private void initTfod() {
            //int tfodMonitorViewId = hardwareMap.appContext.getResources().getIdentifier(
                    //"tfodMonitorViewId", "id", hardwareMap.appContext.getPackageName());
            //TFObjectDetector.Parameters tfodParameters = new TFObjectDetector.Parameters(tfodMonitorViewId);
            //tfodParameters.minResultConfidence = 0.75f ;
            //tfodParameters.isModelTensorFlow2 = true;
            //tfodParameters.inputSize = 320;
            //tfod = ClassFactory.getInstance().createTFObjectDetector(tfodParameters, vuforia);
            //tfod.loadModelFromAsset(TFOD_MODEL_ASSET, LABELS);
        //}
//}



