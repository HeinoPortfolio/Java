package dataclasses;

public class SalariedEmployee extends Employee 
{
	private double salary;
	
	//Constructors of the class.*************************************************
	/* Purpose:  	Default constructor of the class.
	 * Receives:	None
	 * Post:		The object has been initialized and created.
	 * ***************************************************************************/
	public SalariedEmployee() 
	{
		this.salary = 0;
	}

	public SalariedEmployee(String first, String last, String phn, Address addr) 
	{
		super(first, last, phn, addr);
		this.salary = 0;
	}

	@Override
	public double calculatePay() 
	{
		
		return this.salary;
	}

}
