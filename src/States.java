import java.util.ArrayList;
import java.util.List;

public class States {
	
	int[][] currentState = {//initial state at start of game
							{0,0,0,0},
							{0,-1,1,0},
							{0,1,-1,0},
							{0,0,0,0}
							
						};
	
	int Xcount = 2, Ocount = 2, totalCount = 4,utility = 0; //count of tiles on the board 
	

	public States(int[][] State) {//Constructor in case we need it 
		
		currentState = State;
	}
	public States() { // second constrctor
		
	}
	
	/*
	 * Need a State.expand() function that generates next available legal moves in a list
	 */
	public List<States> expand(){ 
		ArrayList<States> movesList = new ArrayList<>();
		
		return movesList;
	}
	
	public void lineDisplay(int l, int[][] array) {
		for (int c =0; c<= 3; c++) {
		
		if(array[l][c]==1) {System.out.print(" x");}
		if(array[l][c]==-1) {System.out.print(" o");}
		if(array[l][c]==0) {System.out.print("  ");}
		if(array[l][c]==2) {System.out.print(" L");}
		// 2 or "L" for Legal Move
		// 0 for empty
		
			}
	}
	
	public void displayState(int[][] array) {
		System.out.println("  a b c d");
		System.out.print("1"); lineDisplay(0,array); System.out.print(" 1"); System.out.println(" "); 
		System.out.print("2"); lineDisplay(1,array); System.out.print(" 2"); System.out.println(" "); 
		System.out.print("3"); lineDisplay(2,array); System.out.print(" 3"); System.out.println(" "); 
		System.out.print("4"); lineDisplay(3,array); System.out.print(" 4"); System.out.println(" "); 
		System.out.println("  a b c d");
	}
	
	
	public boolean isTerminalState() {//given a state on the board check if board is full
		//Goal/terminal state is when there are no zeros or twos on the board
		if (totalCount==16) return true;
		
		return false;
	}
	
	public int Utility() {//returns +/- 1 or 0 when in a terminal state to determine the winner
		//counts the number of white & black tiles and whoever has more gets +1
		//have a static variable int that tracks total score.
		return 0;
	}
	
	public int[][] getState(){
		return currentState;
	}
	
	public void setState(int[][] state) {
		currentState = state;
	}

}
