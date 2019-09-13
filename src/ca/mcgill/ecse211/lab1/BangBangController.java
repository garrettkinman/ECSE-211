package ca.mcgill.ecse211.lab1;

import static ca.mcgill.ecse211.lab1.Resources.*;

public class BangBangController extends UltrasonicController {

  public static int TOO_CLOSE = 50;

  public BangBangController() {
    LEFT_MOTOR.setSpeed(MOTOR_HIGH); // Start robot moving forward
    RIGHT_MOTOR.setSpeed(MOTOR_HIGH);
    LEFT_MOTOR.forward();
    RIGHT_MOTOR.forward();
  }

  @Override
  public void processUSData(int distance) {
    /**
     * 1. If robot is within range, just keep both motors fast.
     * 2. If robot is too close, make left motor faster and right motor slower to turn right.
     *    If robot is really too close, set both motor speed to fast and move backwards.
     * 3. If robot is too far from the wall, right motor faster and left slower to turn to left.
     * 
     */

    // Check to see if magnitude is within bounds
    filter(distance);

    // Initialize speed variables for both motors
    int leftMotorSpeed = 0;
    int rightMotorSpeed = 0;

    // 1. Robot getting too close to wall
    if (distance <= BAND_WIDTH) {
      leftMotorSpeed = MOTOR_HIGH;
      rightMotorSpeed = MOTOR_HIGH;
    }
    else if (distance < BAND_CENTER - BAND_WIDTH) {
      if (distance < TOO_CLOSE) {
        LEFT_MOTOR.setSpeed(MOTOR_HIGH);
        RIGHT_MOTOR.setSpeed(MOTOR_HIGH);
        LEFT_MOTOR.forward();
        RIGHT_MOTOR.backward();
        return;
      }
      leftMotorSpeed = MOTOR_HIGH;
      rightMotorSpeed = MOTOR_LOW;
    }
    else {
      leftMotorSpeed = MOTOR_LOW;
      rightMotorSpeed = MOTOR_HIGH;
    }

    // Set speed of motors
    LEFT_MOTOR.setSpeed(leftMotorSpeed);
    RIGHT_MOTOR.setSpeed(rightMotorSpeed);
    // Make robot move forward
    LEFT_MOTOR.forward();
    RIGHT_MOTOR.forward();
  }


  @Override
  public int readUSDistance() {
    return this.distance;
  }
}
