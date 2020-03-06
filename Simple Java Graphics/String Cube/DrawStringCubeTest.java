/*  DrawStringCubeTest.Java --
 *  Purpose: To draw frame with string panel
 *  Description:  This file sets up a JFrame that will contain a JPanel with 
 *  string representation of a cube.  
 *  Requirements:  None  
 *  
 *  Note:  This problem was taken from the text by Deitel. Please see read me
 *  located in the repository for more information on this problem.
 * 
 */

package MyGraphics;

import javax.swing.JFrame;


public class DrawStringCubeTest {
	
	public static void main(String args[])
	{
		DrawPanel panel = new DrawPanel();
		
		JFrame application = new JFrame("String Cube");
		
		application.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		application.add(panel);
		application.setSize(600, 600);
		application.setLocation(300, 100);
		application.setVisible(true);
		
		
	}// end main

} // end DrawPanelTest
