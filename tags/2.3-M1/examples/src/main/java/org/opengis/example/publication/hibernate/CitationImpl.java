package org.opengis.example.publication.hibernate;

import java.util.Collection;
import java.util.Collections;
import java.util.HashSet;
import java.util.Set;

import org.opengis.example.publication.Citation;
import org.opengis.example.publication.CitationDate;
import org.opengis.example.publication.CitationDateImpl;

class CitationImpl implements Citation {
    private final String isbn;

    // These would be uncommented if we were using hibernate
    //
    // @OneToMany(mappedBy = "citation", cascade = CascadeType.ALL)
    // @OnDelete(action = OnDeleteAction.CASCADE)
    // @Relationship
    final Set<CitationDateImpl> dates = new HashSet<CitationDateImpl>();

    public CitationImpl(final String isbn) {
        this.isbn = isbn;
    }

    public String getISBN() {
        return isbn;
    }

    public Collection<CitationDate> getDates() {
        return Collections.unmodifiableSet((Set<? extends CitationDate>)dates);
    }
}