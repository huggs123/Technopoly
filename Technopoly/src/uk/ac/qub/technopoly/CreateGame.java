package uk.ac.qub.technopoly;

import java.util.Scanner;

public class CreateGame {

	public static void main(String[] args) throws InterruptedException {
		startGame();
	}
	
	public static void startGame() throws InterruptedException {
		
		Scanner input = new Scanner (System.in); // Create a Scanner object
		System.out.println("Would you like to play a new game of Technopoly? Answer: y or n");
		
		boolean startGame = false;
		
		do {
			String answer1 = input.nextLine();
			if (answer1.equals("y")) {
				startGame = true;
			} else if (answer1.equals("n")) {
				System.out.println("Are you sure you want to quit? Answer: y or n");
				String answer2 = input.nextLine();
				if(answer2.equals("y")) {
					System.out.print("Game not started!!........\n");
					System.out.print("Ending program\n");
					System.exit(0);
				} else {
					startGame=true;
				}
			} else {
				System.out.println("Invalid option. Please enter y or n\n");
			}
		} while (!startGame);
		
		createPlayers();
		
		Thread.sleep(1000);
		
		Board.createTiles();
		System.out.print("\nStarting game ");
		
		for (int n = 0; n < 60; n++) {
			System.out.print(".");
			Thread.sleep(25);
		}
		
		System.out.println();
		
		Game.currentPlayer = 0;
		
		Game.takeTurn();
	}
	
	public static void createPlayers() throws InterruptedException {
		
		Scanner input = new Scanner (System.in); // Create a Scanner object
			
		//variables
		boolean addPlayer = true;
		int numPlayers = 0;
		String playerName;
		
		System.out.println("\nPlease enter player's name...");
		playerName  = input.nextLine();
		Player newPlayer = new Player(numPlayers-1, playerName, Game.INITIAL_CASH, 0, 0, false);
		Game.players.add(newPlayer);
		numPlayers++;
		System.out.println("\n'"+playerName+"' added");
		Thread.sleep(1000);
	
		do {
			System.out.println("\nDo you wish to add another player? Select y or n");
			String answerAddAnother = input.nextLine();
			if (answerAddAnother.equals("n")) {
				if (numPlayers < Game.MIN_PLAYERS) {
					System.out.print("\nYou cannot play with one player. Would you like to add another player? Select y or n \n\n");
					String answerAddAnother2 = input.nextLine();
					if (answerAddAnother2.equals("n")) {
						addPlayer = false;
						System.out.println("\nNot enough players, ending game...");
						System.exit(0);
					} else if (answerAddAnother2.equals("y")) {
						System.out.println("\nPlease enter player's name...");
						playerName  = input.nextLine();
						
						boolean playerExists = false;
						
						for (int n = 0; n < Game.players.size(); n++) {
							String arrayPlayerName = Game.players.get(n).getPlayerName();
							if (arrayPlayerName.equals(playerName)) {
								System.out.println("Player already exists - cannot have two players with the same name.");
								playerExists = true;
							}
						}
						
						if (!playerExists) {
							Player newPlayer2 = new Player(numPlayers-1, playerName, Game.INITIAL_CASH, 0, 0, false);
							Game.players.add(newPlayer2);
							System.out.println("\n'"+playerName+"' added");
							Thread.sleep(1000);
							numPlayers++;
						}
						
					} else {
						System.out.println("\nInvalid option. Please enter y or n");
						addPlayer = true;
					}
					
				} else {
					addPlayer = false;
				}
				
			} else if (answerAddAnother.equals("y")){
				System.out.println("\nPlease enter player's name...");
				playerName  = input.nextLine();
				boolean playerExists = false;
				for (int n = 0; n < Game.players.size(); n++) {
					String arrayPlayerName = Game.players.get(n).getPlayerName();
					if (arrayPlayerName.equals(playerName)) {
						System.out.println("Player already exists - cannot have two players with the same name.");
						playerExists = true;
					}
				}
				
				if (!playerExists) {
					Player newPlayer2 = new Player(numPlayers-1, playerName, Game.INITIAL_CASH, 0, 0, false);
					Game.players.add(newPlayer2);
					System.out.println("\n'"+playerName+"' added");
					Thread.sleep(1000);
					numPlayers++;
				}
				
				if(numPlayers > Game.MAX_PLAYERS) {
					addPlayer = false;
					System.out.println("\nMaximum number of players reached, initialising game...");
				} else {
					addPlayer = true;
				}
			} else {
				System.out.println("\nInvalid option. Please enter y or n\n");
				addPlayer = true;
			}
			
		} while (addPlayer);//end of addPlayer do while loop ******************
	
		System.out.print("\nThe following players have been added:");
		System.out.println();
		
		for (int n = 0; n < Game.players.size();n++) {
			System.out.println(Game.players.get(n).getPlayerName());
		}
	
	}
}