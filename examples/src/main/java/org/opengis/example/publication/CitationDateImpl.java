package org.opengis.example.publication;

import java.util.Date;

public class CitationDateImpl implements CitationDate {
    final Date date;

    public CitationDateImpl() {
        this(new Date());
    }

    public CitationDateImpl(Date date) {
        this.date = date;
    }

    public Date toDate() {
        return date;
    }
}
