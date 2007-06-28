package org.opengis.example.visit;

import java.util.Collection;

public interface Citation {
    String getISBN();
    Collection<? extends CitationDate> getDates();
    Object accept( MetadataVisitor visitor, Object extraData );
}
