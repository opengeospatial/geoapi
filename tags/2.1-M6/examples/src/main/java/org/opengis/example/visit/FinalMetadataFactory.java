package org.opengis.example.visit;

import java.util.Collection;
import java.util.Collections;
import java.util.Date;
import java.util.Set;

public class FinalMetadataFactory implements MetadataFactory {

	public Citation createCitation(final String isbn, Set<CitationDate> dates) {
		final Set<CitationDate> lockedDates = Collections.unmodifiableSet( dates );
		return new Citation(){
			public Object accept(MetadataVisitor visitor, Object extraData) {
				return visitor.visit( this, extraData);
			}
			public Collection<? extends CitationDate> getDates() {
				return lockedDates;
			}
			public String getISBN() {
				return isbn;
			}			
		};
	}

	public CitationDate createCitationDate(final Date date) {
		return new CitationDate(){
			public Object accept(MetadataVisitor visitor, Object extraData) {
				return visitor.visit( this, extraData);
			}
			public Date getDate() {
				return date;
			}			
		};
	}
}
