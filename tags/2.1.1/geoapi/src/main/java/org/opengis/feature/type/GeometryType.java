package org.opengis.feature.type;

import org.opengis.referencing.crs.CoordinateReferenceSystem;


/**
 * Represents (explicitly) the binding of an AttributeType to Geometry information.
 *
 * @author Jody Garnett
 */
public interface GeometryType extends AttributeType {
    /**
     * The Geometry type represented.
     * <p>
     * The class indicated here may be specified using generics, while the geoapi
     * Geometry interface is common, JTS Geometry and plain old Java2D Shape
     * may also be pressed into service.
     *
     * @return Geometry binding
     */
    Class<?> getBinding();

    /**
     * If there is a superclass it better also be a Geometry
     */
    AttributeType getSuper();

    /**
     * The coordinate reference system of the Geometries
     * contained by attributes of this type.
     * <p>
     * Note: when using GeoAPI Geometry (where CRS is XPathable) we can define
     * this restriction as a Facet (indeed we can allow for a discrete set of allowable
     * CRS).
     * </p>
     */
    CoordinateReferenceSystem getCRS();
}
