package org.opengis.filter;

import org.opengis.feature.Feature;

/**
 * This is the abstract base class for OGC-style filters.  Roughly speaking, a
 * filter encodes the information present in the WHERE clause of a SQL
 * statement.  There are various subclasses of this class that implement many
 * types of filters, such as simple property comparisons or spatial queries.
 */
public interface Filter {
    
    /**
     *
     */
    public boolean evaluate(Feature feature);
}
