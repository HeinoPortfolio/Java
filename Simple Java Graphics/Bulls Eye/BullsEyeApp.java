package Bullseye;

import javax.swing.*;

public class BullsEyeApp {

	public static void main(String[] args)
	{
		
		JFrame bullsEyeApp= new JFrame();
		
		bullsEyeApp.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		bullsEyeApp.setTitle("Bulls Eye");
		bullsEyeApp.setSize(300,300);
		bullsEyeApp.setResizable(false);
		
		bullsEyeApp.add(new BullsEye());
		
		
		bullsEyeApp.setVisible(true);
		
		
		System.out.println("ENTERED");

	}

}
