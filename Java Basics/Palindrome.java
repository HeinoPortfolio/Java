package Interview;

import java.util.*;

public class Palindrome {
	
	public static boolean isPalindrome(String str1)
	{
		boolean isPalindrome = false; 
		boolean  done = true;
		
		int left = 0;
		int right = str1.length() - 1;
		
		while(done)
		{
			if(left == right) 
			{
				isPalindrome = true;
				done = false;
			}
			if(str1.charAt(left) == str1.charAt(right)) 
			{
				left = left + 1;
				right = right - 1;
			}
			else
			{
				isPalindrome = false;
				done = false;
			}
		}
		return isPalindrome;
	}

	public static void main(String[] args)
	{

		System.out.println("The string is a palindrome: " + isPalindrome("This"));
	}

}
