package org.usfirst.frc.team3735.robot.subsystems;

import org.usfirst.frc.team3735.robot.RobotMap;
import org.usfirst.frc.team3735.robot.commands.ShooterJoystick;

import com.ctre.CANTalon;

import edu.wpi.first.wpilibj.Solenoid;
import edu.wpi.first.wpilibj.command.Subsystem;

/**
 *
 */
public class Shooter extends Subsystem {

    // Put methods for controlling this subsystem
    // here. Call these from Commands.
	private CANTalon leftMotor;
	private CANTalon rightMotor;
	private CANTalon angler;
	
	private Solenoid sol;
	
	public Subsystem shooterMotors = new Lock();
	public Subsystem angleControl = new Lock();
	
	class Lock extends Subsystem{
		@Override
		protected void initDefaultCommand() {
			// TODO Auto-generated method stub
			
		}
		
	}


	public Shooter(){
		leftMotor = new CANTalon(RobotMap.Shooter.leftMotor);
		leftMotor.setInverted(RobotMap.Shooter.leftMotorInverted);
		
		rightMotor = new CANTalon(RobotMap.Shooter.rightMotor);
		rightMotor.setInverted(RobotMap.Shooter.rightMotorInverted);
		
		angler = new CANTalon(RobotMap.Shooter.angler);
		angler.setInverted(RobotMap.Shooter.anglerInverted);
		sol = new Solenoid(RobotMap.Shooter.solenoid);
		angler.enableBrakeMode(true);

	}

    public void initDefaultCommand() {
        setDefaultCommand(new ShooterJoystick());

    }

	public void setAngleMotor(double speed) {
		angler.set(speed);
	}

	public void setMotorSpeed(double value) {
		leftMotor.set(value);
		rightMotor.set(value);

	}
	
	public void setSolenoid(boolean b){
		sol.set(b);
	}
}
	

