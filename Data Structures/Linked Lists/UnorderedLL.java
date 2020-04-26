package datastructures;

import dataclasses.DataElement;

public class UnorderedLL extends LList 
{

	public UnorderedLL() 
	{
		super();
	}
	
	public UnorderedLL(UnorderedLL ull)
	{
		super(ull);
	}

	@Override
	public boolean search(DataElement searchItem) 
	{
		boolean found = false;
		
		LinkedListNode current = first;
		
		while(current != null && !found)
		{
			if(current.info.equals(searchItem))
			{
				found = true;
			}
			else
				current = current.link;
		}
		
		return found;
		
	}
	
	public void insertAtIndex(int index, DataElement insertItem)
	{
		int indexcnt = 0;
		LinkedListNode current;
		LinkedListNode prev;
		LinkedListNode newNode = new LinkedListNode();
		
		
		if (index < 0  || index > this.lenghtOfList())
		{
			System.err.println("Index is out of bounds.  Operation could not be completed!");		
		}
		else 
		{
			
			newNode.info = insertItem.getCopy();
			newNode.link = null;
			
			// Insert at the head of the list
			if (index == 0)
			{
				this.insertFirst(insertItem);
			}
			else if(index == this.count)
			{
				System.out.println("ENTERED ELSE IF");
				this.last.link = newNode;
				last = newNode;
				this.count++;
			}
			else
			{
				current = this.first;
				prev = null;
				
				while(indexcnt < index) 
				{
					prev = current;
					current = current.link;
					
					if(current == null)
					{
						break;
					}
					
					indexcnt++;
				}
				
				// Assign value.
				newNode.link = current;
				prev.link = newNode;
				this.count++;
				
			} //else
		} // else
	}// insertItemAtIndex
	
	
	

	@Override
	public void deleteItem(DataElement deleteItem) 
	{
		LinkedListNode current;
		LinkedListNode trail;
		boolean found = false;
		
		
		if(first == null)
			System.out.println("\n Empty list.  Cannot perform operation");
		else
		{
			if(first.info.equals(deleteItem))
			{
				first = first.link;
				
				if (first ==null)
				{
					last = null;
				}
				count--;
			}
			else
			{
				trail = first;
			 
				current = first.link;
				
				while(current != null && !found)
				{
					if(current.info.equals(deleteItem))
					{
						found = true;
					}
					else
					{
						trail = current;
						current = current.link;
					}
				} // while
				
				if(found)
				{
					count--;
					trail.link = current.link;
					
					if(last == current)
					{
						last = trail;
						
					}
				}
				else
				{
					System.out.println("Item is not in the list!");
				}
			}
			
			
		}
	}

}
