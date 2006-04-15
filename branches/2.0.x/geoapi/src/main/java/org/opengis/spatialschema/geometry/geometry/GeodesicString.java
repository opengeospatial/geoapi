/*$************************************************************************************************
 **
 ** $Id$
 **
 ** $Source$
 **
 ** Copyright (C) 2003-2005 Open GIS Consortium, Inc.
 ** All Rights Reserved. http://www.opengis.org/legal/
 **
 *************************************************************************************************/
package org.opengis.spatialschema.geometry.geometry;

// J2SE direct dependencies
import java.util.List;

// OpenGIS direct dependencies
import org.opengis.spatialschema.geometry.primitive.CurveInterpolation;
import org.opengis.spatialschema.geometry.primitive.CurveSegment;

// Annotations
import org.opengis.annotation.UML;
import static org.opengis.annotation.Obligation.*;
import static org.opengis.annotation.Specification.*;


/**
 * Sequence of geodesic segments. The interface essentially combines a
 * <code>Sequence&lt;{@link Geodesic}&gt;</code> into a single object,
 * with the obvious savings of storage space.
 *  
 * @version <A HREF="http://www.opengis.org/docs/01-101.pdf">Abstract specification 5</A>
 * @author Martin Desruisseaux (IRD)
 * @since GeoAPI 1.0
 *
 * @see GeometryFactory#createGeodesicString
 */
@UML(identifier="GM_GeodesicString", specification=ISO_19107)
public interface GeodesicString extends CurveSegment {
    /**
     * Returns a sequence of positions between which this {@code GeodesicString} is interpolated
     * using geodesics from the geoid or {@linkplain org.opengis.referencing.datum.Ellipsoid ellipsoid} of the
     * {@linkplain org.opengis.referencing.crs.CoordinateReferenceSystem coordinate reference system} being used.
     * The organization of these points is identical to that in {@link LineString}.
     *
     * @return The control points.
     */
    @UML(identifier="controlPoint", obligation=MANDATORY, specification=ISO_19107)
    public PointArray getControlPoints();

    /**
     * The interpolation for a {@code GeodesicString} is
     * "{@linkplain CurveInterpolation#GEODESIC geodesic}".
     *
     * @return Always {@link CurveInterpolation#GEODESIC}.
     */
    @UML(identifier="interpolation", obligation=MANDATORY, specification=ISO_19107)
    public CurveInterpolation getInterpolation();

    /**
     * Decomposes a geodesic string into an equivalent sequence of geodesic segments.
     *
     * @return The equivalent sequence of geodesic segments.
     */
    @UML(identifier="asGM_Geodesic", obligation=MANDATORY, specification=ISO_19107)
    public List<Geodesic> asGeodesics();
}
