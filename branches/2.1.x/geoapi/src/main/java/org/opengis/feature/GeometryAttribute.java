package org.opengis.feature;

import org.opengis.feature.type.GeometryType;
import org.opengis.referencing.crs.CoordinateReferenceSystem;
import org.opengis.geometry.BoundingBox;

//Java 1.4 imports
//import org.opengis.feature.type.AttributeType;
/**
 * Represent a Geometry as an attribute content.
 * <p>
 * This class is cotton candy and does not add any new modelling ability beyond
 * what could be accomplished by type narrowing an Attribute to return one of our
 * Geometry classes based on the ISO 19107 specification.
 * <p>
 * What this class does allows is a bridge to applications making use of a more simple
 * model of geometry. Two are in popular use:
 * <ul>
 * <li>Java 2D Shape: Simple topology representation used for Graphics2D, with no concept
 * of coordinate reference system.
 * <li>JTS Topology Suite Geometry: topology representation used by the SFSQL specification,
 * coordinate reference system is indicated by a getSRS() integer (the meaning of which is
 * application specific). In practice most applications use this value to store a EPSG code.
 * </ul>
 * This GeometryAttribute interface supplements these strict definitions of topology with the
 * information needed for wider use as a geometry.
 * <ul>
 * <li>getCRS() - the CoordinateReferenceSystem of the geometry value
 * <li>getBounds() - a BoundingBox for the geometry value
 * </ul>
 * We are not specifying the topology binding here, an application may safely implement this
 * interface using any of the above options (or make use of propiatary definitions such as
 * oracle SDO). We recommend the ISO 19107 Geometry definition supplied with GeoAPI simply
 * because we know it is complete.
 * </p>
 */
public interface GeometryAttribute extends Attribute {
    /**
     * The Coordinate Reference System of this geometry.
     * <p>
     * This may not be needed when using GeoAPI Geometry, it would
     * be a simple helper method for:
     * ((Geometry)getValue()).getCoordinateReferenceSystem().
     * </p>
     * <p>
     * As it stands this method will help transition code over from JTS as
     * GeoAPI Geometry implementations are made avaialble.
     * </p>
     */
    CoordinateReferenceSystem getCRS();

    /**
     * Sets the coordinate reference system for the attribute.
     */
    void setCRS(CoordinateReferenceSystem crs);

    /**
     * The bounds of this geometry.
     * <p>
     * This may not be needed when using GeoAPI Geometry, it would
     * be a simple helper method for:
     * ((Geometry)getValue()).getEnvelope().
     * </p>
     * <p>
     * As it stands this method will help transition code over from JTS as
     * GeoAPI Geometry implementations are made avaialble.
     * </p>
     */
    BoundingBox getBounds();

    /**
     * Although this is tipically a derrived quantity of the contents, this
     * value is often available in precomputed form from data providers.
     * <p>
     * This method allows a data provider to store the bounds information
     * associated with the contents of this geometry attribute.
     * @param bounds
     */
    void setBounds(BoundingBox bounds);

    /**
     * GeometryType should be configured with a Geometry for getJavaType.
     * <p>
     * Q: If needed a set of well-known GeometryType can be constructed, may be
     * needed to report CRS and Bounds constraints on data? A: It was needed
     * when we switched over to Attribute
     */
    GeometryType getType();

    /**
     * Retrieve Geometry.
     * <p>
     * We may want to relax this to Object to allow for JTS or GeoAPI based
     * objects for the first release.
     */
    Object getValue();

    /**
     * Set provided Geometry
     * <p>
     * We may want to relax this to Object to allow for JTS or GeoAPI based
     * objects for the first release.
     */
    void setValue(Object geom);
}
