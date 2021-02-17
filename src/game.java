import java.util.Scanner;
import java.util.HashMap;


public class game {
	
	public  static int[][] array = {
			//1 for dark and -1 for light
			{-1,-1,-1,0},
			{1,1,-1,0},
			{0,1,-1,0},
			{0,0,0,0}
	};
	
	
	public static void lineDisplay(int l, int[][] array) {
		for (int c =0; c<= 3; c++) {
		
		if(array[l][c]==1) {System.out.print(" x");}
		if(array[l][c]==-1) {System.out.print(" o");}
		if(array[l][c]==0) {System.out.print("  ");}
		if(array[l][c]==2) {System.out.print(" L");}
		// 2 or "L" for Legal Move
		// 0 for empty
		
	}}
	
	public static void display(int[][] array) {
		System.out.println("  a b c d");
		System.out.print("1"); lineDisplay(0,array); System.out.print(" 1"); System.out.println(" "); 
		System.out.print("2"); lineDisplay(1,array); System.out.print(" 2"); System.out.println(" "); 
		System.out.print("3"); lineDisplay(2,array); System.out.print(" 3"); System.out.println(" "); 
		System.out.print("4"); lineDisplay(3,array); System.out.print(" 4"); System.out.println(" "); 
		System.out.println("  a b c d");
	}
	
	public static HashMap<String, String> hmap4by4 = new HashMap<String, String>();
	
	
	public static String legalMoves(int xo, int[][] array) {
		//because we are in 4X4 now, I only have to check the direct surroundings of the piece to find legal moves
		//once we move to 8X8 the process will be trickier as we'll have to loop through the possible pieces that are farther than the direct surroundings
		String res = "";
			for (int i=0; i<=3; i++) {
				for (int j=0; j<=3; j++) {
					if (array[i][j]==xo) { //xo being whatever team you're on
						for (int k=i-1; k <=i+1; k++) {
							for (int m= j-1; m<=j+1; m++) {
								if (k<4 && k>-1 && m<4 && m>-1 && array[k][m]!= array[i][j]) { //checking for surroundings that are different from the actual piece and are contained in our grid
									if (array[k][m]== -1*xo) { //multiplying xo by -1 means "if this piece is from the opposite team ==> opposite sign"
										int diffK = i-k;  int diffM = j-m;
										if (k-diffK<4 && k-diffK>-1 && m-diffM<4 && m-diffM>-1 && array[k-diffK][m-diffM] == 0) {
										array[k-diffK][m-diffM] = 2; 
										//casting the legal moves indexes into string to find their a0 indexes in the hashmap:
										String check = String.valueOf(k-diffK)+String.valueOf(m-diffM);
										String var= hmap4by4.get(check);
									      res = res + var + " "; 
										}
									}	
							
							}
						}
							}
					}
				}
			}
		
		
		return res;
	}
	
	public static void hmap44() {
		hmap4by4.put("00","a1");
		hmap4by4.put("01", "b1");
		hmap4by4.put("02", "c1");
		hmap4by4.put("03", "d1");
		hmap4by4.put("10", "a2");
		hmap4by4.put("11", "b2");
		hmap4by4.put("12", "c2");
		hmap4by4.put("13", "d2");
		hmap4by4.put("20", "a3");
		hmap4by4.put("21", "b3");
		hmap4by4.put("22", "c3");
		hmap4by4.put("23", "d3");
		hmap4by4.put("30", "a4");
		hmap4by4.put("31", "b4");
		hmap4by4.put("32", "c4");
		hmap4by4.put("33", "d4");
	}
	

	public static void main(String[] args) {
		
		System.out.println("Reversi by....");
		System.out.println("Choose your game");
		System.out.println("1. Small 4x4 Reversi");
		System.out.println("2. Medium 6x6 Reversi");
		System.out.println("3. Standard 8x8 Reversi");

		Scanner s = new Scanner(System.in);
		System.out.print("Your choice? ");
		int gameChoice = s.nextInt();

		if (gameChoice == 1) {
			hmap44();
			System.out.println("You have chosen Small 4x4 Reversi");
			System.out.println("Choose your opponent:");
			System.out.println("1. An agent that plays randomly");
			System.out.println("2. An agent that uses MINIMAX");
			System.out.println("3. An agent that uses MINIMAX with alpha-beta pruning");
			System.out.println("4. An agent that uses H-MINIMAX with a fixed depth cutoff and a-b pruning");

			System.out.print("Your choice? ");
			int algoChoice = s.nextInt();
			if (algoChoice == 1) {
				System.out.println("You have chosen an agent that plays randomly");
				System.out.print("Do you want to play DARK (x) or LIGHT (o)?");

				String colorChoice = s.next();
				int t = 0;
				if (colorChoice.equals("x")) {
					t = 1;
					display(array);
					String lm = legalMoves(t, array);
					System.out.println(" ");

					System.out.println("Now with legal moves displayed as L");

					display(array);
					
					System.out.println("Need help? legal moves possible: " + lm);
					

				} else if (colorChoice.equals("o")) {
					t = -1;
					display(array);
					String lm = legalMoves(t, array);
					System.out.println(" ");

					System.out.println("Now with legal moves displayed as L");

					display(array);
					System.out.println("Need help? legal moves possible: " + lm);
				}

			}

			else if (algoChoice == 2) {
				System.out.println("You have chosen an agent that uses MINIMAX");
				System.out.print("Do you want to play DARK (x) or LIGHT (o)?");

				String colorChoice = s.next();
				if (colorChoice.equals("x")) {

				} else if (colorChoice.equals("o")) {

				}
			}

			else if (algoChoice == 3) {
				System.out.println("You have chosen an agent that uses MINIMAX with alpha-beta pruning");
				System.out.print("Do you want to play DARK (x) or LIGHT (o)?");

				String colorChoice = s.next();
				if (colorChoice.equals("x")) {

				} else if (colorChoice.equals("o")) {

				}

			}

			else if (algoChoice == 4) {
				System.out.println(
						"You have chosen an agent that uses H-MINIMAX with a fixed depth cutoff and a-b pruning");
				System.out.print("Do you want to play DARK (x) or LIGHT (o)?");

				String colorChoice = s.next();
				if (colorChoice.equals("x")) {

				} else if (colorChoice.equals("o")) {

				}

			}

		}

		else if (gameChoice == 2) {
			System.out.println("You have chosen: Medium 6x6 Reversi");
			System.out.println("Choose your opponent:");
			System.out.println("1. An agent that plays randomly");
			System.out.println("2. An agent that uses MINIMAX");
			System.out.println("3. An agent that uses MINIMAX with alpha-beta pruning");
			System.out.println("4. An agent that uses H-MINIMAX with a fixed depth cutoff and a-b pruning");

			System.out.print("Your choice? ");
			int algoChoice = s.nextInt();
			if (algoChoice == 1) {
				System.out.println("You have chosen an agent that plays randomly");
				System.out.print("Do you want to play DARK (x) or LIGHT (o)?");

				String colorChoice = s.next();
				if (colorChoice.equals("x")) {

				} else if (colorChoice.equals("o")) {

				}

			}

			else if (algoChoice == 2) {
				System.out.println("You have chosen an agent that uses MINIMAX");
				System.out.print("Do you want to play DARK (x) or LIGHT (o)?");

				String colorChoice = s.next();
				if (colorChoice.equals("x")) {

				} else if (colorChoice.equals("o")) {

				}
			}

			else if (algoChoice == 3) {
				System.out.println("You have chosen an agent that uses MINIMAX with alpha-beta pruning");
				System.out.print("Do you want to play DARK (x) or LIGHT (o)?");

				String colorChoice = s.next();
				if (colorChoice.equals("x")) {

				} else if (colorChoice.equals("o")) {

				}

			}

			else if (algoChoice == 4) {
				System.out.println(
						"You have chosen an agent that uses H-MINIMAX with a fixed depth cutoff and a-b pruning");
				System.out.print("Do you want to play DARK (x) or LIGHT (o)?");

				String colorChoice = s.next();
				if (colorChoice.equals("x")) {

				} else if (colorChoice.equals("o")) {

				}

			}

		}

		else if (gameChoice == 3) {
			System.out.println("You have chosen: Standard 8x8 Reversi");
			System.out.println("Choose your opponent:");
			System.out.println("1. An agent that plays randomly");
			System.out.println("2. An agent that uses MINIMAX");
			System.out.println("3. An agent that uses MINIMAX with alpha-beta pruning");
			System.out.println("4. An agent that uses H-MINIMAX with a fixed depth cutoff and a-b pruning");

			System.out.print("Your choice? ");
			int algoChoice = s.nextInt();
			if (algoChoice == 1) {
				System.out.println("You have chosen an agent that plays randomly");
				System.out.print("Do you want to play DARK (x) or LIGHT (o)?");

				String colorChoice = s.next();
				if (colorChoice.equals("x")) {

				} else if (colorChoice.equals("o")) {

				}

			}

			else if (algoChoice == 2) {
				System.out.println("You have chosen an agent that uses MINIMAX");
				System.out.print("Do you want to play DARK (x) or LIGHT (o)?");

				String colorChoice = s.next();
				if (colorChoice.equals("x")) {

				} else if (colorChoice.equals("o")) {

				}
			}

			else if (algoChoice == 3) {
				System.out.println("You have chosen an agent that uses MINIMAX with alpha-beta pruning");
				System.out.print("Do you want to play DARK (x) or LIGHT (o)?");

				String colorChoice = s.next();
				if (colorChoice.equals("x")) {

				} else if (colorChoice.equals("o")) {

				}

			}

			else if (algoChoice == 4) {
				System.out.println(
						"You have chosen an agent that uses H-MINIMAX with a fixed depth cutoff and a-b pruning");
				System.out.print("Do you want to play DARK (x) or LIGHT (o)?");

				String colorChoice = s.next();
				if (colorChoice.equals("x")) {

				} else if (colorChoice.equals("o")) {

				}

			}
		}

	}

}
