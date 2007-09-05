package org.opengis.example.visit;

import java.util.Date;
import java.util.Set;

/**
 * AbstractFactory for metadata constructs.
 * <p>
 * Please be kind and do not mix implementations from different factories.
 * 
 * @author Jody
 */
public interface MetadataFactory {
	CitationDate createCitationDate( Date date );
    Citation createCitation( String isbn, Set<CitationDate> dates);
}