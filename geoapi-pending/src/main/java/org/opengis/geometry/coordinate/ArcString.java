/*
 *    GeoAPI - Java interfaces for OGC/ISO standards
 *    http://www.geoapi.org
 *
 *    Copyright (C) 2004-2021 Open Geospatial Consortium, Inc.
 *    All Rights Reserved. http://www.opengeospatial.org/ogc/legal
 *
 *    Permission to use, copy, and modify this software and its documentation, with
 *    or without modification, for any purpose and without fee or royalty is hereby
 *    granted, provided that you include the following on ALL copies of the software
 *    and documentation or portions thereof, including modifications, that you make:
 *
 *    1. The full text of this NOTICE in a location viewable to users of the
 *       redistributed or derivative work.
 *    2. Notice of any changes or modifications to the OGC files, including the
 *       date changes were made.
 *
 *    THIS SOFTWARE AND DOCUMENTATION IS PROVIDED "AS IS," AND COPYRIGHT HOLDERS MAKE
 *    NO REPRESENTATIONS OR WARRANTIES, EXPRESS OR IMPLIED, INCLUDING BUT NOT LIMITED
 *    TO, WARRANTIES OF MERCHANTABILITY OR FITNESS FOR ANY PARTICULAR PURPOSE OR THAT
 *    THE USE OF THE SOFTWARE OR DOCUMENTATION WILL NOT INFRINGE ANY THIRD PARTY
 *    PATENTS, COPYRIGHTS, TRADEMARKS OR OTHER RIGHTS.
 *
 *    COPYRIGHT HOLDERS WILL NOT BE LIABLE FOR ANY DIRECT, INDIRECT, SPECIAL OR
 *    CONSEQUENTIAL DAMAGES ARISING OUT OF ANY USE OF THE SOFTWARE OR DOCUMENTATION.
 *
 *    The name and trademarks of copyright holders may NOT be used in advertising or
 *    publicity pertaining to the software without specific, written prior permission.
 *    Title to copyright in this software and any associated documentation will at all
 *    times remain with copyright holders.
 */
package org.opengis.geometry.coordinate;

import java.util.List;
import org.opengis.geometry.primitive.CurveInterpolation;
import org.opengis.geometry.primitive.CurveSegment;
import org.opengis.annotation.UML;

import static org.opengis.annotation.Obligation.*;
import static org.opengis.annotation.Specification.*;


/**
 * Similar to a {@linkplain LineString line string} except that the interpolation is
 * by circular arcs. Since it requires 3 points to determine a circular arc, the
 * {@linkplain #getControlPoints control points} are treated as a sequence of overlapping
 * sets of 3 {@linkplain Position positions}, the start of each arc, some point between the
 * start and end, and the end of each arc. Since the end of each arc is the start of the next,
 * this {@linkplain Position position} is not repeated in the {@linkplain #getControlPoints
 * control points} sequence.
 *
 * @version <A HREF="http://www.opengeospatial.org/standards/as">ISO 19107</A>
 * @author Martin Desruisseaux (IRD)
 * @since GeoAPI 1.0
 *
 * @see GeometryFactory#createArcString
 * @see ArcStringByBulge#asArcString
 */
@UML(identifier="GM_ArcString", specification=ISO_19107)
public interface ArcString extends CurveSegment {
    /**
     * Returns the number of circular arcs in the string. Since the interpolation method
     * requires overlapping sets of 3 positions, the number of arcs determines the number
     * of {@linkplain #getControlPoints control points}:
     *
     * <blockquote>
     * <pre>numArc = ({@link #getControlPoints controlPoints}.length - 1)/2</pre>
     * </blockquote>
     *
     * @return the number of circular arcs.
     */
    @UML(identifier="numArc", obligation=MANDATORY, specification=ISO_19107)
    int getNumArc();

    /**
     * Returns the sequence of points used to control the arcs in this string. The first three
     * {@linkplain Position positions} in the sequence determines the first arc. Any three
     * consecutive {@linkplain Position positions} beginning with an odd offset, determine
     * another arc in the string.
     *
     * @return the control points. The array size is <code>2*{@link #getNumArc numArc}&nbsp;+1</code>.
     */
    @UML(identifier="controlPoints", obligation=MANDATORY, specification=ISO_19107)
    PointArray getControlPoints();

    /**
     * The interpolation for a {@code ArcString} is
     * "{@linkplain CurveInterpolation#CIRCULAR_ARC_3_POINTS circular arc by 3 points}".
     *
     * @return always {@link CurveInterpolation#CIRCULAR_ARC_3_POINTS}.
     */
    @UML(identifier="interpolation", obligation=MANDATORY, specification=ISO_19107)
    CurveInterpolation getInterpolation();

    /**
     * Constructs a sequence of arcs that is the geometric equivalent of this arc string.
     *
     * @return the sequence of arcs.
     */
    @UML(identifier="asGM_Arc", obligation=MANDATORY, specification=ISO_19107)
    List<Arc> asArcs();
}
