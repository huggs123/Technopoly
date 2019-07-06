package uk.ac.qub.technopoly;

import java.util.Random;

public class Dice {

	private static final int MIN_VALUE = 1;
	private static final int MAX_VALUE = 6;
	private int faceValue;

	public void randomDice() {
		Random rand = new Random();
		int number = 0;
		number = rand.nextInt(MAX_VALUE) + MIN_VALUE;
		this.setFaceValue(number);
	}

	public int getFaceValue() {
		return faceValue;
	}

	public void setFaceValue(int faceValue) {
		this.faceValue = faceValue;
	}

}