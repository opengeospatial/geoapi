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
     * Given a feature, this method determines whether the feature passes the
     * test(s) represented by this filter object.
     */
    public boolean evaluate(Feature feature);

    /**
     * Accepts a visitor.  Implementations of all subinterfaces must have with a
     * method whose content is the following:
     * <pre>return visitor.visit(this, extraData);</pre>
     */
    public Object accept(FilterVisitor visitor, Object extraData);
}
