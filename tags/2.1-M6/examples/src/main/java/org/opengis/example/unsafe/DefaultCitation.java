package org.opengis.example.unsafe;

import java.util.Collection;
import java.util.HashSet;

public class DefaultCitation implements Citation {
	private Collection<CitationDate> dates = new HashSet<CitationDate>();
	private String isbn;
	
	public Collection<CitationDate> getDates() {
		return dates;
	}

	public String getISBN() {
		return isbn;
	}

	public void setDates(Collection<CitationDate> dates) {
		this.dates = dates;
	}

	public void setISBN(String isbn) {
		this.isbn = isbn;
	}
}


