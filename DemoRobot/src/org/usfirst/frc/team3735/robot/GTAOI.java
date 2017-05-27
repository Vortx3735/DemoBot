package org.usfirst.frc.team3735.robot;

import org.usfirst.frc.team3735.robot.util.DriveOI;
import org.usfirst.frc.team3735.robot.util.JoystickPOVButton;
import org.usfirst.frc.team3735.robot.util.JoystickTriggerButton;
//import org.usfirst.frc.team3735.robot.commands.DriveTurnToAngleHyperbola;
import org.usfirst.frc.team3735.robot.commands.*;
import org.usfirst.frc.team3735.robot.commands.drive.*;
import org.usfirst.frc.team3735.robot.commands.drive.spinnyspin.DriveSpinMove;
import org.usfirst.frc.team3735.robot.commands.drive.turntoangle.DriveTurnToAnglePIDCtrl;
import org.usfirst.frc.team3735.robot.commands.drive.turntoangle.DriveTurnToAnglePIDCtrlVision;

import org.usfirst.frc.team3735.robot.commands.vision.*;


import org.usfirst.frc.team3735.robot.subsystems.Vision.Pipes;

import edu.wpi.first.wpilibj.Joystick;
import edu.wpi.first.wpilibj.buttons.Button;
import edu.wpi.first.wpilibj.buttons.JoystickButton;
import edu.wpi.first.wpilibj.command.Command;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

public class GTAOI {

	Joystick joy;
	Joystick cojoy;

	public GTAOI() {

		// joystick port mapping
		joy = new Joystick(0);
		cojoy = new Joystick(1);

		// Button Mapping for driver joy-stick
		Button a = new JoystickButton(joy, 1);
		Button b = new JoystickButton(joy, 2);
		Button x = new JoystickButton(joy, 3);
		Button y = new JoystickButton(joy, 4);
		Button lb = new JoystickButton(joy, 5);
		Button rb = new JoystickButton(joy, 6);
		Button back = new JoystickButton(joy, 7);
		Button start = new JoystickButton(joy, 8);
		Button ls = new JoystickButton(joy, 9);
		Button rs = new JoystickButton(joy, 10);

		Button pov0 = new JoystickPOVButton(joy, 0);
		Button pov45 = new JoystickPOVButton(joy, 45);
		Button pov90 = new JoystickPOVButton(joy, 90);
		Button pov135 = new JoystickPOVButton(joy, 135);
		Button pov180 = new JoystickPOVButton(joy, 180);
		Button pov225 = new JoystickPOVButton(joy, 225);
		Button pov270 = new JoystickPOVButton(joy, 270);
		Button pov315 = new JoystickPOVButton(joy, 315);

		// Button Mapping for codriver joy-stick
		Button ca = new JoystickButton(cojoy, 1);
		Button cb = new JoystickButton(cojoy, 2);
		Button cx = new JoystickButton(cojoy, 3);
		Button cy = new JoystickButton(cojoy, 4);
		Button clb = new JoystickButton(cojoy, 5);
		Button crb = new JoystickButton(cojoy, 6);
		Button cback = new JoystickButton(cojoy, 7);
		Button cstart = new JoystickButton(cojoy, 8);
		Button cls = new JoystickButton(cojoy, 9);
		Button crs = new JoystickButton(cojoy, 10);
		Button clt = new JoystickTriggerButton(cojoy, false, .5);
		Button crt = new JoystickTriggerButton(cojoy, true, .5);

		Button cpov0 = new JoystickPOVButton(cojoy, 0);
		Button cpov45 = new JoystickPOVButton(cojoy, 45);
		Button cpov90 = new JoystickPOVButton(cojoy, 90);
		Button cpov135 = new JoystickPOVButton(cojoy, 135);
		Button cpov180 = new JoystickPOVButton(cojoy, 180);
		Button cpov225 = new JoystickPOVButton(cojoy, 225);
		Button cpov270 = new JoystickPOVButton(cojoy, 270);
		Button cpov315 = new JoystickPOVButton(cojoy, 315);


		x.whileHeld(new DriveAddSensitiveLeft());
		y.whileHeld(new DriveAddSensitiveRight());

		start.whenPressed(new DriveChangeToGearDirection());
		back.whenPressed(new DriveChangeToBallDirection());
		
		pov0.whenPressed(new ShooterMotorOn(1, "High"));
		pov90.whenPressed(new ShooterMotorOn(.7, "Medium"));
		pov180.whenPressed(new ShooterMotorOn(.4, "Low"));
		pov270.whenPressed(new ShooterMotorOn(0, null));
		lb.whenPressed(new ShooterMotorOn(-.7, null));

		
		a.whileHeld(new ShooterLaunch());
		
		Command disabled = new DisableDrive();
		ls.toggleWhenPressed(disabled);
		a.toggleWhenPressed(disabled);
		
		
		
	}
	
	public boolean isOverriddenByDrive(){
		return Math.abs(getMainLeftX()) > .1 || getMainLeftTrigger() > .1 || getMainRightTrigger() > .1;
	}

	public double getMainLeftX() {
		return joy.getX();
	}

	public double getMainLeftY() {
		return joy.getY() * -1;
	}

	public double getMainRightX() {
		return joy.getRawAxis(4);
	}

	public double getMainRightY() {
		return joy.getRawAxis(5) * -1;
	}

	public double getMainLeftTrigger() {
		return joy.getZ();
	}

	public double getMainRightTrigger() {
		return joy.getThrottle();
	}

	public double getMainRightMagnitude() {
		return Math.hypot(getMainRightX(), getMainRightY());
	}

	// returns the angle of the right main joystick in degrees in the range
	// (-180, 180]
	public double getMainRightAngle() {
		return Math.toDegrees(Math.atan2(getMainRightX(), getMainRightY()));
	}

	public double getDriveMove() {
		return (getMainRightTrigger() - getMainLeftTrigger());
	}

	public double getDriveTurn() {
		return getMainLeftX();
	}

	public double getCoLeftX() {
		return cojoy.getX();
	}

	public double getCoLeftY() {
		return cojoy.getY() * -1;
	}

	public double getCoRightX() {
		return cojoy.getRawAxis(4);
	}

	public double getCoRightY() {
		return cojoy.getRawAxis(5) * -1;
	}

	public double getCoLeftTrigger() {
		return cojoy.getZ();
	}

	public double getCoRightTrigger() {
		return cojoy.getThrottle();
	}

	public void log() {
//		SmartDashboard.putNumber("right joystick angle", getMainRightAngle());
//		SmartDashboard.putNumber("right joystick magnitude",
//				getMainRightMagnitude());

	}

}
