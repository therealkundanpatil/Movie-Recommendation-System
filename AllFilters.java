/*
 * AllFilters.java
 * 
 * Version : 1.0		Date : 08/16/2020
 * 
 * Revision : $Log$
 * 
 */
package movierecommendation;

import java.util.ArrayList;

public class AllFilters implements Filter {
	ArrayList<Filter> filters;

	public AllFilters() {
		filters = new ArrayList<Filter>();
	}

	public void addFilter(Filter f) {
		filters.add(f);
	}

	@Override
	public boolean satisfies(String id) {
		// TODO Auto-generated method stub
		for (Filter f : filters) {
			if (!f.satisfies(id)) {
				return false;
			}
		}

		return true;
	}
}
