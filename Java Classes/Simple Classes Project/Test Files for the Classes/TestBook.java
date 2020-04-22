package dataclasses;

public class TestBook {

	public static void main(String[] args) 
	{
		Book tstBook = new Book();
		
		System.out.println(tstBook.toString());
		
		String[] auth;
		
		auth = tstBook.getAuthors();
		
		//int index = 0;
		for(String ath : auth)
		{
			System.out.println("Author: " + ath);
		}
		// Create a new Book.
		String[] authors = {"Steven King", "Richard Backman"};
		Book tstBook2 = new Book("The Shining", authors, "Penguin", "123456789", 2.30, 10);
		
		System.out.print(tstBook2.toString());
	}

}
