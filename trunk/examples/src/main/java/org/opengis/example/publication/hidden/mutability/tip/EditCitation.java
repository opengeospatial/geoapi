package org.opengis.example.publication.hidden.mutability.tip;

import java.util.Collection;
import java.util.HashSet;
import java.util.Set;

public class EditCitation implements Citation {
    private String isbn;

    private Set<EditCitationDate> dates;

    EditCitation() {
        isbn = null;
        dates = new HashSet<EditCitationDate>();
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
