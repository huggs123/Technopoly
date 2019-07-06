package uk.ac.qub.technopoly;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class GameTest {

	// test data
	Player player1;
	String expectedPlayerName1;

	@BeforeAll
	static void setUpBeforeClass() throws Exception {
	}

	@BeforeEach
	void setUp() throws Exception {

		// initialise the data
		player1 = new Player(0, "Bob", 1000, 0, 0, false);

		expectedPlayerName1 = "Bob";

	}// end of set up

	@Test
	final void testTakeTurn() throws InterruptedException {

		System.out.println("\nAbout to test Game.takeTurn() method\n");
		// activate takeTurn() method

		System.out.println("Adding player Bob to players array");
		Game.players.add(player1);

		System.out.println("About to test takeTurn() method\n");

		// activate takeTurn() method
		Game.takeTurn();

		System.out.println("\nTesting if current player name and expected player name are the same....\n");
		// current player name test
		assertEquals(expectedPlayerName1, Game.players.get(Game.currentPlayer).getPlayerName());

		System.out.println("Testing of takeTurn() method complete");

	}// endofTestTakeTurn

	@Test
	final void testRollDice() {
		// fail("Not yet implemented"); // TODO
	}

	@Test
	final void testMovePosition() {
		// fail("Not yet implemented"); // TODO
	}

	@Test
	final void testEndTurn() {
		// fail("Not yet implemented"); // TODO
	}

	@Test
	final void testGetResults() {
		// fail("Not yet implemented"); // TODO
	}

	@Test
	final void testResults() {
		// fail("Not yet implemented"); // TODO
	}

	@Test
	final void testEndGame() {
		// fail("Not yet implemented"); // TODO
	}

}
