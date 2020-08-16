/*
 * EfficientRater.java
 * 
 * Version : 1.0		Date : 08/16/2020
 * 
 * Revision : $Log$
 */
package movierecommendation;

import java.util.ArrayList;
import java.util.HashMap;

public class EfficientRater implements Rater {
	private String id;
	private HashMap<String, Rating> myRatings;

	public EfficientRater() {

	}

	public EfficientRater(String id) {
		this.id = id;
		myRatings = new HashMap<String, Rating>();
	}

	@Override
	public void addRating(String item, double rating) {
		// TODO Auto-generated method stub
		myRatings.put(item, new Rating(item, rating));
	}

	@Override
	public boolean hasRating(String item) {
		// TODO Auto-generated method stub
		if (myRatings.containsKey(item)) {
			return true;
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
		if (myRatings.containsKey(item)) {
			return myRatings.get(item).getValue();
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
		ArrayList<String> myList = new ArrayList<String>();
		for (String key : myRatings.keySet()) {
			myList.add(myRatings.get(key).getItem());
		}
		return myList;
	}

	public void setMyRatings(HashMap<String, Rating> myRatings) {
		this.myRatings = myRatings;
	}
	
	public HashMap<String,Rating> getMyRatings(){
		return myRatings;
	}
}
