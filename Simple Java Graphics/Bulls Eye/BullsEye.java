package Bullseye;

import java.awt.Color;
import java.awt.Graphics;
import javax.swing.*;
import java.util.Random;

public class BullsEye extends JPanel
{
	private int x_pos;				// X position
	private int y_pos;				// y position
	private int height;
	private int width;

	public BullsEye()
	{
		width = 100;
		height = 100;
		y_pos = 75;
		x_pos = 100;
	}
	
	public void paintComponent(Graphics g)
	{
		Random rand = new Random();
		
		
		super.paintComponent(g);
		
		//System.out.println("Entered Paint");
		Color cr1 = new Color(rand.nextInt(255),rand.nextInt(255),rand.nextInt(255));
		Color cr2 = new Color(rand.nextInt(255),rand.nextInt(255),rand.nextInt(255));
		
		for (int i = 0; i < 5; i++)
		{
			
			if(i % 2 == 0)
			{
				g.setColor(cr1);
			}
			else
			{
				g.setColor(cr2);
			}
			
			g.fillOval(x_pos, y_pos, width, height);
			x_pos = x_pos + 10;
			y_pos = y_pos + 10;
			width = width - 20;
			height = height - 20;
		}
	}
}
