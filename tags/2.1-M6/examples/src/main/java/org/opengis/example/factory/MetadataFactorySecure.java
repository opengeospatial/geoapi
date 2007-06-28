package org.opengis.example.factory;

import java.util.HashSet;
import java.util.Set;


class MetadataFactorySecure implements MetadataFactory {
    public Citation createCitation( String isbn, Set<? extends CitationDate> dates ){
        assert User.isAuthorized( "citation" );
        
        return new CitationSecure( isbn, copyDates(dates) );
    }

    private Set<SecureCitationDate> copyDates(Set<? extends CitationDate> dates) {
        Set<SecureCitationDate> copy = new HashSet<SecureCitationDate>();
        for (CitationDate date : dates) {
            copy.add(new SecureCitationDate(date));
        }
        return copy;
    }
}