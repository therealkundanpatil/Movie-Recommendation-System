package movierecommendation;

import java.util.ArrayList;
import java.util.Collections;

public class MovieRunnerSimilarRatings {
	
	public void printAverageRatings() {
		FourthRatings fourthRating = new FourthRatings();
		RaterDatabase.initialize("ratings.csv");
		RaterDatabase.initialize("ratedmoviesfull.csv");
		System.out.println("****************Average Ratings*****************");
		ArrayList<Rating> ratings = fourthRating.getAverageRatings(35);
		Collections.sort(ratings);
		System.out.println("Found " + ratings.size() + " movies");

	}

	public void printAverageRatingsByYearAfterAndGenre() {
		FourthRatings fourthRatings = new FourthRatings();
		RaterDatabase.initialize("ratings.csv");
		RaterDatabase.initialize("ratedmoviesfull.csv");

		YearAfterFilter yAF = new YearAfterFilter(1990);
		GenreFilter gf = new GenreFilter("Drama");
		AllFilters af = new AllFilters();
		af.addFilter(yAF);
		af.addFilter(gf);

		System.out.println("***********Average Ratings By Year After and Genre Filters************");
		ArrayList<Rating> ratings = fourthRatings.getAverageRatingsByFilter(8, af);
		Collections.sort(ratings);
		System.out.println("Found " + ratings.size() + "movies");
	}

	public void printSimilarRatings() {
		FourthRatings fourthRatings = new FourthRatings();
		RaterDatabase.initialize("rating.csv");
		MovieDatabase.initialize("ratedmoviesfull.csv");

		String raterId = "71";
		int numSimilarRaters = 20;
		int minimalRaters = 5;

		ArrayList<Rating> ratings = fourthRatings.getSimilarRatings(raterId, numSimilarRaters, minimalRaters);
		for (Rating rating : ratings) {
			System.out.println(MovieDatabase.getTitle(rating.getItem()) + "\tRating: " + rating.getValue());
		}
	}

	public void printSimilarRatingsByGenre() {
		FourthRatings fourthRatings = new FourthRatings();
		RaterDatabase.initialize("rating.csv");
		MovieDatabase.initialize("ratedmoviesfull.csv");

		String raterId = "964";
		int numSimilarRaters = 20;
		int minimalRaters = 5;
		GenreFilter gf = new GenreFilter("Mystery");

		ArrayList<Rating> ratings = fourthRatings.getSimilarRatingsByFilter(raterId, numSimilarRaters, minimalRaters,
				gf);
		for (Rating rating : ratings) {
			System.out.println(MovieDatabase.getTitle(rating.getItem()) + "\tRating: " + rating.getValue());
		}
	}

	public void printSimilarRatingsByDirector() {
		FourthRatings fourthRatings = new FourthRatings();
		RaterDatabase.initialize("rating.csv");
		MovieDatabase.initialize("ratedmoviesfull.csv");

		String raterId = "120";
		int numSimilarRaters = 10;
		int minimalRaters = 2;

		DirectorFilter df = new DirectorFilter(
				"Clint Eastwood, J.J. Abrams,Alfred Hitchcock,Sydney Pollack,David Cronenberd,Oliver Stone, Mike Leigh");
		ArrayList<Rating> ratings = fourthRatings.getSimilarRatingsByFilter(raterId, numSimilarRaters, minimalRaters,
				df);
		for (Rating rating : ratings) {
			System.out.println(MovieDatabase.getTitle(rating.getItem()) + "\tRating: " + rating.getValue());
			System.out.println("\t" + MovieDatabase.getDirector(rating.getItem()));
		}

	}

	public void printSimilarRatingsByGenreAndMinutes() {
		FourthRatings fourthRatings = new FourthRatings();
		RaterDatabase.initialize("rating.csv");
		MovieDatabase.initialize("ratedmoviesfull.csv");

		String raterId = "168";
		int numSimilarRaters = 10;
		int minimalRaters = 3;
		MinutesFilter mf = new MinutesFilter(80, 160);
		GenreFilter gf = new GenreFilter("Drama");
		AllFilters af = new AllFilters();

		af.addFilter(mf);
		af.addFilter(gf);

		ArrayList<Rating> ratings = fourthRatings.getSimilarRatingsByFilter(raterId, numSimilarRaters, minimalRaters,
				af);
		for (Rating rating : ratings) {
			System.out.println(
					MovieDatabase.getTitle(rating.getItem()) + "\t" + MovieDatabase.getMinutes(rating.getItem())
							+ "\tRating : " + rating.getValue() + "\t" + MovieDatabase.getGenres(rating.getItem()));
		}
	}

	public void printSimilarRatingsByYearAfterAndMinutes() {
		FourthRatings fourthRatings = new FourthRatings();
		RaterDatabase.initialize("rating.csv");
		MovieDatabase.initialize("ratedmoviesfull.csv");

		String raterId = "314";
		int numSimilarRaters = 10;
		int minimalRaters = 5;
		MinutesFilter mf = new MinutesFilter(70, 200);
		YearAfterFilter yAF = new YearAfterFilter(1975);
		AllFilters af = new AllFilters();
		af.addFilter(mf);
		af.addFilter(yAF);

		ArrayList<Rating> ratings = fourthRatings.getSimilarRatingsByFilter(raterId, numSimilarRaters, minimalRaters,
				af);
		for (Rating rating : ratings) {
			System.out.println(MovieDatabase.getTitle(rating.getItem()) + "\t" + MovieDatabase.getYear(rating.getItem())
					+ "\t" + MovieDatabase.getMinutes(rating.getItem()) + "\tRating : " + rating.getValue());
		}
	}

	public static void main(String[] args) {
		MovieRunnerSimilarRatings mrsr = new MovieRunnerSimilarRatings();
		mrsr.printAverageRatings();
		mrsr.printAverageRatingsByYearAfterAndGenre();
		mrsr.printSimilarRatings();
		mrsr.printSimilarRatingsByGenre();
		mrsr.printSimilarRatingsByDirector();
		mrsr.printSimilarRatingsByGenreAndMinutes();
		mrsr.printSimilarRatingsByYearAfterAndMinutes();
	}

}
