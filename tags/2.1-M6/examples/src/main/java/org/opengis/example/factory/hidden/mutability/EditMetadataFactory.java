package org.opengis.example.factory.hidden.mutability;

import java.util.HashSet;
import java.util.Set;

import org.opengis.example.factory.CitationDate;
import org.opengis.example.factory.MetadataFactory;

public class EditMetadataFactory implements MetadataFactory {

    public EditCitation createCitation(String isbn,
            Set<? extends CitationDate> dates) {
        return new EditCitation( isbn, copyDates(dates) );
    }

    private Set<EditCitationDate> copyDates(Set<? extends CitationDate> dates) {
        Set<EditCitationDate> copy = new HashSet<EditCitationDate>();
        for (CitationDate date : dates) {
            copy.add(new EditCitationDate(date));
        }
        return copy;
    }
}
