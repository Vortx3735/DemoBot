package org.usfirst.frc.team3735.robot.commands.drive;


import org.usfirst.frc.team3735.robot.Constants;
import org.usfirst.frc.team3735.robot.Robot;
import org.usfirst.frc.team3735.robot.util.Setting;
import org.usfirst.frc.team3735.robot.util.VortxMath;

import com.ctre.CANTalon.TalonControlMode;

import edu.wpi.first.wpilibj.Timer;
import edu.wpi.first.wpilibj.command.Command;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

/**
 *
 */
public class ExpDrive extends Command {
	/************************************/
	/* Constants						*/
	/************************************/	
	//Range is (0,1] , 1 is no filter, .333 or .167, .125 is recommended 

	/************************************/
	/* Variables						*/
	/************************************/	
	
	private double moveSetValue;
	private double turnSetValue;
		
	private double moveMotor;
	private double turnMotor;
	
	private double moveMotorPrev;
	private double turnMotorPrev;
	
	private boolean isJoystickInput;
	
	
	private double fodAngle;
	private double fodMove;
	private double fodTurn;
	
	private static Setting navxCo = new Setting("FOD Navx Coefficient", 2.5);
	private static Setting navxPow = new Setting("FOD Navx Exponent", 1);
	private static Setting fodMoveCo = new Setting("FOD Move Exponent", 3);
	
	private static Setting moveExponent = new Setting("Move Exponent", Constants.Drive.moveExponent);
	private static Setting turnExponent = new Setting("Turn Exponent", Constants.Drive.turnExponent);
	private static Setting scaledMaxMove = new Setting("Scaled Max Move", Constants.Drive.scaledMaxMove);
	private static Setting scaledMaxTurn = new Setting("Scaled Max Turn", Constants.Drive.scaledMaxTurn);
	private static Setting moveReactivity = new Setting("Move Reactivity", Constants.Drive.moveReactivity);
	private static Setting turnReactivity = new Setting("Turn Reactivity", Constants.Drive.turnReactivity);
	
	
	/************************************/
	/* Code								*/
	/************************************/
    public ExpDrive() {
        // Use requires() here to declare subsystem dependencies
    	requires(Robot.drive);
		isJoystickInput = true;
    	Robot.drive.setUpDriveForSpeedControl();
    }
    
    public ExpDrive(double move, double turn){
    	moveSetValue = move;
    	turnSetValue = turn;
    	//System.out.println("Exp Move no Joystick");
    	
    	requires(Robot.drive);
    	isJoystickInput = false;
    	Robot.drive.setUpDriveForSpeedControl();

    }

    // Called just before this Command runs the first time
    protected void initialize() {
    	Robot.drive.setUpDriveForSpeedControl();
    	if(isJoystickInput){
    		moveSetValue			= 0.0;
    		turnSetValue			= 0.0;
    	}
		moveMotor			= 0.0;
		turnMotor			= 0.0;
		moveMotorPrev = 0.0;
		turnMotorPrev = 0.0;

    }

    // Called repeatedly when this Command is scheduled to run
    public void execute() {
		if(isJoystickInput){
			moveSetValue = Robot.oi.getDriveMove();
			turnSetValue = Robot.oi.getDriveTurn();
		}
		

		moveMotor = (moveSetValue-moveMotorPrev)*moveReactivity.getValue() + moveMotorPrev;
		turnMotor = (turnSetValue-turnMotorPrev)*turnReactivity.getValue() + turnMotorPrev;

		moveMotorPrev = moveMotor;
		turnMotorPrev = turnMotor;
		
					
		moveMotor = moveMotor * Math.pow(Math.abs(moveMotor), moveExponent.getValue() - 1);
		turnMotor = turnMotor * Math.pow(Math.abs(turnMotor), turnExponent.getValue() - 1);
		
		moveMotor = moveMotor * scaledMaxMove.getValue();
		turnMotor = turnMotor * scaledMaxTurn.getValue();
		
		turnMotor = turnMotor + Robot.oi.getCoLeftX() * .2;
		

//		SmartDashboard.putNumber("Move Motor", moveMotor);
//		SmartDashboard.putNumber("Turn Motor", turnMotor);
//		moveMotor = VortxMath.limit(moveMotor, -1, 1);
//		turnMotor = VortxMath.limit(turnMotor, -1, 1);


		Robot.drive.arcadeDrive(moveMotor, turnMotor, false);
		
		
		log();
    }

    // Make this return true when this Command no longer needs to run execute()
    public boolean isFinished() {
        return false;
    }

    // Called once after isFinished returns true
    public void end() {
    }

    // Called when another command which requires one or more of the same
    // subsystems is scheduled to run
    public void interrupted() {

    }
    
    private void log(){

    }
}
