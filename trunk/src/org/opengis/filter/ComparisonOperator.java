package org.opengis.filter;

import org.opengis.filter.expression.Expression;

/**
 * Abstract base class for filters that compare a value against another value or
 * range of values.  The nature of the comparison is dependent on the subclass.
 * This interface has no methods, but is included to line up with the types in
 * the schemas for OGC Filter 1.0.0.
 */
public interface ComparisonOperator extends Filter {

}
