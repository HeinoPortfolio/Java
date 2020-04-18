/*ReverseString.java
 * 
 * 	Author: Heino
 * 
 * 	Purpose:  	This is a variant on how to reverse a string.  StringBuilder is 
 * 				not used in this and it is not broken down in characters.  
 * 				This is not meant to be the best solution but as away to 
 * 				manipulate substrings.
 * *****************************************************************************/
public class ReverseString {

	public static void main(String[] args) 
	{
		String origStr = "This was a fun problem!";
		char temp1, temp2;
		int index1 = 0;
		int index2 = 0;
		int length = 0;
		int halflength = 0;
		
		// get the length of the string.
		index2 = origStr.length() - 1;
		length = origStr.length();
		
		halflength = origStr.length() / 2;
		
		System.out.println("The contents of the string before reversal are: " + origStr);
		
		for(int i = 0; i < halflength; i++)
		{
			temp1 = origStr.charAt(index2);  // rear letter
			temp2 = origStr.charAt(index1);	 // forward letter
			
			origStr = origStr.substring(0, index1) + temp1 + origStr.substring(index1 + 1, length);
			origStr = origStr.substring(0, index2) + temp2 + origStr.substring(index2 + 1);
			
			index1++;
			index2--;
		}
		System.out.println("The contents of the string after reversal are: " + origStr);	
	}

}
