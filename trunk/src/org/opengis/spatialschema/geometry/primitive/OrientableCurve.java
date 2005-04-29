/*$************************************************************************************************
 **
 ** $Id$
 **
 ** $Source$
 **
 ** Copyright (C) 2003 Open GIS Consortium, Inc. All Rights Reserved. http://www.opengis.org/Legal/
 **
 *************************************************************************************************/
package org.opengis.spatialschema.geometry.primitive;

// OpenGIS direct dependencies
import org.opengis.spatialschema.geometry.complex.CompositeCurve;

// Annotations
import org.opengis.annotation.UML;
import static org.opengis.annotation.Obligation.*;
import static org.opengis.annotation.Specification.*;


/**
 * A curve and an orientation inherited from {@link OrientablePrimitive}. If the orientation is
 * positive, then the <code>OrientableCurve</code> is a {@linkplain Curve curve}. If the orientation
 * is negative, then the <code>OrientableCurve</code> is related to another {@linkplain Curve curve}
 * with a parameterization that reverses the sense of the curve traversal.
 *
 * @author ISO/DIS 19107
 * @author <A HREF="http://www.opengis.org">OpenGIS&reg; consortium</A>
 * @version <A HREF="http://www.opengis.org/docs/01-101.pdf">Abstract specification 5</A>
 */
@UML (identifier="GM_OrientableCurve", specification=ISO_19107)
public interface OrientableCurve extends OrientablePrimitive {
    /**
     * Returns an ordered pair of points, which are the start point and end point of the curve.
     * If the curve is closed, then the boundary shall be empty.
     *
     * @return The sets of positions on the boundary.
     */
/// @UML (identifier="boundary", obligation=MANDATORY, specification=ISO_19107)
/// public CurveBoundary getBoundary();

    /**
     * Returns the owner of this orientable curve, or {@code null} if none.
     *
     * @return The owner of this orientable curve, or {@code null} if none.
     *
     * @revisit I'm not sure to interpret correctly the ISO specification.
     *          Sound like ISO returns an array (or a sequence) here.
     */
    @UML (identifier="composite", obligation=MANDATORY, specification=ISO_19107)
    public CompositeCurve getComposite();
}
