/*
 * ThirdRatings.java
 * 
 * Version : 1.0		Date : 08/16/2020
 * 
 * Revision : $Log$
 * 
 */
package movierecommendation;

import java.util.ArrayList;
import java.util.HashMap;

public class ThirdRatings {
	private ArrayList<EfficientRater> myRaters;
	
	public ThirdRatings() {
		this("ratings.csv");
	}
	
	public ThirdRatings(String fileName) {
		FirstRatings fr = new FirstRatings();
		myRaters = fr.loadRaters(fileName);
	}
	
	public int getRaterSize() {
		return myRaters.size();
	}
	
	private double getAverageById(String id, int minimalRaters) {
		double count = 0.0;
		HashMap<String, Integer> movieRatedByHowMany = new HashMap<String,Integer>();
		for(EfficientRater er : myRaters) {
			for(Rating rating : er.getMyRatings().values()) {
				String movieId = rating.getItem();
				double value = rating.getValue();
				if(movieId.equals(id)) {
					count += value;
				}
				if(movieRatedByHowMany.containsKey(movieId)) {
					int howMany = movieRatedByHowMany.get(movieId) + 1;
					movieRatedByHowMany.put(movieId, howMany);
				}
				else {
					movieRatedByHowMany.put(movieId, 1);
				}
			}
		}
		
		if(movieRatedByHowMany.containsKey(id)) {
			int totalRatings = movieRatedByHowMany.get(id);
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
		ArrayList<Rating> averageRatingList = new ArrayList<Rating>();
		ArrayList<String> myMovies = MovieDatabase.filterBy(new TrueFilter());
		
		for(String m : myMovies) {
			String id = m;
			double average = getAverageById(id, minimalRaters);
			if(average != 0.0) {
				Rating averageRating = new Rating(id,average);
				averageRatingList.add(averageRating);
			}
		}
		return averageRatingList;
	}
	
	public ArrayList<Rating> getAverageRatingsByFilter(int minimalRaters, Filter filterCriteria){
		ArrayList<String> movieSatisfied = MovieDatabase.filterBy(filterCriteria);
		ArrayList<Rating> averageRatingList = new ArrayList<Rating>();
		
		for(String m : movieSatisfied) {
			String id = m;
			double average = getAverageById(id, minimalRaters);
			if(average != 0.0) {
				Rating averageRating = new Rating(id,average);
				averageRatingList.add(averageRating);
			}
		}
		
		return averageRatingList;
	}
}
