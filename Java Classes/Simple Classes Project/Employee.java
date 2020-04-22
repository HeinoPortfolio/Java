/* Employee.java
 * 	
 *  Purpose: 	This designs a class to model data that is common 
 *  			to all employees. It is a subclass of the Person
 *  			class.  It has one abstract method 
 *  			1.  calculatePay() - used to calculate pay of 
 *  			the employee.  This method will be implemented 
 *  			in any subclass of Employee
 * 
 ************************************************************/
package dataclasses;

public abstract class Employee extends Person
{

	private String employeeID;
	private String socialsecnum;
	private String jobtitle;
	private Address empAddr;
	private int age;
	private double totalpay;
	
	//Constructors of the class.*************************************************
	/* Purpose:  	Default constructor of the class.
	 * Receives:	None
	 * Post:		The object has been initialized and created.
	 * ***************************************************************************/
	public Employee()
	{
		super();
		this.totalpay = 0.0;
		this.employeeID = "0000000000000";
		this.socialsecnum = null;
		this.jobtitle = "TEMP";
		this.empAddr = new Address();
		this.age = 150;
	}

	/* Purpose:  	Explicit constructor of the class.
	 * Receives:	first - String
	 * 				last - String
	 * 				phn  - String (phone number)
	 * 				addr - Address object
	 * Post:		The object has been initialized and created.
	 * ***************************************************************************/
	public Employee(String first, String last, String phn, Address addr)
	{
		super(first, last, phn, addr);

		this.totalpay = 0.0;
		this.employeeID = "0000000000000";
		this.socialsecnum = null;
		this.jobtitle = "TEMP";
		this.empAddr = new Address();
		this.age = 150;
	}

	//Setters.****************************************************************
	/* *********************************************************
	 *  Purpose:	Sets the employee ID
	 *  Receives:	ID - String
	 *  Post:		The employeeID variable has been set
	 * *********************************************************/
	public void setEmployeeID(String ID)
	{
		this.employeeID = ID;
	}
	
	/* *********************************************************
	 *  Purpose:	Sets the employee SSN
	 *  Receives:	ssn - String
	 *  Post:		The socialsecnum variable has been set
	 * *********************************************************/
	public void setSSN(String ssn)
	{
		this.socialsecnum = ssn;
	}
	
	/* *********************************************************
	 *  Purpose:	Sets the employee job title
	 *  Receives:	title - String
	 *  Post:		The jobtitle variable has been set
	 * *********************************************************/
	public void setJobTitle(String title)
	{
		this.jobtitle = title;
	}
	
	
	//Getters of the class.****************************************************
	/* *********************************************************
	 *  Purpose:	Gets the total pay for the employee
	 *  Receives:	None
	 *  Returns:	totalpay - String
	 *  Post:		The totalpay variable has been returned.
	 * *********************************************************/
	public double getTotalPay()
	{
		return this.totalpay;
	}
	
	// Methods of the class.****************************************************
	/* *********************************************************
	 *  Purpose:	Output employee information unformatted
	 *  Receives:	None
	 *  Returns:	String outstr
	 *  Post:		String with the employee information.
	 * *********************************************************/
	public String toString()
	{
		String outstr;
		
		outstr = super.toString()+ "\nAge of employee: " + this.age +"\nEmployee ID: "
				+ this.employeeID + "\nSSN: " + this.socialsecnum + "\nJob title: " + this.jobtitle 
				+ "\nTotal Pay: " + this.totalpay ;
		
		return outstr;
	}
	
	public void updateTotalPay(double pay)
	{
		this.totalpay = this.totalpay + pay;
	}
	
	
	public abstract double calculatePay();
}
