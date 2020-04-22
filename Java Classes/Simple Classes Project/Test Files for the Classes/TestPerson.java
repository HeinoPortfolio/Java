package dataclasses;

public class TestPerson {

	public static void main(String[] args) 
	{
		Person newper = new Person();
		
		//System.out.println(newper.toString());
		
		Address addr = new Address("126 Hopping Bird Way","Tipp City", "OH", null, "45371", null, "USA");
		
		Person newper1 = new Person("Fred", "Flintstone","667-232-4856", addr); 
		
		//System.out.println("\n\n" + newper1.toString());
		
		// Do newper and newper1 equal
		//System.out.println(newper.equals(newper1));
		//System.out.println(newper.equals(newper));
		
		// set the address in newper.
		//newper.setAddr(new Address("10 Maxwell Ct.","Tipp City", "OH", null, "45371", null, "USA"));
		//System.out.println("\n\n" + newper.toString());
		
		// Get the address only
		//System.out.println("\n\n" + newper.getAddress().toString());
		/*
		Member newmem = new Member();
		
		System.out.println("Value of member: \n" +newmem);
		
		newmem.setAmountSpent(10.00);
		newmem.setNumberOfBooksBought(11);
		System.out.println("Value of member: \n" + newmem);
		
		// check the discot
		if (newmem instanceof Member)
		{
			double totalamount= newmem.calcTotalSpent(100.00);
			System.out.print(totalamount);
			System.out.println("\n\nValue of member: \n" + newmem);
		}
		else
		{
			System.out.println("\n\nNot a member: \n");
		}
		
		*/
		HourlyEmployee newemp = new HourlyEmployee();
		
		System.out.println("\n\nValue of Employee: \n" + newemp);
		
		newemp.setAddr(addr);
		newemp.setEmployeeID("45371");
		newemp.setHourlyRate(12.35);
		newemp.setHoursWorked(40);
		System.out.println("\n\nValue of Employee: \n" + newemp);
		
		// Check calc pay.
		newemp.calculatePay();
		System.out.println("\n\n\n\nValue of Employee: \n" + newemp);
	}

}
