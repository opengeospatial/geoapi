package org.opengis.example.visit;

import java.util.Calendar;
import java.util.Collections;
import java.util.Date;

import junit.framework.TestCase;

public class VisitTest extends TestCase {
	public void testCopy(){
        MetadataFactory factory = new AMetadataFactory();
        CitationDate date = factory.createCitationDate( new Date() );
        Citation citation = factory.createCitation( "1", Collections.singleton( date ) );
     
        assertTrue( citation instanceof ACitation );
        
        MetadataTransform transform = new MetadataTransform( new FinalMetadataFactory() );        
        Citation copy = transform.visit( citation, null );
        
        assertFalse( "Different implementation", copy instanceof ACitation );
        assertEquals( "Same content", citation.getISBN(), copy.getISBN() );
        assertEquals( "Same content", citation.getDates().iterator().next().getDate(),
        		copy.getDates().iterator().next().getDate() );
	}
	
	public void testTime(){
		Calendar time = Calendar.getInstance();		
		time.set( 1991, 1, 3 );
		Date then = time.getTime();
		
        MetadataFactory factory = new FinalMetadataFactory();
        CitationDate date = factory.createCitationDate( then );
        Citation citation = factory.createCitation( "1", Collections.singleton( date ) );
             
        TemporalTransform transform = new TemporalTransform( factory );        
        Citation transformed = transform.visit( citation, 12 );
        
        Date after = transformed.getDates().iterator().next().getDate();
        assertTrue( "Later", then.before( after ) ); 
	}
	
}
