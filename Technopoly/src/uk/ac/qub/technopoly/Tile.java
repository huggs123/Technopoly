package uk.ac.qub.technopoly;

public abstract class Tile {

	private int tileNo;
	private String tileName;

	public Tile(int tileNo, String tileName) {
		super();
		this.tileNo = tileNo;
		this.tileName = tileName;
	}

	public int getTileNo() {
		return tileNo;
	}

	public void setTileNo(int tileNo) {
		this.tileNo = tileNo;
	}

	public String getTileName() {
		return tileName;
	}

	public void setTileName(String tileName) {
		this.tileName = tileName;
	}

}
