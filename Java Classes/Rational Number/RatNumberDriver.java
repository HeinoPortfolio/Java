package rationalnumber;

public class RatNumberDriver {

	public static void main(String[] args) 
	{
		RationalNumber rat1 = new RationalNumber(-1, 4);
		RationalNumber rat2 = new RationalNumber(-3,4);
		RationalNumber sub1 = new RationalNumber(1,8);
		RationalNumber sub2 = new RationalNumber(1,2);
		RationalNumber mul1 = new RationalNumber(1,2);
		RationalNumber mul2 = new RationalNumber(1,4);
		RationalNumber div1 = new RationalNumber(3,4);
		RationalNumber div2 = new RationalNumber(1,4);
	
		
		//rat1.setNumDenom(3, 4);
		
		// Test the Addition of rational numbers
		RationalNumber sum;
		sum = rat1.addTwoRat(rat2);
		
		System.out.println(sum.toString());
		
		// Test the subtraction of rational numbers.
		
		RationalNumber sub;
		sub = sub1.subTwoRat(sub2);
		System.out.println(sub.toString());
		
		// Test the multiplication of rational numbers.
		RationalNumber mul;
		mul = mul1.mulTwoRat(mul2);
		System.out.println(mul.toString());
		
		//Test the division of two rational numbers.
		RationalNumber div;
		div = div1.divTwoRat(div2);
		System.out.println(div.toString());
		
		RationalNumber form = new RationalNumber(2,3);
		// Test formated output.
		form.formatOuput(5);
		
		RationalNumber who = new RationalNumber(1,3,5);
		
		who.formatOuput(3);
		
		
		
	}

}
