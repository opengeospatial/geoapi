package org.opengis.filter;

/**
 * Instances of this interface represent a filter that passes only for features
 * that have one of the IDs given to this object.
 */
public interface FeatureId extends Filter {
    /**
     * Returns an array containing the IDs of Features that will pass this
     * filter.
     */
    public String [] getIDs();
}
