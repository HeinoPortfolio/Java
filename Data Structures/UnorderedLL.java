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
