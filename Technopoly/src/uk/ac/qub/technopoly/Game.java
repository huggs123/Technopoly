package uk.ac.qub.technopoly;

import java.util.ArrayList;
import java.util.Scanner;
import java.util.concurrent.TimeUnit;

public class Game {

	public static ArrayList<Player> players = new ArrayList<Player>();
	public static Board mainBoard;
	public static int currentPlayer;
	public static final int MIN_PLAYERS = 2;
	public static final int MAX_PLAYERS = 4;
	public static final int INITIAL_CASH = 1000;

	public static void takeTurn() throws InterruptedException {

		String currentPlayerName = players.get(currentPlayer).getPlayerName();

		// display list of options
		System.out.println("\n..........................................................................");
		System.out.println("\n" + currentPlayerName + ", please select one of the following options:");
		System.out.println("1. Roll dice");
		System.out.println("2. Release app version");
		System.out.println("3. Display resources");
		System.out.println("4. End turn");
		System.out.println("99. Quit game");

		// get user input and call relevant method

		int selection;

		Scanner input = new Scanner(System.in);

		do {

			System.out.print("\nEnter option: ");

			try {
				selection = input.nextInt();
			} catch (Exception e) {
				selection = 99;
			}

			switch (selection) {
			case 1:
				rollDice();
				break;
			case 2:
				Banker.versionRelease();
				break;
			case 3:
				Game.players.get(Game.currentPlayer).displayResources();
				takeTurn();
				break;
			case 4:
				endTurn();
				break;
			case 99:
				quitGame();
				break;
			default:
				System.out.println("\nInvalid option. Please enter a number between 1 and 4.");
				break;
			}
			input.nextLine();
		} while (selection < 1 || selection > 4);

	}
	
	public static void quitGame() throws InterruptedException {
		
		System.out.print("Are you sure you want to end the Game Y or N");
		Scanner input = new Scanner(System.in);
		String selection = input.nextLine();
		if (selection.equals("y")){
			endGame();
		}else {
			System.out.println("Game not Ended");
			takeTurn();
		}
		
	}
	

	public static void rollDice() throws InterruptedException {

		if (players.get(currentPlayer).isDiceRolled()) {
			System.out.println("\nYou have already rolled the dice this turn. Please select another option.");
			Thread.sleep(1000);
			takeTurn();
		} else {

			String currentPlayerName = players.get(currentPlayer).getPlayerName();

			System.out.println("\nRolling dice...");
			System.out.println();
			Thread.sleep(1000);

			Dice dice1 = new Dice();
			Dice dice2 = new Dice();

			dice1.randomDice();
			dice2.randomDice();

			int dice1Value = dice1.getFaceValue();
			int dice2Value = dice2.getFaceValue();

			players.get(currentPlayer).setDiceRolled(true);

			int totalDiceValue = dice1Value + dice2Value;

			System.out.println(currentPlayerName + ", you rolled " + dice1Value + " and " + dice2Value + " (total "
					+ totalDiceValue + ")");

			Thread.sleep(1000);

			movePosition(totalDiceValue);

		}

	}

	public static void movePosition(int totalDiceValue) throws InterruptedException {
		int currentPosition = players.get(currentPlayer).getPlayerPosition();
		int theoreticalNewPosition = currentPosition + totalDiceValue;
		int newPosition;
		if (theoreticalNewPosition >= 12) {
			newPosition = theoreticalNewPosition - 12;
			Banker.passGo();
		} else {
			newPosition = theoreticalNewPosition;
		}

		players.get(currentPlayer).setPlayerPosition(newPosition);

		System.out.println("\nYou have landed on " + Board.tiles[newPosition].getTileName());

		Thread.sleep(1000);

		if (newPosition == 0 || newPosition == 6) {
			System.out.println("\nNo further action required, enjoy!");
			Thread.sleep(1000);
		} else {
			int tileOwnerID = ((App) Board.tiles[newPosition]).getOwner();
			if (tileOwnerID == 99) {
				int tilePrice = ((App) Board.tiles[newPosition]).getPurchasePrice();

				boolean answer = false;
				do {
					System.out.println("\nApp not owned by any player. Do you want to buy this tile for " + tilePrice
							+ " bitcoin? Answer y or n");
					Scanner input = new Scanner(System.in);
					String answerBuyTile = input.nextLine();
					Thread.sleep(1000);
					if (answerBuyTile.equals("n")) {
						answer = true;
					} else if (answerBuyTile.equals("y")) {
						Banker.buyApp(currentPlayer, newPosition);
						answer = true;
					} else {
						System.out.println("\nNot a valid option. Please enter y or n");
						answer = false;
					}
				} while (!answer);

			} else if (tileOwnerID == currentPlayer) {
				System.out.println("\nYou already own this tile");
				Thread.sleep(1000);
			} else {
				String tileOwnerName = players.get(tileOwnerID).getPlayerName();
				System.out.println("\nThis app is owned by " + tileOwnerName);
				Banker.payRoyalty(newPosition);
			}
		}

		takeTurn();

	}

	public static void endTurn() throws InterruptedException {
		if (!players.get(currentPlayer).isDiceRolled()) {
			System.out.println("\nYou must roll the dice before ending your turn.");
			Thread.sleep(1000);
			takeTurn();
		} else {

			System.out.println();
			System.out.println(players.get(currentPlayer).getPlayerName() + "'s turn ended");
			Thread.sleep(1000);

			if (currentPlayer < players.size() - 1) {
				currentPlayer++;
			} else {
				currentPlayer = 0;
			}
			players.get(currentPlayer).setDiceRolled(false);

			takeTurn();

		}

	}

	public static <T> void results(ArrayList<Player> players2) throws InterruptedException {

		int n = players2.size() - 1;

		System.out.println("\n\nCongratulations " + players2.get(n).getPlayerName()
				+ " you have beat all the other players. Your final Resources were " + players2.get(n).getPlayerCash()
				+ ".");
		System.out.println("\n Thr resources at the end of the Game are:\n");

		for (int i = players2.size() - 1; i >= 0; i--) {

			players2.get(i).displayResources();
		}
	}

	public static void endGame() throws InterruptedException {

		results(players);

		System.out.println("\n\nGame Over");
		//System.exit(0);
		// }
	}

}