package dataclasses;

public class Person 
{
	private String firstname;
	private String lastname;
	private Address address;
	private String phone;
	
	
	//Constructors of the class.*************************************************
	/* Purpose:  	Default constructor of the class.
	 * Receives:	None
	 * Post:		The object has been initialized and created.
	 * ***************************************************************************/
	public Person()
	{
		this.firstname = "John";
		this.lastname = "Doe";
		this.phone = "516-890-0989";
		this.address = new Address();
	}
	
	/* Purpose:  	Explicit constructor of the class.
	 * Receives:	first - String
	 * 				last - String
	 * 				phn  - String (phone number)
	 * 				addr - Address object
	 * Post:		The object has been initialized and created.
	 * ***************************************************************************/
	public Person(String first, String last, String phn, Address addr)
	{
		this.firstname = first;
		this.lastname = last;
		this.phone = phn;
		this.address = addr;
		
	}
	// Setters of the class.***************************************************
	/* *********************************************************
	 *  Purpose:	Sets the first name
	 *  Receives:	first - String
	 *  Post:		The firstname variable has been set
	 * *********************************************************/
	public void setFirst(String first)
	{
		this.firstname = first;
	}
	
	/* *********************************************************
	 *  Purpose:	Sets the last name
	 *  Receives:	last - String
	 *  Post:		The lastname variable has been set
	 * *********************************************************/
	public void setLast(String last)
	{
		this.lastname = last;
	}
	
	/* *********************************************************
	 *  Purpose:	Sets the address
	 *  Receives:	addr - String
	 *  Post:		The address variable has been set
	 * *********************************************************/
	public void setAddr(Address addr)
	{
		this.address.setAddress(addr);
	}
	
	/* *********************************************************
	 *  Purpose:	Gets the first name
	 *  Receives:	None
	 *  Returns:	firstname - String
	 *  Post:		The firstname variable has been returned.
	 * *********************************************************/
	public String getFirstName()
	{
		return this.firstname;
	}
	
	/* *********************************************************
	 *  Purpose:	Gets the last name
	 *  Receives:	None
	 *  Returns:	lastname - String
	 *  Post:		The lastname variable has been returned.
	 * *********************************************************/
	public String getLastName() 
	{
		return this.lastname;
	}
	
	/* *********************************************************
	 *  Purpose:	Gets the Address
	 *  Receives:	None
	 *  Returns:	address - Address object
	 *  Post:		The address variable has been returned.
	 * *********************************************************/
	public Address getAddress()
	{
		return this.address;
	}
	
	//Methods of the class.***********************************
	/* *********************************************************
	 *  Purpose:	See if two people are the same are equal
	 *  Receives:	Person per
	 *  Returns:	boolean
	 *  Post:		Either true if the people are equal or false
	 *  			if they are not.
	 * *********************************************************/
	public boolean equals(Person per)
	{
		return (this.firstname.equals(per.firstname) && this.lastname.equals(per.lastname) 
				&& this.address.equals(per.address));
	}
	
	/* *********************************************************
	 *  Purpose:	String to output information in the Person
	 *  Receives:	None
	 *  Returns:	outstr- String
	 *  Post:		The outstr has been returned.
	 * *********************************************************/
	public String toString()
	{
		String outstr;
		
		outstr = "\n" + this.firstname + " " + this.lastname + " " + this.address.toString() 
				+ "\n" + this.phone;
		
		return outstr;
	}
	
} // end person.
