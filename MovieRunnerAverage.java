/*
 * MovieRunnerAverage.java
 * 
 * Version : 1.0		Date : 08/16/2020
 * 
 * Revision : $Log$
 */
package movierecommendation;

import java.util.ArrayList;
import java.util.Collections;

public class MovieRunnerAverage {
	public void printAverageRatings() {
		SecondRatings secondRatingObject = new SecondRatings();
		ArrayList<Rating> averageRatingList = secondRatingObject.getAverageRatings(12);
		System.out.println(averageRatingList + " size");
		Collections.sort(averageRatingList);

		for (Rating rating : averageRatingList) {
			double value = rating.getValue();
			String id = rating.getItem();
			String title = secondRatingObject.getTitle(id);
			System.out.println(value + " " + title);
		}
	}

	public void getAverageRatingOneMovie(String movieTitle) {
		SecondRatings secondRatingsObject = new SecondRatings();
		ArrayList<Rating> averageRatingList = secondRatingsObject.getAverageRatings(1);
		
		for(Rating rating : averageRatingList) {
			double value = rating.getValue();
			String id = rating.getItem();
			String title = secondRatingsObject.getTitle(id);
			if(title.equals(movieTitle)) {
				System.out.println(value+ " "+ title);
			}
			
		}
	}
}
