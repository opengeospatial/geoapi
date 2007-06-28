package org.opengis.example.visit;

import java.util.Date;

public interface CitationDate {
    Date getDate();
    Object accept( MetadataVisitor visitor, Object extraData );
}
