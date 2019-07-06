package uk.ac.qub.technopoly;

public class App extends Tile {

	private int category;
	private int purchasePrice;
	private int royaltyPrice;
	private int owner;
	private int numBeta;
	private int numFinal;
	private int betaPurchasePrice;
	private int finalPurchasePrice;
	private int betaRentPrice;
	private int finalRentPrice;
	public static final int MAX_BETA = 3;
	public static final int MAX_FINAL = 1;

	public App(int tileNo, String tileName, int category, int purchasePrice, int royaltyPrice, int owner, int numBeta,
			int numFinal, int betaPurchasePrice, int finalPurchasePrice, int betaRentPrice, int finalRentPrice) {
		super(tileNo, tileName);
		this.category = category;
		this.purchasePrice = purchasePrice;
		this.royaltyPrice = royaltyPrice;
		this.owner = owner;
		this.numBeta = numBeta;
		this.numFinal = numFinal;
		this.betaPurchasePrice = betaPurchasePrice;
		this.finalPurchasePrice = finalPurchasePrice;
		this.betaRentPrice = betaRentPrice;
		this.finalRentPrice = finalRentPrice;
	}

	public void displayAppDetails() throws InterruptedException {
		System.out.println(this.getTileNo());
		System.out.println(this.getTileName());
		System.out.println(this.category);
		System.out.println(this.purchasePrice);
		System.out.println(this.royaltyPrice);
		System.out.println(this.owner);
		System.out.println(this.numBeta);
		System.out.println(this.numFinal);
		System.out.println(this.betaPurchasePrice);
		System.out.println(this.finalPurchasePrice);
		System.out.println(this.betaRentPrice);
		System.out.println(this.finalRentPrice);
		System.out.println();
	}

	public int getCategory() {
		return category;
	}

	public void setCategory(int category) {
		this.category = category;
	}

	public int getPurchasePrice() {
		return purchasePrice;
	}

	public void setPurchasePrice(int purchasePrice) {
		this.purchasePrice = purchasePrice;
	}

	public int getRoyaltyPrice() {
		return royaltyPrice;
	}

	public void setRoyaltyPrice(int royaltyPrice) {
		this.royaltyPrice = royaltyPrice;
	}

	public int getOwner() {
		return owner;
	}

	public void setOwner(int owner) {
		this.owner = owner;
	}

	public int getNumBeta() {
		return numBeta;
	}

	public void setNumBeta(int numBeta) {
		this.numBeta = numBeta;
	}

	public int getNumFinal() {
		return numFinal;
	}

	public void setNumFinal(int numFinal) {
		this.numFinal = numFinal;
	}

	public int getBetaPurchasePrice() {
		return betaPurchasePrice;
	}

	public void setBetaPurchasePrice(int betaPurchasePrice) {
		this.betaPurchasePrice = betaPurchasePrice;
	}

	public int getFinalPurchasePrice() {
		return finalPurchasePrice;
	}

	public void setFinalPurchasePrice(int finalPurchasePrice) {
		this.finalPurchasePrice = finalPurchasePrice;
	}

	public int getBetaRentPrice() {
		return betaRentPrice;
	}

	public void setBetaRentPrice(int betaRentPrice) {
		this.betaRentPrice = betaRentPrice;
	}

	public int getFinalRentPrice() {
		return finalRentPrice;
	}

	public void setFinalRentPrice(int finalRentPrice) {
		this.finalRentPrice = finalRentPrice;
	}

}
