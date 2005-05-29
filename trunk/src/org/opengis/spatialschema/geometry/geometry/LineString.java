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
import org.opengis.spatialschema.geometry.primitive.CurveSegment;

// Annotations
import org.opengis.annotation.UML;
import static org.opengis.annotation.Obligation.*;
import static org.opengis.annotation.Specification.*;


/**
 * A sequence of line segments, each having a parameterization like the one
 * {@link LineSegment}. The class essentially combines a
 * {@link List List&lt;LineSegment&gt;} into a single object,
 * with the obvious savings of storage space.
 *  
 * @version <A HREF="http://www.opengis.org/docs/01-101.pdf">Abstract specification 5</A>
 * @author Martin Desruisseaux (IRD)
 * @since GeoAPI 1.0
 *
 * @see GeometryFactory#createLineString
 */
@UML(identifier="GM_LineString", specification=ISO_19107)
public interface LineString extends CurveSegment {
    /**
     * Returns a sequence of positions between which the curve is linearly interpolated.
     * The first position in the sequence is the {@linkplain #getStartPoint start Point}
     * of this {@code LineString}, and the last point in the sequence is the
     * {@linkplain #getEndPoint end point} of this {@code LineString}.
     *
     * @return The control points between which the curve is linearly interpolated.
     */
    @UML(identifier="controlPoint", obligation=MANDATORY, specification=ISO_19107)
    public PointArray getControlPoints();

    /**
     * Decomposes a line string into an equivalent sequence of line segments.
     *
     * @return The sequence of line segments.
     */
    @UML(identifier="asGM_LineSegment", obligation=MANDATORY, specification=ISO_19107)
    public List<LineSegment> asLineSegments();
}
