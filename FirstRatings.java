/*
 * FirstRatings.java
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

public class FirstRatings {

	public ArrayList<Movie> loadMovies(String fileName) {
		ArrayList<Movie> movieList = new ArrayList<>();
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
				String title = records[1];
				String year = records[2];
				String country = records[3];
				String genre = records[4];
				String dr = records[5];
				int minutes = Integer.parseInt(records[6]);
				String poster = records[7];
				Movie movieObject = new Movie(id, title, year, country, genre, dr, minutes, poster);
				movieList.add(movieObject);
			}
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (NumberFormatException e) {
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

		return movieList;
	}

	public void testLoadMovies() {
		ArrayList<Movie> movieList = new ArrayList<>();
		System.out.println("Total movies: " + movieList.size());
		HashMap<String, Integer> eachDirectorNum = new HashMap<>();
		int count1 = 0;
		int count2 = 0;

		for (Movie m : movieList) {
			if (m.getMovieGenre().indexOf("Comedy") != -1) {
				count1 += 1;
			}
			if (m.getMinutes() > 150) {
				count2 += 1;
			}
			String directors = m.getMovieDirector();
			String[] directorArray = directors.split(",");
			for (String director : directorArray) {
				if (eachDirectorNum.keySet().contains(director)) {
					eachDirectorNum.put(director, eachDirectorNum.get(director) + 1);
				} else {
					eachDirectorNum.put(director, 1);
				}
			}
		}
		System.out.println(count1 + " comedy");
		System.out.println(count2 + " longer than 150 minutes");
		int max = 0;

		for (String director : eachDirectorNum.keySet()) {
			if (eachDirectorNum.get(director) > max) {
				max = eachDirectorNum.get(director);
			}
		}

		for (String director : eachDirectorNum.keySet()) {
			if (eachDirectorNum.get(director) == max) {
				System.out.println(director);
			}
		}

		System.out.println(max + " directed by director");
	}

	public ArrayList<EfficientRater> loadRaters(String fileName) {
		ArrayList<EfficientRater> rater = new ArrayList<EfficientRater>();
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
				boolean flag = true;
				String raterId = records[0];
				String item = records[1];
				Double score = Double.parseDouble(records[2]);

				for (EfficientRater er : rater) {
					if (er.getId().equals(raterId)) {
						er.addRating(item, score);
						flag = false;
					}
				}

				if (flag) {
					EfficientRater err = new EfficientRater(raterId);
					err.addRating(item, score);
					rater.add(err);
				}
			}
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (NumberFormatException e) {
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
		return rater;
	}

	public void testLoadRater() {
		ArrayList<EfficientRater> allRater = loadRaters("ratings.csv");
		System.out.println("Total Raters: " + allRater.size());
		int max = 0;
		int raterCount = 0;
		int maxRating = 0;
		String item = "1798709";
		ArrayList<String> diff = new ArrayList<>();

		for (EfficientRater er : allRater) {
			if (er.getId().equals("193")) {
				System.out.println("193 has " + er.getMyRatings().size() + " ratings");
			}
			if (er.numRatings() > max) {
				max = er.getMyRatings().size();
			}
			if (er.hasRating(item)) {
				raterCount += 1;
			}

			for (String s : er.getItemsRated()) {
				if (!diff.contains(s)) {
					diff.add(s);
				}
			}
		}

		for (EfficientRater er : allRater) {
			if (er.numRatings() == max) {
				maxRating += 1;
				System.out.println(er.getId());
			}
		}

		System.out.println("Movie " + item + "\t" + raterCount);
		System.out.println("Total number of " + diff.size() + " were rated");
		System.out.println("There are " + maxRating + " raters have " + max + "ratings");
	}
}
