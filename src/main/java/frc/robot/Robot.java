// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot;

import com.revrobotics.CANSparkBase.IdleMode;
import com.revrobotics.CANSparkLowLevel.MotorType;
import com.revrobotics.CANSparkMax;

import edu.wpi.first.wpilibj.Joystick;
import edu.wpi.first.wpilibj.TimedRobot;
import edu.wpi.first.wpilibj.smartdashboard.SendableChooser;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import edu.wpi.first.wpilibj2.command.button.JoystickButton;

/**
 * The VM is configured to automatically run this class, and to call the functions corresponding to
 * each mode, as described in the TimedRobot documentation. If you change the name of this class or
 * the package after creating this project, you must also update the build.gradle file in the
 * project.
 */
public class Robot extends TimedRobot {
  private static final String kDefaultAuto = "Default";
  private static final String kCustomAuto = "My Auto";
  private String m_autoSelected;
  private final SendableChooser<String> m_chooser = new SendableChooser<>();

  //defining motors
  private CANSparkMax r_motor_1 = new CANSparkMax(Constants.right_M1_ID, MotorType.kBrushless); // front motor
  private CANSparkMax r_motor_2 = new CANSparkMax(Constants.right_M2_ID, MotorType.kBrushless); //middle front motor
  private CANSparkMax r_motor_3 = new CANSparkMax(Constants.right_M3_ID, MotorType.kBrushless); //middle back motor
  private CANSparkMax r_motor_4 = new CANSparkMax(Constants.right_M4_ID, MotorType.kBrushless); // back motor

  private CANSparkMax l_motor_1 = new CANSparkMax(Constants.left_M1_ID, MotorType.kBrushless); // front motor
  private CANSparkMax l_motor_2 = new CANSparkMax(Constants.left_M2_ID, MotorType.kBrushless); // middle front motor
  private CANSparkMax l_motor_3 = new CANSparkMax(Constants.left_M3_ID, MotorType.kBrushless); // middle back motor
  private CANSparkMax l_motor_4 = new CANSparkMax(Constants.left_M4_ID, MotorType.kBrushless); // back motor

// pivot motor define
  private CANSparkMax pivot_motor = new CANSparkMax(Constants.pivot_motor_ID, MotorType.kBrushless);



  //joysticks

  private Joystick r_Joystick = new Joystick(1);
  private Joystick l_Joystick = new Joystick(0);

//joystick buttons
private JoystickButton pivot_up= new JoystickButton(r_Joystick, 2);
private JoystickButton pivot_down= new JoystickButton(r_Joystick, 3);
  
  
  /**
   * This function is run when the robot is first started up and should be used for any
   * initialization code.
   */
  @Override
  public void robotInit() {
    m_chooser.setDefaultOption("Default Auto", kDefaultAuto);
    m_chooser.addOption("My Auto", kCustomAuto);
    SmartDashboard.putData("Auto choices", m_chooser);
  
    //setting Idle mode
  r_motor_1.setIdleMode(IdleMode.kBrake);
  r_motor_2.setIdleMode(IdleMode.kBrake);
  r_motor_3.setIdleMode(IdleMode.kBrake);
  r_motor_4.setIdleMode(IdleMode.kBrake);


  l_motor_1.setIdleMode(IdleMode.kBrake);
  l_motor_2.setIdleMode(IdleMode.kBrake);
  l_motor_3.setIdleMode(IdleMode.kBrake);
  l_motor_4.setIdleMode(IdleMode.kBrake);
 

  pivot_motor.setIdleMode(IdleMode.kBrake);
//invert motors
//r_motor_1.setInverted(true);
//r_motor_2.setInverted(true);
//r_motor_3.setInverted(true);
//r_motor_4.setInverted(true);

//l_motor_1.setInverted(true);
//l_motor_2.setInverted(true);
//l_motor_3.setInverted(true);
//l_motor_4.setInverted(true);

//inverting  pivot motor
//pivot_motor.setInverted(true);

//back right motor follow front right motor
r_motor_2.follow(r_motor_1);
r_motor_3.follow(r_motor_1);
r_motor_4.follow(r_motor_1);
//make back left follow front left motor
l_motor_2.follow(l_motor_1);
l_motor_3.follow(l_motor_1);
l_motor_4.follow(l_motor_1);
}

  /**
   * This function is called every 20 ms, no matter the mode. Use this for items like diagnostics
   * that you want ran during disabled, autonomous, teleoperated and test.
   *
   * <p>This runs after the mode specific periodic functions, but before LiveWindow and
   * SmartDashboard integrated updating.
   */
  @Override
  public void robotPeriodic() {}

  /**
   * This autonomous (along with the chooser code above) shows how to select between different
   * autonomous modes using the dashboard. The sendable chooser code works with the Java
   * SmartDashboard. If you prefer the LabVIEW Dashboard, remove all of the chooser code and
   * uncomment the getString line to get the auto name from the text box below the Gyro
   *
   * <p>You can add additional auto modes by adding additional comparisons to the switch structure
   * below with additional strings. If using the SendableChooser make sure to add them to the
   * chooser code above as well.
   */
  @Override
  public void autonomousInit() {
    m_autoSelected = m_chooser.getSelected();
    // m_autoSelected = SmartDashboard.getString("Auto Selector", kDefaultAuto);
    System.out.println("Auto selected: " + m_autoSelected);
  }

  /** This function is called periodically during autonomous. */
  @Override
  public void autonomousPeriodic() {
    switch (m_autoSelected) {
      case kCustomAuto:
        // Put custom auto code here
        break;
      case kDefaultAuto:
      default:
        // Put default auto code here
        break;
    }
  }

  /** This function is called once when teleop is enabled. */
  @Override
  public void teleopInit() {}

  /** This function is called periodically during operator control. */
  @Override
  public void teleopPeriodic() {
    

   // Arcade drive
  r_motor_1.set(r_Joystick.getY() - r_Joystick.getX());  // Right motor forward/backward and turning
  l_motor_1.set(r_Joystick.getY() + r_Joystick.getX());  // Left motor forward/backward and turning


  
  

  if (pivot_up.getAsBoolean()) {
    pivot_motor.set(Constants.pivot_speed);
  }
  else if (pivot_down.getAsBoolean()) {
    pivot_motor.set(-Constants.pivot_speed);
  }
  else {
    pivot_motor.set(0);
  }
}

  /** This function is called once when the robot is disabled. */
  @Override
  public void disabledInit() {}

  /** This function is called periodically when disabled. */
  @Override
  public void disabledPeriodic() {}

  /** This function is called once when test mode is enabled. */
  @Override
  public void testInit() {}

  /** This function is called periodically during test mode. */
  @Override
  public void testPeriodic() {}

  /** This function is called once when the robot is first started up. */
  @Override
  public void simulationInit() {}

  /** This function is called periodically whilst in simulation. */
  @Override
  public void simulationPeriodic() {}
}
