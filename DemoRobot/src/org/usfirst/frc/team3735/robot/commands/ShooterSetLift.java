package org.usfirst.frc.team3735.robot.commands;

import org.usfirst.frc.team3735.robot.Robot;

import edu.wpi.first.wpilibj.command.Command;

/**
 *
 */
public class ShooterSetLift extends Command {
	boolean up;
    public ShooterSetLift(boolean up) {
        // Use requires() here to declare subsystem dependencies
        // eg. requires(chassis);
    	this.up = up;
    }

    // Called just before this Command runs the first time
    protected void initialize() {
    	Robot.shooter.setLiftUp(up);
    }

    // Called repeatedly when this Command is scheduled to run
    protected void execute() {
    }

    // Make this return true when this Command no longer needs to run execute()
    protected boolean isFinished() {
        return false;
    }

    // Called once after isFinished returns true
    protected void end() {
    	Robot.shooter.setLiftUp(!up);
    }

    // Called when another command which requires one or more of the same
    // subsystems is scheduled to run
    protected void interrupted() {
    	end();
    }
}
