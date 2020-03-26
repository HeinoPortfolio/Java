package Person;

public class Person {
	
	private String firstname;
	private String lastname;
	private String middlename;
	
	public Person()
	{
		firstname = "";
		lastname = "";
		middlename = "";
	}
	
	public Person(String first, String last)
	{
		firstname = first;
		lastname = last;
		middlename= "";
	}
	
	public Person(Person copyPerson)
	{
		firstname = copyPerson.firstname;
		middlename = copyPerson.middlename;
		lastname = copyPerson.lastname;
	}
	
	public String toString()
	{
		return (firstname + " " + lastname);
	}
	
	// Setters of the class-------------------------------------____
	public void setName(String first, String last, String middle)
	{
		firstname = first;
		middlename = middle;
		lastname = last;
	}
	
	public void setFirst(String first)
	{
		firstname = first;
	}
	
	public void setMiddle(String middle) 
	{
		middlename = middle;
	}
	
	public void setLast(String last)
	{
		lastname = last;
	}
	
	// Getters of the class. ----------------------------------------------
	public String getFirstName()
	{
		return firstname;
	}
	
	public String getLastName()
	{
		return lastname;
	}
	
	//Methods of the class. ----------------------------------------------
	
	public boolean checkLast(String last)
	{
		return  this.lastname == last;
	}
	
	public boolean checkFirst(String first) 
	{
		return this.firstname == first;
	}
	
	// Check to see if two objects return the same information
	
	public boolean equals(Person p1) 
	{
		return ((this.firstname == p1.firstname) && (this.lastname == p1.lastname));
	}
	
	public Person makeCopy(Person p1) 
	{
		//Person temp = new Person(p1.firstname, p1.lastname);
		Person temp = new Person(p1);
		
		//System.out.println(temp.firstname + " Lastname: " + temp.lastname);
		
		return temp;
	}
}
