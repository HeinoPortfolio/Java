import java.awt.Graphics;
import javax.swing.JPanel;


public class ConcentricCircles extends JPanel
{
	private int x_pos;				// X position
	private int y_pos;				// y position
	private int height;
	private int width;
	
	// Constructor
	public ConcentricCircles()
	{
		x_pos = 120 + 15;
		y_pos = 120 + 5;
		width = 10;
		height = 10;
	}
	
	public ConcentricCircles(int ht, int wth)
	{
		width = 10;
		height = 10;
		y_pos = (ht / 2) - 25;
		x_pos = (wth / 2) - 15;
	}
	
	
	public void paintComponent(Graphics g) 
	{
		super.paintComponent(g);
		
		for(int i = 0; i < 12; i++)
		{
			g.drawOval(x_pos, y_pos, width, height);
			x_pos = x_pos - 10;
			y_pos = y_pos - 10;
			width = width + 20;
			height = height + 20;
		}
	}
}
