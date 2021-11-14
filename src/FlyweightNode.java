/**
 * Flyweight Node definition
 * @author Ian MacLeod
 * @date 10/25/21
 *
 */
public class FlyweightNode extends Node{
	
	/**
	 * Non-default Constructor
	 * @param isLeaf whether or not the node is a leaf
	 * @param type flag (Leaf, Internal, or Flyweight)
	 */
	public FlyweightNode(boolean isLeaf, String type) {
		super(isLeaf, type);
	}
	
	
}
