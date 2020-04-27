/*LList.java
 * 
 * 	Author: Heino
 * 
 *  Purpose:  Defines a super class to model a linked list. 
 *  Note: This could be  accomplished using the concept of a Generics.
 * 
 * */
package datastructures;

import dataclasses.*;
import datastructures.LList.LinkedListNode;


public abstract class LList
{
	/* Inner class to model the node that will store the data 
	 * It will use a DataElement which all other data types that 
	 * can be used in this  list will be derived.
	 * 
	 * */
	protected class LinkedListNode
	{
		DataElement info;				// Will hold the data of type DataElement
		LinkedListNode link;			// Points to the next node in the list.
	}
	
	protected LinkedListNode first;		// The first node in the list
	protected LinkedListNode last;		// THe last node in the list.
	
	protected int count;				// Holds the number of nodes in the list.
	
	/* ***************************************************************
	 * Constructor for the list
	 * Receives: Nothing.  No parameters are passed
	 * Post: The list has been created.  
	 *****************************************************************/
	public LList()
	{
		this.first = null;
		this.last = null;
		this.count = 0;
	}
	
	/******************************************************************
	 * Copy constructor
	 * Purpose:  	To make a copy of another list.
	 * Receives:	LList object (to be copied)
	 * Post:		The list parameter has been copied and copy has been 
	 * 				made   
	 *******************************************************************/
	public LList(LList tocopy)
	{
		copy(tocopy);
	}
	
	/*******************************************************************
	 * Purpose: 	Initialized to an empty state.  
	 * Receives:	Nothing
	 * Post:		The list has been reset to an empty state and no 
	 * 				longer contains any nodes. 
	 ******************************************************************/
	public void initializelist()
	{
		this.first = null;
		this.last = null;
		this.count = 0;
	}
	
	// Methods of the class.***************************************************
	/*****************************************************************
	 * Purpose: 	Gets front element returns a copy of the element  
	 * Receives:	Nothing
	 * Returns:		A DataElement
	 * Post:		The DataElement has been returned.  
	 ******************************************************************/
	public DataElement front()
	{
		DataElement temp = first.info.getCopy();
		return temp;
	}
	
	/*******************************************************************
	 * Purpose: 	Gets back element returns a copy of the element  
	 * Receives:	Nothing
	 * Returns:		A DataElement
	 * Post:		The DataElement has been returned.  
	 ******************************************************************/
	public DataElement back()
	{
		DataElement temp = last.info.getCopy();
		return temp;
	}
	

	/******************************************************************
	 * Purpose: 	Insert first node into front of the list.  
	 * Receives:	DataElement
	 * Returns:		Nothing
	 * Post:		The DataElement has been inserted.  
	 ******************************************************************/
	public void insertFirst(DataElement newItem)
	{
		LinkedListNode newNode = new LinkedListNode();
		
		newNode.info = newItem.getCopy();
		newNode.link = first;
		
		first = newNode;
		
		if(last == null)
			last = newNode;
		
		count++;
		
	}
	
	/******************************************************************
	 * Purpose: 	Insert last node into front of the list.  
	 * Receives:	DataElement
	 * Returns:		Nothing
	 * Post:		The DataElement has been inserted.  
	 ******************************************************************/
	public void insertLast(DataElement newItem) 
	{ 
		LinkedListNode newNode = new LinkedListNode();
		
		newNode.info = newItem.getCopy();
		newNode.link = null;
		
		if(first == null)
		{
			first = newNode;
			last = newNode;
		}
		else
		{
			last.link = newNode;
			last = newNode;
		}
		count++;
	}
	
	
	/******************************************************************
	 * Purpose: 	Check the see if the list is empty.  
	 * Receives:	Nothing
	 * Returns:		boolean 
	 * Post:		Returned true if the list is empty or false if 
	 * 				it is not.  
	 ******************************************************************/
	public boolean isEmptyList()
	{
		return (first == null);
	}

	/******************************************************************
	 * Purpose: 	Make a copy of a list.  
	 * Receives:	LList
	 * Returns:		Nothing
	 * Post:		The list has been copied.  
	 ******************************************************************/
	private void copy(LList toCopy)
	{
		LinkedListNode newNode;
		LinkedListNode current;
		
		first = null;
		
		if(toCopy.first == null)
		{
			first = null;
			last = null;
			count = 0;
		}
		else
		{
			count = toCopy.count;
			current = toCopy.first;
			
			first = new LinkedListNode();
			first.info = current.info.getCopy();
			first.link = null;
			last = first;
			current = current.link;
			
			while(current != null)
			{
				newNode = new LinkedListNode();
				newNode.info = current.info.getCopy();
				newNode.link = null;
				last.link = newNode;
				last = newNode;
				current = current.link;
			} // while
		}// else
		
	} // copy
	
	/******************************************************************
	 * Purpose: 	Make a copy of a list.  
	 * Receives:	LList
	 * Returns:		Nothing
	 * Post:		The list has been copied.  
	 ******************************************************************/
	public void copyList(LList otherList) 
	{
		if(this != otherList)
			copy(otherList);
	}
	
	/******************************************************************
	 * Purpose: 	Print the list. 
	 * Receives:	Nothing
	 * Returns:		Nothing
	 * Post:		Contents of the list have printed.  
	 ******************************************************************/
	public void printList()
	{
		LinkedListNode current = this.first;
		
		while(current != null)
		{
			System.out.println(current.info);
			//Advance 
			current = current.link;
		}
	}
	
	/******************************************************************
	 * Purpose: 	Length of the list
	 * Receives:	Nothing
	 * Returns:		int count (class variable)
	 * Post:		length of the list has been returned.  
	 ******************************************************************/
	public int lenghtOfList()
	{
		return count;
	}
	
	/******************************************************************
	 * Purpose: 	Returns the index of an element
	 * Receives:	Nothing
	 * Returns:		int index
	 * Post:		index of item has been returned.  
	 ******************************************************************/
	public int returnIndex(DataElement indexItem)
	{
		int index = -1;
		boolean found = false;
		
		if(first == null)
		{
			System.out.println("The list is empty!");		// REMOVE
			index = -1;
		}
		else
		{	
			LinkedListNode current = first;
			
			while(current != null && !found)
			{
				if(current.info.equals(indexItem))
				{
					found = true;
					
				}
				else
					current = current.link;
					index++;
			}
		}
		
		if(found == false)
		{
			index =-1;
		}
		
		return index;
	}
	
	/******************************************************************
	 * Purpose: 	Removes the element at index.
	 * Receives:	int index
	 * Returns:		Nothing
	 * Post:		Element at the given index has been removed and the 
	 * 				count of nodes has been updated..  
	 ******************************************************************/
	public void removeItemAtIndex(int index) 
	{
		LinkedListNode current;
		
		current = first;
			
		if(index  < 0 || index >= this.count)
			System.err.println("List index out of bounds.  Cannot perform the operation!");
		else if(first == null)
			System.err.println("List is empty!.  Cannot perform the operation!");
		else if (index ==  0)
			first = first.link;
		else
		{
			current = this.first;
			
			for(int i = 0; i < index - 1; i++ )
			{
				current = current.link;
			}
			current.link = current.link.link;	
		}
		this.count--;	
	} // end RemoveItemAtIndex
	
	//Abstract methods.********************************************************
	/******************************************************************
	 * Purpose: 	Abstract method to search for an item in the list
	 * Receives:	DataElement
	 * Returns:		boolean
	 * Post:		True if the item is in the list.  
	 ******************************************************************/
	public abstract boolean search(DataElement searchItem);
	
	/******************************************************************
	 * Purpose: 	Abstract method to delete an item in the list
	 * Receives:	DataElement
	 * Returns:		Nothing
	 * Post:		The item has been deleted form the list.  
	 ******************************************************************/
	public abstract void deleteItem(DataElement deleteItem);
	
	
	
	
	
	
	
	
	
	
	
}
