package org.usfirst.frc.team3735.robot.ois;

import org.usfirst.frc.team3735.robot.util.DriveOI;
import org.usfirst.frc.team3735.robot.util.JoystickPOVButton;
import org.usfirst.frc.team3735.robot.util.JoystickTriggerButton;
import org.usfirst.frc.team3735.robot.util.oi.XboxController;
//import org.usfirst.frc.team3735.robot.commands.DriveTurnToAngleHyperbola;
import org.usfirst.frc.team3735.robot.commands.*;
import org.usfirst.frc.team3735.robot.commands.drive.*;


import org.usfirst.frc.team3735.robot.subsystems.Vision.Pipes;

import edu.wpi.first.wpilibj.Joystick;
import edu.wpi.first.wpilibj.buttons.Button;
import edu.wpi.first.wpilibj.buttons.JoystickButton;
import edu.wpi.first.wpilibj.command.Command;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

public class GTAOI implements DriveOI{

	public XboxController joy;
	public XboxController cojoy;
	

	public GTAOI() {
		joy = new XboxController(0);
		cojoy = new XboxController(1);

		// joystick port mapping
		
		joy.x.whileHeld(new DriveAddSensitiveLeft());
		joy.y.whileHeld(new DriveAddSensitiveRight());

		
		joy.pov0.whenPressed(new ShooterMotorOn(1, "High"));
		joy.pov90.whenPressed(new ShooterMotorOn(.7, "Medium"));
		joy.pov180.whenPressed(new ShooterMotorOn(.4, "Low"));
		joy.pov270.whenPressed(new ShooterMotorOn(0, null));
		joy.lb.whenPressed(new ShooterMotorOn(-.7, null));

		//joy.b.toggleWhenPressed(new ShooterSwitch());
		joy.a.whileHeld(new ShooterLaunch());

		
		
		
	}
	public double getFODMag() {
		//return main.getRightMagnitude();
		return 0;
	}
	
	public double getFODAngle(){
		//return main.getRightAngle();
		return 0;
	}
	
	public boolean isOverriddenByDrive(){
		return Math.abs(getDriveMove()) > .2 || Math.abs(getDriveTurn()) > .2;
	}


	public double getDriveMove() {
		return (joy.getRightTrigger() - joy.getLeftTrigger());
	}

	public double getDriveTurn() {
		return joy.getLeftX();
	}


	public void log() {
//		SmartDashboard.putNumber("right joystick angle", getMainRightAngle());
//		SmartDashboard.putNumber("right joystick magnitude",
//				getMainRightMagnitude());

	}
	@Override
	public double getMainLeftX() {
		// TODO Auto-generated method stub
		return 0;
	}
	@Override
	public double getMainLeftY() {
		// TODO Auto-generated method stub
		return 0;
	}
	@Override
	public double getMainRightX() {
		// TODO Auto-generated method stub
		return 0;
	}
	@Override
	public double getMainRightY() {
		// TODO Auto-generated method stub
		return 0;
	}
	@Override
	public double getMainLeftTrigger() {
		// TODO Auto-generated method stub
		return 0;
	}
	@Override
	public double getMainRightTrigger() {
		// TODO Auto-generated method stub
		return 0;
	}
	@Override
	public double getCoLeftX() {
		// TODO Auto-generated method stub
		return 0;
	}
	@Override
	public double getCoLeftY() {
		// TODO Auto-generated method stub
		return 0;
	}
	@Override
	public double getCoRightX() {
		// TODO Auto-generated method stub
		return 0;
	}
	@Override
	public double getCoRightY() {
		// TODO Auto-generated method stub
		return 0;
	}
	@Override
	public double getCoLeftTrigger() {
		// TODO Auto-generated method stub
		return 0;
	}
	@Override
	public double getCoRightTrigger() {
		// TODO Auto-generated method stub
		return 0;
	}

}
