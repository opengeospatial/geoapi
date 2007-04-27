package org.opengis.example.visit;

import java.util.Date;

public class ACitationDate implements CitationDate {
    final Date date;
    ACitationDate( Date date ){
    	this.date = date;
    }
	public Date getDate() {
		return date;
	}
	public Object accept(MetadataVisitor visitor, Object extraData) {
		return visitor.visit( this, extraData );
	}
}
