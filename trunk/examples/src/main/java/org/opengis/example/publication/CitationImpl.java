package org.opengis.example.publication;

import java.util.Collection;
import java.util.Collections;
import java.util.Set;

public class CitationImpl implements Citation {
    private final String isbn;

    private final Set<CitationDate> dates;

    public CitationImpl(String isbn, Set<CitationDate> dates) {
        this.isbn = isbn;
        this.dates = Collections.unmodifiableSet(dates);
    }

    public String getISBN() {
        return isbn;
    }

    public Collection<CitationDate> getDates() {
        return dates;
    }
}
