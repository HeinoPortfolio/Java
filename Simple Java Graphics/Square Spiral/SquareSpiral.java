/*SquareSpiral.java -
 * 
 *  Author: Heino
 *  
 *  Purpose:	Square Spiral class.  This is a class that will
 *  			draw a square spiral. It will start at the approximately the 
 *  			center of the panel and draw out from there.
 */ 
import java.awt.Graphics;
import javax.swing.JPanel;

public class SquareSpiral extends JPanel
{
	int mult = 1;
	int x_pos = 150;
	int y_pos = 150;
	int x2;
	int y2;
	
	public void paintComponent(Graphics g) 
	{
		super.paintComponent(g);
		
		for(int i = 0; i < 30; i++)
		{
			if(mult % 2 != 0)
			{
				// Down Segment.***********************
				x2 = x_pos;
				y2 = y_pos + (mult * 10);
				g.drawLine(x_pos, y_pos, x2, y2);
			
				// Left Segment.***********************
				x2 = x_pos - (mult * 10);
				g.drawLine(x_pos, y2, x2, y2);
				x_pos = x2;
				y_pos = y2;
				mult++; 
				
			} // end if.
			else
			{
				// Up Segment. **************************
				y2= y_pos - (mult * 10);
				g.drawLine(x_pos, y_pos, x_pos, y2);
			
				// Right Segment.******************************
				x2 = x_pos + (mult * 10);
				g.drawLine(x_pos, y2, x2, y2);
				x_pos = x2;
				y_pos = y2;
				mult++;
				
			} // end else
			
		} // for
		
	} // end paint
	
} // end class.

	
