package dataclasses;

public class HourlyEmployee extends Employee 
{

	private double hourlyrate;					// Holds the hourly rate of employee
	private double hoursworked;					// Holds the number of hours worked
	private double overtime;					// Holds the total number of overtime hours.

	//Constructors of the class.*************************************************
	/* Purpose:  	Default constructor of the class.
	 * Receives:	None
	 * Post:		The object has been initialized and created.
	 * ***************************************************************************/
	public HourlyEmployee() 
	{
		super();
		this.hourlyrate = 1.0;
		this.overtime = 0;
	}

	/* Purpose:  	Explicit constructor of the class.
	 * Receives:	first - String
	 * 				last - String
	 * 				phn  - String (phone number)
	 * 				addr - Address object
	 * Post:		The object has been initialized and created.
	 * ***************************************************************************/
	public HourlyEmployee(String first, String last, String phn, Address addr) 
	{
		super(first, last, phn, addr);
		this.hourlyrate = 1.0;
		this.overtime = 0;
	}

	//Setters of the class.*************************************************
	/* *********************************************************
	 *  Purpose:	Sets the hourly rate
	 *  Receives:	rate - double
	 *  Post:		The hourlyrate variable has been set
	 * *********************************************************/
	public void setHourlyRate(double rate)
	{
		this.hourlyrate = rate;
	}
	
	/* *********************************************************
	 *  Purpose:	Sets the hours worked
	 *  Receives:	hours - double
	 *  Post:		The hoursworked variable has been set
	 * *********************************************************/
	public void setHoursWorked(double hours)
	{
		this.hoursworked = hours;
	}
	
	// Methods of the class.******************************************
	/* *********************************************************
	 *  Purpose:	Output Hourly employee information unformatted
	 *  Receives:	None
	 *  Returns:	String outstr
	 *  Post:		String with the hourly employee information.
	 * *********************************************************/
	public String toString()
	{
		String outstr;
		
		outstr = super.toString() + "\nHours worked: " + this.hoursworked 
				+ "\nEmployee Hourly Rate: " + this.hourlyrate + "\nTotal Pay: $" 
				+ this.getTotalPay();
		
		return outstr;
	}
	
	/* ****************************************************************
	 *  Purpose: 	To calculate the pay of the employee
	 *  Returns: 	The pay for the employee.
	 *  Post:    	The pay has been calculated and the total pay 
	 *  			has been updated.
	 * ****************************************************************/
	@Override
	public double calculatePay() 
	{
		double paycheck = 0;
		
		if(this.hoursworked < 40)
		{
			paycheck =  this.hourlyrate * this.hoursworked;
			System.out.println("Employee has NOT earned overtime! ");
		}
		else
		{
			paycheck = (40 * this.hourlyrate) + ((this.hoursworked - 40)  * (this.hourlyrate * 1.5));
			System.out.println("Employee has NOT earned overtime! ");
		}

		// Update total pay.
		this.updateTotalPay(paycheck);
		return paycheck;
	}

}
