package org.opengis.example.visit;

import java.util.Date;
import java.util.Set;

public class AMetadataFactory implements MetadataFactory {
	public ACitation createCitation(String isbn, Set<CitationDate> dates) {
		return new ACitation( isbn, dates );
	}
	public ACitationDate createCitationDate(Date date) {
		return new ACitationDate( date );
	}	
}
