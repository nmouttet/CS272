
public class IntNodeTest {

	public static void main(String[] args) 
	{
		System.out.println("Intializing 10 nodes");
		IntNode node1 = new IntNode();
		node1.setData(4);
		IntNode node2 = new IntNode();
		node1.setLink(node2);
		node2.setData(23);
		IntNode node4 = new IntNode();
		node4.setData(2);
		IntNode node3 = new IntNode(720, node4);
		node2.setLink(node3);
		IntNode node5 = new IntNode(2324, null);
		node4.setLink(node5);
		IntNode node6 = new IntNode(6, null);
		node5.setLink(node6);
		IntNode node7 = new IntNode(919499, null);
		node6.setLink(node7);
		IntNode node8 = new IntNode(5561, null);
		node7.setLink(node8);
		IntNode node9 = new IntNode(456789, null);
		node8.setLink(node9);
		IntNode node10 = new IntNode(202628, null);
		node9.setLink(node10);
		
		System.out.println("The nodes are as follows:");
		System.out.println(node1.toString());
		System.out.println();
		System.out.println("Adding a new node after the second node");
		node2.addNodeAfterThis(58);
		System.out.println("The new node list:");
		System.out.println(node1.toString());
		System.out.println("The curresnt size of the node list: " + IntNode.listLength(node1));		
		System.out.println();
		System.out.println("Removing the node after the fourth node");
		node3.removeNodeAfterThis();
		System.out.println("The new node list:");
		System.out.println(node1.toString());
		System.out.println("The curresnt size of the node list: " + IntNode.listLength(node1));
		System.out.println();
		System.out.println("Searching from the first node for the value 720: " + IntNode.search(node1, 720));
		System.out.println("Searching form the first node for the value 117: " + IntNode.search(node1, 117));
		
		
	}

}
