import java.io.*;
import java.util.*;
/**
 * DNA Tree
 * CPSC 340 Project 2
 * @author Ian MacLeod
 * @date 10/25/21
 * @version 6.0    
 * 
 */
public class DNAtree {

	public static void main(String[] args) { 
		
		File file = new File("SampleInput3.txt");      
		BufferedReader bf = null; 
		Tree tree = new Tree();               
		try {   
			bf = new BufferedReader(new FileReader(file));
			String line = bf.readLine(); 
			line = line.replaceAll("^\\s+", "").replaceAll("\\s+$", ""); 
			while(line != null) { 
				line = line.trim(); 
				if(line.length() != 0) { 
					line = formatCommand(line);
					//System.out.println(line);
					String[] commands = line.split("\\s+"); 
					if(commands[0].equals("insert")) { 
						if(commands[1].length() > tree.max) {
							tree.setMax(commands[1].length()); 
						}
						tree.insert(commands[1]);
						//tree.print();  
						
					}else if(commands[0].equals("print")) { 
						if(commands.length == 1) {
							tree.print();
						}else {
							if(commands[1].equals("lengths")) {
								tree.lengths();
							}else {
								tree.stats();  
							}    
							
						}
					}else if(commands[0].equals("search")) {
						//searching exact
						if(commands[1].contains("$")) {
							tree.searchExactMatch(commands[1].substring(0, commands[1].length() - 1));
						}else {
							
							
								tree.search(commands[1]);
								tree.treeCountReset();
								tree.setVisitedAll(); 
							
							
						}
						
					}else if(commands[0].equals("remove")) {
						if(tree.getRoot() == null) {
							System.out.println("sequence " + commands[1] + " does not exist");
						}else {
							tree.remove(commands[1]);
							tree.existingSequences.remove(commands[1]);
							//tree.treeCountReset();
							//tree.setVisitedAll();
						}
						
						
					}
				}
				
				
				line = bf.readLine();
			}
			
			
		}catch(IOException e) {
			e.printStackTrace();
		}finally {
			try {
				bf.close();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}

	}
	
	/**
	 * Function that removes whitespace and formats the command line properly
	 * @param line
	 * @return
	 */
	public static String formatCommand(String line) {
		StringBuilder sb = new StringBuilder();
		String[] s = line.split(" ");
		for(int i = 0; i < s.length; i++) {
			if(i != s.length - 1) {
				if(s[i].length() != 0) {
					sb.append(s[i] + " ");
				}
			}else {
				sb.append(s[i]);
			}
		}
		
		return sb.toString();
	}
	
	
	

}
