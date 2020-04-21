/* Member.java
 * 
 *  Purpose:  	Defines a class that models a member of a store
 * 	Note:	  	It is a subclass of the Customer class.  It 
 * 			  	implements one abstract method of the class.  
 * 				Method to calculate the discount and reset the 
 * 				count of books and the amount the member has paid. 
 * 
 * ******************************************************************/
package dataclasses;

public class Member extends Customer 
{
	
	private String memberID;							// Holds the member ID.
	private int numberOfBooksBought;					// HOld the number of books purchased
	private double amountSpent;							// Total amount purchased
	private final double discount = .05;				// Total discount applied 
	
	/* *****************************************************************
	 *  Purpose:	Default constructor of the class
	 *  Receives:  	None
	 *  Post:		The object has been created
	 *******************************************************************/
	public Member() 
	{
		super();
		this.memberID ="123435";
		this.numberOfBooksBought = 0;
		this.amountSpent =0;
	}

	/* *****************************************************************
	 *  Purpose:	Explicit constructor of the class
	 *  Receives:  	first  - String first name
	 *  			last   - String last name
	 *  			phn  - String phn
	 *  			addr - Address object
	 *  Post:		The object has been created
	 *  
	 * ****************************************************************/
	public Member(String first, String last, String phn, Address addr) 
	{
		super(first, last, phn, addr);
		this.memberID ="123435";
		this.numberOfBooksBought = 0;
		this.amountSpent =0;
	} 

	// Setters.*************************************************************************
	/* *********************************************************
	 *  Purpose:	Sets the number of books purchased
	 *  Receives:	numbooks - int
	 *  Post:		The numberOfBooks bought variable has been set
	 * *********************************************************/
	public void setNumberOfBooksBought(int numbooks)
	{
		this.numberOfBooksBought = numbooks;
	}
	
	/* *********************************************************
	 *  Purpose:	Sets the amount spent
	 *  Receives:	amtspent - double
	 *  Post:		The amountSpent bought variable has been set
	 * *********************************************************/
	public void setAmountSpent(double amtspent)
	{
		this.amountSpent = amtspent;
	}
	
	//Getter.***************************************************************************
	/* *********************************************************
	 *  Purpose:	Gets the number of books
	 *  Receives:	None
	 *  Returns:	numberofBooksboutght
	 *  Post:		The numberOfBooksBought variable has been set
	 * *********************************************************/
	public int getNumberOfBooks() 
	{
		return this.numberOfBooksBought;
	}
	
	/* *********************************************************
	 *  Purpose:	Gets the total amount spent
	 *  Receives:	None
	 *  Returns:	amountSpent
	 *  Post:		The amountSpent variable has been set
	 * *********************************************************/
	public double getTotalSpent()
	{
		return this.amountSpent;
	}
	
	// Method of the class.*************************************************************
	/*
	 *  Purpose:  	Method to calculate the discount and reset the amount spent
	 *  			and total number of books purchased.
	 *  Receives:   currentamount - double 
	 *  PostL		The discount has been applied and relevant variables have 
	 *  			been updated, if applicable.
	 * */
	@Override
	public double calcTotalSpent(double currentamount) 
	{
		double totalamount = 0;
		double discount =0;
		
		// Calculate the discount if applicable.
		if (this.numberOfBooksBought >= 11 )
		{
			System.out.println("Entered discount if"); ///REMOVE
			discount = ( this.amountSpent/ 10.0) ;
			System.out.println("Value of discount: " + discount); ///Remove
			this.numberOfBooksBought = 0;
			this.amountSpent = 0;
		}
			
		double everydaydisc = (this.amountSpent * this.discount);
		
		System.out.println("Value of every day discount: " +  everydaydisc);// REMOVE
		
		totalamount = currentamount - everydaydisc - discount;
		
		return totalamount;
	}
	
	/* *********************************************************
	 *  Purpose:	Output member information unformatted
	 *  Receives:	None
	 *  Returns:	String outstr
	 *  Post:		String with the member information.
	 * *********************************************************/
	public String toString()
	{
		String outstr;
		
		outstr = super.toString() +"\nMember ID: "+ this.memberID + "\nTotal amount spent: " + this.amountSpent 
				+ "\nTotal number of books purchased: " + this.numberOfBooksBought;
		
		return outstr;
	}

}
