/*
 * Copyright (C) 2003 Open GIS Consortium, Inc. All Rights Reserved. http://www.opengis.org/Legal/
 */
package org.opengis.gm.geometry;

// J2SE direct dependencies
import java.util.List;

// OpenGIS direct dependencies
import org.opengis.gm.primitive.CurveSegment;


/**
 * A sequence of line segments, each having a parameterization like the one
 * {@link LineSegment}. The class essentially combines a
 * {@link List List&lt;LineSegment&gt;} into a single object,
 * with the obvious savings of storage space.
 *  
 * @UML datatype GM_LineString
 * @author ISO/DIS 19107
 * @author <A HREF="http://www.opengis.org">OpenGIS&reg; consortium</A>
 * @version 2.0
 *
 * @revisit The UML diagram defines the following constructor, which can't be
 *          express with the "toFoo" idiom:
 *
 *          "Takes two or more positions and creates the appropriate line string
 *           joining them."
 */
public interface LineString extends CurveSegment {
    /**
     * Returns a sequence of positions between which the curve is linearly interpolated.
     * The first position in the sequence is the {@linkplain #getStartPoint start Point}
     * of the <code>LineString</code>, and the last point in the sequence is the
     * {@linkplain #getEndPoint end point} of the <code>LineString</code>.
     *
     * @return The control points between which the curve is linearly interpolated.
     * @UML operation controlPoint
     */
    public PointArray getControlPoints();

    /**
     * Decomposes a line string into an equivalent sequence of line segments.
     *
     * @return The sequence of line segments.
     * @UML operation asGM_LineSegment
     */
    public List/*<LineSegment>*/ asLineSegment();
}
