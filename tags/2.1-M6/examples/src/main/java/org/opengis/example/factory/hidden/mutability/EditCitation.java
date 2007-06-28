package org.opengis.example.factory.hidden.mutability;

import java.util.Collection;
import java.util.Set;

import org.opengis.example.factory.Citation;

public class EditCitation implements Citation{
    private String isbn;

    private Set<EditCitationDate> dates;

    EditCitation(String isbn2, Set<EditCitationDate> dates) {
        this.isbn=isbn2;
        this.dates=dates;
    }

    public synchronized String getISBN() {
        return isbn;
    }

    public synchronized void setISBN(String isbn) {
        this.isbn = isbn;
    }

    public synchronized Collection<EditCitationDate> getDates() {
        return dates;
    }

}
