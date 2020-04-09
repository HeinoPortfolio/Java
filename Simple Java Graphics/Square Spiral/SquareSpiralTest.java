/*SquareSpiralTest.java -
 * 
 *  Author: Heino
 *  
 *  Purpose:	To test the Square Spiral class.  This is a class that will
 *  			draw a square spiral.
 */ 

import javax.swing.JFrame;

public class SquareSpiralTest 
{
	
	
		public static void main(String[] args) 
		{
			SquareSpiral panel = new SquareSpiral();
			
			JFrame application =  new JFrame("Draw Square Spiral");
			
			application.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
			
			application.add(panel);
			application.setSize(300, 300);
			application.setVisible(true);
			application.setResizable(false);

		}
}
