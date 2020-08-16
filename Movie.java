/*
 * Movie.java
 * 
 * Version : 1.0		Date : 08/16/2020
 * 
 * Revision : $Log$
 * 
 */
package movierecommendation;

public class Movie {
	private String movieId;
	private String movieTitle;
	private int year;
	private String movieDirector;
	private String movieGenre;
	private String country;
	private String poster;
	private int minutes;
	
	public Movie() {
		
	}
	
	public Movie(String movieId,String movieTitle,String year,String movieGenre) {
		this.movieId = movieId.trim();
		this.movieTitle = movieTitle.trim();
		this.year = Integer.parseInt(year.trim());
		this.movieGenre = movieGenre;
	}
	
	public Movie(String movieId,String movieTitle,String year,String country, String movieGenre,String movieDirector,int minutes, String poster) {
		this.movieId = movieId.trim();
		this.movieTitle = movieTitle.trim();
		this.year = Integer.parseInt(year.trim());
		this.country = country;
		this.movieGenre = movieGenre;
		this.movieDirector = movieDirector;
		this.minutes = minutes;
		this.poster = poster;
	}

	String getMovieId() {
		return movieId;
	}

	String getMovieTitle() {
		return movieTitle;
	}

	int getYear() {
		return year;
	}

	String getMovieDirector() {
		return movieDirector;
	}

	String getMovieGenre() {
		return movieGenre;
	}

	String getCountry() {
		return country;
	}

	String getPoster() {
		return poster;
	}

	int getMinutes() {
		return minutes;
	}

	@Override
	public String toString() {
		return "Movie [movieId=" + movieId + ", movieTitle=" + movieTitle + ", year=" + year + ", movieDirector="
				+ movieDirector + ", movieGenre=" + movieGenre + ", country=" + country + ", poster=" + poster
				+ ", minutes=" + minutes + "]";
	}
	
	
	
}
