import java.util.ArrayList;
import java.util.List;

public class States {
	
	int[][] currentState = {//initial state at start of game
							{0,0,0,0},
							{0,-1,1,0},
							{0,1,-1,0},
							{0,0,0,0}
							
						};
	
	int[][] currentState8by8 = {
			{0,0,0,0,0,0,0,0},
			{0,0,0,0,0,0,0,0},			
			{0,0,0,0,0,0,0,0},			
			{0,0,0,-1,1,0,0,0},	
			{0,0,0,1,-1,0,0,0},
			{0,0,0,0,0,0,0,0},
			{0,0,0,0,0,0,0,0},
			{0,0,0,0,0,0,0,0}			
	};
	
	int Xcount = 2, Ocount = 2, totalCount = 4,utility = 0; //count of tiles on the board 
	

	public States(int[][] State) {//Constructor in case we need it 
		
		currentState = State;
	}
	public States() { // second constrctor
		
	}
	public States(int whichState) {
		if(whichState==3) {
			currentState = currentState8by8;
		}
	}
	
	/*
	 * Need a State.expand() function that generates next available legal moves in a list
	 */
	public List<States> expand(){ 
		ArrayList<States> movesList = new ArrayList<>();
		
		return movesList;
	}
	
	public void lineDisplay(int l, int[][] array) {
		for (int c =0; c<array.length; c++) {
		
		if(array[l][c]==1) {System.out.print(" x");}
		if(array[l][c]==-1) {System.out.print(" o");}
		if(array[l][c]==0) {System.out.print("  ");}
		if(array[l][c]==2) {System.out.print(" L");}
		// 2 or "L" for Legal Move
		// 0 for empty
		
			}
	}
	
	public void displayState() {
		System.out.println("  a b c d");
		System.out.print("1"); lineDisplay(0,currentState); System.out.print(" 1"); System.out.println(" "); 
		System.out.print("2"); lineDisplay(1,currentState); System.out.print(" 2"); System.out.println(" "); 
		System.out.print("3"); lineDisplay(2,currentState); System.out.print(" 3"); System.out.println(" "); 
		System.out.print("4"); lineDisplay(3,currentState); System.out.print(" 4"); System.out.println(" "); 
		System.out.println("  a b c d");
	}
	
	public void displayState8by8() {
		System.out.println("  a b c d e f g h");
		System.out.print("1"); lineDisplay(0,currentState8by8); System.out.print(" 1"); System.out.println(" "); 
		System.out.print("2"); lineDisplay(1,currentState8by8); System.out.print(" 2"); System.out.println(" "); 
		System.out.print("3"); lineDisplay(2,currentState8by8); System.out.print(" 3"); System.out.println(" "); 
		System.out.print("4"); lineDisplay(3,currentState8by8); System.out.print(" 4"); System.out.println(" "); 
		System.out.print("5"); lineDisplay(4,currentState8by8); System.out.print(" 5"); System.out.println(" "); 
		System.out.print("6"); lineDisplay(5,currentState8by8); System.out.print(" 6"); System.out.println(" "); 
		System.out.print("7"); lineDisplay(6,currentState8by8); System.out.print(" 7"); System.out.println(" "); 
		System.out.print("8"); lineDisplay(7,currentState8by8); System.out.print(" 8"); System.out.println(" "); 		
		System.out.println("  a b c d e f g h");
	}
	
	public boolean isTerminalState() {//given a state on the board check if board is full
		//Goal/terminal state is when there are no zeros or twos on the board
		totalCount = Xcount+Ocount;
		if (totalCount>=16) return true;
		
		return false;
	}
	
	public int Utility(int player) {//returns +/- 1 or 0 when in a terminal state to determine the winner
		//counts the number of white & black tiles and whoever has more gets +1
		//have a static variable int that tracks total score.
		if(player == 1) return Xcount - Ocount;
		else return Ocount - Xcount;
		
	}
	public void GameOver() {
		System.out.println("Game Over!");
		System.out.println(" X tiles = "+ Xcount);
		System.out.println("O tiles = "+ Ocount);
		if(Xcount>Ocount)
			System.out.println("X wins!");
		else if(Ocount>Xcount)
			System.out.println("O wins!");
		else
			System.out.println("its a Tie!");
		
	}
	
	public int[][] getState(){
		return currentState;
	}
	
	public void setState(int[][] state) {
		currentState = state;
	}

}
