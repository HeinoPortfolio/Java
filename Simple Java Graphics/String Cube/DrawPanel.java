/*  DrawPanel.Java --
 *  Purpose: To draw a string-like cube.
 *  Description:  This file sets up a JPanel that will contain a string 
 *  representation of a cube.  
 *  Requirements:  The lines from opposite corners should connect with those 
 *  from the other side.  
 *  
 *  Note:  This problem was taken from the text by Deitel. Please see read me
 *  located in the repository for more information on this problem.
 * 
 */

package MyGraphics;

import  java.awt.Graphics;
import javax.swing.JPanel;


public class DrawPanel extends JPanel
{
	public void paintComponent (Graphics g) 
	{
		super.paintComponent(g);
		
		int width = 15;
		int height = getHeight() - 15;
		int width2 = 15;
		int height2 = 15;
		
		while (width < getWidth() && height > 0)
		{
			// Draw the top left lines
			g.drawLine(0, 0, width, height);
			
			// Draw the bottom right lines 
			g.drawLine(getWidth(), getHeight(), width, height );
			
			// Draw top right lines
			g.drawLine(getWidth(), 0, width2, height2);
			
			// Draw the bottom left lines
			g.drawLine(0, getWidth(), width2, height2);
			
			// Top left and bottom right increments
			width = width + 15;
			height = height - 15;
			
			// Top right and bottom left increments
			width2 = width2 + 15;
			height2 = height2 + 15;
			
		}
		
		
		 //end while
		
	} // end paintComponent
	
} // end DrawPanel
