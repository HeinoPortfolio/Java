/*Address.java 
 * 
 *  Purpose: 	This file defines an address class. It will hold 
 *  			information that is relevant to an address. It 
 *  			will hold elements like street, city, state, etc.  
 * 
 * */
package dataclasses;


public class Address extends DataElement
{
	private String street;					// Holds the street address
	private String city;					// Holds the city
	private String state;					// Holds the state
	private String county;					// Holds the county
	private String ZIP;						// Holds the ZIP
	private String aptNum;
	private String country;
	
	
	// Default COnstructor.
	/* *****************************************************************
	 *  Purpose:	Default constructor of the class
	 *  Receives:  	None
	 *  Post:		The object has been created
	 *  
	 * ****************************************************************/
	public Address()
	{
		this.street = "1234 ONE WAY NO WAY";
		this.city = "SOME TOWN";
		this.state = "ZZ";
		this.county = "SOME COUNTY";
		this.ZIP = "12345";
		this.aptNum = null;
		this.country = "THE STATES";
	}
	
	/* *****************************************************************
	 *  Purpose:	Explicit constructor of the class
	 *  Receives:  	str  - String street
	 *  			cty  - String city
	 *  			stte - String state
	 *  			cnty - String county
	 *  			zp   - String ZIP
	 *  			apt  - String apartment number
	 *  			cnt  - String county
	 *  Post:		The object has been created
	 *  
	 * ****************************************************************/
	public Address(String str, String cty, String stte, String cnty, 
			String zp, String apt, String cnt)
	{
		this.street = str;
		this.city = cty;
		this.state = stte;
		this.county = cnty;
		this.ZIP = zp;
		this.aptNum = apt;
		this.country = cnt;
	}
	
	// Setters.****************************************************************
	/* *********************************************************
	 *  Purpose:	Sets the street value
	 *  Receives:	str - String street
	 *  Post:		The street variable has been set
	 * *********************************************************/
	public void setStreet(String str)
	{
		this.street = str;
	}
	
	/* *********************************************************
	 *  Purpose:	Sets the city value
	 *  Receives:	cty - String city
	 *  Post:		The city variable has been set
	 * *********************************************************/
	public void setCity(String cty)
	{
		this.city = cty;
	}
	
	/* *********************************************************
	 *  Purpose:	Sets the state value
	 *  Receives:	stte - String state
	 *  Post:		The state variable has been set
	 * *********************************************************/
	public void setState(String stte)
	{
		this.state = stte;
	}
	
	/* *********************************************************
	 *  Purpose:	Sets the county value
	 *  Receives:	cnt - String county
	 *  Post:		The county variable has been set
	 * *********************************************************/
	public void setCounty(String cnt)
	{
		this.county = cnt;
	}
	
	/* *********************************************************
	 *  Purpose:	Sets the ZIP value
	 *  Receives:	zp - String street
	 *  Post:		The ZIP variable has been set
	 * *********************************************************/
	public void setZIP(String zp)
	{
		this.ZIP = zp;
	}
	
	/* *********************************************************
	 *  Purpose:	Sets the country value
	 *  Receives:	cntry - String street
	 *  Post:		The country variable has been set
	 * *********************************************************/
	public void setCountry(String cntry)
	{
		this.country = cntry;
	}
	
	/* *********************************************************
	 *  Purpose:	Sets the Address
	 *  Receives:	addr - Address object
	 *  Post:		The country variable has been set
	 * *********************************************************/
	public void setAddress(Address addr)
	{
		this.aptNum = addr.aptNum;
		this.city = addr.city;
		this.country = addr.country;
		this.state = addr.state;
		this.county = addr.county;
		this.street = addr.street;
		this.ZIP = addr.ZIP;
	}
	
	// Getters.*****************************************************
	/* *********************************************************
	 *  Purpose:	Gets the street value
	 *  Returns:	String street
	 *  Post:		The street variable has been returned.
	 * *********************************************************/
	public String getStreet()
	{
		return this.street;
	}
	
	/* *********************************************************
	 *  Purpose:	Gets the city value
	 *  Returns:	String city
	 *  Post:		The city variable has been returned.
	 * *********************************************************/
	public String getCity()
	{
		return this.city;
	}
	
	/* *********************************************************
	 *  Purpose:	Gets the state value
	 *  Returns:	String state
	 *  Post:		The state variable has been returned.
	 * *********************************************************/
	public String getState()
	{
		return this.state;
	}
	
	/* *********************************************************
	 *  Purpose:	Gets the ZIP value
	 *  Returns:	String ZIP
	 *  Post:		The ZIP variable has been returned.
	 * *********************************************************/
	public String getZip()
	{
		return this.ZIP;
	}
	
	/* *********************************************************
	 *  Purpose:	Gets the county value
	 *  Returns:	String county
	 *  Post:		The county variable has been returned.
	 * *********************************************************/
	public String getCounty()
	{
		return this.county;
	}
	
	/* *********************************************************
	 *  Purpose:	Gets the country value
	 *  Returns:	String country
	 *  Post:		The country variable has been returned.
	 * *********************************************************/
	public String getCountry()
	{
		return this.country;
	}
	
	
	/* *********************************************************
	 *  Purpose:	Output address information unformatted
	 *  Receives:	None
	 *  Returns:	String outstr
	 *  Post:		String with the address information.
	 * *********************************************************/
	public String toString()
	{
		String outstr;
		
		outstr = "\n" + this.street;
		
		if (this.aptNum != null)
		{
			outstr	 = outstr + " Apt. #" + this.aptNum;
		}
		outstr = outstr+"\n" + this.city +" " + this.state  + " " 
				+ this.ZIP + "\n" +  this.country ; 
		
		return outstr;
	}

	@Override
	public boolean equals(DataElement otherElement) 
	{
		
		Address newaddr = (Address) otherElement;
		boolean result = false;
		
		result = ((this.street.equals(newaddr.street)) && (this.city.equals(newaddr.city)) 
				&& (this.state.equals(newaddr.state)) && (this.ZIP.equals(newaddr.ZIP)));
		
		return result;
	
	}

	@Override
	public int compareTo(DataElement otherElement) 
	{
		Address otherAddr;
		otherAddr = (Address)otherElement;
		int difference = 0;
		
		//System.out.println("The type is: " + otherAddr.getClass() + "\n Contents");			// REMOVE
		difference = this.street.compareTo(otherAddr.street);
		
		return difference;
	}

	@Override
	public void makeCopy(DataElement otherElement) {
		// TODO Auto-generated method stub
		
	}

	//Makes a copy of an Address object
	@Override
	public DataElement getCopy() 
	{
		Address tempAddr = new Address();
		
		tempAddr.street = this.street;
		tempAddr.city = this.city;
		tempAddr.state = this.state;
		tempAddr.aptNum = this.aptNum;
		tempAddr.country = this.country;
		tempAddr.county = this.county;
		tempAddr.ZIP = this.ZIP;
		
		return tempAddr;
		
	}
} // end Address.


