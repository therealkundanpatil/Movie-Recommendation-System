/*
 * RaterDatabase.java
 * 
 * Version : 1.0		Date : 08/16/2020
 * 
 * Revision : $Log$
 * 
 */
package movierecommendation;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;

import com.opencsv.CSVReader;
import com.opencsv.exceptions.CsvValidationException;

public class RaterDatabase {
	private static HashMap<String, Rater> ourRaters;

	private static void initialize() {
		if (ourRaters == null) {
			ourRaters = new HashMap<String, Rater>();
		}
	}

	public static void initialize(String fileName) {
		if (ourRaters == null) {
			ourRaters = new HashMap<String, Rater>();
			addRatings(fileName);
		}
	}

	public static void addRatings(String fileName) {
		initialize();
		String path = "src\\data\\" + fileName;
		File f = new File(path);
		FileReader fileReader = null;
		CSVReader csvReader = null;
		try {
			fileReader = new FileReader(f);
			csvReader = new CSVReader(fileReader);
			csvReader.readNext();
			String[] records;

			while ((records = csvReader.readNext()) != null) {
				String id = records[0];
				String item = records[1];
				Double rating = Double.parseDouble(records[2]);
				addRaterRating(id, item, rating);
			}
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (CsvValidationException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			if (csvReader != null) {
				try {
					csvReader.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		}
	}

	public static void addRaterRating(String raterId, String movieId, double rating) {
		initialize();
		Rater rater = null;
		if (ourRaters.containsKey(raterId)) {
			rater = ourRaters.get(raterId);
		} else {
			rater = new EfficientRater(raterId);
			ourRaters.put(raterId, rater);
		}

		rater.addRating(movieId, rating);
	}

	public static Rater getRater(String id) {
		initialize();
		return ourRaters.get(id);
	}

	public static ArrayList<Rater> getRaters() {
		initialize();
		ArrayList<Rater> myList = new ArrayList<Rater>(ourRaters.values());
		return myList;
	}
}
