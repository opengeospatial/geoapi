package org.opengis.filter.spatial;

/**
 * Spatial filter that evaluates to true when the bounding box of the feature's
 * geometry overlaps the bounding box provided in this object's properties.  An
 * implementation may choose to throw an exception if one attempts to test
 * features that are in a different SRS than the SRS contained here.
 */
public interface BBOX extends SpatialOperator {
    /**
     * Returns the spatial reference system in which the bounding box
     * coordinates contained by this object should be interpreted.  This string
     * must take one of two forms: either "EPSG:xxxxx" where "xxxxx" is a valid
     * EPSG coordinate system code, or an OGC well-known-text representation of
     * a coordinate system as defined in the OGC Simple Features for SQL
     * specification.
     */
    public String getSRS();

    /**
     * Returns the minimum value for the first coordinate.
     */
    public double getMinX();

    /**
     * Returns the minimum value for the second coordinate.
     */
    public double getMinY();

    /**
     * Returns the maximum value for the first coordinate.
     */
    public double getMaxX();

    /**
     * Returns the maximum value for the second coordinate.
     */
    public double getMaxY();
}
