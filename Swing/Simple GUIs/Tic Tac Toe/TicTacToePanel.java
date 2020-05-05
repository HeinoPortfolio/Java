package tictactoegui;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.SystemColor;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.border.Border;

public class TicTacToePanel extends JPanel implements ActionListener
{

	private JButton[] xoroBtns;
	private JButton resetBtn;
	private JButton undoBtn;
	protected final String[] btnLabels = {"1", "2", "3", "4", "5", "6","7", "8", "9"};
	private int prevMove = -1;
	private TicTacToeClass ttc = new TicTacToeClass();
	private TicTacToeFrame ttf;
	
	public TicTacToePanel(TicTacToeFrame ttf) 
	{
		this.createTicTacToeBaord();
		this.ttf = ttf;
	}

	@Override
	public void actionPerformed(ActionEvent e) 
	{
		String buttonNum = ((JButton)e.getSource()).getText();
		
		int btnIndex = 0;
		
		if(buttonNum == "Reset")
		{
			// Reset the previous move. 
			this.prevMove = -1;				
			
			// Reset the board.
			this.resetBoard();
		}
		else if(buttonNum == "Undo")
		{	
			if(prevMove != -1)
				undoMove(prevMove);
		}	
		
		else if(buttonNum != "X" && buttonNum != "O")
		{
			// Convert to num.
			btnIndex = Integer.parseInt(buttonNum);
			
			// Record the previous move 
 			prevMove = btnIndex;
 			
 			// Update the button
 			xoroBtns[btnIndex - 1].setText(ttc.getXorO());
			xoroBtns[btnIndex - 1].setForeground(Color.BLUE);
			 
			// Update the board Array
			ttc.setMove((btnIndex - 1), ttc.getXorO());
			
			//update the turn.
			ttc.updateTurn();
			
			//Check for winner
			if(ttc.checkForWin() != true)
				//update player
				ttc.changeXorO();
			else
			{
				String message = ttc.getXorO() + " has won this game!!!";
				
				// Lock the board. 
				this.lockBoard();
				
				// Show winning message to the user.
				JOptionPane.showMessageDialog(this, message, "Winner!", 
						JOptionPane.PLAIN_MESSAGE);
				
				// Ask player if they want to play a game.
				int answer = JOptionPane.showConfirmDialog(null, "Would You like to play again?  ", "PLay Again?"
						,JOptionPane.YES_NO_OPTION);
				if(answer == JOptionPane.YES_OPTION)
				{
					this.resetBoard();
				}
			}
		}	
	}
	
	public void createTicTacToeBaord()
	{
		
		xoroBtns = new JButton[btnLabels.length];
		this.setBackground(Color.lightGray);
		this.setLayout(new GridBagLayout());
		GridBagConstraints gc = new GridBagConstraints();
		
		gc.weightx = 1;   // how much space the component takes up
		gc.weighty = 0.1;
		
		gc.gridx = 0;
		gc.gridy = 0;
		
		//gc.fill = GridBagConstraints.BOTH;  					// REMOVE
		gc.anchor = GridBagConstraints.CENTER;
		gc.insets = new Insets(0,0,0,0);
		
		int llbIndex = 1;
		for (int nxt = 0; nxt < xoroBtns.length; nxt++)
		{
			// Create the buttons
			xoroBtns[nxt] = new JButton(btnLabels[nxt]);
			
			// Set Font size
			xoroBtns[nxt].setFont(new Font("Arial", Font.BOLD, 44));
			
			// Add the action listener
			xoroBtns[nxt].addActionListener(this);
			
			//add the components to the panel.
			xoroBtns[nxt].setPreferredSize(new Dimension(100,100));
			
			if((llbIndex % 3) != 0)
			{
				this.add(xoroBtns[nxt], gc);
				gc.gridx = gc.gridx + 1;
			}
			else if(llbIndex % 3 == 0) 
			{
				this.add(xoroBtns[nxt], gc);
				gc.gridx = 0;
				gc.gridy = gc.gridy + 1;		
			}
			llbIndex++;
		}// end for.
	
		//Add the reset button and undo.
		gc.gridx = gc.gridx + 1;
		resetBtn = new JButton("Reset");
		resetBtn.setToolTipText("Reset the board to initial state.");
		resetBtn.setPreferredSize(new Dimension(100,50));
		resetBtn.addActionListener(this);
		this.add(resetBtn, gc);
		
		gc.gridx = gc.gridx + 1;
		undoBtn = new JButton("Undo");
		undoBtn.setToolTipText("Undo the last move.");
		undoBtn.setPreferredSize(new Dimension(100,50));
		undoBtn.addActionListener(this);
		this.add(undoBtn, gc);
		
		Border innerBorder = BorderFactory.createTitledBorder("Tic Tac Toe Board");
		Border outerBorder = BorderFactory.createEmptyBorder(5,5,5,5);
		this.setBorder(BorderFactory.createCompoundBorder(outerBorder, innerBorder));	
	}
	
	public void resetBoard()
	{
		for (int nxt = 0; nxt < xoroBtns.length; nxt++)
		{
			xoroBtns[nxt].setText(btnLabels[nxt]); 
			xoroBtns[nxt].setForeground(Color.BLACK);
			xoroBtns[nxt].setEnabled(true);
		}
		ttc.resetBoardArray();
	}
	
	public void lockBoard()
	{
		for (int nxt = 0; nxt < xoroBtns.length; nxt++)
		{
			xoroBtns[nxt].setEnabled(false);; 
		}
	}
	
	public void undoMove(int index)
	{
		xoroBtns[index - 1].setText(btnLabels[index - 1]); 
		xoroBtns[index - 1].setForeground(Color.BLACK);
		ttc.undoMove();
	}

}
