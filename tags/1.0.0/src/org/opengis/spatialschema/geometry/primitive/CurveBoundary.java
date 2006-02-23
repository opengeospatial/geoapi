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


/**
 * The boundary of {@linkplain Curve curves}.
 * A <code>CurveBoundary</code> contains two {@linkplain Point point} references
 * ({@linkplain #getStartPoint start point} and {@linkplain #getEndPoint end point}).
 *
 * @UML type GM_CurveBoundary
 * @author ISO/DIS 19107
 * @author <A HREF="http://www.opengis.org">OpenGIS&reg; consortium</A>
 * @version 2.0
 */
public interface CurveBoundary extends PrimitiveBoundary {
    /**
     * Returns the start point.
     *
     * @return The start point.
     *
     * @see #getEndPoint
     */
    public Point getStartPoint();

    /**
     * Returns the end point.
     *
     * @return The end point.
     *
     * @see #getStartPoint
     */
    public Point getEndPoint();
}
