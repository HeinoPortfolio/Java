package Person;

import java.time.LocalDate;
import java.util.*;

public class MyDate 
{
	private int Month;
	private int Day;
	private int Year;
	
	// Constructors of the class.------------------------------------------
	public MyDate()
	{
		LocalDate local = LocalDate.now();
		
		Month = local.getMonthValue();
		Day = local.getDayOfMonth(); 
		Year = local.getYear();
	}
	
	public MyDate(int month, int day, int year)
	{
		Month = month;
		Day = day;
		Year = year;
	}
	
	// Setter. ---------------------------------------------------------
	
	public void setMonth(int month)
	{
		Month = month;
	}
	
	public void setDay(int  day)
	{
		Day = day;
	}
	
	public void setYear(int year)
	{
		Year = year;
	}
	
	// Getters. ---------------------------------------------------------
	public int getMonth()
	{
		return this.Month;
	}
	
	public int getDay()
	{
		return this.Day;
	}
	
	public int getYear()
	{
		return this.Year;
	}
	
	// Methods of the class. --------------------------------------------
	public String toString()
	{
		return (this.Month + " " + this.Day + "  " + this.Year );
	}
}
