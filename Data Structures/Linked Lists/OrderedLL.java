package datastructures;

import dataclasses.DataElement;

public class OrderedLL extends LList 
{

	public OrderedLL() 
	{
		super();
	}

	public OrderedLL(LList tocopy) 
	{
		super(tocopy);
		
	}
	
	public void insertElement(DataElement insertItem)
	{
		LinkedListNode current ;
		//LinkedListNode trail ;
		LinkedListNode newNode = new LinkedListNode();
		//boolean found;
		
		//get a copy of the node to be inserted
		newNode.info = insertItem.getCopy();
		newNode.link = null;
		
		if(first == null)
		{
			first = newNode;
			this.count++;
		}
		else
		{
			current = first;
			while((current.link != null) && (current.link.info.compareTo(newNode.info) <= 0) )
			{
				current = current.link;
			}
			
			if(!current.info.equals(newNode.info))
			{
				newNode.link = current.link;
				current.link = newNode;
				this.count++;
			}
			else
			{
				System.out.println("\n  Item already in the list!  Duplicates are not allowed! \n");
			}
		}
		
	} // InsertElement
	
	@Override
	public boolean search(DataElement searchItem) 
	{
		LinkedListNode current = first;
		boolean found = false;
		
		while(current != null && !found)
		{
			if(current.info.compareTo(searchItem) >= 0)
			{
				found = true;
			}
			else 
			{
				current= current.link;
			}
		}//while
		
		if(found)
		{
			found = current.info.equals(searchItem);
		}	
		
		return found;
	}

	
	@Override
	public void deleteItem(DataElement deleteItem) 
	{
		LinkedListNode current;
		LinkedListNode trail;
		boolean found;
		
		if(first == null)
		{
			System.err.println("The list is empty. ");
		}// if
		else 
		{
			if(first.info.equals(deleteItem))
			{
				first = first.link;
				this.count--;
			}
			else 
			{
				found = false;
				trail = first;
				current = first.link;
				
				while(current != null && !found)
				{
					if(current.info.compareTo(deleteItem) >= 0)
					{
						found = true;
					}// if
					else 
					{
						trail = current;
						current = current.link;
					} // else
				} // while
				
				if(current == null)
				{
					System.err.println("\n\n**Item is not in the list.  Operation coulod not be completed!** \n");
				}
				else
				{
					if(current.info.equals(deleteItem))
					{
						if(first == current)
						{
							first = first.link;
							this.count--;
						}
						else 
						{
							trail.link = current.link;
							this.count--;
						}
					} // if
					else
					{
						System.err.println("\n\n**Item is not in the list.  Operation coulod not be completed!** \n");
					}
				} // else
			} // else inner
		} // else
		
		
	}

}





