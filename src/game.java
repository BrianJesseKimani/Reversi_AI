//OLD
import java.util.Scanner;
import java.util.HashMap;
import java.util.ArrayList; 
import java.util.List; 
import java.util.Random; 
import java.util.Collections;


public class game {
	
	public  static int[][] board4by4 = {
			//1 for dark and -1 for light
			{0,0,0,0},
			{0,1,-1,0},
			{0,-1,1,0},
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
	
	
	public  List<String> km = new ArrayList<>(); 
	
	public static List<String> legalMoves(int xo, int[][] array) {
		//because we are in 4X4 now, I only have to check the direct surroundings of the piece to find legal moves
				//once we move to 8X8 the process will be trickier as we'll have to loop through the possible pieces that are farther than the direct surroundings
				List<String>  res = new ArrayList<>();
				String check= ""; String var ="";
					for (int i=0; i<=3; i++) {
						for (int j=0; j<=3; j++) {
							if (array[i][j]==xo) { //xo being whatever team you're on
								for (int k=i-1; k <=i+1; k++) {
									for (int m= j-1; m<=j+1; m++) {
										if (k<4 && k>-1 && m<4 && m>-1 && array[k][m]!= array[i][j]) { //checking for surroundings that are different from the actual piece and are contained in our grid
											if (array[k][m]== -1*xo) { //multiplying xo by -1 means "if this piece is from the opposite team ==> opposite sign"
												int diffK = i-k;  int diffM = j-m;
												if (k-diffK<4 && k-diffK>-1 && m-diffM<4 && m-diffM>-1) { 
													if (array[k-diffK][m-diffM] == 0) {
												//casting the legal moves indexes into string to find their a0 indexes in the hashmap:
												check = String.valueOf(k-diffK)+String.valueOf(m-diffM);
												var= hmap4by4.get(check);
												if(!(res.contains(var))) {
											      res.add(var);
											      //array[k-diffK][m-diffM] = 2;
											      }
												
												}
												
													else if(array[k-diffK][m-diffM] == -1*xo) {
													
													if ((diffK==-1 ||diffK==1) && (diffM==-1 ||diffM==1) ) { 
														int n=k; int o=m;
														n=n-diffK; o=o-diffM;
														while ( n<4 && n>-1 && o>-1 && o<4 ) {  
															
															if (array[n][o]==0) {
																check = String.valueOf(n)+String.valueOf(o);
																 var= hmap4by4.get(check);
																 if(!(res.contains(var))) {
																      res.add(var);
																    //  array[n][o] = 2;
																      }
																		}
															n=n-diffK; o=o-diffM;
															}	
												}
													
												else if ((diffK==-1 ||diffK==1) && diffM==0) { 
														int n=k;
														n=n-diffK;
														while ( n<4 && n>-1) { 
															if (array[n][j]==0) {
																 check = String.valueOf(n)+String.valueOf(j);
																 var= hmap4by4.get(check);
																 if(!(res.contains(var))) {
																      res.add(var);
																    //  array[n][j] = 2;
																      }
																		}
															n=n-diffK;
															}
													}
													
													else if ((diffM==-1 ||diffM==1) && diffK==0) { 
														int o=m;
														o=o-diffM;
														while ( o<4 && o>-1) {  
															if (array[i][o]==0) {
																check = String.valueOf(i)+String.valueOf(o);
																 var= hmap4by4.get(check);
																 if(!(res.contains(var))) {
																      res.add(var);
																     // array[i][o] = 2;
																      }
																		}
															o=o-diffM;
															
															}}
													
													
													
													
												}
												
											}	
									} }
								}
									}
							}
						}
					}
				
				
				return res;
	}
	
	public static void randomMove(int u, States state) {
		int[][] array = state.getState();
		Random rand = new Random(); 
		String bit = "";
		List<String> lm = legalMoves(u, array);
		if (lm.isEmpty()) {
			System.out.println("No moves possible. Skip!");
			
			}
			else {
				//System.out.println("The random move will be picked from this list of legal moves: " + lm);
		String lol = 
				//"d1";
				lm.get(rand.nextInt(lm.size())); 
		//display(array);
		//deleteL(array);

		for ( String key : hmap4by4.keySet() ) {
		    if (hmap4by4.get(key).equals(lol))
			{bit = key;
		   
			}
		}
		

		int index = Integer.valueOf(bit);
		int i = index / 10% 10;
		int j = index % 10;
		array[i][j]=u;
		if(u==1) {state.Xcount++;} //changed to track the no. of tiles on board
		else if(u==-1) {state.Ocount++;}
		
		for (int k=i-1; k <=i+1; k++) {
			
			for (int m= j-1; m<=j+1; m++) {
				
				if (k<4 && k>-1 && m<4 && m>-1 && array[k][m]!= array[i][j]) { 
					if (array[k][m]== -1*u ) {  
						int diffK = i-k;  int diffM = j-m;
						if ((diffK==-1 ||diffK==1) && (diffM==-1 ||diffM==1) ) { 
							int n=k; int o=m;
							n=n-diffK; o=o-diffM;
							while ( n<4 && n>-1 && o>-1 && o<4 ) {  
								
								if (array[n][o]==u) {
									break;
											}
								n=n-diffK; o=o-diffM;
								}
							if ( o<4 && o>-1 && n<4 && n>-1) {  
							while (n!=k && o!=m) { n=n+diffK; o=o+diffM;
							if (array[n][o]==-1*u) {
								array[n][o]=u;  
								if(u==1) {state.Xcount++; state.Ocount--;} //changed to track the no. of tiles on board
								else if(u==-1) {state.Xcount--;state.Ocount++;}
								}}
							}
						}
						
						else if ((diffK==-1 ||diffK==1) && diffM==0) { 
							int n=k;
							n=n-diffK;
							while ( n<4 && n>-1) {  
								
								if (array[n][j]==u) {
									break;
											}
								n=n-diffK;
								}
							if ( n<4 && n>-1) {  
							while (n!=k) {n=n+diffK;
							if (array[n][m]==-1*u) {
								array[n][m]=u; 
								if(u==1) {state.Xcount++; state.Ocount--;} //changed to track the no. of tiles on board
								else if(u==-1) {state.Xcount--;state.Ocount++;}
								}}
							}
						}
						
						else if ((diffM==-1 ||diffM==1) && diffK==0) { 
							int o=m;
							o=o-diffM;
							while ( o<4 && o>-1) {  
								if (array[i][o]==u) {
									break;
											}
								o=o-diffM;
								
								}
							if ( o<4 && o>-1) {  
							while (o!=m) {
								o=o+diffM;
								if (array[k][o]==-1*u) {
								array[k][o]=u;  
								if(u==1) {state.Xcount++; state.Ocount--;} //changed to track the no. of tiles on board
								else if(u==-1) {state.Xcount--;state.Ocount++;}
								}}
						}
						}
					}	
			}
					
		}
			}
	}
	}
	
	public static States userMove(String input, int u, States state) { //changed from return int[][] to return State
		int[][] array = state.getState();
		String bit = "";
		for ( String key : hmap4by4.keySet() ) {
		    if (hmap4by4.get(key).equals(input))
			bit=key ;
		}
		int index = Integer.valueOf(bit);
		int i = index / 10 % 10;
		int j = index % 10;
		array[i][j]=u;
		if(u==1) {state.Xcount++;} //changed to track the no. of tiles on board
		else if(u==-1) {state.Ocount++;}
		
		for (int k=i-1; k <=i+1; k++) {
			for (int m= j-1; m<=j+1; m++) {
				if (k<4 && k>-1 && m<4 && m>-1 && array[k][m]!= array[i][j]) { 
					if (array[k][m]== -1*u ) {  
						int diffK = i-k;  int diffM = j-m;
						if ((diffK==-1 ||diffK==1) && (diffM==-1 ||diffM==1) ) { 
							//System.out.println("diagonal innn ");
							int n=k; int o=m;
							n=n-diffK; o=o-diffM;
							while ( n<4 && n>-1 && o>-1 && o<4 ) {  
								
								if (array[n][o]==u) {
									break;
											}
								n=n-diffK; o=o-diffM;
								}
							if ( o<4 && o>-1 && n<4 && n>-1) {  
							while (n!=k && o!=m) { n=n+diffK; o=o+diffM;
							if (array[n][o]==-1*u) {
								array[n][o]=u;  
								if(u==1) {state.Xcount++; state.Ocount--;} //changed to track the no. of tiles on board
								else if(u==-1) {state.Xcount--;state.Ocount++;}
								}}
							}
						}
						
						else if ((diffK==-1 ||diffK==1) && diffM==0) { 
							//System.out.println("vertical innn ");

							int n=k;
							n=n-diffK;
							while ( n<4 && n>-1) {  
								
								if (array[n][j]==u) {
									break;
											}
								n=n-diffK;
								}
							if ( n<4 && n>-1) {  
							while (n!=k) {n=n+diffK;
							if (array[n][m]==-1*u) {
								array[n][m]=u;  
								if(u==1) {state.Xcount++; state.Ocount--;} //changed to track the no. of tiles on board
								else if(u==-1) {state.Xcount--;state.Ocount++;}
								}}
							}
						}
						
						
						else if ((diffM==-1 ||diffM==1) && diffK==0) { 
							//System.out.println("horizontal innn ");
							int o=m;
							o=o-diffM;
							while ( o<4 && o>-1) {  
								if (array[i][o]==u) {
									break;
											}
								o=o-diffM;
								
								}
							if ( o<4 && o>-1) {  
							while (o!=m) {
								o=o+diffM;
								if (array[k][o]==-1*u) {
								array[k][o]=u; 
								if(u==1) {state.Xcount++; state.Ocount--;} //changed to track the no. of tiles on board
								else if(u==-1) {state.Xcount--;state.Ocount++;}
								}}
						}
						}
						
						
					}	
			}
			
			}}
		
		return state;
	}
	
	public static States copyMove(String input, int u, States state) { //changed from return int[][] to return State
		States copy = new States();
		copy.Ocount = state.Ocount;
		copy.Xcount = state.Xcount;
		copy.totalCount = copy.Xcount +copy.Ocount;
		for(int i=0;i<state.currentState.length;i++) {
			for(int j=0;j<state.currentState[i].length;j++) {
				copy.currentState[i][j] = state.currentState[i][j];
			}
		}
		int[][] array = copy.getState();
		String bit = "";
		for ( String key : hmap4by4.keySet() ) {
		    if (hmap4by4.get(key).equals(input))
			bit=key ;
		}
		int index = Integer.valueOf(bit);
		int i = index / 10 % 10;
		int j = index % 10;
		array[i][j]=u;
		if(u==1) {copy.Xcount++;} //changed to track the no. of tiles on board
		else if(u==-1) {copy.Ocount++;}
		
		for (int k=i-1; k <=i+1; k++) {
			for (int m= j-1; m<=j+1; m++) {
				if (k<4 && k>-1 && m<4 && m>-1 && array[k][m]!= array[i][j]) { 
					if (array[k][m]== -1*u ) {  
						int diffK = i-k;  int diffM = j-m;
						if ((diffK==-1 ||diffK==1) && (diffM==-1 ||diffM==1) ) { 
							//System.out.println("diagonal innn ");
							int n=k; int o=m;
							n=n-diffK; o=o-diffM;
							while ( n<4 && n>-1 && o>-1 && o<4 ) {  
								
								if (array[n][o]==u) {
									break;
											}
								n=n-diffK; o=o-diffM;
								}
							if ( o<4 && o>-1 && n<4 && n>-1) {  
							while (n!=k && o!=m) { n=n+diffK; o=o+diffM;
							if (array[n][o]==-1*u) {
								array[n][o]=u;  
								if(u==1) {copy.Xcount++; copy.Ocount--;} //changed to track the no. of tiles on board
								else if(u==-1) {copy.Xcount--;copy.Ocount++;}
								}}
							}
						}
						
						else if ((diffK==-1 ||diffK==1) && diffM==0) { 
							//System.out.println("vertical innn ");

							int n=k;
							n=n-diffK;
							while ( n<4 && n>-1) {  
								
								if (array[n][j]==u) {
									break;
											}
								n=n-diffK;
								}
							if ( n<4 && n>-1) {  
							while (n!=k) {n=n+diffK;
							if (array[n][m]==-1*u) {
								array[n][m]=u;  
								if(u==1) {copy.Xcount++; copy.Ocount--;} //changed to track the no. of tiles on board
								else if(u==-1) {copy.Xcount--;copy.Ocount++;}
								}}
							}
						}
						
						
						else if ((diffM==-1 ||diffM==1) && diffK==0) { 
							//System.out.println("horizontal innn ");
							int o=m;
							o=o-diffM;
							while ( o<4 && o>-1) {  
								if (array[i][o]==u) {
									break;
											}
								o=o-diffM;
								
								}
							if ( o<4 && o>-1) {  
							while (o!=m) {
								o=o+diffM;
								if (array[k][o]==-1*u) {
								array[k][o]=u; 
								if(u==1) {copy.Xcount++; copy.Ocount--;} //changed to track the no. of tiles on board
								else if(u==-1) {copy.Xcount--;copy.Ocount++;}
								}}
						}
						}
						
						
					}	
			}
			
			}}
		
		return copy;
	}
		
	
	
//	public static void updateBoard(String input, int u, int[][] array) {
//		
//	}
	
	public static HashMap<String, String> hmap4by4 = new HashMap<String, String>();
	
	public static void hmap44() {
		hmap4by4.put("00","a1");
		hmap4by4.put("01", "b1");
		hmap4by4.put("02", "c1");
		hmap4by4.put("03", "d1");
		hmap4by4.put("04", "e1");
		hmap4by4.put("05", "f1");
		hmap4by4.put("06", "g1");
		hmap4by4.put("07", "h1");
		
		hmap4by4.put("10", "a2");
		hmap4by4.put("11", "b2");
		hmap4by4.put("12", "c2");
		hmap4by4.put("13", "d2");
		hmap4by4.put("14", "e2");
		hmap4by4.put("15", "f2");
		hmap4by4.put("16", "g2");
		hmap4by4.put("17", "h2");
		
		hmap4by4.put("20", "a3");
		hmap4by4.put("21", "b3");
		hmap4by4.put("22", "c3");
		hmap4by4.put("23", "d3");
		hmap4by4.put("24", "e3");
		hmap4by4.put("25", "f3");
		hmap4by4.put("26", "g3");
		hmap4by4.put("27", "h3");
		
		hmap4by4.put("30", "a4");
		hmap4by4.put("31", "b4");
		hmap4by4.put("32", "c4");
		hmap4by4.put("33", "d4");
		hmap4by4.put("34", "e4");
		hmap4by4.put("35", "f4");
		hmap4by4.put("36", "g4");
		hmap4by4.put("37", "h4");
		
		hmap4by4.put("40", "a5");
		hmap4by4.put("41", "b5");
		hmap4by4.put("42", "c5");
		hmap4by4.put("43", "d5");
		hmap4by4.put("44", "e5");
		hmap4by4.put("45", "f5");
		hmap4by4.put("46", "g5");
		hmap4by4.put("47", "h5");
		
		
		hmap4by4.put("50", "a6");
		hmap4by4.put("51", "b6");
		hmap4by4.put("52", "c6");
		hmap4by4.put("53", "d6");
		hmap4by4.put("54", "e6");
		hmap4by4.put("55", "f6");
		hmap4by4.put("56", "g6");
		hmap4by4.put("57", "h6");
		
		hmap4by4.put("60", "a7");
		hmap4by4.put("61", "b7");
		hmap4by4.put("62", "c7");
		hmap4by4.put("63", "d7");
		hmap4by4.put("64", "e7");
		hmap4by4.put("65", "f7");
		hmap4by4.put("66", "g7");
		hmap4by4.put("67", "h7");
		
		hmap4by4.put("70", "a8");
		hmap4by4.put("71", "b8");
		hmap4by4.put("72", "c8");
		hmap4by4.put("73", "d8");
		hmap4by4.put("74", "e8");
		hmap4by4.put("75", "f8");
		hmap4by4.put("76", "g8");
		hmap4by4.put("77", "h8");
		
	}
	
	

	public static void main(String[] args) {
//		int u=67;
//		System.out.println("el ahad: mod 10 ==> " + u % 10);
//		System.out.println("el aacharat: div by 10 then mod 10 ==> " + u / 10% 10);
		
		//States gameState = new States();
		Solution solution = new Solution();
		
		System.out.println("Reversi by....");
		System.out.println("Choose your game");
		System.out.println("1. Small 4x4 Reversi");
		System.out.println("2. Medium 6x6 Reversi");
		System.out.println("3. Standard 8x8 Reversi");

		Scanner s = new Scanner(System.in);
		System.out.print("Your choice? ");
		int gameChoice = s.nextInt();
		States gameState = new States(gameChoice);

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
					int lol =0;
					gameState.displayState();
					boolean userCanMove = true;
					//boolean opponentCanMove = true;
					while (userCanMove == true) {
					System.out.println(" ");
					int skips = 0;
					
					List<String> lm = legalMoves(t, gameState.getState()); //change to expand function in states
					if (lm.isEmpty()) { // game not over but rather player is skipped
						skips++;
						System.out.println("No moves to play, Skipped!");
						lm = legalMoves(-t, gameState.getState());
						if(lm.isEmpty()) {
							if(gameState.isTerminalState()) {
								gameState.GameOver();
								userCanMove = false;
								break;
							}
							else {
								System.out.println("No move possible, skipped!");
								skips++;
								if(skips>=2) {
									gameState.GameOver();
									break;
								}
							}
							continue;
						}
						//for(String str: lm) {//generates all the child nodes for frontier
							States duplicate = gameState;  
							//States childState =  userMove(str, -t,duplicate); //changed so that each state generated has a Xcount and Ocount
							solution.populateFrontier(gameState, solution.makeNode(gameState));
						//}
						gameState = solution.graphSearch(-t);//AI plays using minimax
						//if(gameState == null)??
						//randomMove(-t,  gameState);
						gameState.displayState();
						continue;
						//game should play till end or until player quits, add q button to provide that option
//						userCanMove = false;
//						System.out.println("Game Over.");
//						break;
					}
					System.out.println(" ");
					System.out.println("Need help? legal moves possible: " + lm);
					System.out.println("Or press \"q\" to quit");
					System.out.println("Your move: ");
					String yourMove = s.next();
					
					if(yourMove.equalsIgnoreCase("q")) {
						userCanMove = false;
						System.out.println("Game Forfeited....");
						//insert finish function - declare winner
						break;
					}
					if (lm.contains(yourMove) && userCanMove==true) {
					gameState = copyMove(yourMove, t,gameState);}
					//else if(userCanMove==false) {}
					else {System.out.println("Please choose a move that is legal."); continue;}
					
					
					gameState.displayState();
					System.out.println(" ");
					System.out.println(" Your opponent has played: ");
					System.out.println(" ");
					System.out.println("Ocount = "+gameState.Ocount + ", Xcount = "+ gameState.Xcount);

					lm = legalMoves(-t, gameState.getState());//legal moves for AI
					if(lm.isEmpty()) {
						if(gameState.isTerminalState()) {
							gameState.GameOver();
							userCanMove = false;
							break;
						}
						else {
							System.out.println("No move possible, skipped!");
							skips++;
						}
						continue;
					}
					//for(String str: lm) {//generates all the child nodes for frontier
						States duplicate = gameState;  
						//States childState =  userMove(str, -t,duplicate); //changed so that each state generated has a Xcount and Ocount
					solution.populateFrontier(gameState, solution.makeNode(gameState));
					//}
					
					
					
					System.out.println(solution.frontier.isEmpty());
					gameState = solution.graphSearch(-t);//AI plays using minimax
					//if(gameState == null)??
					//randomMove(-t,  gameState);
					System.out.println(" ");
					System.out.println(gameState.utility);
					System.out.println(" ");
				
					gameState.displayState();
					
					
					System.out.println("Ocount = "+gameState.Ocount + ", Xcount = "+ gameState.Xcount);
					System.out.println("");
					//lol ++;
					}
					

				} else if (colorChoice.equals("o")) {
//					t = -1;
//					display(board4by4);
//					String lm = legalMoves(t, board4by4);
//					System.out.println(" ");
//
//					System.out.println("Now with legal moves displayed as L");
//
//					display(board4by4);
//					System.out.println("Need help? legal moves possible: " + lm);
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
