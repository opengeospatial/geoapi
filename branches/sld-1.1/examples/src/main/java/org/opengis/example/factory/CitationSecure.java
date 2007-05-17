package org.opengis.example.factory;

import java.util.Collection;
import java.util.Collections;
import java.util.Set;

class CitationSecure implements Citation {
    private final String isbn;
    private final Set<SecureCitationDate> dates;
    CitationSecure ( String isbn, Set<SecureCitationDate> dates ){
        this.isbn = isbn;
        this.dates = Collections.unmodifiableSet( dates );
    }
    public String getISBN() {
        assert User.isAuthorized( "citation.isbn" );
        return isbn;
    }
    public Collection<SecureCitationDate> getDates() {
        assert User.isAuthorized( "citation.date" );
        return dates;
    }    
}
