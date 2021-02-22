import java.util.ArrayList;
import java.util.List;
import java.util.Stack;

public class Solution { // we create the sollution class. 
	// the solution class calls the game class which prompts the user
	// and somewhere we incorporate the state class
	
	Stack<Node> frontier = new Stack<Node>(); 
	int Player = 0;
	private class Node{ // class to store states in the frontier
		
		States state;
		Node parent;
		int pathCost=0; // the number of moves up till current node
		
		public Node(States state, Node parent) { //constructor
			this.state = state;
			this.parent = parent;
			pathCost++;
		}
		public Node(States state) { //constructor
			this.state = state;
			this.parent = null;
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
	
	public Node makeNode(States state) {
		return new Node(state);
	}
	
	public void populateFrontier(States child, Node parent) { //adds nodes to frontier; used in game class
		Node node = new Node(child, parent);
		frontier.add(node);
	}
	
	public States graphSearch(int player){ // this is essentially where the game starts 
		
		
		//while(true) { // Game running
			if(frontier.isEmpty()) return null; //finish game function? print winners
			
			Node node1 = minimax(player); // selects best next node from frontier for the current player
			//if(node1.state.isTerminalState()) return null; //finish game function?
		//	node1.state.displayState();//else shoe computer move and promt user to play 
			
//			for(States s: node1.state.expand()) {
//				Node n = new Node(s, node1);
//				frontier.add(n);
//			}
		//}
		
		return node1.state;
	}
	
	public Node minimax(int player) {//minimax
		//returns a move, or rather, the next node to explore.
		//ArrayList<States> list = new ArrayList<>(); to store the states for sorting according to
		// best utility score and return that node
		Node node = frontier.peek();
		Player = player;
		node.state.utility = maxValue(node.state,player);
		//not sure if we should repopulate frontier since we dont know what move player will make
//		List<String> lm = game.legalMoves(player, node.state.getState());
//		for(String str: lm) {
//			frontier.add(new Node(game.userMove(str, player, node.state),node));
//		}
		
		return node;					
	}
	
	public int maxValue(States state,int player) {
		if(state.isTerminalState()) {
			//state.utility = state.Utility(player);
			System.out.println("max terminal");
			System.out.println("0Count = "+ state.Ocount +"Xcount:= "+state.Xcount);
			state.displayState();
			System.out.println("UT = "+state.Utility(player));
			return state.Utility(Player);
		}
		else {
			state.utility = Integer.MIN_VALUE;
			States newState = state;//new States();
			States duplicate = state;
			List<String> lm = game.legalMoves(player, state.getState());
			if(lm.isEmpty()) {
//				newState = state;
				return state.utility;
			}
			for(String str: lm) {
				duplicate = state;
				newState = game.userMove(str, player, duplicate);
				newState.utility = minValue(newState,-player);
				if(newState.utility>state.utility)
					state.utility = newState.utility;
			}
			return state.utility;
		}
			
	}

	public int minValue(States state, int player) {
		if(state.isTerminalState()) {
			//state.utility = state.Utility(player);
			System.out.println("min terminal:");
			System.out.println("0Count = "+ state.Ocount +"Xcount:= "+state.Xcount);
			state.displayState();
			return state.Utility(Player);
		}
		else {
			state.utility = Integer.MAX_VALUE;
			States newState = state;//new States();
			States duplicate = state;
			List<String> lm = game.legalMoves(player, state.getState());
			if(lm.isEmpty()) {
//				newState = state;
				return state.utility;
			}
			for(String str: lm) {
				duplicate = state;
				newState = game.userMove(str, player, duplicate);
				newState.utility = maxValue(newState,-player);
				if(newState.utility<state.utility)
					state.utility = newState.utility;
			}
			return state.utility;
		}
	}
}
