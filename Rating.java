/*
 * Rating.java
 * 
 * Version : 1.0		Date : 08/16/2020
 * 
 * Revision : $Log$
 */
package movierecommendation;

public class Rating implements Comparable<Rating> {
	private String item;
	private double value;

	public Rating(String item, double value) {
		this.item = item;
		this.value = value;
	}

	String getItem() {
		return item;
	}

	double getValue() {
		return value;
	}

	@Override
	public String toString() {
		return "Rating [item=" + item + ", value=" + value + "]";
	}

	@Override
	public int compareTo(Rating o) {
		// TODO Auto-generated method stub
		if (value < o.value) {
			return -1;
		} else if (value > o.value) {
			return 1;
		}
		return 0;
	}

}
