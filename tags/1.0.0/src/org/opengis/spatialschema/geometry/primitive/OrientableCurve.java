/*$************************************************************************************************
 **
 ** $Id$
 **
 ** $URL$
 **
 ** Copyright (C) 2003 Open GIS Consortium, Inc. All Rights Reserved. http://www.opengis.org/Legal/
 **
 *************************************************************************************************/
package org.opengis.spatialschema.geometry.primitive;

// OpenGIS direct dependencies
import org.opengis.spatialschema.geometry.complex.CompositeCurve;


/**
 * A curve and an orientation inherited from {@link OrientablePrimitive}. If the orientation is
 * positive, then the <code>OrientableCurve</code> is a {@linkplain Curve curve}. If the orientation
 * is negative, then the <code>OrientableCurve</code> is related to another {@linkplain Curve curve}
 * with a parameterization that reverses the sense of the curve traversal.
 *
 * @UML type GM_OrientableCurve
 * @author ISO/DIS 19107
 * @author <A HREF="http://www.opengis.org">OpenGIS&reg; consortium</A>
 * @version 2.0
 */
public interface OrientableCurve extends OrientablePrimitive {
    /**
     * Returns an ordered pair of points, which are the start point and end point of the curve.
     * If the curve is closed, then the boundary shall be empty.
     *
     * @return The sets of positions on the boundary.
     * @UML operation boundary
     */
/// public CurveBoundary getBoundary();

    /**
     * Returns the owner of this orientable curve, or <code>null</code> if none.
     *
     * @return The owner of this orientable curve, or <code>null</code> if none.
     * @UML association composite
     *
     * @revisit I'm not sure to interpret correctly the ISO specification.
     *          Sound like ISO returns an array (or a sequence) here.
     */
    public CompositeCurve getComposite();
}
