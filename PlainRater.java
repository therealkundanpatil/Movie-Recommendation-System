/*
 * PlainRater.java
 * 
 * Version : 1.0		Date : 08/16/2020
 * 
 * Revision : $Log$
 * 
 */
package movierecommendation;

import java.util.ArrayList;

public class PlainRater implements Rater {
	private String id;
	private ArrayList<Rating> myRatings;

	public PlainRater(String id) {
		this.id = id;
		myRatings = new ArrayList<Rating>();
	}

	@Override
	public void addRating(String item, double rating) {
		// TODO Auto-generated method stub
		myRatings.add(new Rating(item, rating));
	}

	@Override
	public boolean hasRating(String item) {
		// TODO Auto-generated method stub
		for (int k = 0; k < myRatings.size(); k++) {
			if (myRatings.get(k).getItem().equals(item)) {
				return true;
			}
		}
		return false;
	}

	@Override
	public String getId() {
		// TODO Auto-generated method stub
		return id;
	}

	@Override
	public double getRating(String item) {
		// TODO Auto-generated method stub
		for (int i = 0; i < myRatings.size(); i++) {
			if (myRatings.get(i).getItem().equals(item)) {
				return myRatings.get(i).getValue();
			}
		}

		return -1;
	}

	@Override
	public int numRatings() {
		// TODO Auto-generated method stub
		return myRatings.size();
	}

	@Override
	public ArrayList<String> getItemsRated() {
		// TODO Auto-generated method stub
		ArrayList<String> list = new ArrayList<>();
		for (int i = 0; i < myRatings.size(); i++) {
			list.add(myRatings.get(i).getItem());
		}
		return list;
	}

}
