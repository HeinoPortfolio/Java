package datastructures;

import CustomExceptions.QueueUnderflowException;
import dataclasses.DataElement;

public class QueueList extends UnorderedLL {

	public QueueList() {
		super();
	}

	public QueueList(QueueList qll) {
		super(qll);
		
	}
	
	public void initializeQueue()
	{
		this.initializelist();
	}
	
	public boolean isEmptyQueue()
	{
		return this.isEmptyList();
	}

	public void addQueue(DataElement item)
	{
		this.insertLast(item);
	}
	
	public DataElement front()
	{
		return super.front();
	}
	
	public DataElement back()
	{
		return super.back();  
	}
	
	public void copyQueue(QueueList copyqueue)
	{
		this.copyList(copyqueue);
	}
	
	public void deleteFront() throws QueueUnderflowException
	{
		if (this.isEmptyQueue())
			throw new QueueUnderflowException("The Queue is empty!");
		this.first = first.link;
		this.count--;
		
		if(this.first == null)
			last = null;
	}
}











