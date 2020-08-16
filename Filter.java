/*
 * Filter.java
 * 
 * Version : 1.0 	Date : 08/16/2020
 * 
 * Revision : $Log$
 * 
 */
package movierecommendation;

public interface Filter {
	public boolean satisfies(String id);
}
