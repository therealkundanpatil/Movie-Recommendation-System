/*
 * DirectorFilter.java
 * 
 * Version : 1.0		Date : 08/16/2020
 * 
 * Revision : $Log$
 * 
 */
package movierecommendation;

public class DirectorFilter implements Filter{
	
	private String[] myDirector;
	
	public DirectorFilter(String director) {
		myDirector = director.split(",");
	}

	@Override
	public boolean satisfies(String id) {
		// TODO Auto-generated method stub
		for(String director : myDirector) {
			if(MovieDatabase.getDirector(id).contains(director)) {
				return true;
			}
		}
		return false;
	}
	
}
