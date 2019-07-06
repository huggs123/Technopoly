package uk.ac.qub.technopoly;

import static org.junit.Assert.*;

import static org.junit.Assert.*;

import java.util.ArrayList;
import java.util.Arrays;

import org.junit.Before;
import org.junit.Test;

import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

public class CreateGameTest {

	// test data
	public static final int MIN_PLAYERS = 2;
	public static final int MAX_PLAYERS = 4;
	public static final int INITIAL_CASH = 1000;

	Player player1;
	Player player2;

	public static ArrayList<Player> players;

	public static ArrayList<Player> expectedPlayers;

	@BeforeClass
	public static void setUpBeforeClass() throws Exception {
	}

	@Before
	public void setUp() throws Exception {

		// initialise the data

		// players = new ArrayList<Player>();
		expectedPlayers = new ArrayList<Player>();

		// seting Player objects for expected result Player arrays
		player1 = new Player(0, "Bob", 1000, 0, 0, false);
		player2 = new Player(1, "Graham", 1000, 0, 0, false);

	}// end of set up

	@Test
	public final void testMain() {
		// fail("Not yet implemented"); // TODO
	}

	@Test
	public final void testStartGame() {
		// fail("Not yet implemented"); // TODO
	}

	@Test
	public final void testCreatePlayers() throws InterruptedException {
		System.out.println("About to test Game.createPlayers() method\n");

		// adding new Players to expectedPlayers array
		expectedPlayers.add(player1);
		expectedPlayers.add(player2);

		System.out.println("createPlayers method starting....\n");
		// The static method createPlayers() from the type Game should be accessed in a
		// static way not on an instance e.g. do not need testGame.createPlayers()
		CreateGame.createPlayers();

		System.out.println("\nStarting Array size test....");
		// testing that expected and actual Player arrays are the same size
		assertEquals(expectedPlayers.size(), Game.players.size());
		System.out.println("Array size test complete\n");

		System.out.println("Starting playerID test....");
		// testing that the playerID set for the expected and actual Player arrays are
		// the same
		assertEquals(expectedPlayers.get(0).getPlayerID(), Game.players.get(0).getPlayerID());

		// assertEquals(expectedPlayers.get(1).getPlayerID(),
		// Game.players.get(1).getPlayerID());
		System.out.println("playerID test complete\n");

		System.out.println("Starting playerName test....");
		// testing that the playerNames set for the expected and actual Player arrays
		// are the same
		assertEquals(expectedPlayers.get(0).getPlayerName(), Game.players.get(0).getPlayerName());
		assertEquals(expectedPlayers.get(1).getPlayerName(), Game.players.get(1).getPlayerName());
		System.out.println("playerName test complete\n");

		System.out.println("Starting playerCash test....");
		// testing that the cash set for the expected and actual Player arrays are the
		// same
		assertEquals(expectedPlayers.get(0).getPlayerCash(), Game.players.get(0).getPlayerCash());
		assertEquals(expectedPlayers.get(1).getPlayerCash(), Game.players.get(1).getPlayerCash());
		System.out.println("playerCash test complete\n");

		System.out.println("Starting playerEquity test....");
		// testing that the equity set for the expected and actual Player arrays are the
		// same
		assertEquals(expectedPlayers.get(0).getPlayerEquity(), Game.players.get(0).getPlayerEquity());
		assertEquals(expectedPlayers.get(1).getPlayerEquity(), Game.players.get(1).getPlayerEquity());
		System.out.println("playerEquity test complete\n");

		System.out.println("Starting playerPosition test....");
		// testing that the board position set for the expected and actual Player arrays
		// are the same
		assertEquals(expectedPlayers.get(0).getPlayerPosition(), Game.players.get(0).getPlayerPosition());
		assertEquals(expectedPlayers.get(1).getPlayerPosition(), Game.players.get(1).getPlayerPosition());
		System.out.println("playerPosition test complete\n");

		System.out.println("Starting diceRolled test....");
		// testing that the the dice boolean is set to false for both the expected and
		// actual Player arrays
		assertEquals(expectedPlayers.get(0).isDiceRolled(), Game.players.get(0).isDiceRolled());
		assertEquals(expectedPlayers.get(1).isDiceRolled(), Game.players.get(1).isDiceRolled());
		System.out.println("diceRolled test complete\n");

		// print out - test complete
		System.out.println("Testing of createPlayers() method complete\n");
	}

}
