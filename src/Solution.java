import java.util.Stack;

public class Solution {

	private class Frontier{ // the frontier to be explored using a LIFO stack of states
		
		Stack<States> stack;
		
		public Frontier() { //constructor initializing the frontier
			stack = new Stack<States>();
		}
		
		public void add(States newState) {
			stack.add(newState);
		}
		
		/*
		 * Need a frontier.SelectOne() function that utilizes Minimax(for 4*4 Reversi) &
		 * Heuristic-Minimax for 8*8 Reversi to pick the next best node to explore
		 * Possibly two seperate functions
		 */
		public States selectOne() {
			return null;
		}
		
		
	}
}
