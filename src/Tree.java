import java.util.*;
/**
 * 5 Way Tree Definition
 * @author Ian MacLeod
 * @date 10/25/21
 */
public class Tree {
	
	//instance variables
	private Node rt;
	private FlyweightNode fly;
	//int depth = 0;
	List<String> existingSequences;
	int internalCount = 0;
	int flyCount = 0;
	int leafCount = 0;
	static int count;
	String temp;   
	String visited;
	List<String> visitedAll = new ArrayList<String>();
	List<String> visitedExact = new ArrayList<String>();
	int max = 0;
	String x;
	
	/**
	 * Constructor that initializes the root to a flyweight and an arraylist to store exisiting seqeuences
	 */
	public Tree() {
		fly = new FlyweightNode(true, "F");
		rt = fly;
		existingSequences = new ArrayList<String>();
	}
	
	/**
	 * Recursive method that inserts a sequence into the tree
	 * @param rt Root of the tree
	 * @param seq Sequence of the leaf
	 * @param depth current depth
	 * @return returns rt after the insertion is complete, with the node in the right place
	 */
	private Node insertAt(Node rt, String seq, int depth) {
		if(rt.getType().equals("F")) { //if its a flyweight
			rt = new LeafNode(seq, true, "L");
			if(!existingSequences.contains(seq)) {
				System.out.println("sequence " + seq + " inserted at level " + depth);
				existingSequences.add(seq);
			}
		}else if(rt.getType().equals("L")) { //If its a leaf
			this.temp = ((LeafNode)rt).getSeq();
			
			rt = new InternalNode(false, "I", fly); 
			
			int i = 1;
			if(i == 1) {
				
				try {
					if(temp.charAt(depth) == 'A') {
						((InternalNode)rt).setA(insertAt(((InternalNode)rt).getA(), temp, depth + 1));
						
					}
					
					if(temp.charAt(depth) == 'C') {
						((InternalNode)rt).setC(insertAt(((InternalNode)rt).getC(), temp, depth + 1));
						
					}
					
					if(temp.charAt(depth) == 'T') {
						((InternalNode)rt).setT(insertAt(((InternalNode)rt).getT(), temp, depth + 1));
						
					}
					
					if(temp.charAt(depth) == 'G') {
						((InternalNode)rt).setG(insertAt(((InternalNode)rt).getG(), temp, depth + 1));
						
					}
				}catch(StringIndexOutOfBoundsException e) {
					((InternalNode)rt).set$(insertAt(((InternalNode)rt).get$(), temp, depth + 1));
				}
				
				
				try {
					if(seq.charAt(depth) == 'A') {
						((InternalNode)rt).setA(insertAt(((InternalNode)rt).getA(), seq, depth + 1));
						
					}
					
					if(seq.charAt(depth) == 'C') {
						
						((InternalNode)rt).setC(insertAt(((InternalNode)rt).getC(), seq, depth + 1));
					}
					
					if(seq.charAt(depth) == 'G') {
						
						((InternalNode)rt).setG(insertAt(((InternalNode)rt).getG(), seq, depth + 1));
					}
					
					if(seq.charAt(depth) == 'T') {
						((InternalNode)rt).setT(insertAt(((InternalNode)rt).getT(), seq, depth + 1));
						
					}
				}catch(StringIndexOutOfBoundsException e) {
					
					((InternalNode)rt).set$(insertAt(((InternalNode)rt).get$(), seq, depth + 1));
					
					
				}
				
				
			}else {
				int count = depth;
				while(temp.charAt(depth) == seq.charAt(depth)) {				
					if(seq.charAt(depth) == 'A' && temp.charAt(depth) == 'A') {
						((InternalNode)rt).setA(new InternalNode(false, "I", fly));
						((InternalNode)rt).setA(insertAt(((InternalNode)rt).getA(), temp, depth + 1));	
					}
				}	 
			}
					
		}else { //internal
			
			try {
				if(seq.charAt(depth) == 'A') {
					((InternalNode)rt).setA(insertAt(((InternalNode)rt).getA(), seq, depth + 1));
				}
				
				if(seq.charAt(depth) == 'C') {
					
					((InternalNode)rt).setC(insertAt(((InternalNode)rt).getC(), seq, depth + 1));
				}
				
				if(seq.charAt(depth) == 'G') {
					
					((InternalNode)rt).setG(insertAt(((InternalNode)rt).getG(), seq, depth + 1));
				}
				
				if(seq.charAt(depth) == 'T') {
					((InternalNode)rt).setT(insertAt(((InternalNode)rt).getT(), seq, depth + 1));
					
				}
			}catch(StringIndexOutOfBoundsException e) {
				((InternalNode)rt).set$(insertAt(((InternalNode)rt).get$(), seq, depth + 1));
			}
			
			
			
		}
		
		return rt;
	}
	
	/**
	 * Insert Helper method that calls the recursive method
	 * @param seq Sequence to be added
	 */
	public void insert(String seq) {
		if(existingSequences.contains(seq)) {
			System.out.println("sequence " + seq + " already exists");
			return;
		}
		
		rt = insertAt(rt, seq, 0);
	}
	
	/**
	 * Method that prints the tree via the preorder traversal
	 * @param n root node to start
	 * @param d current depth
	 */
	private void preorder(Node n, int d) {
		if(n != null) {
			String spaces = "";
			for(int i = 0; i < d; i++) {
				spaces = spaces + "  ";
			}
			if(n.getType().equals("L")) {
				if(existingSequences.size() <= 1) {
					System.out.println(((LeafNode)n).getSeq());
				}else {
					System.out.println(spaces + ((LeafNode)n).getSeq());
				}
				
			}
			
			if(n.getType().equals("I")) {
				if(internalCount < 1) {
					System.out.println(spaces + "I");
				}else {
					
					
					System.out.println(spaces + "I");
				}
				
				preorder(((InternalNode)n).getA(), d + 1);
				preorder(((InternalNode)n).getC(), d + 1);
				preorder(((InternalNode)n).getG(), d + 1);
				preorder(((InternalNode)n).getT(), d + 1);
				preorder(((InternalNode)n).get$(), d + 1);
			}
			
			if(n.getType().equals("F")) {
				
				System.out.println(spaces + "E");
			}
		}
	}
	
	/**
	 * print helper method
	 */
	public void print() {
		System.out.println("tree dump:");
		preorder(rt, 0);
	}
	
	/**
	 * Printing method that prints the sequences lengths as well as the entire tree via preorder traversal
	 * @param n root node to start
	 * @param d depth of node
	 */
	private void printLengths(Node n, int d) {
		if(n != null) {
			String spaces = "";
			for(int i = 0; i < d; i++) {
				spaces = spaces + "  ";
			}
			if(n.getType().equals("L")) {
				if(existingSequences.size() <= 1) {
					System.out.println(((LeafNode)n).getSeq() + " " + ((LeafNode)n).getSeq().length());
				}else {
					
					System.out.println(spaces + ((LeafNode)n).getSeq() + " "  + ((LeafNode)n).getSeq().length());
				}
				
			}
			
			if(n.getType().equals("I")) {
				if(internalCount < 1) {
					System.out.println(spaces + "I");
				}else {
					
					
					System.out.println(spaces + "I");
				}
				
				printLengths(((InternalNode)n).getA(), d + 1);
				printLengths(((InternalNode)n).getC(), d + 1);
				printLengths(((InternalNode)n).getG(), d + 1);
				printLengths(((InternalNode)n).getT(), d + 1);
				printLengths(((InternalNode)n).get$(), d + 1);
			}
			
			if(n.getType().equals("F")) {
				
				System.out.println(spaces + "E");
			}
		}
	}
	
	/**
	 * print lengths helper method
	 */
	public void lengths() {
		System.out.println("tree dump:");
		printLengths(rt, 0);
	}
	
	/**
	 * Recursive method that prints the tree and the breakdown percentages of each letter in the leaf's sequence
	 * @param n root node
	 * @param d depth
	 */
	private void printStats(Node n, int d) {
		
		if(n != null) {
			String spaces = "";
			for(int i = 0; i < d; i++) {
				spaces = spaces + "  ";
			}
			if(n.getType().equals("L")) {
				if(existingSequences.size() <= 1) {
					
					double Acount = 0;
					double Ccount = 0;
					double Gcount = 0;
					double Tcount = 0;
					for(int i = 0; i < ((LeafNode)n).getSeq().length(); i++) {
						if(((LeafNode)n).getSeq().charAt(i) == 'A') {
							Acount++;
						}else if(((LeafNode)n).getSeq().charAt(i) == 'C') {
							Ccount++;
						}else if(((LeafNode)n).getSeq().charAt(i) == 'G') {
							Gcount++;
						}else if(((LeafNode)n).getSeq().charAt(i) == 'T') {
							Tcount++;
						}
					}
					
					
					System.out.printf(spaces + ((LeafNode)n).getSeq() + " A:%.2f " + "C:%.2f " + "G:%.2f " + "T:%.2f\n", Acount / ((LeafNode)n).getSeq().length() * 100, 
							Ccount / ((LeafNode)n).getSeq().length() * 100, Gcount / ((LeafNode)n).getSeq().length() * 100, Tcount / ((LeafNode)n).getSeq().length() * 100);
					
				}else {
					double Acount = 0;
					double Ccount = 0;
					double Gcount = 0;
					double Tcount = 0;
					for(int i = 0; i < ((LeafNode)n).getSeq().length(); i++) {
						if(((LeafNode)n).getSeq().charAt(i) == 'A') {
							Acount++;
						}else if(((LeafNode)n).getSeq().charAt(i) == 'C') {
							Ccount++;
						}else if(((LeafNode)n).getSeq().charAt(i) == 'G') {
							Gcount++;
						}else if(((LeafNode)n).getSeq().charAt(i) == 'T') {
							Tcount++;
						}
					}
					
					
					System.out.printf(spaces + ((LeafNode)n).getSeq() + " A:%.2f " + "C:%.2f " + "G:%.2f " + "T:%.2f\n", Acount / ((LeafNode)n).getSeq().length() * 100, 
							Ccount / ((LeafNode)n).getSeq().length() * 100, Gcount / ((LeafNode)n).getSeq().length() * 100, Tcount / ((LeafNode)n).getSeq().length() * 100);
					
					
				}
				
			}
			
			if(n.getType().equals("I")) {
				if(internalCount < 1) {
					System.out.println(spaces + "I");
				}else {
					
					
					System.out.println(spaces + "I");
				}
				
				printStats(((InternalNode)n).getA(), d + 1);
				printStats(((InternalNode)n).getC(), d + 1);
				printStats(((InternalNode)n).getG(), d + 1);
				printStats(((InternalNode)n).getT(), d + 1);
				printStats(((InternalNode)n).get$(), d + 1);
			}
			
			if(n.getType().equals("F")) {
				
				System.out.println(spaces + "E");
			}
		}
	}
	
	/**
	 * print stats helper method
	 */
	public void stats() {
		System.out.println("tree dump:");
		printStats(rt, 0);
	}
	
	/**
	 * Searching recursive method that searches for an exact match of the sequence and counts the nodes visited
	 * @param rt root
	 * @param seq seq to search for
	 * @param depth depth
	 * @return whether or not the sequence has been found
	 */
	private boolean searchExact(Node rt, String seq, int depth) {
		if(rt.getType().equals("L")) {
			if(((LeafNode)rt).getSeq().equals(seq)) {
				visited = "# of nodes visited: " + (1 + depth);
				System.out.println(visited);
				System.out.println("sequence: " + seq);
				
				return true;
			}else {
				visited = "# of nodes visited: " + (1 + depth);
				System.out.println(visited);
				return false;
			}
		}else if(rt.getType().equals("I")) {
			try {
				if(seq.charAt(depth) == 'A') {
					return searchExact(((InternalNode)rt).getA(), seq, depth + 1);
				}
				
				if(seq.charAt(depth) == 'C') {
					return searchExact(((InternalNode)rt).getC(), seq, depth + 1);
				}
				
				if(seq.charAt(depth) == 'G') {
					return searchExact(((InternalNode)rt).getG(), seq, depth + 1);
				}
				
				if(seq.charAt(depth) == 'T') {
					return searchExact(((InternalNode)rt).getT(), seq, depth + 1);
				}
			}catch(StringIndexOutOfBoundsException e) {
				return searchExact(((InternalNode)rt).get$(), seq, depth + 1);
			}
		}else {
			visited = "# of nodes visited: " + (1 + depth);
			System.out.println(visited);
			return false;
		}
		
		return false;
		
		
	}
	
	/**
	 * Search exact helper method
	 * @param seq sequence to search for
	 */
	public void searchExactMatch(String seq) {
		
		boolean found = searchExact(rt, seq, 0);
		if(found == false) {
			System.out.println("no sequence found");
		}
	}
	
	
	/**
	 * Recursive search method that finds matches based on prefix, and counts the nodes visited
	 * @param rt root
	 * @param seq sequence to search for
	 * @param depth depth
	 * @return number of nodes visited
	 */
	private int searchAll(Node rt, String seq, int depth) {
		if(rt.getType().equals("L")) {
			try {
				if(((LeafNode)rt).getSeq().substring(0, seq.length()).contains(seq)) {
					
					//this.visitedAll.add(seq);
					this.visitedAll.add((((LeafNode)rt).getSeq()));
					return 2;
				}else {
					
					//System.out.println("no sequence found");
					x = "no sequence found";
					return 2;
				}
			}catch(IndexOutOfBoundsException e) {
				x = "no sequence found";
				return 2;
			}
			
		}else if(rt.getType().equals("F")) {
			x = "no sequence found";
			return 2;
		}else {
			count++;
			
			while(depth < seq.length()) {
				if(seq.charAt(depth) == 'A') {
					return searchAll(((InternalNode)rt).getA(), seq, depth + 1);
				}else if(seq.charAt(depth) == 'C') {
					return searchAll(((InternalNode)rt).getC(), seq, depth + 1);
				}else if(seq.charAt(depth) == 'G') {
					return searchAll(((InternalNode)rt).getG(), seq, depth + 1);
				}else if(seq.charAt(depth) == 'T') {
					return searchAll(((InternalNode)rt).getT(), seq, depth + 1);
					
				}
			}
			
			//System.out.println(" ");
			int num = traverse(rt);
			//System.out.println("Traverse is " + num);
			return num;
		}
		
		//return 1;
	}
	
	/**
	 * Search all helper function
	 * @param seq sequence to search for
	 */
	public void search(String seq) {
		if(rt.getType().equals("F")) {
			System.out.println("# of nodes visited: " + 1);
			System.out.println("no sequence found");
		}else {
			int num = searchAll(rt, seq, 0);
			if(num == 1) {
				System.out.println("# of nodes visited: " + (num + 1));
				System.out.println("no sequence found");
			}else {
				System.out.println("# of nodes visited: " + (num + count - 1));
				if(x != null) {
					System.out.println(x);
					x = null;
				}
				//visited = "# of nodes visited: " + (num + count - 1);
			}
			
			for(String s : this.visitedAll) {
				System.out.println("sequence: " + s);
			}
		}
		
	}
	
	/**
	 * Method that takes an internal node and counts all nodes (including the internal node passed in) under it
	 * @param rt Internal node
	 * @return number of nodes in subtree
	 */
	public int traverse(Node rt) {
		
		if(rt != null) {
			if(rt.getType().equals("L")) {
				this.visitedAll.add(((LeafNode)rt).getSeq());
				//System.out.println("Adding " + (((LeafNode)rt).getSeq()));
				//System.out.println(((LeafNode)rt).getSeq());
				//return 1;
			}else if(rt.getType().equals("F")) {
				//System.out.println("E");
				//return 1;
			}else if(rt.getType().equals("I")) {
				//System.out.println("I");
				return traverse(((InternalNode)rt).getA()) + traverse(((InternalNode)rt).getC()) + traverse(((InternalNode)rt).getG()) +
				traverse(((InternalNode)rt).getT()) + traverse(((InternalNode)rt).get$()) + 1;
			}
				
		}
		
		return 1;
		
	}
	
	/**
	 * Method that removes a node from the tree
	 * @param rt root
	 * @param seq sequence to remove
	 * @param depth depth
	 * @return rt after tree has been restructured
	 */
	private Node removeAt(Node rt, String seq, int depth) {
		
		if(rt.getType().equals("F")) {
			System.out.println("sequence " + seq + " does not exist");
			return rt;
		}
		
		if(rt.getType().equals("L")) {
			if(((LeafNode)rt).getSeq().equals(seq)) {
				System.out.println("sequence " + seq + " removed");
				rt = new FlyweightNode(true, "F");
			}else {
				System.out.println("sequence " + seq + " does not exist");
				
			}
			
			return rt;
		}else if(rt.getType().equals("I")) {
			
			try {
				if(seq.charAt(depth) == 'A') {
					((InternalNode)rt).setA(removeAt(((InternalNode)rt).getA(), seq, depth + 1));
				}else if(seq.charAt(depth) == 'C') {
					((InternalNode)rt).setC(removeAt(((InternalNode)rt).getC(), seq, depth + 1));
				}else if(seq.charAt(depth) == 'G') {
					((InternalNode)rt).setG(removeAt(((InternalNode)rt).getG(), seq, depth + 1));
				}else if(seq.charAt(depth) == 'T') {
					((InternalNode)rt).setT(removeAt(((InternalNode)rt).getT(), seq, depth + 1));
				}
			}catch(StringIndexOutOfBoundsException e) {
				((InternalNode)rt).set$(removeAt(((InternalNode)rt).get$(), seq, depth + 1));
			}
			
	
			
			Node n = rt;
			int c = 0;
			
			
			if(((InternalNode)rt).getA().getType().equals("F")) {
				c++;
			}else if(((InternalNode)rt).getA().getType().equals("L")){
				n = ((InternalNode)rt).getA();
			}
			
			if(((InternalNode)rt).getC().getType().equals("F")) {
				c++;
				
			}else if(((InternalNode)rt).getC().getType().equals("L")){
				
				n = ((InternalNode)rt).getC();
				
			}
			
			if(((InternalNode)rt).getG().getType().equals("F")) {
				c++;
				
			}else if(((InternalNode)rt).getG().getType().equals("L")){
				
				n = ((InternalNode)rt).getG();
			}
			
			if(((InternalNode)rt).getT().getType().equals("F")) {
				c++;
				
			}else if(((InternalNode)rt).getT().getType().equals("L")){
				
				n = ((InternalNode)rt).getT();
			}
			
			if(((InternalNode)rt).get$().getType().equals("F")) {
				c++;
			}else if(((InternalNode)rt).get$().getType().equals("L")){
				n = ((InternalNode)rt).get$();
			}
			
			if(c == 4) {
				c = 0;
				return n;
			}
		}
		
		return rt;
		
	}
	
	/**
	 * Remove helper method
	 * @param seq sequence to remove
	 */
	public void remove(String seq) {
		rt = removeAt(rt, seq, 0);
		if(rt == null) {
			System.out.println("sequence " + seq + " does not exist");
		}
	}
	
	
	/**
	 * Resets the arraylist that contains all of the visited nodes
	 */
	public void setVisitedAll() {
		this.visitedAll = new ArrayList<>();
	}
	
	/**
	 * resets tree count (This count will be useful in counting nodes visited)
	 */
	public void treeCountReset() {
		count = 0;
	}
	
	/**
	 * Gets count of nodes
	 * @return count of nodes
	 */
	public int getCount() {
		return count;
	}
	
	/**
	 * Method that returns current root
	 * @return root
	 */
	public Node getRoot() {
		return rt;
	}
	
	/**
	 * Sets the max length of the longest sequence inserted
	 * @param newMax length of new longest sequence inserted
	 */
	public void setMax(int newMax) {
		this.max = newMax;
	}
}
