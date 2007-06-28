package org.opengis.example.factory;

import java.util.Set;

public interface MetadataFactory {
    Citation createCitation( String isbn, Set<? extends CitationDate> dates);
}