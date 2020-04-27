package CustomExceptions;

public class QueueUnderflowException extends StackException
{
		public QueueUnderflowException() 
		{
			super("Stack Underflow !");
		}
		
		public QueueUnderflowException(String errormsg) 
		{
			super(errormsg);		
		}

}
