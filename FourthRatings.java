/*
 * FourthRatings.java
 * 
 * Version : 1.0		Date : 08/16/2020
 * 
 * Revision : $Log$
 * 
 */
package movierecommendation;

import java.util.ArrayList;
import java.util.Collections;

public class FourthRatings {
	private double getAverageById(String id, int minimalRaters) {
		ArrayList<Rater> myRaters = RaterDatabase.getRaters();
		int sum = 0;
		double average = 0.0;
		ArrayList<Rater> ratings = new ArrayList<Rater>();

		for (Rater rater : myRaters) {
			if (rater.hasRating(id)) {
				ratings.add(rater);
			}
		}

		for (Rater rater : ratings) {
			if (ratings.size() >= minimalRaters) {
				sum += rater.getRating(id);
				average = sum / (double) ratings.size();
			}
		}
		return average;
	}

	public ArrayList<Rating> getAverageRatings(int minimalRaters) {
		ArrayList<Rating> ratings = new ArrayList<>();
		ArrayList<String> movies = MovieDatabase.filterBy(new TrueFilter());

		for (String movieId : movies) {
			double averageRating = getAverageById(movieId, minimalRaters);
			if (averageRating != 0) {
				Rating rating = new Rating(movieId, averageRating);
				ratings.add(rating);
			}
		}

		return ratings;
	}

	public ArrayList<Rating> getAverageRatingsByFilter(int minimalRaters, Filter filterCriteria) {
		ArrayList<Rating> ratings = new ArrayList<>();
		ArrayList<String> movies = MovieDatabase.filterBy(filterCriteria);
		for (String movieId : movies) {
			double averageRating = getAverageById(movieId, minimalRaters);
			if (averageRating != 0) {
				Rating rating = new Rating(movieId, averageRating);
				ratings.add(rating);
			}
		}
		return ratings;
	}

	private double dotProduct(Rater me, Rater r) {
		double dotProduct = 0.0;
		ArrayList<String> myMovies = me.getItemsRated();
		for (String id : myMovies) {
			if (r.hasRating(id)) {
				double myRating = me.getRating(id);
				double rRating = r.getRating(id);
				myRating -= 5;
				rRating -= 5;
				dotProduct += myRating * rRating;
				System.out.println("Movie : " + id + " dotProduct : " + dotProduct);
			}
		}
		return dotProduct;

	}

	private ArrayList<Rating> getSimilarities(String id) {
		ArrayList<Rating> list = new ArrayList<>();
		Rater me = RaterDatabase.getRater(id);
		for (Rater r : RaterDatabase.getRaters()) {
			if (!r.equals(me)) {
				double product = dotProduct(me, r);
				if (product > 0) {
					list.add(new Rating(r.getId(), product));
				}
			}
		}
		Collections.sort(list, Collections.reverseOrder());
		return list;
	}

	public ArrayList<Rating> getSimilarRatings(String id, int numSimilarRaters, int minimalRaters) {
		ArrayList<Rating> list = new ArrayList<>();
		ArrayList<Rating> similarRatings = getSimilarities(id);
		ArrayList<String> movies = MovieDatabase.filterBy(new TrueFilter());

		for (String movieId : movies) {
			double weightedAverage = 0.0;
			double sum = 0.0;
			int ratersCount = 0;

			for (int i = 0; i < numSimilarRaters; i++) {
				Rating rating = similarRatings.get(i);
				String raterId = rating.getItem();
				double value = rating.getValue();
				Rater myRater = RaterDatabase.getRater(raterId);
				if (myRater.hasRating(movieId)) {
					ratersCount++;
					sum += value * myRater.getRating(movieId);
				}
			}

			if (ratersCount >= minimalRaters) {
				weightedAverage = sum / (double) ratersCount;
				list.add(new Rating(movieId, weightedAverage));
			}
		}
		Collections.sort(list, Collections.reverseOrder());
		return list;
	}

	public ArrayList<Rating> getSimilarRatingsByFilter(String id, int numSimilarRaters, int minimalRaters,
			Filter filterCriteria) {
		ArrayList<Rating> list = new ArrayList<>();
		ArrayList<Rating> similarRatings = getSimilarities(id);
		ArrayList<String> movies = MovieDatabase.filterBy(filterCriteria);
		for (String movieId : movies) {
			double weightedAverage = 0.0;
			double sum = 0.0;
			int ratersCount = 0;

			for (int i = 0; i < numSimilarRaters; i++) {
				Rating rating = similarRatings.get(i);
				String raterId = rating.getItem();
				double value = rating.getValue();
				Rater myRater = RaterDatabase.getRater(raterId);
				if (myRater.hasRating(movieId)) {
					ratersCount++;
					sum += value * myRater.getRating(movieId);
				}
			}
			if (ratersCount >= minimalRaters) {
				weightedAverage = sum / (double) ratersCount;
				list.add(new Rating(movieId, weightedAverage));
			}
		}
		Collections.sort(list, Collections.reverseOrder());
		return list;
	}
}
