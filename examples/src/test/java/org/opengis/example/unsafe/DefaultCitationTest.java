package org.opengis.example.unsafe;

import java.util.HashSet;
import java.util.Set;

import junit.framework.TestCase;

public class DefaultCitationTest extends TestCase {
    public void testDefault(){
        Citation citation = new DefaultCitation();

        citation.setISBN( "1234" );
        citation.getDates().add( new DefaultCitationDate() );
    }
}
