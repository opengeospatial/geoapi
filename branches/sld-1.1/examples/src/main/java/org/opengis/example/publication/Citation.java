package org.opengis.example.publication;

import java.util.Collection;

public interface Citation {
    String getISBN();

    /** @return an unmodifieable collection of CitationDate */
    Collection<CitationDate> getDates();
}