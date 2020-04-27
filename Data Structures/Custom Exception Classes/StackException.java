package CustomExceptions;

public class StackException extends RuntimeException
{
	public StackException()
	{
		
	}
	
	public StackException(String errormsg)
	{
		super(errormsg);
	}
}
