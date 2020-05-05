package tictactoegui;

import java.awt.EventQueue;

public class TicTacToeDriver 
{
	
	public static void main(String[] args) 
	{
		EventQueue.invokeLater(new Runnable() {
		public void run() 
		{
			try 
			{
				TicTacToeFrame tttframe = new TicTacToeFrame();
				tttframe.setVisible(true);
			}catch (Exception e) 
			{
				e.printStackTrace();
			}
		}
		});
	}
}
