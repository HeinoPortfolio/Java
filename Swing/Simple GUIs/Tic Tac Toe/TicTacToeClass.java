package tictactoegui;

import java.util.Arrays;

public class TicTacToeClass 
{
		private String[] boardArray;				// Holds the moves for the game.
		private String prevmove ;					// Holds the previous move X or O
		private int undoMove = -1;					// THe position of the last move.
		private int turn = 0;						// The turn	
		private String xoro = "X";					// Player either X or O
		
		// Constructor.--------------------------------------
		/* Constructor of the class.
		 * Receives:	Nothing
		 * Returns:		Nothing
		 * Post:		The class has been instantiated and all variables have 
		 * 				been correctly initialized.	  
		 * *******************************************************************/
		public TicTacToeClass() 
		{
			this.boardArray = new String[] {"0", "1","2", "3","4","5","6","7","8"};
			this.prevmove = "X";
			this.undoMove = -1;
		}
		
		
		/* ********************************************************************
		 * Purpose:  	Swaps the player from X to O and vice versa
		 * Receives:	Nothing
		 * Returns:		Nothing
		 * Post:		The player has been swapped
		 ******************************************************************* */
		public void changeXorO()
		{
			if(this.xoro == "X")
				this.xoro = "O";
			else
				this.xoro = "X";
		}
		
		/* ********************************************************************
		 * Purpose:  	Checks to see if there is a winner.  
		 * Receives:	Nothing
		 * Returns:		boolean - true or false otherwise.
		 * Post:		the outcome has been returned true if the there is a 
		 * 				winner, false otherwise.
		 ******************************************************************* */
		public boolean checkForWin()
		{
			boolean gameover = false;
			
			if (turn > 2)
			{
				//Check the winning paths.*******************************************
				// Check the rows for the win.*****************************************
				if(boardArray[0].equals(boardArray[1]) && boardArray[1].equals(boardArray[2]))
					gameover = true;
				else if(boardArray[3].equals(boardArray[4]) && boardArray[4].equals(boardArray[5]))
					gameover = true;
				else if(boardArray[6].equals(boardArray[7]) && boardArray[7].equals(boardArray[8]))
					gameover = true;
				//Check the columns for a win.*********************************************
				else if(boardArray[0].equals(boardArray[3]) && boardArray[3].equals(boardArray[6]))
					gameover = true;
				else if(boardArray[1].equals(boardArray[4]) && boardArray[4].equals(boardArray[7]))
					gameover = true;
				else if(boardArray[2].equals(boardArray[5]) && boardArray[5].equals(boardArray[8]))
					gameover = true;
				// Check the diagonal for a win.******************************************
				else if(boardArray[0].equals(boardArray[4]) && boardArray[4].equals(boardArray[8]))
					gameover = true;
				else if(boardArray[2].equals(boardArray[4]) && boardArray[4].equals(boardArray[6]))
					gameover = true;
			}
			return gameover;
		}
		
		/* ********************************************************************
		 * Purpose:  	Sets the player from X to O and vice versa
		 * Receives:	Nothing
		 * Returns:		Nothing
		 * Post:		The player has been changed.
		 ******************************************************************* */
		public void setPlayerTurn()
		{
			if(this.xoro == "X")
				this.xoro = "O";
			else
				this.xoro = "X";
		}
		
		/* ********************************************************************
		 * Purpose:  	Returns the player from X to O and vice versa
		 * Receives:	Nothing
		 * Returns:		String - player
		 * Post:		The player has been returned an it is either and X or 
		 * 				O.
		 ******************************************************************* */
		public String getXorO()
		{
			return this.xoro;
		}
		
		/* ********************************************************************
		 * Purpose:  	Sets the player move and updates the Board Array
		 * Receives:	int position - the position to be updated
		 * 				String move - the move either X or O.
		 * Returns:		Nothing
		 * Post:		The move has been recorded in the board array
		 ******************************************************************* */
		public void setMove(int position, String move)
		{
			
			this.boardArray[position] = move;
			this.undoMove = position;
			this.prevmove = move;
		}
		
		/* ********************************************************************
		 * Purpose:  	Prints the board array.
		 * Receives:	Nothing
		 * Returns:		Nothing
		 * Post:		The board array has been printed to the screen.
		 ******************************************************************* */
		public void printBoardArray()
		{
			for( String array : boardArray)
			{
				System.err.println(array);
			}
		}
		
		/* ********************************************************************
		 * Purpose:  	Resets the board array and associated variables have 
		 * 				beens set to its initial state
		 * Receives:	Nothing
		 * Returns:		Nothing
		 * Post:		The board array has been reset along with all 
		 * 				associated variables. 
		 ******************************************************************* */
		public void resetBoardArray()
		{
			// Reset board.
			for(int index = 0; index < 9; index++)
			{
				this.boardArray[index] = String.valueOf(index); 
			}	
			
			// Reset turn
			this.turn = 0;
			
			// Reset the previous move
			this.xoro = "X";
		}
		
		/* ********************************************************************
		 * Purpose:  	Sets the turn count
		 * Receives:	int turn
		 * Returns:		Nothing
		 * Post:		The turn has been set.
		 * Note:		Was not used in the application but was used to 
		 * 				test the class.
		 ******************************************************************* */
		public void setTurn(int turn)
		{
			this.turn = turn;
		}
		
		/* ********************************************************************
		 * Purpose:  	Updates the number of turns.
		 * Receives:	Nothing
		 * Returns:		Nothing
		 * Post:		The number of turns have been updated.
		 ******************************************************************* */
		public void updateTurn() 
		{
			this.turn++;
		}
		
		/* ********************************************************************
		 * Purpose:  	Undoes the last move by the current player.
		 * Receives:	Nothing
		 * Returns:		Nothing
		 * Post:		The player's move has been undone.
		 ******************************************************************* */
		public void undoMove()
		{
			this.xoro = this.prevmove;
			this.boardArray[this.undoMove] = String.valueOf(this.undoMove); 
		}
		
		/* ********************************************************************
		 * Purpose:  	ToString method of the class
		 * Receives:	Nothing
		 * Returns:		Nothing
		 * Post:		The string has been created and all associated 
		 * 				information of the class has been appended to it.
		 ******************************************************************* */
		@Override
		public String toString() 
		{
			return "\nTicTacToeClass [boardArray=" + Arrays.toString(boardArray) + "\nprevmove=" + prevmove
					+ "\nundoMove=" + undoMove + "\nturn=" + turn + "\nxoro=" + xoro + "]";
		}
}
