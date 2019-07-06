package uk.ac.qub.technopoly;

import java.util.ArrayList;
import java.util.Scanner;

public class Banker {

	public static boolean checkResources(int value, int playerID) {

		boolean resourcesOK = true;
		int resourceToCheck = value;
		int id = playerID;
		int cash = Game.players.get(id).getPlayerCash();
		if (resourceToCheck <= cash) {
			resourcesOK = true;

		} else {
			resourcesOK = false;
		}

		return resourcesOK;
	}

	public static void payRoyalty(int tileNo) throws InterruptedException {
		// call check resources
		// if resourcesOK, subtract royalty fee from players resources
		// if resources not OK, end game

		int currentPlayerID = Game.currentPlayer;
		
		int royalty = ((App) Board.tiles[tileNo]).getRoyaltyPrice();
		int owner = ((App) Board.tiles[tileNo]).getOwner();
		int noBeta = ((App) Board.tiles[tileNo]).getNumBeta();
		int noFinal = ((App) Board.tiles[tileNo]).getNumFinal();
		int betaRent = ((App) Board.tiles[tileNo]).getBetaRentPrice();
		int finalRent = ((App) Board.tiles[tileNo]).getFinalRentPrice();

		int ownerCash = Game.players.get(owner).getPlayerCash();
		String ownerName = Game.players.get(owner).getPlayerName();
		int playerCash = Game.players.get(currentPlayerID).getPlayerCash();
		String playerName = Game.players.get(currentPlayerID).getPlayerName();

		if (noFinal == 1) {

			boolean pay = checkResources(finalRent, currentPlayerID);

			if (pay == true) {
				int balanceNewOwner = ownerCash + finalRent;
				int balanceNewPlayer = playerCash - finalRent;

				Game.players.get(owner).setPlayerCash(balanceNewOwner);
				Game.players.get(currentPlayerID).setPlayerCash(balanceNewPlayer);
				Thread.sleep(1000);
				System.out.println("\n"+playerName + " has paid " + finalRent + " BitCoins to " + ownerName);
				Thread.sleep(2000);
				Game.players.get(currentPlayerID).displayResources();

			} else {

				int balanceNewOwner = ownerCash + playerCash;
				int balanceNewPlayer = 0;

				Game.players.get(owner).setPlayerCash(balanceNewOwner);
				
				Game.players.get(currentPlayerID).setPlayerCash(balanceNewPlayer);
				
				System.out.println(playerName + " has insufficent BitCoins, the game will now end");
				Thread.sleep(1000);
				Game.endGame();
			}

		} else {

			int toPay = royalty + (noBeta * betaRent);
			boolean pay = checkResources(toPay, currentPlayerID);

			if (pay == true) {
				int balanceNewOwner = ownerCash + toPay;
				int balanceNewPlayer = playerCash - toPay;

				Game.players.get(owner).setPlayerCash(balanceNewOwner);
				Game.players.get(currentPlayerID).setPlayerCash(balanceNewPlayer);

				Thread.sleep(1000);
				System.out.println("\n"+playerName + " has paid " + toPay + " BitCoins to " + ownerName);
				Thread.sleep(2000);
				Game.players.get(currentPlayerID).displayResources();

			} else {
				int balanceNewOwner = ownerCash + playerCash;
				int balanceNewPlayer = 0;

				Game.players.get(owner).setPlayerCash(balanceNewOwner);
				
				Game.players.get(currentPlayerID).setPlayerCash(balanceNewPlayer);
				System.out.println(playerName + " has insufficent BitCoins, the game will now end");
				Game.endGame();
			}
		}

	}

	public static void buyApp(int playerID, int tileNo) throws InterruptedException {
		
		String ownerName = Game.players.get(playerID).getPlayerName();
		int playerCash = Game.players.get(playerID).getPlayerCash();
		int currentTilePrice = ((App) Board.tiles[tileNo]).getPurchasePrice();
		String currentTileName = ((App) Board.tiles[tileNo]).getTileName();
		int currentTileCategory = ((App) Board.tiles[tileNo]).getCategory();
		
		// call check resources
		boolean canBuy = checkResources(currentTilePrice, playerID);

		if (canBuy == true) {
			
			// if resources OK subtract royalty fee from players resources and set owner of tile		
			((App) Board.tiles[tileNo]).setOwner(playerID);
			playerCash = playerCash - currentTilePrice;
			Game.players.get(playerID).setPlayerCash(playerCash);
			Game.players.get(playerID).setPlayerEquity(Game.players.get(playerID).getPlayerEquity() + currentTilePrice);
			System.out.println("\n"+ownerName + " has bought " + currentTileName);
			int categoryID = ((App) Board.tiles[tileNo]).getCategory();
			Category tileCategory = Board.categories.get(categoryID);
			
			// check if app purchase means the player now owns the full category
			int numOfCategoryOwned = 0;
			int numInCategory = tileCategory.getNumApps();
			for (int n = 0; n < Board.tiles.length; n++) {
				int categoryOwner;
				try {
					categoryOwner = ((App) Board.tiles[n]).getOwner();
				} catch (Exception e) {
					categoryOwner = 99;
				}
				int checkCategoryID = tileCategory.getCategoryID();
				if (categoryOwner == playerID && checkCategoryID == currentTileCategory) {
					numOfCategoryOwned++;
				}
			}
			
			if (numOfCategoryOwned == tileCategory.getNumApps()) {
				tileCategory.setOwner(playerID);
			}
			
			// display resources
			Thread.sleep(1000);
			Game.players.get(playerID).displayResources();
			
		} else {
			
			// if resources not OK, return to player options (take turn)
			System.out.println("\n"+ownerName + " has insufficent BitCoins, choose another option");
			Thread.sleep(1000);
		}
		
	}

	public static void versionRelease() throws InterruptedException {
		
		// check categories that player owns
		int numCategories = Board.categories.size();
		int numCategoriesOwned = 0;
		int currentPlayer = Game.currentPlayer;
		int playerCash = Game.players.get(currentPlayer).getPlayerCash();
		Scanner input = new Scanner (System.in);
		
		ArrayList<Integer> categoriesOwned = new ArrayList<Integer>();
		
		System.out.println("\nYou currently own the following categories:");
		Thread.sleep(1000);
		
		for (int n = 0; n < numCategories; n++) {
			if (Board.categories.get(n).getOwner() == currentPlayer) {
				System.out.println(n + ". " + Board.categories.get(n).getCategoryName());
				categoriesOwned.add(n);
				numCategoriesOwned++;
			}
		}
		
		if (numCategoriesOwned == 0) {
			System.out.println("\nYou don't own any complete categories");
			Thread.sleep(1000);
			Game.takeTurn();
		} else {
			System.out.println("\nPlease select a category you would like to release a version for");
		
			int answerCategory = input.nextInt();
					
			if(!categoriesOwned.contains(answerCategory)) {
				System.out.println("\nYou do not own this category");
				Thread.sleep(1000);
				Game.takeTurn();
			} else {
				System.out.println("\nSelect an app to modify: ");
				for (int n = 0; n < Board.tiles.length; n++) {
					int tileCategory;
					try {
						tileCategory = ((App) Board.tiles[n]).getCategory();
					} catch (Exception e) {
						tileCategory = 99;
					}
					if (tileCategory == answerCategory) {
						System.out.println(n + ". " + Board.tiles[n].getTileName());
					}
				}
			
				int answerTile = input.nextInt();
				input.nextLine();
				
				//add error correction for non int selection
				
				int tileID = answerTile;
				int tileNumBeta = ((App) Board.tiles[tileID]).getNumBeta();
				int tileNumBetaMax = App.MAX_BETA;
				int tileNumFinal = ((App) Board.tiles[tileID]).getNumFinal();
				int tileNumFinalMax = App.MAX_FINAL;
				int tileFinalPrice = ((App) Board.tiles[tileID]).getFinalPurchasePrice();
				int tileBetaPrice = ((App) Board.tiles[tileID]).getBetaPurchasePrice();
				
				if (tileNumFinal == tileNumFinalMax){
					System.out.println("\nApp is fully developed!");
					Game.takeTurn();
				} else if (tileNumBeta >= tileNumBetaMax && tileNumFinal < tileNumFinalMax) {
				
					boolean answer = false;
				
					do {
						System.out.println("\nWould you like to change "+((App) Board.tiles[tileID]).getTileName()+" to final release? Please enter y or n ");
						String answerFinal = input.nextLine();
						if(answerFinal.equals("y")) {
							((App) Board.tiles[tileID]).setNumFinal(tileNumFinal+1);
							Game.players.get(currentPlayer).setPlayerCash(playerCash-tileFinalPrice);
							Game.players.get(currentPlayer).setPlayerEquity(Game.players.get(currentPlayer).getPlayerEquity() + tileFinalPrice);
							System.out.println("\nNew " + ((App) Board.tiles[tileID]).getTileName() + " tile status:"+((App) Board.tiles[tileID]).getNumBeta() +"/" + App.MAX_BETA + "Beta & " + ((App) Board.tiles[tileID]).getNumFinal() +"/" + tileNumFinalMax + " final.");
							Thread.sleep(1000);
							Game.takeTurn();
							answer = true;
						} else if (answerFinal.equals("n")) {
							Game.takeTurn();
							answer = true;
						} else {
							System.out.println("Not a valid option. Please enter y or n");
							answer = false;
						}
						
					} while (!answer);
				
				} else if (tileNumBeta < tileNumBetaMax) {
					
					boolean answer = false;
					
					do {
						System.out.println("\nWould you like to add beta release status to "+((App) Board.tiles[tileID]).getTileName()+"? Please enter y or n ");
						String answerBeta = input.nextLine();
						if(answerBeta.equals("y")) {
							((App) Board.tiles[answerTile]).setNumBeta(((App) Board.tiles[answerTile]).getNumBeta()+1);
							Game.players.get(currentPlayer).setPlayerCash(playerCash-tileBetaPrice);
							Game.players.get(currentPlayer).setPlayerEquity(Game.players.get(currentPlayer).getPlayerEquity() + tileBetaPrice);
							System.out.println("\nNew " + ((App) Board.tiles[tileID]).getTileName() + " tile status:"+((App) Board.tiles[answerTile]).getNumBeta() +"/" + App.MAX_BETA + " Beta & " + ((App) Board.tiles[answerTile]).getNumFinal() +"/" + App.MAX_FINAL + " final.");
							Thread.sleep(1000);
							Game.takeTurn();
						} else if (answerBeta.equals("n")) {
							Game.takeTurn();
						} else {
							System.out.println("Not a valid option. Please enter y or n");
							answer = false;
						}
					} while (!answer);
					
				} else {
					System.out.println("An error has occured");
				}
			
			}
		}
		
	}

	public static void passGo() throws InterruptedException {
		int playerID = Game.currentPlayer;
		int playerCash = Game.players.get(playerID).getPlayerCash();
		playerCash = playerCash + RDInvestment.INVESTMENT_AMOUNT; 
		Game.players.get(playerID).setPlayerCash(playerCash);
		System.out.println("\nCongratulations, you received a "+RDInvestment.INVESTMENT_AMOUNT+" bitcoin investment");
		Thread.sleep(1000);
		Game.players.get(playerID).displayResources();
	}

}