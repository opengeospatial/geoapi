package org.opengis.example.visit;

import java.util.Collection;
import java.util.Collections;

class ACitation implements Citation {
    final Collection<? extends CitationDate> dates;
    final String isbn;
    
    ACitation( String code, Collection<? extends CitationDate> dates){
    	this.isbn = code;
    	this.dates = Collections.unmodifiableCollection( dates );
    }
	public Collection<? extends CitationDate> getDates() {
		return dates;
	}
	public String getISBN() {
		return isbn;
	}
	public Object accept(MetadataVisitor visitor, Object extraData) {
		return visitor.visit( this, extraData);
	}

}
