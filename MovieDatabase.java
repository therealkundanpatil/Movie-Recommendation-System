/*
 * MovieDatabase.java
 * 
 * Version : 1.0		Date : 08/16/2020
 * 
 * Revision : $Log$
 * 
 */
package movierecommendation;

import java.util.ArrayList;
import java.util.HashMap;

public class MovieDatabase {
	private static HashMap<String,Movie> ourMovies;
	
	public static void initialize(String movieFile) {
		if(ourMovies == null) {
			ourMovies = new HashMap<String,Movie>();
			loadMovies(movieFile);
		}
	}
	
	private static void initialize() {
		if(ourMovies == null) {
			ourMovies = new HashMap<String, Movie>();
			loadMovies("ratedmoviesfull.csv");
		}
	}
	
	private static void loadMovies(String movieFileName) {
		FirstRatings fr = new FirstRatings();
		ArrayList<Movie> list = fr.loadMovies(movieFileName);
		for(Movie m : list) {
			ourMovies.put(m.getMovieId(),m);
		}
	}
	
	public static boolean containsId(String id) {
		initialize();
		return ourMovies.containsKey(id);
	}
	
	public static int getYear(String id) {
		initialize();
		return ourMovies.get(id).getYear();
	}
	
	public static String getGenres(String id) {
		initialize();
		return ourMovies.get(id).getMovieGenre();
	}
	
	public static String getTitle(String id) {
		initialize();
		return ourMovies.get(id).getMovieTitle();
	}
	
	public static Movie getMovie(String id) {
		initialize();
		return ourMovies.get(id);
	}
	
	public static String getPoster(String id) {
		initialize();
		return ourMovies.get(id).getPoster();
	}
	
	public static int getMinutes(String id) {
		initialize();
		return ourMovies.get(id).getMinutes();
	}
	
	public static String getCountry(String id) {
		initialize();
		return ourMovies.get(id).getCountry();
	}
	
	public static String getDirector(String id) {
		initialize();
		return ourMovies.get(id).getMovieDirector();
	}
	
	public static int size() {
		return ourMovies.size();
	}
	
	public static ArrayList<String> filterBy(Filter f){
		initialize();
		ArrayList<String> list = new ArrayList<>();
		
		for(String id : ourMovies.keySet()) {
			if(f.satisfies(id)) {
				list.add(id);
			}
		}
		
		return list;
	}
}
