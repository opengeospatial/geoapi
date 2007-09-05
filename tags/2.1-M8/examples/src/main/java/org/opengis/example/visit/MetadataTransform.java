package org.opengis.example.visit;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class MetadataTransform implements MetadataVisitor {
	protected MetadataFactory factory;
	public MetadataTransform( MetadataFactory factory ){
		this.factory = factory;
	}
	public CitationDate visit(CitationDate date, Object extraData) {		
		return factory.createCitationDate( date.getDate() );
	}
	public Citation visit(Citation citation, Object extraData) {
		Set<CitationDate> copiedDates = new HashSet<CitationDate>();
		for( CitationDate date : citation.getDates() ){
			CitationDate newDate = (CitationDate) date.accept( this, extraData );
			copiedDates.add( newDate );
		}
		
		return factory.createCitation( citation.getISBN(), copiedDates );
	}	
}
