package org.opengis.filter.spatial;

import org.opengis.spatialschema.geometry.Geometry;

/**
 * Abstract superclass for filter operators that perform some sort of spatial
 * comparison on two geometric objects: one which is a property of a feature and
 * one which is a fixed geometry object.
 */
public interface BinarySpatialOperator extends SpatialOperator {
    public String getPropertyName();
    public void setPropertyName(String propertyName);

    public Geometry /* GM_Object */ getGeometry();
    public void setGeometry(Geometry /* GM_Object */ geometry);
}
