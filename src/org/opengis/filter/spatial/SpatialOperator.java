package org.opengis.filter.spatial;

import org.opengis.filter.Filter;

/**
 * Abstract base class for operators that perform a spatial comparison on
 * geometric attributes of a feature.
 */
public interface SpatialOperator extends Filter {
    /**
     * Returns the name of the geometric property that will be used in this
     * spatial operator.  This may be null if the default spatial property is
     * to be used.
     */
    public String getPropertyName();
}
