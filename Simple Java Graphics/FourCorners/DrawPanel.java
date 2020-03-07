package stringart;

import java.awt.Graphics;
import javax.swing.JPanel;


public class DrawPanel extends JPanel
{
	
	public void paintComponent(Graphics g) 
	{
		super.paintComponent(g);
		
		int width1 = 15;
		int height1 = 0;
		
		int width2 = 0;
		int height2 = getHeight();
		
		int width3 = 15;
		int height3 = getHeight();
		
		int width4 = getWidth() - 15;
		int height4 = getHeight();
		
		while(width1 < getWidth())
		{
			// Draw bottom left corner
			g.drawLine(0 , height1, width1, getHeight());
			width1 = width1 + 15;
			height1 = height1 + 15;
			
			// Draw Bottom right corner.
			g.drawLine(width2, getHeight(), getWidth(), height2 );
			width2 = width2 + 15;
			height2 = height2 - 15;
			
			// Draw top left corner
			g.drawLine(0, height3, width3, 0 );
			height3 = height3 - 15;
			width3 = width3 + 15;
			
			// Draw top right corner.
			g.drawLine(getWidth(), height4, width4, 0);
			height4 = height4 - 15;
			width4 = width4 - 15;
			
		}
	}

}
