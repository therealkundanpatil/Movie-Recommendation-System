/*
 * GenreFilter.java
 * 
 * Version : 1.0		Date : 08/16/2020
 * 
 * Revision : $Log$
 */
package movierecommendation;

public class GenreFilter implements Filter {
	private String genre;
	
	public GenreFilter(String genre) {
		this.genre = genre;
	}

	@Override
	public boolean satisfies(String id) {
		// TODO Auto-generated method stub
		return MovieDatabase.getGenres(id).indexOf(genre) != -1;
	}
}
