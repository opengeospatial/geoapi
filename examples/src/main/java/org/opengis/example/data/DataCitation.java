package org.opengis.example.data;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;
import java.util.List;

public class DataCitation implements Citation {

    private String isbn;

    private Collection<CitationDate> dates;

    public synchronized Collection<CitationDate> getDates() {
        if (dates == null)
            return null;
        List<CitationDate> copy = new ArrayList<CitationDate>(dates.size());
        for (CitationDate date : dates) {
            copy.add(new DataCitationDate(date));
        }
        return copy;
    }

    public synchronized String getISBN() {
        return isbn;
    }

    public synchronized void setDates(Collection<CitationDate> dates) {
        this.dates = dates;
    }

    public synchronized void setISBN(String isbn) {
        this.isbn = isbn;
    }

    @Override
    public synchronized int hashCode() {
        return (isbn == null ? 0 : isbn.hashCode())
                | (dates == null ? 0 : dates.hashCode());
    }

    @Override
    public synchronized boolean equals(Object obj) {
        if (obj == null || !(obj instanceof Citation)) {
            return false;
        }
        Citation other = (Citation) obj;
        if (isbn == null && other.getISBN() != null)
            return false;
        if (!isbn.equals(other.getISBN()))
            return false;

        if (other instanceof DataCitation) {
            DataCitation data = (DataCitation) other;

            return (dates == null && data.dates == null)
                    || (dates != null && dates.equals(data.dates));
        }
        Collection<CitationDate> otherDates = other.getDates();
        return (dates == null && otherDates == null)
                || (dates != null && dates.equals(otherDates));
    }
}
