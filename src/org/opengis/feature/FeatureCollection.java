package org.opengis.feature;

import java.util.Iterator;

/**
 * This interface is implemented by Features if they have child Feature
 * objects.  A client can tell at runtime whether a Feature implements this
 * interface by calling getChildren() on the FeatureType.  If the returned array
 * is non-null and has at least one element, then the Features of that type
 * implement this interface and may optionally have child features.
 */
public interface FeatureCollection extends Feature {
    
    /**
     * Return the number of child Feature objects that this collection (which is
     * itself a Feature) contains.  If this is unknown, for example, if the
     * child features have not been loaded, then this method may return -1.
     */
    public int getSize();

    /**
     * Returns an iterator over all of the child Feature objects.
     */
    public Iterator getAllFeatures();

    /**
     * Adds the given feature as a child feature of this one.
     *
     * @throws IllegalArgumentException If the given feature is not one of the
     *   child FeatureTypes of this Feature's type.
     */
    public void addFeature(Feature childFeature);
}
