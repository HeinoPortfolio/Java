package dataclasses;

public class Book 
{
		private String booktitle;
		private String[] Authors = new String[4];
		private String publisher;
		private String ISBN;
		private double price;
		private int number_of_copies;
		
		// Default constructor.
		/* *****************************************************************
		 *  Purpose:	Default constructor of the class
		 *  Receives:  	None
		 *  Post:		The object has been created
		 *  
		 * ****************************************************************/
		public Book()
		{
			this.booktitle = "TEST BOOK";
			this.publisher = "SOME PUB";
			
			int index  = 0;
			for(String ath : this.Authors)
			{
				this.Authors[index]  = "John Doe"; 
				index++;
			}
			
			this.ISBN = "ISBN";
			this.price = 0.00;
			this.number_of_copies = 0;
		}
		
		
		/* *****************************************************************
		 *  Purpose:	Explicit constructor of the class
		 *  Receives:  	title  	- String title
		 *  			auth	- String[] authors 
		 *  			pub 	- String publisher
		 *  			isbn 	- String ISBN
		 *  			price   - double price
		 *  			noc 	- int number of books in stock
		 *  
		 *  Post:		The object has been created
		 *  
		 * ****************************************************************/
		public Book(String title, String[] auth, String pub, String isbn, double price, int noc)
		{
			int index = 0;
			
			this.booktitle = title;
			for (String ath : auth)
			{
				this.Authors[index] = ath;
				index++;
			}
			
			this.publisher = pub;
			this.ISBN = isbn;
			this.price = price;
			this.number_of_copies = noc;	
		}
		
		// Getters.****************************************************************
		/* *********************************************************
		 *  Purpose:	Gets the title value
		 *  Returns:	booktitle - String
		 *  Post:		The booktitle variable has been returned.
		 * *********************************************************/
		public String getTitle()
		{
			return this.booktitle;
		}
		
		/* *********************************************************
		 *  Purpose:	Gets the authors value
		 *  Returns:	authors- String
		 *  Post:		The authors variable has been returned.
		 * *********************************************************/
		public String[] getAuthors()
		{
			return this.Authors;
		}
		
		/* *********************************************************
		 *  Purpose:	Gets the publisher value
		 *  Returns:	publisher- String
		 *  Post:		The publisher variable has been returned.
		 * *********************************************************/
		public String getPub()
		{
			return this.publisher;
		}
		
		/* *********************************************************
		 *  Purpose:	Gets the ISBN value
		 *  Returns:	ISBN- String
		 *  Post:		The ISBN variable has been returned.
		 * *********************************************************/
		public String getISBN()
		{
			return this.ISBN;
		}
		
		/* *********************************************************
		 *  Purpose:	Gets the price value
		 *  Returns:	price- String
		 *  Post:		The price variable has been returned.
		 * *********************************************************/
		public double getPrice()
		{
			return this.price;
		}
		
		/* *********************************************************
		 *  Purpose:	Gets the numberofcopies value
		 *  Returns:	numberofcopies- String
		 *  Post:		The numberofcopies variable has been returned.
		 * *********************************************************/
		public int getNumCopies()
		{
			return this.number_of_copies;
		}
		
		
		// Setters.****************************************************************
		/* *********************************************************
		 *  Purpose:	Sets the title value
		 *  Receives:	title -  String title
		 *  Post:		The title variable has been set
		 * *********************************************************/
		public void setTitle(String title)
		{
			this.booktitle = title;
		}
		
		/* *********************************************************
		 *  Purpose:	Sets the authors value
		 *  Receives:	auth -  String[] authors
		 *  Post:		The authors variable has been set
		 * *********************************************************/
		public void setAuthors(String[] auth)
		{
			int index = 0;
			
			for(String ath : auth)
			{
				this.Authors[index] = ath;
			}
		}
		
		/* *********************************************************
		 *  Purpose:	Sets the publisher value
		 *  Receives:	pub-  String publisher
		 *  Post:		The publisher variable has been set
		 * *********************************************************/
		public void setPublisher(String pub)
		{
			this.publisher = pub;
		}
		
		/* *********************************************************
		 *  Purpose:	Sets the ISBN value
		 *  Receives:	ISBN-  String isbn
		 *  Post:		The ISBN variable has been set
		 * *********************************************************/
		public void setISBN(String isbn)
		{
			this.ISBN = isbn;
		}
		
		/* *********************************************************
		 *  Purpose:	Sets the price value
		 *  Receives:	price -  String price
		 *  Post:		The price variable has been set
		 * *********************************************************/
		public void setPrice(double price)
		{
			this.price = price;
		}
		
		/* *********************************************************
		 *  Purpose:	Sets the numberofcopies value
		 *  Receives:	numberofcopies -  String noc
		 *  Post:		The numberofcopies  variable has been set
		 * *********************************************************/
		public void setNumOfCopies(int noc)
		{
			this.number_of_copies = noc;
		}
		
		//Methods of the class.
		
		/* *********************************************************
		 *  Purpose:	Output Book information unformatted
		 *  Receives:	None
		 *  Returns:	String outstr
		 *  Post:		String with the Book information.
		 * *********************************************************/
		public String toString()
		{
			String outstr;
			
			outstr = "Book title: " + this.booktitle + "\nAuthors: " + this.Authors[0] + "\n " 
									+ this.Authors[1] + "\n "+ this.Authors[2] + "\n " +
									this.Authors[3] + "\nPublisher: " + this.publisher 
									+ "\nISBN: " + this.ISBN + "\nPrice: " + this.price 
									+ "\nNumber of copies: " + this.number_of_copies ;
			
			return outstr;
		}
		
} // end class