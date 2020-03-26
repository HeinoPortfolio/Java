package Person;

import java.io.*;

public class TestPerson {

	public static void main(String[] args) 
	{
		Person name = new Person ();   // Empty object
		Person name1 = new Person ();
		
		Person emp = new Person ("Donald", "Doc");  // Object with values;
		
		//System.out.println("Contents: " + name);
		//System.out.println("Contents: " + emp);
		
		// Checks the last name.
		System.out.println("Last name the same: " + emp.checkLast("Doc"));
		
		// Checks to see if the two names are equal.
		System.out.println("Are P1 and P2 equal: " + emp.equals( new Person("Donald","Doc")));
		
		// Makes a copy of a Person object.
		name1 = emp.makeCopy(emp);			// Makes a copy of the object
		
		System.out.println("Contents name 1: " + name1);
		
		// Changes the name of the object.
		name1.setFirst("Yohan");
		
		System.out.println("Contents name 1: " + name1);
		
		System.out.println("Contents emp 1: " + emp);
		
		//Employee Class ----------------------------------------------------------
		
		Employee ep2 = new Employee("Roger", "Moore", 23.00, 70);
		
		System.out.println("Content of employee: " + ep2);
		
		MyDate myd = new MyDate();
		
		System.out.println("Content of date " + myd);
		
		System.out.println("Value of class variables: " + myd.getDay());	
		
	} // end main.

}
