package org.usfirst.frc.team3735.robot.commands;

import org.usfirst.frc.team3735.robot.Robot;
import org.usfirst.frc.team3735.robot.util.Setting;

import edu.wpi.first.wpilibj.command.Command;

/**
 *
 */
public class ShooterMotorOn extends Command {
	private Setting speed;
	
    public ShooterMotorOn(double spd, String name) {
        // Use requires() here to declare subsystem dependencies
        // eg. requires(chassis);
    	if(name != null){
        	speed = new Setting("Shooter Motor Speed: " + name, spd);
    	}else{
    		speed = new Setting("NaeNae", spd, false);
    	}
    	requires(Robot.shooter.shooterMotors);
    }

    // Called just before this Command runs the first time
    protected void initialize() {
    	
    }

    // Called repeatedly when this Command is scheduled to run
    protected void execute() {
    	Robot.shooter.setMotorSpeed(speed.getValue());
    }

    // Make this return true when this Command no longer needs to run execute()
    protected boolean isFinished() {
        return false;
    }

    // Called once after isFinished returns true
    protected void end() {
    	Robot.shooter.setMotorSpeed(0);
    }

    // Called when another command which requires one or more of the same
    // subsystems is scheduled to run
    protected void interrupted() {
    	end();
    }
}
