/* Craps.java
 *  
 *  Author:		Heino
 *  
 *  Purpose:  	This is a simple game of craps.  It handles the basic rules of 
 *  			the game.  Only one player will be playing at a time.  The 
 *  			player will get $100 to play with. 
 * 
 ******************************************************************************/

package gameofcraps;

import java.util.Scanner;
import java.util.Random;


public class Craps 
{
	public static int getWager(int totalmoney, Scanner wagerScn)
	{
		int wager = 0;
		boolean notagoodwager = true;
		
		// Get the wager.
		while(notagoodwager == true)
		{
			System.out.print("\nYou have $" + totalmoney  + " to wager. Please enter an amount: $");
			wager = wagerScn.nextInt();
			
			if(wager > totalmoney)
			{
				System.out.println("That wager is TOO HIGH! Please enter another amount.");
			}
			else
			{
				notagoodwager = false;
			}
		}
		return wager;		
	} // end getWager.
	
	public static int playGame(int totalmoney, int point, int wager, Random rand, Scanner rollagain)
	{
		boolean done = true;
		int die1 = 0;
		int die2 =0;
		int theroll = 0;
		
		System.out.println("\nYou will need to roll a " + point);
		
		while(done)
		{
			System.out.println("Press any key and enter to continue: ");
			rollagain.nextLine();
			
			// Roll the dice again.
			die1 = rand.nextInt(6) + 1;
			die2 = rand.nextInt(6) + 1;
			
			System.out.println("You rolled a: " + die1 + " and a " + die2 );
			theroll = die1 + die2;
			
			if (theroll == point)
			{
				System.out.println("You won!!!!");
				totalmoney = totalmoney + wager;
				done = false;
				
			}
			else if(theroll == 7 || theroll == 11)
			{
				System.out.println("You lost!!!!");
				totalmoney = totalmoney - wager;
				done = false;
			}
		} // end while.
		return totalmoney;
	}
	
	public static void main(String[] args) 
	{
		boolean cont = true;							// boolean to continue
		Scanner goagain = new Scanner(System.in);		// To take in go again answer
		Scanner wagerScn= new Scanner(System.in);		// Take in wager.	
		Scanner rollagain = new Scanner(System.in);		// Roll again
		Random rand = new Random();						// To randomize dice values
		
		int totalmoney = 100;							// Starting wager amount
		int wager = 0;									// Entered wager amount
		String yesorno;									// yes or no to continue
		int die1 = 0;							
		int die2 = 0;
		int point =0;
		
		System.out.print("Welcome to the game of Craps! \nThe rules are simple: \n\n");
		System.out.print("\t 1. You will role two die.\n\t 2. If you roll a 7 or 11 on the first role you win."
				+ "\n\t 3. If you role a 2,3,12 on the first role you lose. "
				+ "\n\t 4. If you role any other number that is your POINT."
				+ "\n\t 5. You must role this point before you role a 7 or 11 in subsequent turns."
				+ "\n\t 6.  If you role the POINT you will win the amount wagered. \n");
		
		do
		{
			// Get the wager.
			wager = getWager(totalmoney, wagerScn);
			
			// Roll the dice the first time.
			die1 = rand.nextInt(6) + 1;
			die2 = rand.nextInt(6) + 1;
			
			System.out.println("You rolled a: " + die1 + " and a " + die2);
			point = die1 + die2;
			
			if(point == 7 || point == 11)
			{
				System.out.println("You won on your first roll!!!");
				totalmoney = wager + totalmoney;
				System.out.println("Your new total: $" + totalmoney);
			}
			else if(point == 2 || point == 3 || point == 12)
			{
				if(point == 2)
				{
					System.out.println("SNAKE EYES!!!!!!!!!!!!!!!!!!!!!!!!");
				}
				else if(point == 12)
				{
					System.out.println("BOXCARS!!!!!!!!!!!!!!!!!!!!!!!!");
				}
				System.out.println("You lost!!!!");
				totalmoney = totalmoney - wager;
			}
			else 
			{
				System.out.println("\nYou did not make 7 or 11.  You will need to roll again.");
				
				// Call to the main game method.
				totalmoney = playGame(totalmoney, point, wager, rand, rollagain);	
			}
			
			System.out.print("\nDo you want to play again?  Enter Y or N: ");
			yesorno = goagain.nextLine();
			
			if(yesorno.toUpperCase().equalsIgnoreCase("N"))
			{
				cont = false;
				System.out.println("The game is over!!!");
			}
		}while(cont);
		
		goagain.close();
		wagerScn.close();
		rollagain.close();
		

	}

}
