/*
 * SecondRatings.java
 * 
 * Version : 1.0		Date : 08/16/2020
 * 
 * Revision : $Log$
 * 
 */
package movierecommendation;

import java.util.ArrayList;
import java.util.HashMap;

public class SecondRatings {
	private ArrayList<Movie> myMovies;
	private ArrayList<EfficientRater> myRaters;

	public SecondRatings() {
		this("ratedmoviesfull.csv", "ratings.csv");
	}

	public SecondRatings(String movieFile, String ratingFile) {
		FirstRatings fr = new FirstRatings();
		myMovies = fr.loadMovies(movieFile);
		myRaters = fr.loadRaters(ratingFile);
	}

	ArrayList<Movie> getMyMovies() {
		return myMovies;
	}

	ArrayList<EfficientRater> getMyRaters() {
		return myRaters;
	}

	int getMovieSize() {
		return myMovies.size();
	}

	int getRaterSize() {
		return myRaters.size();
	}

	private double getAverageById(String id, int minimalRaters) {
		double count = 0.0;
		HashMap<String, Integer> moviesRatedByHowMany = new HashMap<>();
		for (EfficientRater er : myRaters) {
			for (Rating rating : er.getMyRatings().values()) {
				String movieId = rating.getItem();
				double value = rating.getValue();
				if (movieId.equals(id)) {
					count += value;
				}
				if (moviesRatedByHowMany.keySet().contains(movieId)) {
					int howMany = moviesRatedByHowMany.get(movieId) + 1;
					moviesRatedByHowMany.put(movieId, howMany);
				} else {
					moviesRatedByHowMany.put(movieId, 1);
				}
			}
		}
		if(moviesRatedByHowMany.keySet().contains(id)) {
			int totalRatings = moviesRatedByHowMany.get(id);
			if(totalRatings < minimalRaters) {
				return 0.0;
			}
			else {
				double average = count / totalRatings;
				return average;
			}
		}
		return 0.0;
	}
	
	public ArrayList<Rating> getAverageRatings(int minimalRaters){
		ArrayList<Rating> averageRatingList = new ArrayList<>();
		for(Movie m : myMovies) {
			String id = m.getMovieId();
			double average = getAverageById(id, minimalRaters);
			if(average != 0.0) {
				Rating averageRating = new Rating(id,average);
				averageRatingList.add(averageRating);
			}
		}
		
		return averageRatingList;
	}
	
	public String getTitle(String id) {
		for(Movie m : myMovies) {
			if(m.getMovieId().equals(id)) {
				return m.getMovieTitle();
			}
		}
		
		return "Movie id : "+id+" was not found";
	}
	
	public String getID(String title) {
		for(Movie m : myMovies) {
			if(m.getMovieTitle().equals(title)) {
				return m.getMovieId();
			}
		}
		return "No such title";
	}
}
