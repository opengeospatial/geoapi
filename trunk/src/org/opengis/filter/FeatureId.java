package org.opengis.filter;

/**
 * Instances of this interface represent a filter that passes only for features
 * that have the feature ID given to this object.  This class deviates from the
 * OGC Filter specification somewhat since we allow our Feature objects to have
 * composite primary keys.  So a feature ID must be an array of objects.
 */
public interface FeatureId extends Filter {
    /**
     * Returns the array of objects whose elements will be compared against the
     * (possibly composite) primary key of features.  If a given feature type
     * has only one attribute for its primary key, then the returned array may
     * be of length one.
     * <p>
     * It is an error to attempt to execute a Filter where the array given to
     * this method has a length that is different from the number of primary key
     * columns of the features being filtered.
     */
    public Object [] getFID();
}
