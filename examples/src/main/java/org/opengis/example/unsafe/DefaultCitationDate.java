package org.opengis.example.unsafe;

import java.util.Date;

public class DefaultCitationDate implements CitationDate {
    private Date date;
	public Date getDate() {
		return date;
	}
	public void setDate(Date date) {
		this.date = date;
	}
}
