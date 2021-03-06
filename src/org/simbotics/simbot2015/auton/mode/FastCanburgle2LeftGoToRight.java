package org.simbotics.simbot2015.auton.mode;

import org.simbotics.simbot2015.auton.AutonCommand;
import org.simbotics.simbot2015.auton.AutonControl;
import org.simbotics.simbot2015.auton.canburglar.*;
import org.simbotics.simbot2015.auton.drive.*;
import org.simbotics.simbot2015.auton.util.AutonWait;
import org.simbotics.simbot2015.util.Vect;

import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

public class FastCanburgle2LeftGoToRight implements AutonMode {

	@Override
	public AutonCommand[] getMode() {
		AutonBuilder ab = new AutonBuilder();
		
		ab.addCommand(new SetGyroOffset(270));
		
		// first can
		ab.addCommand(new CanburglarSetLatch(true));
		ab.addCommand(new AutonWait((long) SmartDashboard.getNumber("Canburgle Fast Time: ")));
		
		//ab.addCommand(new DriveToPoint(new Vect(0, -96), 270, 1,true)); // pull it forward was 0,-96
		ab.addCommand(new CanburglarWinchIn(AutonControl.Winch_In_Time));
		
		//ab.addCommand(new DriveWait());		// wait until it's pulled the first cans down
		ab.addCommand(new CanburglarWait());
		
		ab.addCommand(new CanburglarArmUp(AutonControl.ARM_Lift_Height));   // lift arm
	//	ab.addCommand(new CanburglarWait());
		
		ab.addCommand(new AutonWait(1000));  // waiting so arm is up a bit/doesn't pull can
		
	
		ab.addCommand(new DriveToPoint(new Vect(AutonControl.Canburgle_XChange, 0), 270, 1));  // back up to edge of totes
		ab.addCommand(new DriveWait());
		
		
		
		
		
		return ab.getAutonList();
	}

}