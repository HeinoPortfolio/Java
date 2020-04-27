package CustomExceptions;

public class StackUnderflowException extends StackException{

	public StackUnderflowException() 
	{
		super("Stack Underflow !");
	}
	
	public StackUnderflowException(String errormsg) 
	{
		super(errormsg);		
	}

}
