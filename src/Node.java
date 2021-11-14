/**
 * Node class 
 * @author Ian MacLeod
 * @date 10/25/21
 *
 */
public class Node {
	
	//instance variables
	private boolean leaf;
	private String type;
	
	/**
	 * Non-Default Constructor
	 * @param isLeaf whether or not the node is a leaf
	 * @param type flag
	 */
	public Node(boolean isLeaf, String type) {
		leaf = isLeaf;
		this.type = type;
	}
	
	/**
	 * Returns type of node
	 * @return L, F, or E depending on node
	 */
	public String getType() {
		return type;
	}
	
	/**
	 * returns whether or not the node is a leaf
	 * @return leaf or not a leaf
	 */
	public boolean isLeaf() {
		return leaf;
	}
	
	/**
	 * Sets the node to be a leaf
	 * @param bool true
	 */
	public void setLeaf(boolean bool) {
		leaf = bool;
	}
	
	

}
