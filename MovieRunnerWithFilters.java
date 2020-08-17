/*
 * MovieRunnerWithFilters.java
 * 
 * Version : 1.0		Date : 08/17/2020
 * 
 * Revision : $Log$
 */
package movierecommendation;

import java.util.ArrayList;
import java.util.Collections;

public class MovieRunnerWithFilters {

	public void printAverageRatings() {
		ThirdRatings thirdRatings = new ThirdRatings();
		System.out.println(thirdRatings.getRaterSize() + " number of Raters");
		MovieDatabase.initialize("ratedmoviesfull.csv");
		ArrayList<Rating> averageList = thirdRatings.getAverageRatings(35);
		System.out.println(averageList.size() + " size");
		Collections.sort(averageList);

		for (Rating rating : averageList) {
			double value = rating.getValue();
			String id = rating.getItem();
			String title = MovieDatabase.getTitle(id);
			System.out.println(value + "\t" + title);
		}
	}

	public void printAverageRatingsByYear() {
		Filter f = new YearAfterFilter(2000);
		ThirdRatings thirdRatings = new ThirdRatings();
		System.out.println(thirdRatings.getRaterSize() + " size");
		MovieDatabase.initialize("ratedmoviesfull.csv");
		ArrayList<Rating> averageList = thirdRatings.getAverageRatingsByFilter(20, f);
		System.out.println("Found " + averageList.size() + "movies");
		Collections.sort(averageList);

		for (Rating rating : averageList) {
			double value = rating.getValue();
			String id = rating.getItem();
			String title = MovieDatabase.getTitle(id);
			int year = MovieDatabase.getYear(id);
			System.out.println(value + " " + year + " " + title);
		}
	}

	public void printAverageRatingsByGenre() {
		Filter f = new GenreFilter("Comedy");
		ThirdRatings thirdRatings = new ThirdRatings();
		System.out.println(thirdRatings.getRaterSize() + " number of raters");
		MovieDatabase.initialize("ratedmoviesfull.csv");
		ArrayList<Rating> averageList = thirdRatings.getAverageRatingsByFilter(20, f);
		System.out.println("Found " + averageList.size() + " movies");
	}

	public void printAverageRatingsByMinutes() {
		Filter f = new MinutesFilter(105, 135);
		ThirdRatings thirdRatings = new ThirdRatings();
		System.out.println(thirdRatings.getRaterSize() + " number of raters");
		MovieDatabase.initialize("ratedmoviesfull.csv");
		ArrayList<Rating> averageList = thirdRatings.getAverageRatingsByFilter(5, f);
		System.out.println("Found " + averageList.size() + " movies");
	}

	public void printAverageRatingsByDirectors() {
		Filter f = new DirectorFilter("Charles Chaplin,Michael Mann,Spike Jonze");
		ThirdRatings thirdRatings = new ThirdRatings();
		System.out.println(thirdRatings.getRaterSize() + " number of raters");
		MovieDatabase.initialize("ratedmoviesfull.csv");
		ArrayList<Rating> averageList = thirdRatings.getAverageRatingsByFilter(5, f);
		System.out.println("Found " + averageList.size() + " movies");
	}

	public void printAverageRatingsByYearAfterAndGenre() {
		Filter f1 = new YearAfterFilter(1990);
		Filter f2 = new GenreFilter("Drama");
		AllFilters f = new AllFilters();
		f.addFilter(f1);
		f.addFilter(f2);

		ThirdRatings thirdRatings = new ThirdRatings();
		System.out.println(thirdRatings.getRaterSize() + " number of rater");
		MovieDatabase.initialize("ratedmoviesfull.csv");
		ArrayList<Rating> averageList = thirdRatings.getAverageRatingsByFilter(8, f);
		System.out.println("Found " + averageList.size() + " movies");
		Collections.sort(averageList);

		for (Rating rating : averageList) {
			double value = rating.getValue();
			String id = rating.getItem();
			String title = MovieDatabase.getTitle(id);
			int year = MovieDatabase.getYear(id);
			String genre = MovieDatabase.getGenres(id);

			System.out.println(value + " " + year + " " + genre + " " + title);
		}
	}

	public void printAverageRatingsByDirectorAndMinutes() {
		Filter f1 = new DirectorFilter("Clint Eastwood, Joel Coen, Tim Burton, Ron Howad, Nora Ephron, Sydney Pollock");
		Filter f2 = new MinutesFilter(90, 180);
		AllFilters f = new AllFilters();
		f.addFilter(f1);
		f.addFilter(f2);
		ThirdRatings thirdRatings = new ThirdRatings();
		System.out.println(thirdRatings.getRaterSize() + " number of raters");
		MovieDatabase.initialize("ratedmoviesfull.csv");
		ArrayList<Rating> averageList = thirdRatings.getAverageRatingsByFilter(3, f);
		System.out.println("Found " + averageList.size() + " movies");
		Collections.sort(averageList);

		for (Rating rating : averageList) {
			double value = rating.getValue();
			String id = rating.getItem();
			String title = MovieDatabase.getTitle(id);
			int time = MovieDatabase.getMinutes(id);
			String director = MovieDatabase.getDirector(id);
			System.out.println(value + " " + time + " " + director + "" + title);
		}
	}
}
