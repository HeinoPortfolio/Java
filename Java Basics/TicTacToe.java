/* TicTacToe.javq
 * 
 * 	Author: 	Heino
 * 
 * 	Purpose:	This program sets up a simple Tic Tac Toe game.  It does not 
 * 				use a two dimensional array.  It will use a simple one dimen-
 * 				sional array.
 * 
 * **************************************************************************/

package tictactoe;

import java.util.Scanner;
import java.lang.Integer;

public class TicTacToe 
{
	/* 
	 * 	Purpose:  		This method checks to see if the the move results in a win
	 *  Receives:		String[] boardArr - the array holding the moves.
	 *  Returns:		boolean gameover -  false if the game has been true 
	 *  				otherwise.
	 *  
	 *   Precondition:	The number of squares has not been exceeded.
	 *   Postcondition:	The gameover has been successfully returned
	 ************************************************************************ */
	public static boolean checkForWin(String[] boardArr, int turn, String xoro)
	{	
		boolean gameover = true;
		
		if (turn > 2)
		{
			//Check the winning paths.*******************************************
			// Check the rows for the win.*****************************************
			if(boardArr[0].equals(boardArr[1]) && boardArr[1].equals(boardArr[2]))
			{
				System.out.println("Player " + xoro + " is the winner!!! Row: 1");
				gameover = false;
			}
			else if(boardArr[3].equals(boardArr[4]) && boardArr[4].equals(boardArr[5]))
			{
				System.out.println("Player " + xoro + " is the winner!!! Row: 2");
				gameover = false;
			}
			else if(boardArr[6].equals(boardArr[7]) && boardArr[7].equals(boardArr[8]))
			{
				System.out.println("Player " + xoro + " is the winner!!! Row: 3");
				gameover = false;
			}
			//Check the columns for a win.*********************************************
			else if(boardArr[0].equals(boardArr[3]) && boardArr[3].equals(boardArr[6]))
			{
				System.out.println("Player " + xoro + " is the winner!!! Column: 1");
				gameover = false;
			}
			else if(boardArr[1].equals(boardArr[4]) && boardArr[4].equals(boardArr[7]))
			{
				System.out.println("Player " + xoro + " is the winner!!! Column: 2");
				gameover = false;
			}
			else if(boardArr[2].equals(boardArr[5]) && boardArr[5].equals(boardArr[8]))
			{
				System.out.println("Player " + xoro + " is the winner!!! Column: 3");
				gameover = false;
			}
			// Check the diagonal for a win.******************************************
			else if(boardArr[0].equals(boardArr[4]) && boardArr[4].equals(boardArr[8]))
			{
				System.out.println("Player " + xoro + " is the winner!!! Diagonal: 1");
				gameover = false;
			}
			else if(boardArr[2].equals(boardArr[4]) && boardArr[4].equals(boardArr[6]))
			{
				System.out.println("Player " + xoro + " is the winner!!! Diagonal: 2");
				gameover = false;
			}
		}
		return gameover;
	}
	
	/* 
	 * 	Purpose:  		This method prints the game board.
	 *  Receives:		String[] boardArr - the array holding the moves.
	 *  Returns:		Nothing.
	 * 
	 ************************************************************************ */
	public static void printBoard(String[] boardArr)
	{
		System.out.println(" __  __  __");
		System.out.println("| " + boardArr[0]+ " | " + boardArr[1] + " | " +boardArr[2] + " |");
		System.out.println(" __  __  __");
		System.out.println("| " + boardArr[3]+ " | " + boardArr[4] + " | " +boardArr[5] + " |");
		System.out.println(" __  __  __");
		System.out.println("| " + boardArr[6]+ " | " + boardArr[7] + " | " +boardArr[8] + " |");
		System.out.println(" __  __  __");
	}
	public static void main(String[] args) 
	{
		Scanner positionScn = new Scanner(System.in);
		
		String[] boardArr = {"0", "1","2", "3","4","5","6","7","8"};    // Holds the X's and O's
		String pos = ""; 												// Holds position entered by the user. 
		String xoro = "X";
		boolean gameover = true;
		int turn = 0;
		int posInt = 0;
		
		
		System.out.print("Welcome to the world's simplest Tic Tac Toe game. \n This game requires two players to play.\n");
		
		System.out.print("\nIntructions: \n \t1) Choose a sqaure by entering the number. "
				+ "\n\t For examp a 0 will place an X or O in the top left corner.\n");
		
		//Print the board
		printBoard(boardArr);
		
		while(gameover && turn < 9)
		{
			System.out.println("\nIt is player " + xoro + "'s turn!");
			System.out.print("Please enter a position: ");
			pos = positionScn.next();
			
			if((int)pos.charAt(0) >= 48 && (int)pos.charAt(0) <= 56 )
			{
				posInt = Integer.parseInt(pos);
			
				//Check to see if the space is already occupied.
				if(boardArr[posInt] == "X"  || boardArr[posInt] == "O")
				{
					System.out.println("That position is already used. \nPlease pick another square!!");
				}
				else
				{	
					//Place a new X in the array.
					boardArr[posInt] = xoro;printBoard(boardArr);
					turn++;
					gameover = checkForWin(boardArr, turn, xoro);
				
					if(gameover != false)
					{
						if(xoro == "X")
						{
							xoro = "O";
						}
						else
						{
							xoro = "X";
						}
					}
				}
			} // if
			else
			{
				System.out.println("This is not a valid cell position! Please choose again.");
			}
		} // end while
		if(turn == 9  && gameover != false)
		{
			System.out.println("\nThere are no more moves to be made. Game over.");
		}
		
		positionScn.close();
	}

}
