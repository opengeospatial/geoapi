/*$************************************************************************************************
 **
 ** $Id$
 **
 ** $Source$
 **
 ** Copyright (C) 2003 Open GIS Consortium, Inc. All Rights Reserved. http://www.opengis.org/Legal/
 **
 *************************************************************************************************/
package org.opengis.spatialschema.geometry.geometry;

// J2SE direct dependencies
import java.util.List;

// OpenGIS direct dependencies
import org.opengis.spatialschema.geometry.primitive.CurveInterpolation;
import org.opengis.spatialschema.geometry.primitive.CurveSegment;

// Annotations
///import org.opengis.annotation.UML;
///import static org.opengis.annotation.Obligation.*;


/**
 * Sequence of geodesic segments. The interface essentially combines a
 * <code>Sequence&lt;{@link Geodesic}&gt;</code> into a single object,
 * with the obvious savings of storage space.
 *  
 * @author ISO/DIS 19107
 * @author <A HREF="http://www.opengis.org">OpenGIS&reg; consortium</A>
 * @version 2.0
 *
 * @see GeometryFactory#createGeodesicString
 */
///@UML (identifier="GM_GeodesicString")
public interface GeodesicString extends CurveSegment {
    /**
     * Returns a sequence of positions between which this <code>GeodesicString</code> is interpolated
     * using geodesics from the geoid or {@linkplain org.opengis.referencing.datum.Ellipsoid ellipsoid} of the
     * {@linkplain org.opengis.referencing.crs.CoordinateReferenceSystem coordinate reference system} being used.
     * The organization of these points is identical to that in {@link LineString}.
     *
     * @return The control points.
     */
/// @UML (identifier="controlPoint", obligation=MANDATORY)
    public PointArray getControlPoints();

    /**
     * The interpolation for a <code>GeodesicString</code> is
     * "{@linkplain CurveInterpolation#GEODESIC geodesic}".
     *
     * @return Always {@link CurveInterpolation#GEODESIC}.
     */
/// @UML (identifier="interpolation", obligation=MANDATORY)
    public CurveInterpolation getInterpolation();

    /**
     * Decomposes a geodesic string into an equivalent sequence of geodesic segments.
     *
     * @return The equivalent sequence of geodesic segments.
     */
/// @UML (identifier="asGM_Geodesic", obligation=MANDATORY)
    public List/*<Geodesic>*/ asGeodesics();
}
