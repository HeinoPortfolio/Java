package datastructures;


import dataclasses.Address;


public class TestLinkedList {

	public static void main(String[] args) 
	{
		//LList newlist = new LList();
		UnorderedLL newlist = new UnorderedLL();
		
		Address newAddr = new Address();
		newlist.insertFirst(newAddr);				// 1
		//newlist.insertLast(newAddr);
		
		//newlist.printList();
		
		//String str, String cty, String stte, String cnty, 
		//String zp, String apt, String cnt
		
		Address newAddr1 = new Address("10 Maxwell Court", "Tipp City","OH", null,"45371", null,null);
		Address newAddr2 = new Address("10 Maxwell Court", "Tipp City","OH", null,"45371", null,null);
		Address newAddr3 = new Address("57 Reyam Rd", "Floral Park","NY", null,"11563", null,null);
		
		//System.out.println("\nAre they equal?  " + newlist.search(newAddr1));
		//System.out.println("\nAre they equal?  " + newlist.search(newAddr));
		
		// add to list
		newlist.insertLast(newAddr1);   // 2
		newlist.printList();
		
		//System.out.println("\nAre they in the list?  " + newlist.search(newAddr2));
		
		// Delete item from the list.
		//newlist.deleteItem(newAddr2);
		newlist.printList();
		System.out.println("Length of list: " + newlist.lenghtOfList());
		
		// Test the index.
		
		System.out.println("TESTING THE INDEX METHOD:   \n");
		int index = newlist.returnIndex(newAddr2);
		System.out.println("The index of the element is: " + index);
		newlist.insertLast(newAddr3);												// 3
		index = newlist.returnIndex(newAddr3);
		System.out.println("The index of the element is: " + index);
		newlist.printList();

	}

}
