package datastructures;


import dataclasses.Address;


public class TestLinkedList {

	public static void main(String[] args) 
	{
		//LList newlist = new LList();
		//UnorderedLL newlist = new UnorderedLL();
		
		//Address newAddr = new Address();
		//newlist.insertFirst(newAddr);				// 1
		//newlist.insertLast(newAddr);
		
		//newlist.printList();
		
		//String str, String cty, String stte, String cnty, 
		//String zp, String apt, String cnt
		
		/*
		Address newAddr1 = new Address("10 Maxwell Court", "Tipp City","OH", null,"45371", null,null);
		Address newAddr2 = new Address("10 Maxwell Court", "Tipp City","OH", null,"45371", null,null);
		Address newAddr3 = new Address("57 Reyam Rd", "Floral Park","NY", null,"11563", null,null);
		*/
		
		//System.out.println("\nAre they equal?  " + newlist.search(newAddr1));
		//System.out.println("\nAre they equal?  " + newlist.search(newAddr));
		
		// add to list
		//newlist.insertLast(newAddr1);   // 2
		//newlist.printList();
		
		//System.out.println("\nAre they in the list?  " + newlist.search(newAddr2));
		
		// Delete item from the list.
		//newlist.deleteItem(newAddr2);
		//newlist.printList();
		//System.out.println("Length of list: " + newlist.lenghtOfList());
		
		// Test the index. -----------------------------------------------------------------------------
		/*
		System.out.println("TESTING THE INDEX METHOD:   \n");
		int index = newlist.returnIndex(newAddr2);
		System.out.println("The index of the element is: " + index);
		newlist.insertLast(newAddr3);												// 3
		index = newlist.returnIndex(newAddr3);
		System.out.println("The index of the element is: " + index);
		newlist.printList();
		
		// Test the compare.-----------------------------------------------------------------------------
		
		int diff = -2;
		diff = newAddr3.compareTo(newAddr3);
		System.out.println("The difference: " + diff);
		*/
		
		
		// Create an ordered list ----------------------------------------------------------------------
		
		//Address newAddr1 = new Address("10 Maxwell Court", "Tipp City","OH", null,"45371", null,null);
		//Address newAddr2 = new Address("40 Maxwell Court", "Tipp City","OH", null,"45371", null,null);
		//Address newAddr3 = new Address("57 Reyam Rd", "Floral Park","NY", null,"11563", null,null);
		
		
		
		// ORDERED LIST TEST***********************************************************************************************
		/*
		OrderedLL orderLL = new OrderedLL();
		orderLL.insertElement(newAddr1);
		//System.out.println("Contents of the ordered list: " );
		//orderLL.printList();
				
		orderLL.insertElement(newAddr3);
		orderLL.insertElement(newAddr2);
		//System.out.println("\n\nContents of the ordered list: " );
		//orderLL.printList();
		
		// add duplicate -------------------------------------------------------------
		//orderLL.insertElement(newAddr1);
		//orderLL.insertElement(newAddr2);
		//orderLL.insertElement(newAddr3);
		//System.out.println("Contents of the ordered list(Duplicate): " );
		//orderLL.printList();
		
		System.out.println("Contents of the ordered list(Duplicate 2222): " );
		//orderLL.printList();
		Address newAddr4 = new Address("90 Reyam Rd", "Floral Park","NY", null,"11563", null,null);
		
		Address newAddr5 = new Address("90 Reyam Rd", "Floral Park","NY", null,"11563", null,null);
		
		// Not in the list.
		Address newAddr6 = new Address("40 Maxwell Court", "Tipp City","OH", null,"45371", null,null);
		Address newAddr7 = new Address("1 Maxwell Court", "Tipp City","OH", null,"45371", null,null);
		Address newAddr8 = new Address("40 Maxwell Way", "Tipp City","OH", null,"45371", null,null);
		
		//orderLL.insertElement(newAddr7);
		
		orderLL.insertElement(newAddr4);
		//System.out.println("length of list is: " + orderLL.lenghtOfList());
		orderLL.printList();
		
		// Test search method.---------------------------------------------------------
		//System.out.println("Is found:  " + orderLL.search(newAddr8));
		
		
		// Test the delete method.
		orderLL.deleteItem(newAddr7);
		
		System.out.println("Contents of the ordered list (DDelete 3333): " );
		orderLL.printList();
		
		orderLL.deleteItem(newAddr6);
		System.out.println("Contents of the ordered list (DDelete 3333): " );
		orderLL.printList();
		
		orderLL.deleteItem(newAddr4);
		System.out.println("Contents of the ordered list (DDelete 3333): " );
		orderLL.printList();
		
		// Initialize list to fresh list.
		orderLL.initializelist();
		System.out.println("Contents of the ordered list (Initialized): " );
		orderLL.printList();
		
		*/
		
		//Test insert at index Unrodred.
		UnorderedLL unorderLL = new UnorderedLL();
		Address newAddr1 = new Address("10 Maxwell Court", "Tipp City","OH", null,"45371", null,null);
		Address newAddr2 = new Address("8579 263 St.", "Tipp City","OH", null,"45371", null,null);
		Address newAddr3 = new Address("57 Reyam Rd", "Floral Park","NY", null,"11563", null,null);
		
		unorderLL.insertFirst(newAddr1);										// 1
		//System.out.println("Contents of the ordered list (Index): " );
		//unorderLL.printList();
		
		unorderLL.insertAtIndex(0, newAddr2);									// 2	
		//System.out.println("Contents of the ordered list (Index): " );
		//unorderLL.printList();
		
		
		// Insert in between.
		unorderLL.insertAtIndex(0, newAddr3);									// 3
		//System.out.println("Contents of the ordered list (Index): " );
		//unorderLL.printList();
		
		unorderLL.insertAtIndex(1, newAddr1);									//4
		//System.out.println("Contents of the ordered list (Index): " );
		unorderLL.printList();
		
		System.out.println("length of list is: " + unorderLL.lenghtOfList());
		
		
		// Test remove at position
		unorderLL.removeItemAtIndex(1);
		System.out.println("\n\nContents of the ordered list (REMOVE): " );
		unorderLL.printList();
		
		
	}

}












