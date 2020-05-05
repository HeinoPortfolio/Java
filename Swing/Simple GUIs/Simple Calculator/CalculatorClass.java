package customclasses;

public class CalculatorClass 
{
	private double numberOne;
	private double numberTwo;
	private char operator;
	
	public CalculatorClass()
	{
		this.numberOne = 0;
		this.numberTwo = 0;
	}
	
	public CalculatorClass(double num1, double num2)
	{
		this.numberOne = num1;
		this.numberTwo = num2;
	}

	// Metho0ds of the class.
	
	public char getOperation() {
		return operator;
	}

	public void setOperation(char operation) {
		this.operator = operation;
	}

	public double addTwoNumbers()
	{
		return this.numberOne + this.numberTwo;
	}
	
	public double subTwoNumbers()
	{
		return this.numberOne - this.numberTwo;
	}
	
	public double mulTwoNumber()
	{
		return this.numberOne * this.numberTwo;
	}
	public double divTwoNumbers() 
	{
		return this.numberOne / this.numberTwo;
	}      
	
	@Override
	public String toString() {
		return "CalculatorClass [numberOne=" + numberOne + ", numberTwo=" + numberTwo + ", getNumberOne()="
				+ getNumberOne() + ", getNumberTwo()=" + getNumberTwo() + ", getClass()=" + getClass() + ", hashCode()="
				+ hashCode() + ", toString()=" + super.toString() + "]";
	}

	public double getNumberOne() {
		return numberOne;
	}

	public void setNumberOne(double numberOne) {
		this.numberOne = numberOne;
	}

	public double getNumberTwo() {
		return numberTwo;
	}

	public void setNumberTwo(double numberTwo) {
		this.numberTwo = numberTwo;
	}
	
	public double translateOperation()
	{
		
		double result = 0.0;
		
		if(this.operator == '*')
		{
			result = this.mulTwoNumber();
		}
		else if(this.operator == '+')
		{
			result = this.addTwoNumbers();
		}
		else if(this.operator == '-')
		{
			result = this.subTwoNumbers();
		}
		else if(this.operator == '/')
		{
			result = this.divTwoNumbers();
		}
		
		return result;
		
	}
}
