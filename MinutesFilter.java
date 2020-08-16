/*
 * MinutesFilter.java
 * 
 * Version : 1.0		Date : 08/16/2020
 * 
 * Revision : $Log$
 * 
 */
package movierecommendation;

public class MinutesFilter implements Filter {
	private int minMinutes;
	private int maxMinutes;

	public MinutesFilter(int min, int max) {
		minMinutes = min;
		maxMinutes = max;
	}

	@Override
	public boolean satisfies(String id) {
		// TODO Auto-generated method stub
		return MovieDatabase.getMinutes(id) >= minMinutes && MovieDatabase.getMinutes(id) <= maxMinutes;
	}

}
