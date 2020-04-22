package dataclasses;

public class TestAddress {

	public static void main(String[] args) 
	{
		Address newAddr = new Address();
		Address newAddr2 = new Address();
		newAddr2.setStreet("126 Popple Bridge Rd.");
		
		System.out.println(newAddr2.toString());
		
		System.out.println(newAddr.equals(newAddr2));
		
		System.out.println(newAddr.toString());
	}

}
