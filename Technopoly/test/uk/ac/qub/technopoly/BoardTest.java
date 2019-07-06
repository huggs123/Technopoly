package uk.ac.qub.technopoly;

import static org.junit.Assert.assertTrue;
import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class BoardTest {

	Board board;

	@BeforeEach
	void setUp() throws Exception {

		Board.createTiles();
	}

	@Test
	void testGetTiles() {

		for (int i = 0; i < 12; i++) {
			if (i == 0) {

				assertTrue("R&D not ok", board.tiles[i].getTileName().equals("R & D Investment"));
			} else if (i == 6) {
				assertTrue("Tech Conference not ok", board.tiles[i].getTileName().equals("Tech Conference"));
			} else if (i == 3) {
				assertTrue("Facebook not ok", board.tiles[i].getTileName().equals("Facebook"));

			} else if (i == 10) {
				assertTrue("FortNite not ok", board.tiles[i].getTileName().equals("FortNite"));

			}
		}
	}
}