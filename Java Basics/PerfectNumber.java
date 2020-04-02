/* Perfect Number.java
 * 
 * 	Author: Heino
 * 
 *  Purpose:  Determine if a number is a perfect number. A perfect number is a 
 *  number whose factors including 1 add up to the number. This file will find 
 *  all the number from 1 to 1000.
 *  
 *  Note: This file was also an exercise in functional decomposition and para-
 *  meter passing using various data types. 
 * 
 ******************************************************************************/

package Numbers;

import java.util.ArrayList;
import java.lang.*;

public class PerfectNumber 
{
	
	public static ArrayList findFactors(int num)
	{
		ArrayList<Integer> factors = new ArrayList<Integer>();
	
		for (int i = 1; i < Math.sqrt(num); i++)
		{
			if(num % i == 0)
			{
				if(num / i == 0)
				{
					factors.add(i);
				}
				else
				{
					factors.add(i);
					factors.add(num/i);
				}
			}
		}
		return factors;
	}
	
	public static int sumFactors(ArrayList<Integer> factors, int num)
	{
		int sum =0;
		
		for(Integer fac : factors)
		{
			if (fac != num)
			{
				sum = sum + fac;
			}
		}
		return sum;
	}
	
	public static void isPerfect(int sum, int n) 
	{
		if (sum == n)
		{
			System.out.print(n + " ");
		}
	}
	
	public static void main(String[] args) 
	{
		ArrayList<Integer> myFactors;
		
		int sum = 0;
		
		for(int n = 1; n <= 1000; n++ )
		{
			myFactors = findFactors(n);
		
			sum = sumFactors(myFactors, n);
		
			isPerfect(sum, n);
		}
	}

}
