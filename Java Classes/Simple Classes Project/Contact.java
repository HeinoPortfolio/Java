package dataclasses;

public class Contact extends Person 
{
	public String contacttype;			// Holds contact type either "friend" or "business"
	
	
	//Constructors of the class.*************************************************
	/* Purpose:  	Default constructor of the class.
	*  Receives:	None
	*  Post:		The object has been initialized and created.
	* ***************************************************************************/
	public Contact() 
	{
		super();
		this.contacttype = "";
	}

	/* Purpose:  	Explicit constructor of the class.
	 * Receives:	first - String
	 * 				last - String
	 * 				phn  - String (phone number)
	 * 				addr - Address object
	 * Post:		The object has been initialized and created.
	 * ***************************************************************************/
	public Contact(String first, String last, String phn, Address addr) 
	{
		super(first, last, phn, addr);
		this.contacttype = "";
	}
	
	// Setter.********************************************************
	/* *********************************************************
	 *  Purpose:	Sets the contact type
	 *  Receives:	contact - String
	 *  Post:		The contactype variable has been set
	 * *********************************************************/
	public void setContactType(String contact)
	{
		this.contacttype = contact;
	}
	
	//Getter.********************************************************
	/* *********************************************************
	 *  Purpose:	Gets the Address
	 *  Receives:	None
	 *  Returns:	address - Address object
	 *  Post:		The address variable has been returned.
	 *  ********************************************************/
	public String getContactType()
	{
		return this.contacttype;
	}
	
	/* *********************************************************
	 *  Purpose:	Output Contact information unformatted
	 *  Receives:	None
	 *  Returns:	String outstr
	 *  Post:		String with the contactinformation.
	 * *********************************************************/
	public String toString()
	{
		String outstr;
		
		outstr = super.toString() + "\nType of contact: " + this.contacttype;
		
		return outstr;
	}

}
