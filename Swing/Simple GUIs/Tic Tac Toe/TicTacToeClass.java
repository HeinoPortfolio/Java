package tictactoegui;

import java.util.Arrays;

public class TicTacToeClass 
{
		private String[] boardArray;
		private String prevmove ;
		private int undoMove = -1;
		private int turn = 0;
		private String xoro = "X";		
		
		// Constructor.--------------------------------------
		public TicTacToeClass() 
		{
			this.boardArray = new String[] {"0", "1","2", "3","4","5","6","7","8"};
			this.prevmove = "X";
			this.undoMove = -1;
		}
		
		public void changeXorO()
		{
			if(this.xoro == "X")
				this.xoro = "O";
			else
				this.xoro = "X";
		}
		
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
		
		public void setPlayerTurn()
		{
			if(this.xoro == "X")
				this.xoro = "O";
			else
				this.xoro = "X";
		}
		public String getXorO()
		{
			return this.xoro;
		}
		public void setMove(int position, String move)
		{
			
			this.boardArray[position] = move;
			this.undoMove = position;
			this.prevmove = move;
		}
		
		public void printBoardArray()
		{
			for( String array : boardArray)
			{
				System.err.println(array);
			}
		}
		
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
		
		public void setTurn(int turn)
		{
			this.turn = turn;
		}
		
		public void updateTurn() 
		{
			this.turn++;
		}

		public void undoMove()
		{
			this.xoro = this.prevmove;
			this.boardArray[this.undoMove] = String.valueOf(this.undoMove); 
		}
		
		@Override
		public String toString() 
		{
			return "\nTicTacToeClass [boardArray=" + Arrays.toString(boardArray) + "\nprevmove=" + prevmove
					+ "\nundoMove=" + undoMove + "\nturn=" + turn + "\nxoro=" + xoro + "]";
		}
		
		
}
