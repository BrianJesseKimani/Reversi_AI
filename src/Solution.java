import java.util.ArrayList;
import java.util.List;
import java.util.Stack;

public class Solution { // we create the sollution class. 
	// the solution class calls the game class which prompts the user
	// and somewhere we incorporate the state class
	
	Stack<Node> frontier = new Stack<Node>(); 
	
	private class Node{ // class to store states in the frontier
		
		States state;
		Node parent;
		int pathCost=0; // the number of moves up till current node
		
		public Node(States state, Node parent) { //constructor
			this.state = state;
			this.parent = parent;
			pathCost++;
		}
		
		public Node getParent() {
			return parent;
		}
		
		public int getMoves() {
			return pathCost;
		}
		
		
		/*
		 * Need a frontier.SelectOne() function that utilizes Minimax(for 4*4 Reversi) &
		 * Heuristic-Minimax for 8*8 Reversi to pick the next best node to explore
		 * Possibly two seperate functions
		 * add minimax score variable to each state to calculate best move 
		 */
		
	}
	
	public void graphSearch(){ // this is essentially where the game starts 
		
		
		while(true) { // Game running
			if(frontier.isEmpty()) return; //finish game function? print winners
			
			Node node1 = minimax(); // selects best next node from frontier
			if(node1.state.isTerminalState()) return; //finish game function?
			
			for(States s: node1.state.expand()) {
				Node n = new Node(s, node1);
				frontier.add(n);
			}
		}
		
		
	}
	
	public Node minimax() {//minimax
		//returns a move, or rather, the next node to explore.
		//ArrayList<States> list = new ArrayList<>(); to store the states for sorting according to
		// best utility score and return that node
		return null;					
	}
}
