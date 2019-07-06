package uk.ac.qub.technopoly;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Board {

	public static Tile[] tiles;
	public static ArrayList<Category> categories = new ArrayList<Category>();

	public static void createTiles() {
		// read tiles from file & validate

		Tile[] tile = new Tile[12];

		int count = 0;

		try {
			File file = new File("squares.csv");
			FileReader filereader = new FileReader(file.getName());
			BufferedReader bufferReader = new BufferedReader(filereader);
			String line;

			line = bufferReader.readLine();

			int categoryID = 0;
			int categoryCount = 0;

			while (line != null) {

				String[] list = line.split(",");
				List<String> lines = Arrays.asList(list);

				if (lines.get(0).equals("0")) {

					Integer tileNo = new Integer(lines.get(0));
					tile[count] = new RDInvestment(tileNo, lines.get(1));

				} else if (lines.get(0).equals("6")) {

					Integer tileNo = new Integer(lines.get(0));
					tile[count] = new TechConference(tileNo, lines.get(1));

				} else {

					Integer tileNo = new Integer(lines.get(0));
					String tileName = lines.get(1);
					String categoryName = lines.get(2);
					Integer purchasePrice = new Integer(lines.get(3));
					Integer royaltyPrice = new Integer(lines.get(4));
					Integer owner = new Integer(lines.get(5));
					Integer numBeta = new Integer(lines.get(6));
					Integer numFinal = new Integer(lines.get(7));
					Integer betaPurchase = new Integer(lines.get(8));
					Integer finalPurchase = new Integer(lines.get(9));
					Integer betaRent = new Integer(lines.get(10));
					Integer finalRent = new Integer(lines.get(11));

					boolean categoryExists = false;

					if (categories.isEmpty()) {

						Category newCategory = new Category(categoryCount, categoryName, 1, 99);
						Board.categories.add(newCategory);
						categoryCount++;
						categoryID = 0;

					} else {

						for (int n = 0; n < categories.size(); n++) {
							if (categories.get(n).getCategoryName().equals(categoryName)) {
								categoryExists = true;
								categories.get(n).setNumApps(categories.get(n).getNumApps() + 1);
								categoryID = n;
							}
						}

						if (!categoryExists) {
							Category newCategory = new Category(categoryCount, categoryName, 1, 99);
							Board.categories.add(newCategory);
							categoryID = categoryCount;
							categoryCount++;
						}
					}

					tile[count] = new App(tileNo, tileName, categoryID, purchasePrice, royaltyPrice, owner, numBeta,
							numFinal, betaPurchase, finalPurchase, betaRent, finalRent);

				}

				count++;
				line = bufferReader.readLine();
			}
			bufferReader.close();
			filereader.close();
			System.out.println("\nBoard created with the following categories:");

			for (int n = 0; n < categories.size(); n++) {
				System.out.println(categories.get(n).getCategoryName() + " " + categories.get(n).getNumApps());
			}

		} catch (IOException e) {
			e.printStackTrace();
		}

		Board.tiles = tile;

	}

	public Tile[] getTiles() {
		return tiles;
	}

}
