package rationalnumber;

import java.lang.Math;

public class RationalNumber 
{
	private int numerator;
	private int denominator;
	private int wholeNum;
	
	//Constructors of the class.*******************************************
	//Default constructor.
	/* **********************************************************************
	 *  Purpose: 	Sets the numerator and the denominator of the class.
	 * Receives: 	None
	 * Post:		The object has been created with the given values. 
	 * 
	 ******************************************************************* */
	public RationalNumber() 
	{
		numerator = 1;
		denominator = 1;
		wholeNum = 0;
	} // end default constructor.
	
	// Explicit value constructors. ***********************************************
	
	/* *****************************************************************************
	 * Purpose: 	Sets the numerator and the denominator of the class.
	 * Receives: 	int num - numerator value
	 * 				int denom - denominator value
	 * Post:		The object has been created with the given values. 
	 * 
	 * *****************************************************************************/
	public RationalNumber(int num, int denom)
	{
		numerator = num;
		denominator = denom;
		wholeNum = 0;
	}
	
	/* *****************************************************************************
	 * Purpose: 	Sets the numerator, denominator, and whole number of the class.
	 * Receives: 	int num - numerator value
	 * 				int denom - denominator value
	 * 				int wholeNum- whole number value
	 * Post:		The object has been created with the given values. 
	 * 
	 * *****************************************************************************/
	public RationalNumber(int num, int denom, int whole)
	{
		numerator = num;
		denominator = denom;
		wholeNum = whole;
	}
	
	
	//Setters of the class.*************************************************************
	/* *****************************************************************************
	 * Purpose: 	Sets the numerator, denominator, and whole parts of the class.
	 * Receives: 	int num - numerator value
	 * 				int denom - denominator value
	 * 				int who - whole part of the number
	 * Post:		The object has been set with the given values. 
	 * 
	 * *****************************************************************************/
	public void setNumDenomWhole(int num, int denom, int who)
	{
		this.numerator = num;
		this.denominator = denom;
		this.wholeNum = who;
	}
	
	/* *****************************************************************************
	 * Purpose: 	Sets the numerator and denominator of the class.
	 * Receives: 	int num - numerator value
	 * 				int denom - denominator value
	 * Post:		The object has been set with the given values. 
	 * 
	 * *****************************************************************************/
	public void setNumDenom(int num, int denom)
	{
		this.numerator = num;
		this.denominator = denom;
	}
	/* *****************************************************************************
	 * Purpose: 	Sets the numerator of the class.
	 * Receives: 	int num - numerator value
	 * Post:		The object has been set with the given values. 
	 * 
	 * *****************************************************************************/
	public void setNum(int num)
	{
		this.numerator = num;
	}
	
	/* *****************************************************************************
	 * Purpose: 	Sets the denominator of the class.
	 * Receives: 	int denom - denominator value
	 * Post:		The object has been set with the given values. 
	 * 
	 * *****************************************************************************/
	public void setDenom(int denom)
	{
		this.denominator = denom;
	}
	
	// Getters.******************************************************************
	/* *****************************************************************************
	 * Purpose: 	Gets the numerator of the class.
	 * Returns: 	int numerator
	 * Post:		The numerator has been returned. 
	 * 
	 * *****************************************************************************/
	public int getNum()
	{ 
		return this.numerator;
	}
	
	/* *****************************************************************************
	 * Purpose: 	Gets the denominator of the class.
	 * Returns: 	int denominator
	 * Post:		The denominator has been returned. 
	 * 
	 * *****************************************************************************/
	public int getDenom()
	{
		return this.denominator;
	}
	
	/* *****************************************************************************
	 * Purpose: 	Gets the whole number of the class.
	 * Returns: 	int wholeNum
	 * Post:		The whole number has been returned. 
	 * 
	 * *****************************************************************************/
	public int getWhole()
	{
		return this.wholeNum;
	}
	
	//Methods of the class.************************************************************
	/* *****************************************************************************
	 * Purpose: 	Finds the GCD of the two numbers.
	 * Returns: 	int gcd - the greatest common divisor
	 * Receives:	int term1, int term2
	 * Post:		The gcd has been returned. 
	 * 
	 * *****************************************************************************/
	public int GCD(int term1, int term2)
	{
		if(term1 == 0)
			return term2;
		
		return GCD(term2 % term1, term1);
	}

	/* *****************************************************************************
	 * Purpose: 	Reduces the fraction to simplest terms.
	 * Returns: 	int[] parts - holds the numerator, denominator and whole part of 
	 * 							  the rational number	
	 * 				part[0] -   whole part
	 * 				parts[1] -  numerator
	 * 				parts[2] -  denominator
	 * Receives:	int term1, int term2
	 * Post:		The gcd has been returned. 
	 * 
	 * *****************************************************************************/
	public int[] reduceFraction(int numer, int denom)
	{
		int[] parts = new int[3];
		int remainder = 0;
		int gcd = 0;
		
		if(Math.abs(numer) == Math.abs(denom)) 
		{
			parts[0] = numer / denom;
		}
		else if(numer > denom)
		{
			parts[0] = (int)((double)numer / denom);
			
			// Reduce the fraction.********
			remainder = numer % denom;
			
			// Get the GCD.
			gcd = this.GCD(remainder, denom);
			
			// reduce the numerator 
			parts[1] = remainder / gcd;
			
			// reduce the denominator
			parts[2] = denom / gcd;
		}
		else
		{
			gcd = this.GCD(numer, denom);
			parts[0] = 0;
			parts[1] = numer / gcd;
			parts[2] = denom / gcd;
		}		
		return parts;
	}
	
	//Add two rational number together.
	/* *****************************************************************************
	 * Purpose: 	Adds two rational numbers together.
	 * Returns: 	RationalNumber - the result of the addition
	 * Receives:	RationalNumber rat2 - number to be added
	 * Post:		The sum has been returned. 
	 * 
	 * *****************************************************************************/
	public RationalNumber addTwoRat(RationalNumber rat2)
	{  
		RationalNumber sumRat = new RationalNumber();
		int numer = 0;
		int denom = 0;
		int parts[];
		
		numer = (this.numerator * rat2.denominator) + (rat2.numerator * this.denominator);
		
		denom = this.denominator * rat2.denominator;
	
		parts = reduceFraction(numer, denom);
		sumRat.setNumDenomWhole(parts[1], parts[2], parts[0]);
		
		return sumRat;	
	}
	
	
	// Subtract two rational numbers.
	/* *****************************************************************************
	 * Purpose: 	Subtract two rational numbers together.
	 * Returns: 	RationalNumber - the result of the subtraction
	 * Receives:	RationalNumber rat2 - number to be added
	 * Post:		The difference has been returned. 
	 * 
	 * *****************************************************************************/
	public RationalNumber subTwoRat(RationalNumber rat2)
	{  
		RationalNumber subRat = new RationalNumber();
		int numer = 0;
		int denom = 0;
		int parts[];
		
		numer = (this.numerator * rat2.denominator) - (rat2.getNum() * this.denominator);
		
		denom = this.denominator * rat2.getDenom();
		
		parts = reduceFraction(numer, denom);
		subRat.setNumDenomWhole(parts[1], parts[2], parts[0]);
		
		return subRat;	
	}
	
	// Multiply two rational numbers.
	/* *****************************************************************************
	 * Purpose: 	Multiply two rational numbers together.
	 * Returns: 	RationalNumber - the result of the multiplication
	 * Receives:	RationalNumber rat2 - number to be added
	 * Post:		The product has been returned. 
	 * 
	 * *****************************************************************************/
	public RationalNumber mulTwoRat(RationalNumber rat2)
	{  
		RationalNumber mulRat = new RationalNumber();
		int numer = 0;
		int denom = 0;
		int parts[];
		
		numer = (this.numerator * rat2.numerator);
		
		denom = this.denominator * rat2.getDenom();
		
		parts = reduceFraction(numer, denom);
		mulRat.setNumDenomWhole(parts[1], parts[2], parts[0]);
		
		return mulRat;	
	}
	
	// Divide two rational numbers.
	/* *****************************************************************************
	 * Purpose: 	Divide two rational numbers together.
	 * Returns: 	RationalNumber - the result of the division
	 * Receives:	RationalNumber rat2 - number to be added
	 * Post:		The result has been returned. 
	 * 
	 * *****************************************************************************/
	public RationalNumber divTwoRat(RationalNumber rat2)
	{  
			RationalNumber divRat = new RationalNumber();
			int numer = 0;
			int denom = 0;
			int parts[];
			
			numer = (this.numerator * rat2.denominator);
			
			denom = this.denominator * rat2.numerator;
			
			parts = reduceFraction(numer, denom);
			divRat.setNumDenomWhole(parts[1], parts[2], parts[0]);
			
			return divRat;	
		}
	
	//Print the floating point representation of the rational number
	/* *****************************************************************************
	 * Purpose: 	Prints a floating point representation of the rational number.
	 * Returns: 	Nothing
	 * Receives:	int precision - number of places to the right
	 * Post:		The formatted output has been output. 
	 * 
	 * *****************************************************************************/
	public void formatOuput(int precision)
	{
		String format = "%" + "." + precision +"f";
		float complete = (float)this.wholeNum +  ((float)this.numerator / this.denominator);
		String form = String.format(format, complete);
		
		System.out.println(form);
	}
	
	/* *****************************************************************************
	 * Purpose: 	Prints a/b representation of the rational number.
	 * Returns: 	Nothing
	 * Receives:	int precision - number of places to the right
	 * Post:		The formatted output has been output. 
	 * 
	 * *****************************************************************************/
	public String toString()
	{
		String ratNumStr = "";
		
		if(numerator == 0)
		{
			ratNumStr = Integer.toString(wholeNum);
		}
		else if(wholeNum != 0)
		{
			ratNumStr = this.wholeNum + " " + this.numerator + "/" +  this.denominator;
		}
		else if(denominator  < 0)
		{
			ratNumStr = (-1 * this.numerator)+ "/" + (-1 * this.denominator);
		}
		else
		{
			ratNumStr = this.numerator + "/" + this.denominator;
		}
		return ratNumStr;
	}
} // end class.
