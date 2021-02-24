import java.util.ArrayList;
import java.util.List;
import java.util.Stack;

public class Solution { // we create the sollution class. 
	// the solution class calls the game class which prompts the user
	// and somewhere we incorporate the state class
	
	Stack<Node> frontier = new Stack<Node>(); 
	int Player = 0;
	int Cutoff=6;
	private static class Node{ // class to store states in the frontier
		
		States state;
		Node parent;
		static int pathCost=0;// the number of moves up till current node
		
		public Node(States state, Node parent) { //constructor
			this.state = state;
			this.parent = parent;
			pathCost=pathCost+1;
		}
		public Node(States state) { //constructor
			this.state = state;
			this.parent = null;
			//pathCost=pathCost+1;
		}
		
		public Node getParent() {
			return parent;
		}
		
		public int getMoves() {
			return pathCost;
		}
		public int getPathCost() {
			return pathCost-1;
		}
		
		/*
		 * Need a frontier.SelectOne() function that utilizes Minimax(for 4*4 Reversi) &
		 * Heuristic-Minimax for 8*8 Reversi to pick the next best node to explore
		 * Possibly two seperate functions
		 * add minimax score variable to each state to calculate best move 
		 */	
	}
	
	public Node makeNode(States state) {
		return new Solution.Node(state);
	}
	
	public void populateFrontier(States child, Node parent) { //adds nodes to frontier; used in game class
		Node node = new Node(child, parent);
		frontier.add(node);
	}
	
	public States graphSearch(int player,int algoChoice){ // this is essentially where the game starts 
			
			if(frontier.isEmpty()) return null; 
			
			Node node1;
			if(algoChoice == 2) {
				 node1 = minimax(player);
			}
			else if(algoChoice == 3) {
				 node1 = Alpha_Beta_Minimax(player);
			}
			else {
				 node1 = heuristic_Minimax(player);
			}
			
		return node1.state;
	}
	
	public Node minimax(int player) {//minimax
		//returns a move, or rather, the next node to explore.
		
		Node node = frontier.peek();
		Player = player;
		node.state = maxValue(node.state,player);

		return node;					
	}
	public States maxValue(States state,int player) {
		if(state.isTerminalState()) {
			state.utility = state.Utility(Player);
			return state;
		}
		else {
			state.utility = Integer.MIN_VALUE;
			States newState = state;//new States();
			States duplicate = state;
			List<String> lm = game.legalMoves(player, state.getState());
			if(lm.isEmpty()) {
			
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
		if(state.isTerminalState()) {
			
			state.utility = state.Utility(Player);
			return state;
		}
		else {
			state.utility = Integer.MAX_VALUE;
			States newState = state;//new States();
			States duplicate = state;
			List<String> lm = game.legalMoves(player, state.getState());
			if(lm.isEmpty()) {
			
				return state;
			}
			Stack<States> store = new Stack<States>();
			for(String str: lm) {
				duplicate = state;
				newState = game.copyMove(str, player, duplicate);
				
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
	
	
	
	public Node Alpha_Beta_Minimax(int player) {//minimax
	
		Node node = frontier.peek();
		Player = player;
		node.state = AlphaBeta_MaxValue(node.state,player,Integer.MIN_VALUE,Integer.MAX_VALUE);
		
		return node;					
	}
	public States AlphaBeta_MaxValue(States state,int player,int alpha,int beta) {
		if(state.isTerminalState()) {
			state.utility = state.Utility(Player);
			return state;
		}
		else {
			state.utility = Integer.MIN_VALUE;
			States newState = state;//new States();
			States duplicate = state;
			List<String> lm = game.legalMoves(player, state.getState());
			if(lm.isEmpty()) {
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
				States answer = AlphaBeta_MinValue(best,(-player),alpha,beta);
				if(answer.utility>state.utility) {
					state = best;	
					alpha = Integer.max(alpha, state.utility);
				}
					if(state.utility>=beta) return state;
			}
			return state;
		}	
	}
	public States AlphaBeta_MinValue(States state, int player,int alpha,int beta) {
		if(state.isTerminalState()) {
			state.utility = state.Utility(Player);
			return state;
		}
		else {
			state.utility = Integer.MAX_VALUE;
			States newState = state;//new States();
			States duplicate = state;
			List<String> lm = game.legalMoves(player, state.getState());
			if(lm.isEmpty()) {
				return state;
			}
			Stack<States> store = new Stack<States>();
			for(String str: lm) {
				duplicate = state;
				newState = game.copyMove(str, player, duplicate);
				store.add(newState);
				}
			while(!store.isEmpty()) {
			    States best = store.pop();
				States answer = AlphaBeta_MaxValue(best,(-player),alpha,beta);
				if(answer.utility<state.utility) {
					state = best;
					beta = Integer.min(beta, state.utility);
				}
					if(state.utility<=alpha) return state;
				}
			
			return state;
		}
	}
	
	public Node heuristic_Minimax(int player) {//minimax
		
		Node node = frontier.peek();
		Player = player;
		int depth = 0;
		node.state = heuristic_MaxValue(node.state,player,Integer.MIN_VALUE,Integer.MAX_VALUE,depth);
		
		return node;					
	}
	public States heuristic_MaxValue(States state,int player,int alpha,int beta,int depth) {
		if(depth==Cutoff) {
			state.utility = state.Utility(Player);
			state.heuristic = depth + state.utility;
			//System.out.println("pathCost = "+node.getPathCost());
			return state;
		}
		else {
			state.heuristic = Integer.MIN_VALUE;
			States newState = state;//new States();
			States duplicate = state;
			List<String> lm = game.legalMoves(player, state.getState());
			if(lm.isEmpty()) {
				state.heuristic = depth + state.Utility(Player);
				return state;
			}
			Stack<States> store = new Stack<States>();
			for(String str: lm) {
				//System.out.println(lm);
				duplicate = state;
				newState = game.copyMove(str, player, duplicate);
				store.add(newState);
				}
			//state.displayState8by8();
			//System.out.println("max at depth = "+depth);
			while(!store.isEmpty()) {
				States best = store.pop();
				States answer = heuristic_MinValue(best,(-player),alpha,beta,depth+1);
				if(answer.heuristic>state.heuristic) {
					state = best;	
					alpha = Integer.max(alpha,state.heuristic);
				}
					if(state.heuristic>=beta) return state;
			}
			return state;
		}	
	}
	public States heuristic_MinValue(States state, int player,int alpha,int beta,int depth) {
		if(depth==Cutoff) {
			state.utility = state.Utility(Player);
			state.heuristic = depth + state.utility;
			//System.out.println("pathCost = "+node.getPathCost());
			return state;
		}
		else {
			state.heuristic = Integer.MAX_VALUE;
			States newState = state;//new States();
			States duplicate = state;
			List<String> lm = game.legalMoves(player, state.getState());
			if(lm.isEmpty()) {
				state.heuristic = depth + state.Utility(Player);
				return state;
			}
			Stack<States> store = new Stack<States>();
			for(String str: lm) {
				//System.out.println(lm);
				duplicate = state;
				newState = game.copyMove(str, player, duplicate);
				store.add(newState);
				}
			//state.displayState8by8();
			//System.out.println("Min at depth = "+depth);
			while(!store.isEmpty()) {
			    States best = store.pop();
				States answer = heuristic_MaxValue(best,(-player),alpha,beta,depth+1);
				if(answer.heuristic<state.heuristic) {
					state = best;
					beta = Integer.min(beta, state.heuristic);
				}
					if(state.heuristic<=alpha) return state;
				}
			
			return state;
		}
	}
}
