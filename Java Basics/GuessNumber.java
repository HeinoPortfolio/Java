/* GuessNumber.java
 *  
 *  Author:  Heino
 *  
 *  Purpose:  	A simple number guessing game that prompts the user to enter a 
 *  		   	number to guess the random number that was generated. The numbers 
 *  			range from 0 to 10.  There is a maximum of 5  guesses before the 
 *  			game is over.
 *   
 ***********************************************************************************************/
package Numbers;

import java.util.*;

public class GuessNumber {

	public static void main(String[] args)
	{
		Random rand = new Random();
		Scanner scan = new Scanner(System.in);
		Scanner scan2 = new Scanner(System.in);
		
		boolean done = true;
		boolean playagain = true;
		
		int guess;
		int tries = 1;
		
		String play;
		
		int  randNum = rand.nextInt(11);

		//System.out.println(randNum);
		
		do 
		{
			System.out.print("The number will be between 0 and 10. \nYou will have 5 chances to guess the number.");
			
			System.out.print("Please enter first guess: ");
			guess = scan.nextInt();
			
			while(done && tries < 5)
			{
				if(guess < randNum)
				{
					System.out.print("That guess is too low!. Please enter a new guess: ");
					guess = scan.nextInt();
					tries++;
				}
				else if (guess > randNum)
				{
					System.out.print("That guess is too high!. Please enter a new guess: ");
					guess = scan.nextInt();	
					tries++;
				}
				else if (guess == randNum)
				{
					System.out.print("That guess is right!!!!");
					System.out.println("The number was:  " + randNum);
					done = false;
				}
				
				
			} // end while
			
			if(tries >= 5)
			{
					System.out.println("You have exceeded the number of guesses for this round! ");
					System.out.println("The number was:   " + randNum);
					done = false;
					tries = 0;
			}
			
			System.out.print("\nDo you want to play again?  Y or N:    ");
			play = scan2.nextLine();
			
			if(play.toUpperCase().equals("N"))
			{
				playagain = false;
			}
			else
			{
				tries = 0;
				done = true;
				randNum = rand.nextInt(11);
				//System.out.println(randNum);
			}
			
		} while(playagain); // do while.
		
		System.out.println("Game is over!!!!!");
		
		scan.close();
		scan2.close();
	}

}
