/*
 * Rater.java
 * 
 * Version : 1.0		Date : 08/16/2020
 * 
 * Revision : $Log$
 * 
 */
package movierecommendation;

import java.util.ArrayList;

public interface Rater {
	public void addRating(String item, double rating);

	public boolean hasRating(String item);

	public String getId();

	public double getRating(String item);

	public int numRatings();

	public ArrayList<String> getItemsRated();

}
