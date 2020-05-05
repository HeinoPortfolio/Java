package tictactoegui;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.Toolkit;

import javax.swing.JFrame;
import javax.swing.JPanel;

public class TicTacToeFrame extends JFrame 
{

	private JPanel contentPane;
	private TicTacToePanel buttonPanel;

	public TicTacToeFrame() 
	{
		super("Tic Tac Toe Game");
		Dimension dim = Toolkit.getDefaultToolkit().getScreenSize();
		
		getContentPane().setLayout(new BorderLayout());
		buttonPanel = new TicTacToePanel(this);
		
		// add the  components
		this.getContentPane().add(buttonPanel, BorderLayout.CENTER);
		this.setLocation(dim.width/2 - 150 , dim.height/4);
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.setSize(500,500);
		this.setVisible(true);
	}

}
