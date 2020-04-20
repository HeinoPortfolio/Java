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
		
		public String getTitle()
		{
			return this.booktitle;
		}
		public String[] getAuthors()
		{
			return this.Authors;
		}
		
		public String getPub()
		{
			return this.publisher;
		}
		
		public String getISBN()
		{
			return this.ISBN;
		}
		
		public double getPrice()
		{
			return this.price;
		}
		
		public int getNumCopies()
		{
			return this.number_of_copies;
		}
		
		// Setters.*****************************************************
		public void setTitle(String title)
		{
			this.booktitle = title;
		}
		
		public void setAuthors(String[] auth)
		{
			int index = 0;
			
			for(String ath : auth)
			{
				this.Authors[index] = ath;
			}
		}
		
		public void setPublisher(String pub)
		{
			this.publisher = pub;
		}
		
		public void setISBN(String isbn)
		{
			this.ISBN = isbn;
		}
		
		public void setPrice(double price)
		{
			this.price = price;
		}
		
		public void setNumOfCopies(int noc)
		{
			this.number_of_copies = noc;
		}
		
		//Methods of the class.
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