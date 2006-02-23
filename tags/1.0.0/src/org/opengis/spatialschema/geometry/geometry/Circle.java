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


/**
 * Same as an {@linkplain Arc arc}, but closed to form a full circle.
 * The {@linkplain #getStartAngle start} and {@linkplain #getEndAngle end bearing}
 * are equal and shall be the bearing for the first {@linkplain #getControlPoints
 * control point} listed.
 *
 * This still requires at least 3 distinct non-co-linear points to be unambiguously
 * defined. The arc is simply extended until the first point is encountered.
 *  
 * @UML abstract GM_Circle
 * @author ISO/DIS 19107
 * @author <A HREF="http://www.opengis.org">OpenGIS&reg; consortium</A>
 * @version 2.0
 */
public interface Circle extends Arc {
}
