package org.opengis.example.data;

import java.util.Date;

public class DataCitationDate implements CitationDate {
	private Date date;
	
	public DataCitationDate(){
		this( new Date() );
	}
	public DataCitationDate( CitationDate date ){
		this( date.getDate() );
	}
	public DataCitationDate(Date date) {
		this.date = date;
	}
	public Date getDate() {
		return date;
	}
	public void setDate(Date date) {
		this.date = date;
	}
}
