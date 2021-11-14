/**
 * LeafNode Definition
 * @author Ian MacLeod
 * @date 10/25
 *
 */
public class LeafNode extends Node{
	
	//instance variables
	private String seq;
	boolean leaf = true;
	
	/**
	 * Non-Default Constructor
	 * @param s Sequence
	 * @param bool Whether or not the node is a leaf
	 * @param type L I or F
	 */
	public LeafNode(String s, boolean bool, String type) {
		super(bool, type);
		seq = s;
	}
	
	/**
	 * Method that returns true since all instances are leafs
	 */
	public boolean isLeaf() {
		return true;
	}
	
	/**
	 * Method that grabs the sequence of the node
	 * @return sequence
	 */
	public String getSeq() {
		return seq;
	}

}
