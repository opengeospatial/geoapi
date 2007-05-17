package org.opengis.example.visit;

public interface MetadataVisitor {
     Object visit( CitationDate date, Object extraData );
     Object visit( Citation citation, Object extraData );
}
