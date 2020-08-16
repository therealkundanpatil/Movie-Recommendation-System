/*
 * YearAfterFilter.java
 * 
 * Version : 1.0		Date : 08/16/2020
 * 
 * Revision : $Log$
 * 
 */
package movierecommendation;


public class YearAfterFilter implements Filter{
	private int year;
	
	public YearAfterFilter(int year) {
		this.year = year;
	}

	@Override
	public boolean satisfies(String id) {
		// TODO Auto-generated method stub
		return MovieDatabase.getYear(id) >= year;
	}
	
}
