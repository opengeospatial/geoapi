package org.opengis.feature;

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
     * itself a Feature) contains.
     */
    public int getSize();

    /**
     * Returns the child Feature object having the given index.  Indices range
     * from zero to <code>getSize()-1</code>.
     */
    public Feature getFeature(int index);

    /**
     * Returns an array containing all of the child Feature objects.
     */
    public Feature [] getAllFeatures();

    /**
     * Adds the given feature as a child feature of this one.
     *
     * @throws IllegalArgumentException If the given feature is not one of the
     *   child FeatureTypes of this Feature's type.
     */
    public void addFeature(Feature childFeature);
}
