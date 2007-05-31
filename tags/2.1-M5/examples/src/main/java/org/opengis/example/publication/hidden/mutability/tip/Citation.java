package org.opengis.example.publication.hidden.mutability.tip;

import java.util.Collection;

import org.opengis.example.publication.CitationDate;

public interface Citation {
    String getISBN();

    /** @return an unmodifieable collection of CitationDate */
    Collection<? extends CitationDate> getDates();
}