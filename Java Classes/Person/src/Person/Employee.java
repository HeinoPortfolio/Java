package Person;

public class Employee extends Person
{

	private double payrate;
	private double hoursworked;
	
	// Constructors. ------------------------------------------------
	public Employee()
	{
		super();
		payrate = 0;
		hoursworked = 0;
	}
	
	public Employee(String first, String last, double rate, double hours)
	{
		super(first, last);
		payrate = rate;
		hoursworked = hours;
	}
	
	
	// Setters. 0----------------------------------------------------------
	public void setRate(double rate)
	{
		payrate = rate;
	}
	
	public void setHours(double hours)
	{
		hoursworked = hours;
	}
	
	
	//Getters. ------------------------------------------------------------
	public double getHoursWorked()
	{
		return this.hoursworked;
	}
	
	public double getPayRate()
	{
		return this.payrate;
	}
	
	
	// Methods of the class--------------------------------------------------
	public double calculatePay()
	{
		double paycheck = 0;

		if(this.hoursworked < 40)
		{
			paycheck =  this.hoursworked * this.payrate;
		}
		else
		{
			paycheck = (40 * payrate) +
					((hoursworked - 40)  * (payrate * 1.5));
		}

		return paycheck;
	}
	
	public String toString()
	{
		return (super.toString() + " wages are: " + this.calculatePay());
	}
	

}
