/*
 * Copyright (C) 2003 Open GIS Consortium, Inc. All Rights Reserved. http://www.opengis.org/Legal/
 */
package org.opengis.gm.primitive;


/**
 * A curve and an orientation inherited from {@link OrientablePrimitive}. If the orientation is
 * positive, then the <code>OrientableCurve</code> is a {@link Curve}. If the orientation is
 * negative, then the <code>OrientableCurve</code> is related to another {@link Curve} with a
 * parameterization that reverses the sense of the curve traversal.
 *
 * @UML type GM_OrientableCurve
 * @author ISO/DIS 19107
 * @author <A HREF="http://www.opengis.org">OpenGIS&reg; consortium</A>
 * @version 2.0
 *
 * @revisit Some associations are commented out for now.
 */
public interface OrientableCurve extends OrientablePrimitive {
    /**
     * Returns an ordered pair of points, which are the start point and end point of the curve.
     * If the curve is closed, then the boundary shall be empty.
     *
     * @return The sets of positions on the boundary.
     * @UML operation boundary
     */
    public CurveBoundary getBoundary();

//    public org.opengis.spatialschema.geometry.complex.GM_CompositeCurve composite[];
}
