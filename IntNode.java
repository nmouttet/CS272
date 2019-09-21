/*
 * @author Nathaniel Mouttet
 * 9-21-19
 * CS2729
 */
public class IntNode {
	private int data;
	private IntNode link;
	
	//Creates a node with the default values
	public IntNode()
	{
		data = 0;
		link = null;
	}
	
	//Creates a node with the value of _data linked to _link
	public IntNode(int _data, IntNode _link)
	{
		data = _data;
		link = _link;
	}
	
	//returns data
	public int getData()
	{
		return data;
	}
	
	//returns the link
	public IntNode getLink()
	{
		return link;
	}
	
	//Sets the data
	public void setData(int _data)
	{
		data = _data;
	}
	
	//set the link
	public void setLink(IntNode _link)
	{
		link = _link;
	}
	
	//returns all of the data in the intNode list as a string in the form 12->28->0->34
	public String toString()
	{
		IntNode cursor = this;
		//Gets the first instance
		String string = ""  + cursor.getData();
		cursor = cursor.getLink();
		while(cursor != null)
		{
			string += "->" + cursor.getData();
			cursor = cursor.getLink();
		}
		return string;
	}
	
	//Inserts a node between this node and the one it was linked to 
	public void addNodeAfterThis(int newdata)
	{
		IntNode link = this.link;
		IntNode newNode = new IntNode(newdata, link);
		this.setLink(newNode);
	}
	
	//removes a node after this one and replaces it with the one the removed one was linked to
	public void removeNodeAfterThis()
	{
//		IntNode remove = this.link;
//		IntNode replace = remove.link;
		this.setLink(this.getLink().getLink());		
	}
	
	//returns the number of nodes after a given node
	public static int listLength(IntNode head)
	{
		int counter = 0;
		IntNode cursor = head;
		while(cursor != null)
		{
			counter++;
			cursor = cursor.getLink();
		}
		return counter;
	}
	
	//returns a boolean value based on whether or not the list has a Node with the given data
	//head can't be null
	public static boolean search(IntNode head, int data)
	{
		if(head == null)
		{
			System.out.println("The starting node you entered is null");
			return false;
		}
		IntNode cursor = head;
		while(cursor != null)
		{
			if(cursor.getData() == data)
				return true;
			cursor = cursor.getLink(); 
		}
		return false;
	}	
}