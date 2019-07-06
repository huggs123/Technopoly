package uk.ac.qub.technopoly;

import static org.junit.Assert.*;
import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.ArrayList;

import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import org.junit.jupiter.api.BeforeEach;

public class BankerTest {

	private Player player1 = new Player(0, "John", 500, 0, 11, true);
	private Player player2 = new Player(1, "James", 200, 0, 11, true);
	private Player player3 = new Player(1, "first", 2000, 0, 11, true);

	@BeforeEach
	public void setUp() throws Exception {

	}

	@Test
	public final void testCheckResources() {

		Game.players.add(player1);
		Game.players.add(player2);
		Game.players.add(player3);

		boolean resourceOk = true;
		boolean resourceNotOk = false;
		boolean resource;

		// Test for player with sufficient Resources
		resource = Banker.checkResources(300, 0);
		assertEquals(resourceOk, resource);

		// Test for player with insufficient Resources
		resource = Banker.checkResources(300, 1);
		assertEquals(resourceNotOk, resource);

	}

	@Test
	public final void testPayRoyalty() throws InterruptedException {

		Board.createTiles();
		Game.currentPlayer = 0;

		// Test to show paying royalty for final version
		int balance1 = Game.players.get(0).getPlayerCash() - ((App) Board.tiles[3]).getRoyaltyPrice();
		int balance2 = Game.players.get(1).getPlayerCash() + ((App) Board.tiles[3]).getRoyaltyPrice();

		((App) Board.tiles[3]).setOwner(1);

		Banker.payRoyalty(3);
		assertEquals(balance1, Game.players.get(0).getPlayerCash());
		assertEquals(balance2, Game.players.get(1).getPlayerCash());

		// Test to show paying royalty for tile only
		int balanceFinal1 = Game.players.get(0).getPlayerCash() - ((App) Board.tiles[2]).getFinalRentPrice();
		int balanceFinal2 = Game.players.get(1).getPlayerCash() + ((App) Board.tiles[2]).getFinalRentPrice();

		((App) Board.tiles[2]).setOwner(1);
		((App) Board.tiles[2]).setNumFinal(1);

		Banker.payRoyalty(2);
		assertEquals(balanceFinal1, Game.players.get(0).getPlayerCash());
		assertEquals(balanceFinal2, Game.players.get(1).getPlayerCash());

		// Test to show paying royalty for beta versions version

		((App) Board.tiles[1]).setOwner(1);
		((App) Board.tiles[1]).setNumBeta(2);

		int betaNum = ((App) Board.tiles[1]).getNumBeta();
		int betaRent = ((App) Board.tiles[1]).getBetaRentPrice();
		int royalty = ((App) Board.tiles[1]).getRoyaltyPrice();

		int balanceBeta1 = Game.players.get(0).getPlayerCash() - (royalty + (betaNum * betaRent));
		int balanceBeta2 = Game.players.get(1).getPlayerCash() + (royalty + (betaNum * betaRent));

		System.out.println(Game.players.get(0).getPlayerCash());
		System.out.println(Game.players.get(1).getPlayerCash());

		Banker.payRoyalty(1);
		assertEquals(balanceBeta1, Game.players.get(0).getPlayerCash());
		assertEquals(balanceBeta2, Game.players.get(1).getPlayerCash());

		// Test to show insufficient Resources
		Game.players.get(0).setPlayerCash(50);
		Game.players.get(1).setPlayerCash(100);

		int noBalance1 = 0;
		int noBalance2 = Game.players.get(1).getPlayerCash() + Game.players.get(0).getPlayerCash();

		Banker.payRoyalty(2);
		assertEquals(noBalance1, Game.players.get(0).getPlayerCash());
		assertEquals(noBalance2, Game.players.get(1).getPlayerCash());

	}

	@Test
	public final void testBuyApp() throws InterruptedException {

		// Test to show what happens when player has enough cash
		Game.players.get(0).setPlayerCash(300);
		int expectedBalance = Game.players.get(0).getPlayerCash() - ((App) Board.tiles[8]).getPurchasePrice();
		int expectedOwner = 0;

		Banker.buyApp(0, 8);
		assertEquals(expectedBalance, Game.players.get(0).getPlayerCash());
		assertEquals(expectedOwner, ((App) Board.tiles[8]).getOwner());

		// Test to show what happens when player does not have enough cash
		Game.players.get(0).setPlayerCash(100);
		int expectedBalance1 = Game.players.get(0).getPlayerCash();
		int expectedOwner1 = 99;

		Banker.buyApp(0, 7);
		assertEquals(expectedBalance1, Game.players.get(0).getPlayerCash());
		assertEquals(expectedOwner1, ((App) Board.tiles[7]).getOwner());

	}

	@Test
	public final void testVersionRelease() throws InterruptedException {

		Game.players.add(player1);
		Game.players.add(player2);
		
		Game.currentPlayer = 0;
		
		Board.categories.get(1).setOwner(0);
		Board.categories.get(2).setOwner(0);
		Banker.versionRelease();
		
	}

	@Test
	public final void testPassGo() throws InterruptedException {

		int expected = Game.players.get(0).getPlayerCash() + RDInvestment.INVESTMENT_AMOUNT;

		Banker.passGo();
		assertEquals(expected, Game.players.get(0).getPlayerCash());
	}

	}
