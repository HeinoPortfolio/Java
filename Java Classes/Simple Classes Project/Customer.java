/* Customer.java
 * 
 *  Purpose:  	Defines a class that models a customer of a store
 * 	Note:	  	It is a subclass of the Person class.  It 
 * 			  	contains one abstract method of the class.  
 * 
 * ******************************************************************/

package dataclasses;

public abstract class Customer extends Person {

	//Default Constructor of the class
	/* *****************************************************************
	 *  Purpose:	Default constructor of the class
	 *  Receives:  	None
	 *  Post:		The object has been created
	 *  
	 * ****************************************************************/
	public Customer() 
	{
		super();
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
	public Customer(String first, String last, String phn, Address addr)
	{
		super(first, last, phn, addr);
	}
	
	// Methods of the class
	public abstract double calcTotalSpent(double currentamount);

}
