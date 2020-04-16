package tictactoe;

import java.util.Scanner;
import java.lang.Integer;

public class TicTacToe {

	public static boolean checkForWin(String[] boardArr, int turn, String xoro)
	{	
		boolean gameover = true;
		
		if (turn > 2)
		{
			//Check the winning paths.*******************************************
			// Check the rows for the win
			if(boardArr[0].equals(boardArr[1]) && boardArr[1].equals(boardArr[2]))
			{
				System.out.println("Player " + xoro + " is the winner!!!R1");
				gameover = false;
			}
			if(boardArr[3].equals(boardArr[4]) && boardArr[4].equals(boardArr[5]))
			{
				System.out.println("Player " + xoro + " is the winner!!!R2");
				gameover = false;
			}
			if(boardArr[6].equals(boardArr[7]) && boardArr[7].equals(boardArr[8]))
			{
				System.out.println("Player " + xoro + " is the winner!!!R3");
				gameover = false;
			}
			
			//Check the columns for a win
			if(boardArr[0].equals(boardArr[3]) && boardArr[3].equals(boardArr[6]))
			{
				System.out.println("Player " + xoro + " is the winner!!!C1");
				gameover = false;
			}
			
			if(boardArr[1].equals(boardArr[4]) && boardArr[4].equals(boardArr[7]))
			{
				System.out.println("Player " + xoro + " is the winner!!!C2");
				gameover = false;
			}
			
			if(boardArr[2].equals(boardArr[5]) && boardArr[5].equals(boardArr[8]))
			{
				System.out.println("Player " + xoro + " is the winner!!!C3");
				gameover = false;
			}
			
			// Check the diagonal for a win.
			if(boardArr[0].equals(boardArr[4]) && boardArr[4].equals(boardArr[8]))
			{
				System.out.println("Player " + xoro + " is the winner!!! D1");
				gameover = false;
			}
			if(boardArr[4].equals(boardArr[4]) && boardArr[4].equals(boardArr[6]))
			{
				System.out.println("Player " + xoro + " is the winner!!!D2");
				gameover = false;
			}
		}
		
		return gameover;
	}
	
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
		
		while(gameover)
		{
			System.out.println("\nIt is player " + xoro + "'s turn!");
			System.out.print("Please enter a position: ");
			pos = positionScn.next();
			posInt = Integer.parseInt(pos);
			
			//Check to see if the space is already occupied.
			if(boardArr[posInt] == "X"  || boardArr[posInt] == "O")
			{
				System.out.println("That position is alredy used. \nPlease pick another square!!");
			}
			else
			{	
				//Place a new X in the array.
				boardArr[posInt] = xoro;
				printBoard(boardArr);
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

		} // end while
		
		positionScn.close();
	}

}
