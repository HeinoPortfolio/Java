/* GDCEuclid.java
 * 
 *  Author: Heino
 *  
 *  Purpose: Program to find the GCD of two numbers using Euclid's algorithm.
 *  Will read two values and find the GCD of them.   Will use two different ways
 *  to enter terms using the console.
 ******************************************************************************/
package Numbers;

import java.util.*;
import java.io.*;

public class GCDEuclid 
{
	public static int GCDEuclid(int term1, int term2)
	{
		if(term1 == 0)
			return term2;
		
		System.out.println("Remainder of:  " + term2 +" and  " + term1 +" is "
						+ term2 % term1 + "  The term 1 values:  " + term1);
		
		return GCDEuclid(term2 % term1, term1);
	}
	
	public static void main(String[] args) 
	{
		Scanner scan = new Scanner(System.in);
		int term1;
		int term2 = 30;
		System.out.print("Please enter a term #1: ");
		term1 = scan.nextInt();
		
		BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
		
		System.out.append("Enter term 2: ");
		try 
		{
			String term2s = reader.readLine();
			term2 = Integer.parseInt(term2s);
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}		
		System.out.println("The GCD of: " + GCDEuclid(term1, term2));
	}
}
