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
		node.state = maxValue(node.state,player);
		//System.out.println("UT = "+ node.state.Utility(player));
		//System.out.println("........."+ node.state.utility);

		//not sure if we should repopulate frontier since we dont know what move player will make
//		List<String> lm = game.legalMoves(player, node.state.getState());
//		for(String str: lm) {
//			frontier.add(new Node(game.userMove(str, player, node.state),node));
//		}
		
		return node;					
	}
	
	public States maxValue(States state,int player) {
		//System.out.println("Player = "+ player);
		if(state.isTerminalState()) {
			//state.utility = state.Utility(player);
			//System.out.println("max terminal");
		//	System.out.println("0Count = "+ state.Ocount +"Xcount:= "+state.Xcount);
			//state.displayState();
			state.utility = state.Utility(Player);
//			System.out.println("UT = "+state.Utility(player));
			return state;
		}
		else {
			state.utility = Integer.MIN_VALUE;
			States newState = state;//new States();
			States duplicate = state;
			List<String> lm = game.legalMoves(player, state.getState());
			if(lm.isEmpty()) {
				//System.out.println("???????1");
//				newState = state;
				//state = minValue(state,(-player));
				return state;
			}
			Stack<States> store = new Stack<States>();
			for(String str: lm) {
				duplicate = state;
				newState = game.copyMove(str, player, duplicate);
				//newState.displayState();
				store.add(newState);
				}
			while(!store.isEmpty()) {
				States best = store.pop();
				States answer = minValue(best,(-player));
				if(answer.utility>state.utility)
					state = best;
				
			}
				
			
			return state;
		}
			
	}

	public States minValue(States state, int player) {
		//System.out.println("Player = "+ player);
		if(state.isTerminalState()) {
			//state.utility = state.Utility(player);
			//System.out.println("min terminal:");
			//System.out.println("0Count = "+ state.Ocount +"Xcount:= "+state.Xcount);
			//state.displayState();
			state.utility = state.Utility(Player);
			return state;
		}
		else {
			state.utility = Integer.MAX_VALUE;
			States newState = state;//new States();
			States duplicate = state;
			List<String> lm = game.legalMoves(player, state.getState());
			if(lm.isEmpty()) {
			//   System.out.println("???????1");
//				newState = state;
				//state = maxValue(state,(-player));
				return state;
			}
			Stack<States> store = new Stack<States>();
			for(String str: lm) {
				duplicate = state;
				newState = game.copyMove(str, player, duplicate);
				//System.out.println("New State: ");
				//newState.displayState();
				store.add(newState);
				}
			while(!store.isEmpty()) {
			    States best = store.pop();
				States answer = maxValue(best,(-player));
				if(answer.utility<state.utility)
					state = best;
				}
			
			return state;
		}
	}
}
