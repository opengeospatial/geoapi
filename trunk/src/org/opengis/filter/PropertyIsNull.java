package org.opengis.filter;

/**
 * Filter operator that checks if the named property of a feature is null.
 */
public interface PropertyIsNull extends Filter {
    /**
     * Returns the name of the property whose value will be checked for null.
     */
    public String getPropertyName();
}
