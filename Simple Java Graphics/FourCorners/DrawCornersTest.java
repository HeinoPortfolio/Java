package stringart;

import javax.swing.JFrame;


public class DrawCornersTest {

	public static void main(String[] args) 
	{
		DrawPanel panel = new DrawPanel();
		
		JFrame application =  new JFrame("Draw Four Corners");
		
		application.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		application.add(panel);
		application.setSize(400, 400);
		application.setVisible(true);

	}

}
