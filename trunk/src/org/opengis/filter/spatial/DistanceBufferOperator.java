package org.opengis.filter.spatial;

import org.opengis.spatialschema.geometry.Geometry;

/**
 * Mapping of <code>&ltogc:DistanceBufferType&gt;</code> from the Filter spec.
 */
public interface DistanceBufferOperator extends SpatialOperator {
    public String getPropertyName();
    public void setPropertyName(String propertyName);

    public Geometry /* GM_Object */ getGeometry();
    public void setGeometry(Geometry /* GM_Object */ geometry);

    public double getDistance();
    public void setDistance(double d);

    public String getDistanceUnits();
    public void setDistanceUnits(String units);
}
