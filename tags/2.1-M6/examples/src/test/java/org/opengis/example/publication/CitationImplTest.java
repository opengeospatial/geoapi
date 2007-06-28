package org.opengis.example.publication;

import java.util.Collections;
import java.util.HashSet;
import java.util.Set;

import junit.framework.TestCase;

public class CitationImplTest extends TestCase {
     public void testImplementation(){
    	 Set<CitationDate> dates = new HashSet<CitationDate>();
    	 dates.add( new CitationDateImpl() );
    	 
    	 Citation citation = new CitationImpl( "1234", dates );
     }
}
