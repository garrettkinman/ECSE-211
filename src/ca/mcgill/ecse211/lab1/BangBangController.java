package ca.mcgill.ecse211.lab1;

import static ca.mcgill.ecse211.lab1.Resources.*;

public class BangBangController extends UltrasonicController {

  public BangBangController() {
    LEFT_MOTOR.setSpeed(MOTOR_HIGH); // Start robot moving forward
    RIGHT_MOTOR.setSpeed(MOTOR_HIGH);
    LEFT_MOTOR.forward();
    RIGHT_MOTOR.forward();
  }

  @Override
  public void processUSData(int distance) {
    filter(distance);

    int leftMotorSpeed = 0;
    int rightMotorSpeed = 0;

    if (distance < BAND_CENTER - BAND_WIDTH) { // Robot is too close to wall
      if (distance < 30) { // Robot is really close to wall
        LEFT_MOTOR.setSpeed(MOTOR_HIGH);
        RIGHT_MOTOR.setSpeed(MOTOR_HIGH);
        LEFT_MOTOR.forward();
        RIGHT_MOTOR.backward(); // Reverse right wheel to turn faster
        return;
      }

      leftMotorSpeed = MOTOR_HIGH;
      rightMotorSpeed = MOTOR_LOW;
    } else if (distance > BAND_CENTER + BAND_WIDTH) { // If too far from wall
      leftMotorSpeed = MOTOR_LOW;
      rightMotorSpeed = MOTOR_HIGH;
    } else { // Within range, so move forward
      leftMotorSpeed = MOTOR_HIGH;
      rightMotorSpeed = MOTOR_HIGH;
    }

    // Move robot forward
    LEFT_MOTOR.setSpeed(leftMotorSpeed);
    RIGHT_MOTOR.setSpeed(rightMotorSpeed);
    LEFT_MOTOR.forward();
    RIGHT_MOTOR.forward();
  }// TODO: process a movement based on the us distance passed in (BANG-BANG style)


  @Override
  public int readUSDistance() {
    return this.distance;
  }
}
