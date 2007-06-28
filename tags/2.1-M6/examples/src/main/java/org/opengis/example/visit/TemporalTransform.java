package org.opengis.example.visit;

import java.util.Date;

public class TemporalTransform extends MetadataTransform {
	public TemporalTransform(MetadataFactory factory) {
		super(factory);
	}
    public CitationDate visit(CitationDate date, Object extraData) {
    	Integer adjust = (Integer) extraData;
    	
    	long time = date.getDate().getTime() + adjust;    	
    	Date adjusted = new Date( time );
    	
    	return factory.createCitationDate( adjusted );    	
    }
}
