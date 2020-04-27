package datastructures;

import CustomExceptions.StackUnderflowException;
import dataclasses.DataElement;

public class StackLinkList extends UnorderedLL
{
	
	/* ***************************************************************
	 * Constructor for the stack
	 * Receives: Nothing.  No parameters are passed
	 * Post: The list has been created.  
	 *****************************************************************/
	public StackLinkList()
	{
		super();
	}
	
	// Copy Constructor.
	/******************************************************************
	 * Copy constructor
	 * Purpose:  	To make a copy of another stack.
	 * Receives:	UnorderedLL object (to be copied)
	 * Post:		The stack parameter has been copied and copy has been 
	 * 				made   
	 *******************************************************************/
	public  StackLinkList(StackLinkList otherStack) 
	{
		super(otherStack);
	}
	
	// Set the stack to an initial state.
	/*******************************************************************
	 * Purpose: 	Initialized to an empty state.  
	 * Receives:	Nothing
	 * Post:		The stack has been reset to an empty state and no 
	 * 				longer contains any nodes. 
	 ******************************************************************/
	public void initializeStack()
	{
		this.initializelist();
	}
	
	/******************************************************************
	 * Purpose: 	Check the see if the stack is empty.  
	 * Receives:	Nothing
	 * Returns:		boolean 
	 * Post:		Returned true if the stack is empty or false if 
	 * 				it is not.  
	 ******************************************************************/
	public boolean  isEmptyStack()
	{
		return this.isEmptyList();
	}
	
	/******************************************************************
	 * Purpose: 	Returns the a copy of the top element of the 
	 * 				stack. 
	 * Receives:	Nothing
	 * Returns:		DataElement
	 * Post:		Returned DataElement at the top of the stack.  
	 * 				It has not been removed. 
	 ******************************************************************/
	public DataElement peek() throws StackUnderflowException
	{
		
		if(this.first == null)
			throw new StackUnderflowException("The Stack is Empty! ");

		else 
			return this.first.info.getCopy();
	}
	
	/******************************************************************
	 * Purpose: 	Pushes an element on to the top of the stack 
	 * 				stack. 
	 * Receives:	DataElement
	 * Returns:		Nothing
	 * Post:		The element has been added to the top of the stack. 
	 ******************************************************************/
	public void push(DataElement item)
	{
		this.insertFirst(item);
	}
	
	/******************************************************************
	 * Purpose: 	Retrieves an element from  the top of the stack 
	 * 				stack. 
	 * Receives:	Nothing
	 * Returns:		DataElement
	 * Post:		The a copy of the top element has been returned.  
	 * 				The top element has not been removed. 
	 ******************************************************************/
	public DataElement top() throws StackUnderflowException
	{
		if(this.first == null)
			throw new StackUnderflowException("The Stack is Empty! ");
		else 
			return this.front();
	}
	
	/******************************************************************
	 * Purpose: 	Removes an element from  the top of the stack 
	 * 				stack. 
	 * Receives:	Nothing
	 * Returns:		Nothing
	 * Post:		The top element has been removed. And the count 
	 * 				has been updated by the parent class method.
	 ******************************************************************/
	public void pop() throws StackUnderflowException
	{
		if(this.first == null)
			throw new StackUnderflowException("The Stack is Empty! ");
		
		this.first = first.link;
		this.count--;
			
		if(this.first == null)
			this.last = null;
	} 
	
	/******************************************************************
	 * Purpose: 	Makes a copy of the stack 
	 * 				stack. 
	 * Receives:	StackLinkList
	 * Returns:		Nothing
	 * Post:		The stack has been copied
	 ******************************************************************/
	public void copyStack(StackLinkList copyStack)
	{
		this.copyList(copyStack);
	}
}
