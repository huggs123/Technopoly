package uk.ac.qub.technopoly;

public class Category {

	private int categoryID;
	private String categoryName;
	private int numApps;
	private int owner;

	public Category(int categoryID, String categoryName, int numApps, int owner) {
		super();
		this.categoryID = categoryID;
		this.categoryName = categoryName;
		this.numApps = numApps;
		this.owner = owner;
	}

	public int getCategoryID() {
		return categoryID;
	}

	public void setCategoryID(int categoryID) {
		this.categoryID = categoryID;
	}

	public String getCategoryName() {
		return categoryName;
	}

	public void setCategoryName(String categoryName) {
		this.categoryName = categoryName;
	}

	public int getNumApps() {
		return numApps;
	}

	public void setNumApps(int numApps) {
		this.numApps = numApps;
	}

	public int getOwner() {
		return owner;
	}

	public void setOwner(int owner) {
		this.owner = owner;
	}

}
