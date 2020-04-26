package datastructures;

import dataclasses.*;
import datastructures.LList.LinkedListNode;


public abstract class LList
{
	protected class LinkedListNode
	{
		DataElement info;
		LinkedListNode link;
	}
	
	protected LinkedListNode first;
	protected LinkedListNode last;
	
	protected int count;
	
	// Constructor
	public LList()
	{
		this.first = null;
		this.last = null;
		this.count = 0;
	}
	
	// Copy constructor
	public LList(LList tocopy)
	{
		copy(tocopy);
	}
	
	// Initialized to an empty state.
	public void initializelist()
	{
		this.first = null;
		this.last = null;
		this.count = 0;
	}
	
	// Methods of the class.***************************************************
	//get front returns a copy
	public DataElement front()
	{
		DataElement temp = first.info.getCopy();
		return temp;
	}
	
	//Get the back.
	public DataElement back()
	{
		DataElement temp = last.info.getCopy();
		return temp;
	}
	
	// Insert first node
	public void insertFirst(DataElement newItem)
	{
		LinkedListNode newNode = new LinkedListNode();
		
		newNode.info = newItem.getCopy();
		newNode.link = first;
		
		first = newNode;
		
		if(last == null)
		{
			last = newNode;
		}
		
		count++;
		
	}
	
	// Insert last node
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
	
	// Check the see if the list is empty.
	public boolean isEmptyList()
	{
		return (first == null);
	}
	
	// Make a copy of a list.
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
	
	// <akes a copy of the lkist.
	public void copyList(LList otherList) 
	{
		if(this != otherList)
		{
			copy(otherList);
		}
	}
	
	//Print the list.
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
	
	// Length of the list
	public int lenghtOfList()
	{
		return count;
	}
	
	// Returns the index of an element
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
	
	public void removeItemAtIndex(int index) 
	{
		
		
		LinkedListNode current;
		
		
		current = first;
		
		
		if(index  < 0 || index >= this.count)
		{
			System.err.println("List index out of bounds.  Cannot perform the operation!");
		}
		else if(first == null)
		{
			System.err.println("List is empty!.  Cannot perform the operation!");
		}
		else if (index ==  0)
		{

			first = first.link;
		}
		
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
	
	//Abstract methods.*******************************************************
	public abstract boolean search(DataElement searchItem);
	
	
	
	
	public abstract void deleteItem(DataElement deleteItem);
	
	
	
	
	
	
	
	
	
	
	
}
