/**
 * Internal Node class
 * @author Ian MacLeod
 * @date 10/25/21
 */
public class InternalNode extends Node{
	
	//instance variables
	private Node A;
	private Node C;
	private Node T;
	private Node G;
	private Node $;
	
	/**
	 * Non-Default Constructor
	 * @param bool whether or not a leaf
	 * @param type I, L, or F depending on the node (will be I for internal nodes)
	 * @param fly flyweight
	 */
	public InternalNode(boolean bool, String type, FlyweightNode fly) {
		super(bool, type);
		this.A = fly;
		this.C = fly;
		this.G = fly;
		this.T = fly;
		this.$ = fly;
	}
	
	/**
	 * Gets the node A path
	 * @return
	 */
	public Node getA() {
		return A;
	}
	
	/**
	 * Sets the A path
	 * @param a Node to set on A path
	 */
	public void setA(Node a) {
		A = a;
	}
	
	/**
	 * Gets the node at C Path
	 * @return C path's node
	 */
	public Node getC() {
		return C;
	}
	
	/**
	 * Sets the node on the C path
	 * @param c Node to set
	 */
	public void setC(Node c) {
		C = c;
	}
	
	/**
	 * Gets the node at T path
	 * @return T path node
	 */
	public Node getT() {
		return T;
	}
	
	/**
	 * Sets the node on the T path
	 * @param t Node to be set
	 */
	public void setT(Node t) {
		T = t;
	}
	
	/**
	 * Gets the node at G path
	 * @return Node at G
	 */
	public Node getG() {
		return G;
	}
	
	/**
	 * Sets the node at G path
	 * @param Node to set
	 */
	public void setG(Node g) {
		G = g;
	}
	
	/**
	 * Gets the node at the $ path
	 * @return Node at $ path
	 */
	public Node get$() {
		return $;
	}
	
	/**
	 * Sets the node at $ path
	 * @param $ Node at $ path
	 */
	public void set$(Node $) {
		this.$ = $;
	}
	
	/**
	 * Sets all the Internal nodes pointers
	 * @param n Node to set 
	 * Don't think I end up using this method
	 */
	public void set(Node n) {
		if(n == A) {
			this.A = n;
		}else if(n == C) {
			this.C = n;
		}else if(n == T) {
			this.T = n;
		}else if(n == G) {
			this.G = n;
		}else {
			this.$ = n;
		}
	}
	
	
	
	

}
