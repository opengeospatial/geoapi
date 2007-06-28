package org.opengis.example.publication.hidden.mutability;

import java.util.Collection;
import java.util.Collections;
import java.util.HashSet;
import java.util.Set;

import org.opengis.example.publication.Citation;
import org.opengis.example.publication.CitationDate;

public class EditCitation implements Citation {
    private String isbn;

    Collection<EditCitationDate> dates;

    EditCitation() {
        isbn = null;
        dates=new HashSet<EditCitationDate>();
    }

    public synchronized String getISBN() {
        return isbn;
    }

    public synchronized void setISBN(String isbn) {
        this.isbn = isbn;
    }

    public Collection<CitationDate> getDates() {
        return Collections.unmodifiableSet((Set<? extends CitationDate>)dates);
    }

    public Collection<EditCitationDate> getEditDates() {
        return Collections.synchronizedCollection(dates);
    }
}
