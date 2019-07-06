package uk.ac.qub.technopoly;

public class Player implements Comparable<Player> {
	// GIT TEST 123
	private int playerID;
	private String playerName;
	private int playerCash;
	private int playerEquity;
	private int playerPosition;
	private boolean diceRolled;

	// default constructor
	public Player() {
	}

	// constructor with fields
	public Player(int playerID, String playerName, int playerCash, int playerEquity, int playerPosition,
			boolean diceRolled) {
		super();
		this.playerID = playerID;
		this.playerName = playerName;
		this.playerCash = playerCash;
		this.playerEquity = playerEquity;
		this.playerPosition = playerPosition;
		this.diceRolled = diceRolled;
	}

	public void displayResources() throws InterruptedException {
		System.out.println("\n"+this.playerName+" resources:");
		System.out.println("Cash: "+this.playerCash);
		System.out.println("Equity: "+this.playerEquity);
		System.out.print("Apps owned: ");
		for(int n=0; n < Board.tiles.length; n++) {
			if (n == 0 || n == 6) {
				
			} else {
				if (((App) Board.tiles[n]).getOwner() == this.playerID) {
				System.out.print(Board.tiles[n].getTileName()+",");
				}
			}
		}
		System.out.println();
		Thread.sleep(1000);
	}

	public int getPlayerID() {
		return playerID;
	}

	public void setPlayerID(int playerID) {
		this.playerID = playerID;
	}

	public String getPlayerName() {
		return playerName;
	}

	public void setPlayerName(String playerName) {
		this.playerName = playerName;
	}

	public int getPlayerCash() {
		return playerCash;
	}

	public void setPlayerCash(int playerCash) {
		this.playerCash = playerCash;
	}

	public int getPlayerEquity() {
		return playerEquity;
	}

	public void setPlayerEquity(int playerEquity) {
		this.playerEquity = playerEquity;
	}

	public int getPlayerPosition() {
		return playerPosition;
	}

	public void setPlayerPosition(int playerPosition) {
		this.playerPosition = playerPosition;
	}

	public boolean isDiceRolled() {
		return diceRolled;
	}

	public void setDiceRolled(boolean diceRolled) {
		this.diceRolled = diceRolled;
	}

	@Override
	public int compareTo(Player p) {

		return this.playerCash - p.getPlayerCash();

	}

}
